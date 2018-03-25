package administrativo.service;

import java.io.Serializable;
import java.util.Optional;

import javax.inject.Inject;

import administrativo.controller.LoginController;
import administrativo.model.Usuario;
import arquitetura.utils.Cryptography;
import arquitetura.utils.JPAUtil;

public class LoginService implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7401091260781268200L;
	private  LoginController loginController;
	
	@Inject 
	public LoginService( LoginController loginController) {
		this.loginController=loginController;
	}

	public Usuario loginByUserNameAndPassword(String login, String password) {

		String senhaMD5= Cryptography.md5(password);
		
		Usuario u= loginController.loginByUserNameAndPassword(login,senhaMD5);
		
		if(!JPAUtil.validId(u.getId())) {
			return u;
		}
		
		
		return u;
	}

	public boolean solicitaRecuperacaoSenha(String emailUsuario,String scheme,String serveName,int port,String path) {
		
		
		Optional<Usuario> u = loginController.buscarUsuarioByEmail(emailUsuario);
		
		if(!u.isPresent()) {
			return false;
		}
		
		
		return loginController.recuperarSenha(u.get(),scheme,serveName,port,path);
		
	}

	public boolean atualizarSenhaPrimeiroAcesso(String login, String password,String newPassword) {
		
		String senhaMD5= Cryptography.md5(password);
		
		Usuario u= loginController.loginByUserNameAndPassword(login,senhaMD5);
		
		if(!JPAUtil.validId(u.getId())) {
			return false;
		}
		
		u=loginController.atualizarUsuarioPrimeiroAcesso(u,Cryptography.md5(newPassword));
		
		return JPAUtil.validId(u.getId());
		
		 
		
	}
	
}
