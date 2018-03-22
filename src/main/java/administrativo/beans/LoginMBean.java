package administrativo.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import administrativo.controller.LoginController;
import administrativo.model.Perfil;
import administrativo.model.Usuario;
import arquitetura.utils.Cryptography;
import arquitetura.utils.JPAUtil;
import arquitetura.utils.Messages;
import arquitetura.utils.SessionUtils;
import arquitetura.utils.SispcaLogger;

@Named
@ViewScoped
public class LoginMBean implements Serializable{
	
	 
	private static final long serialVersionUID = 1L;
	
	private String login,password,emailUsuario;
	
	@Inject private  LoginController loginController;
	
	public LoginMBean() {}
 
	
	public String login() {
		
		try {
				
			String senhaMD5= Cryptography.md5(password);
			
			Usuario u= loginController.loginByUserNameAndPassword(login,senhaMD5);
			
			if(!JPAUtil.validId(u.getId())) {
				Messages.addMessageError("Login ou senha Inválidos");
				return "";
			}
			
			List<Perfil> perfis = u.getPerfis();
			
			SessionUtils.put("user", u);
			
			if(u.getPrimeiroAcesso()) {
				
				return "alterarsenha";
			}else {
				//TODO carregar permissao
				return "home";
			}

		}catch(Exception e) {
			SispcaLogger.logError(e.getLocalizedMessage());
			
			Messages.addMessageError("Falha inesperada ao tentar efetuar login");
		}
		
		return "";
	}
	
	public String solicitaRecuperacaoSenha(){
		
		try {
			
			if("".equals(emailUsuario) || emailUsuario==null) {
				Messages.addMessageError("Email não pode ser vazio");
				return "";
			}
			
			
			Optional<Usuario> u = loginController.buscarUsuarioByEmail(emailUsuario);
			
			if(!u.isPresent()) {
				Messages.addMessageError("Email não encontrado");
				return "";
			}
	 
			HttpServletRequest request = SessionUtils.getRequest();
			
			String scheme    = request.getScheme();
			String serveName = request.getServerName();
			int port         = request.getServerPort();
			String path      = request.getContextPath();
			
			loginController.recuperarSenha(u.get(),scheme,serveName,port,path);
			
			Messages.addMessageInfo("Acesse o link enviado para seu email para instruções de recuperação de senha");
			
			return "";
				
		}catch(Exception e) {
			SispcaLogger.logError(e.getMessage());
			
			Messages.addMessageError("Falha inesperada ao tentar efetuar login");
		}
		
		return "";
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}
 
 
	
}
