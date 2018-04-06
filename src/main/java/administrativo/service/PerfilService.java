package administrativo.service;

import java.util.Optional;

import javax.inject.Inject;

import administrativo.controller.PerfilController;
import administrativo.model.Perfil;
import arquitetura.service.AbstractService;

public class PerfilService extends AbstractService<Perfil> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3288174422611664897L;
 

	@Inject
	public PerfilService(PerfilController perfilController) {
		super(perfilController);
	}
 

	public Optional<Perfil> findByDescription(String desc){
		return ((PerfilController) getController()).findByDescription(desc);
	}
	
	 
	
}
