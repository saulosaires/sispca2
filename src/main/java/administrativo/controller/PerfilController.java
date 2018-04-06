package administrativo.controller;

import java.util.Optional;

import javax.inject.Inject;

import administrativo.dao.PerfilDAO;
import administrativo.model.Perfil;
import arquitetura.controller.AbstractController;

public class PerfilController extends AbstractController<Perfil>{
 
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 3597150160739474071L;
 

	@Inject
	public PerfilController(PerfilDAO perfilDAO) {

		super(perfilDAO);
	}

	 

	public Optional<Perfil> findByDescription(String desc) {
		 
		return ((PerfilDAO) getDao()).findByDescription(desc);
	}
 

}
