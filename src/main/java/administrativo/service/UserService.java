package administrativo.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import administrativo.dao.UsuarioDAO;
import administrativo.model.Usuario;
import arquitetura.exception.JpaException;
import arquitetura.service.AbstractService;
import arquitetura.utils.Cryptography;
import arquitetura.utils.EmailUtil;
import arquitetura.utils.JPAUtil;
import arquitetura.utils.SispcaLogger;
import arquitetura.utils.Utils;

public class UserService  extends AbstractService<Usuario> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8096731496831193743L;
 
 
	@Inject
	public UserService(UsuarioDAO dao){
		super(dao);
	}
	
	 private UsuarioDAO dao() {
	    	return ((UsuarioDAO)getDAO());
	 }
	
	public List<Usuario> queryUser(String name, String email, String perfil, String unidadeOrcamentaria) {
		
		if(Utils.emptyParam(name)   && Utils.emptyParam(email)  && 
		   Utils.emptyParam(perfil) && Utils.emptyParam(unidadeOrcamentaria)) {
			return findAll();
		}else {
			return dao().queryUser(name, email, perfil, unidadeOrcamentaria);
		}
		
		
	}
 
	public Optional<Usuario> queryByUserName(String login) {
		return dao().queryByUserName(login);
	}
 
	public Optional<Usuario> queryByCPF(String cpf) {
		return dao().queryByCPF(cpf);
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
			SispcaLogger.logError(e);
		
       }
    	
    	return false;
	
	
	}
	
	public Usuario loginByUserNameAndPassword(String login, String password) throws JpaException {

		String senhaMD5= Cryptography.md5(password);
		
		Optional<Usuario> user = dao().queryByUserNameAndPassword(login, senhaMD5);

		if (!user.isPresent())
			return new Usuario(-1L);

		Usuario u = user.get();

		u.setUltimoLogin(new Date());
		u.setHash(null);
		u.setValidadeHash(null);
		
		update(u);
 
		
		return u;
	}

	public boolean solicitaRecuperacaoSenha(String emailUsuario,String scheme,String serveName,int port,String path) throws JpaException {
		
		
		
		Optional<Usuario> u = dao().queryByEmail(emailUsuario);
		
		if(!u.isPresent()) {
			return false;
		}
		
		
		return recuperarSenha(u.get(),scheme,serveName,port,path);
		
	}
	
    private boolean recuperarSenha(Usuario usuario, String scheme,String serveName,int port,String path) throws JpaException {
    	
    	usuario= criarNovaSenha( usuario);
    	usuario=definiValidadeDoHash(usuario);
    	
    	update(usuario);
    	
    	return enviarEmailRecuperacaoSenha(usuario, scheme,serveName,port,path);
    	
    }

   private Usuario criarNovaSenha(Usuario usuario) {
    	
    	String hash = Cryptography.md5("" + Utils.randomNumber());
    	
    	usuario.setHash(hash);
    	
    	return usuario;
    }

   private Usuario definiValidadeDoHash(Usuario usuario) {
    	
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
			SispcaLogger.logError(e);
		
      }
   	
   	return false;
   }
   
   
	public boolean atualizarSenhaPrimeiroAcesso(String login, String password,String newPassword) throws JpaException {
		
	 
		
		String senhaMD5= Cryptography.md5(password);
		
		Usuario u= loginByUserNameAndPassword(login,senhaMD5);
		
		if(!JPAUtil.validId(u.getId())) {
			return false;
		}
		
		u.setPassword(Cryptography.md5(newPassword));
		u.setHash(null);
		u.setValidadeHash(null);
		u.setPrimeiroAcesso(false);
 
		u=update(u);
				
				
		return Utils.invalidId(u.getId());
		
		 
		
	}
	
}
