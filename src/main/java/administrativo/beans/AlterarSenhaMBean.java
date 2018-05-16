package administrativo.beans;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.model.Usuario;
import administrativo.service.UserService;
import arquitetura.utils.Messages;
import arquitetura.utils.SessionUtils;
import arquitetura.utils.SispcaLogger;

@Named
@ViewScoped
public class AlterarSenhaMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2110396895947469990L;
	private Usuario usuario;
	private String password;
	private String newPassword;
	private String confirmPassword;
	
	private  UserService userService;
	
	@Inject
	public AlterarSenhaMBean(UserService userService) {
		
		this.userService=userService;
		
		usuario = (Usuario) SessionUtils.get("user");
 
	}


	public String alteraSenha() {
		
		try {
			
			if(!newPassword.equals(confirmPassword)) {
				Messages.addMessageError("Senha e confirmação não sao iguais");
				return "";
			}
			
			boolean updated=userService.atualizarSenhaPrimeiroAcesso(usuario.getLogin(),password,newPassword);
			
			if(!updated) {
				Messages.addMessageError("Senha antiga Inválida");
				return "";
			} 
			
			Messages.addMessageInfo("Senha alterada com sucesso");
 
			return "home";
		
		}catch(Exception e) {
			SispcaLogger.logError(e);
			
			Messages.addMessageError("Falha inesperada ao alterar senha");
		}
		
		return "";

		
	}
	
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getNewPassword() {
		return newPassword;
	}


	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}


	public String getConfirmPassword() {
		return confirmPassword;
	}


	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
	
	
}
