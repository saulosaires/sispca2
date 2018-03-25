package administrativo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import administrativo.dao.UsuarioDAO;
import administrativo.model.Usuario;
import arquitetura.utils.Cryptography;
import arquitetura.utils.EmailUtil;
import arquitetura.utils.SispcaLogger;
import arquitetura.utils.Utils;
 

public class LoginController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5934068244899456741L;


	private UsuarioDAO usuarioDao;

	@Inject
	public LoginController(UsuarioDAO usuarioDao) {
		this.usuarioDao=usuarioDao;
	}

	public Usuario loginByUserNameAndPassword(String username, String password) {

		Optional<Usuario> user = usuarioDao.queryByUserNameAndPassword(username, password);

		if (!user.isPresent())
			return new Usuario(-1L);

		Usuario u = user.get();

		u.setLastLogin(new Date());
		u.setHash(null);
		u.setValidadeHash(null);
		
		usuarioDao.update(u);

		return u;
	}
	
	public Usuario atualizarUsuarioPrimeiroAcesso(Usuario usuario,String password) {
		
		usuario.setPassword(password);
		usuario.setHash(null);
		usuario.setValidadeHash(null);
		usuario.setPrimeiroAcesso(false);
 
		return usuarioDao.update(usuario);
		
	}
	
	public Optional<Usuario> buscarUsuarioByEmail(String email) {
		
		return usuarioDao.queryByEmail(email);
	}

    public boolean recuperarSenha(Usuario usuario, String scheme,String serveName,int port,String path) {
    	
    	usuario= criarNovaSenha( usuario);
    	usuario=definiValidadeDoHash(usuario);
    	
    	usuarioDao.update(usuario);
    	
    	return enviarEmailRecuperacaoSenha(usuario, scheme,serveName,port,path);
    	
    }
    
    public Usuario criarNovaSenha(Usuario usuario) {
    	
    	String hash = Cryptography.md5("" + Utils.randomNumber());
    	
    	usuario.setHash(hash);
    	
    	return usuario;
    }

    public Usuario definiValidadeDoHash(Usuario usuario) {
    	
    	GregorianCalendar cal = new GregorianCalendar();
		cal.add(Calendar.MINUTE, 30);
		Date validade = cal.getTime();
    	
    	usuario.setValidadeHash(validade);
    	
    	return usuario;
    	
    }

    public boolean enviarEmailRecuperacaoSenha(Usuario usuario,String scheme,String serveName,int port,String path) {
    	
    	try {
	    	StringBuilder builder = new StringBuilder();
	    	
	    	String assunto = "SISPCA - Recuperação de Senha";
	    	
	    	String varUrlBaseSistema = String.format("%s://%s:%d%s",scheme,serveName,port,path);
			String varResetPasswordUrl = String.format("<a href=\"%s/views/public/recuperasenha.jsf?hash=%s\">Redefinar Senha</a>", varUrlBaseSistema, usuario.getHash());
	
			builder.append("<h1>SISPCA - Recupera&ccedil;&atilde;o de Senha</h1>");
			builder.append("<p>Para redefinir sua senha, acesse o link abaixo. </p>" );
	
	
			builder.append(varResetPasswordUrl);
			
			String mensagem = builder.toString(); 
			
			List<String> destinatarios = new ArrayList<>();
			
			destinatarios.add(usuario.getEmail());
	
			
			return EmailUtil.enviaEmail(assunto, mensagem, destinatarios, null);
		
    	}catch(Exception e) {
			SispcaLogger.logError(e.getMessage());
		
       }
    	
    	return false;
    }
    
}
