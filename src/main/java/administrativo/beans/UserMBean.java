package administrativo.beans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.model.Usuario;
import administrativo.service.UserService;
import arquitetura.exception.JpaException;
import arquitetura.utils.Messages;
import arquitetura.utils.SessionUtils;
import arquitetura.utils.SispcaLogger;

@Named
@SessionScoped
public class UserMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7880830417324625776L;

	private Usuario usuario;
	
	private String password;
	private String passwordConfirmacao;
	
	private UserService userService;
	
	@Inject
	public UserMBean(UserService userService) {
		
		this.userService = userService;
		init();
	}
	
	public void init() {
		
		usuario = (Usuario) SessionUtils.get(SessionUtils.USER);
		
		 
	}

	public String logout() {
		
		SessionUtils.invalidate();
		
		return "login";
	}
	
 	public String altetarMeusDados() {
 		
 		try {
			userService.update(usuario);
		} catch (Exception e) {
			SispcaLogger.logError(e);

			Messages.addMessageError("Falha ao atualizar dados");
		}
 		
 		return "home";
 	}
	
	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmacao() {
		return passwordConfirmacao;
	}

	public void setPasswordConfirmacao(String passwordConfirmacao) {
		this.passwordConfirmacao = passwordConfirmacao;
	}
 
	
	
	
}
