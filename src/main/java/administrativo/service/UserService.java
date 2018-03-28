package administrativo.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import administrativo.controller.PerfilController;
import administrativo.controller.UnidadeOrcamentariaController;
import administrativo.controller.UserController;
import administrativo.model.Perfil;
import administrativo.model.Usuario;
import arquitetura.utils.Utils;
import qualitativo.model.UnidadeOrcamentaria;

public class UserService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8096731496831193743L;

	private UserController userController;
	private PerfilController perfilController;
	private UnidadeOrcamentariaController unidadeOrcamentariaController;

	@Inject
	public UserService(UserController userController,PerfilController perfilController,UnidadeOrcamentariaController unidadeOrcamentariaController){
		this.userController=userController;
		this.perfilController=perfilController;
		this.unidadeOrcamentariaController=unidadeOrcamentariaController;
	}
	
	public List<Usuario> queryUser(String name, String email, String perfil, String unidadeOrcamentaria) {
		
		if(Utils.emptyParam(name)   && Utils.emptyParam(email)  && 
		   Utils.emptyParam(perfil) && Utils.emptyParam(unidadeOrcamentaria)) {
			return userController.findAll();
		}else {
			return userController.queryUser(name, email, perfil, unidadeOrcamentaria);
		}
		
		
	}

	public void delete(Usuario user) {
		userController.delete(user);

	}

	public Usuario findById(Long id) {
		return userController.findById(id);
	}

	public Usuario create(Usuario usuario) {
		return userController.create(usuario);
		
	}

	public Usuario update(Usuario usuario) {
		return userController.update(usuario);
	}

	public Optional<Usuario> queryByUserName(String login) {
		return userController.queryByUserName(login);
	}
	
	public List<Perfil> perfilFindAll(){
		return perfilController.findAll();
	}

	public Perfil perfilFindById(Long id) {
		return perfilController.findById(id);
	}
	
	public List<UnidadeOrcamentaria> uoFindAll(){
		return unidadeOrcamentariaController.findAll();
	}
	
	public UnidadeOrcamentaria uoFindById(Long id) {
		return unidadeOrcamentariaController.findById(id);
	}
	
	public boolean enviarEmailUsuarioCriado(Usuario usuario, String scheme,String serveName,int port,String path) {
		
		return userController.enviarEmailUsuarioCriado(usuario, scheme, serveName, port, path);
	}
	
	
}
