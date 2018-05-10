package administrativo.service;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import administrativo.controller.UserController;
import administrativo.model.Usuario;
import arquitetura.exception.JpaException;
import arquitetura.service.AbstractService;
import arquitetura.utils.Cryptography;
import arquitetura.utils.JPAUtil;
import arquitetura.utils.Utils;

public class UserService  extends AbstractService<Usuario> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8096731496831193743L;
 
 
	@Inject
	public UserService(UserController userController){
		super(userController);
	}
	
	public List<Usuario> queryUser(String name, String email, String perfil, String unidadeOrcamentaria) {
		
		if(Utils.emptyParam(name)   && Utils.emptyParam(email)  && 
		   Utils.emptyParam(perfil) && Utils.emptyParam(unidadeOrcamentaria)) {
			return findAll();
		}else {
			return ((UserController) getController()).queryUser(name, email, perfil, unidadeOrcamentaria);
		}
		
		
	}
 
	public Optional<Usuario> queryByUserName(String login) {
		return ((UserController) getController()).queryByUserName(login);
	}
 
	public Optional<Usuario> queryByCPF(String cpf) {
		return ((UserController) getController()).queryByCPF(cpf);
	}
 
	
	public boolean enviarEmailUsuarioCriado(Usuario usuario, String scheme,String serveName,int port,String path) {
		
		return ((UserController) getController()).enviarEmailUsuarioCriado(usuario, scheme, serveName, port, path);
	}
	
	public Usuario loginByUserNameAndPassword(String login, String password) throws JpaException {

		String senhaMD5= Cryptography.md5(password);
		
		Usuario u= ((UserController) getController()).loginByUserNameAndPassword(login,senhaMD5);
		
		if(!JPAUtil.validId(u.getId())) {
			return u;
		}
		
		
		return u;
	}

	public boolean solicitaRecuperacaoSenha(String emailUsuario,String scheme,String serveName,int port,String path) throws JpaException {
		
		UserController controller = (UserController) getController();
		
		Optional<Usuario> u = controller.buscarUsuarioByEmail(emailUsuario);
		
		if(!u.isPresent()) {
			return false;
		}
		
		
		return controller.recuperarSenha(u.get(),scheme,serveName,port,path);
		
	}

	public boolean atualizarSenhaPrimeiroAcesso(String login, String password,String newPassword) throws JpaException {
		
		UserController controller = (UserController) getController();
		
		String senhaMD5= Cryptography.md5(password);
		
		Usuario u= controller.loginByUserNameAndPassword(login,senhaMD5);
		
		if(!JPAUtil.validId(u.getId())) {
			return false;
		}
		
		u=controller.atualizarUsuarioPrimeiroAcesso(u,Cryptography.md5(newPassword));
		
		return JPAUtil.validId(u.getId());
		
		 
		
	}
	
}
