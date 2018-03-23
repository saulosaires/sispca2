package administrativo.beans;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.controller.LoginController;
import administrativo.model.Usuario;
import arquitetura.utils.Cryptography;
import arquitetura.utils.JPAUtil;
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
	@Inject private  LoginController loginController;
	
	
	public AlterarSenhaMBean() {
		
		usuario = (Usuario) SessionUtils.get("user");
 
	}


	public String alteraSenha() {
		
		try {
				String senhaMD5= Cryptography.md5(password);
				
				Usuario u= loginController.loginByUserNameAndPassword(usuario.getLogin(),senhaMD5);
				
				if(!JPAUtil.validId(u.getId())) {
					Messages.addMessageError("Senha antiga Inválida");
					return "";
				}
				
				if(!newPassword.equals(confirmPassword)) {
					Messages.addMessageError("Senha e confirmação não sao iguais");
					return "";
				}
				
				loginController.atualizarUsuarioPrimeiroAcesso(u,  Cryptography.md5(newPassword));
				
				Messages.addMessageInfo("Senha alterada com sucesso");
				
				return "home";
		
		}catch(Exception e) {
			SispcaLogger.logError(e.getMessage());
			
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
