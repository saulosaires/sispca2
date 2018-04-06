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
import arquitetura.controller.AbstractController;
import arquitetura.exception.JpaException;
import arquitetura.utils.Cryptography;
import arquitetura.utils.EmailUtil;
import arquitetura.utils.SispcaLogger;
import arquitetura.utils.Utils;
 

public class UserController extends AbstractController<Usuario> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1370755486293182418L;
  
	@Inject
	public UserController(UsuarioDAO usuarioDAO){
		super(usuarioDAO);
	}
	
	 
	public List<Usuario> queryUser(String name, String email, String perfil, String unidadeOrcamentaria){

		return ((UsuarioDAO) getDao()).queryUser(name, email, perfil,unidadeOrcamentaria);
	}

	 
	public Optional<Usuario> queryByUserName(String login) {
		return ((UsuarioDAO) getDao()).queryByUserName(login);
		
	}
	
	public boolean enviarEmailUsuarioCriado(Usuario usuario, String scheme,String serveName,int port,String path) {
		
		try {
			
			String assunto = "SISPCA - Geração de Usuário";
			
			StringBuilder builder = new StringBuilder();
 
			String urlBaseSistema = String.format("%s://%s:%d%s",scheme,serveName, port,path);
			String paginaInicial = String.format("<a href=\"%s/views/public/login.jsf\">Acesso ao sistema</a>", urlBaseSistema);
	
			builder.append("<h1>SISPCA - Geração de Usuário</h1>");
			builder.append("<p><b>Login: </b>" + usuario.getLogin() + "</p>");
			builder.append("<p><b>Senha: </b>" + usuario.getSenhaDescriptografada() + "</p>");
			
			builder.append(paginaInicial);
			
			String mensagem = builder.toString(); 
			
			List<String> destinatarios = new ArrayList<>();
			
			destinatarios.add(usuario.getEmail());
	
			return EmailUtil.enviaEmail(assunto, mensagem, destinatarios, null);
		
		}catch(Exception e) {
			SispcaLogger.logError(e.getMessage());
		
       }
    	
    	return false;
		
	}
	

	public Usuario loginByUserNameAndPassword(String username, String password) throws JpaException {
 
		
		Optional<Usuario> user = ((UsuarioDAO) getDao()).queryByUserNameAndPassword(username, password);

		if (!user.isPresent())
			return new Usuario(-1L);

		Usuario u = user.get();

		u.setUltimoLogin(new Date());
		u.setHash(null);
		u.setValidadeHash(null);
		
		update(u);

		return u;
	}
	
	public Usuario atualizarUsuarioPrimeiroAcesso(Usuario usuario,String password) throws JpaException {
		
		usuario.setPassword(password);
		usuario.setHash(null);
		usuario.setValidadeHash(null);
		usuario.setPrimeiroAcesso(false);
 
		return update(usuario);
		
	}
	
	public Optional<Usuario> buscarUsuarioByEmail(String email) {
		
		return ((UsuarioDAO) getDao()).queryByEmail(email);
	}

    public boolean recuperarSenha(Usuario usuario, String scheme,String serveName,int port,String path) throws JpaException {
    	
    	usuario= criarNovaSenha( usuario);
    	usuario=definiValidadeDoHash(usuario);
    	
    	update(usuario);
    	
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
