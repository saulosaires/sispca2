package administrativo.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import administrativo.controller.UserController;
import administrativo.model.Usuario;
import arquitetura.exception.JpaException;
import arquitetura.utils.Utils;

public class UserService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8096731496831193743L;

	private UserController userController;
 
	@Inject
	public UserService(UserController userController){
		this.userController=userController;
	}
	
	public List<Usuario> queryUser(String name, String email, String perfil, String unidadeOrcamentaria) {
		
		if(Utils.emptyParam(name)   && Utils.emptyParam(email)  && 
		   Utils.emptyParam(perfil) && Utils.emptyParam(unidadeOrcamentaria)) {
			return userController.findAll();
		}else {
			return userController.queryUser(name, email, perfil, unidadeOrcamentaria);
		}
		
		
	}
	
	public  List<Usuario> findAll() {
		return userController.findAll();

	}


	public void delete(Usuario user) throws JpaException {
		userController.delete(user);

	}

	public Usuario findById(Long id) {
		return userController.findById(id);
	}

	public Usuario create(Usuario usuario) throws JpaException {
		return userController.create(usuario);
		
	}

	public Usuario update(Usuario usuario) throws JpaException {
		return userController.update(usuario);
	}

	public Optional<Usuario> queryByUserName(String login) {
		return userController.queryByUserName(login);
	}
 
 
	
	public boolean enviarEmailUsuarioCriado(Usuario usuario, String scheme,String serveName,int port,String path) {
		
		return userController.enviarEmailUsuarioCriado(usuario, scheme, serveName, port, path);
	}
	
	
}
