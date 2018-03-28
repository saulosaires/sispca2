package administrativo.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import administrativo.dao.PerfilDAO;
import administrativo.model.Perfil;

public class PerfilController implements Serializable {
 
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 3597150160739474071L;
	private PerfilDAO perfilDAO;

	@Inject
	public PerfilController(PerfilDAO perfilDAO) {

		this.perfilDAO = perfilDAO;
	}

	public Perfil findById(Long id) {

		return perfilDAO.findOne(id);
	}
	
	public List<Perfil> findAll() {

		return perfilDAO.findAllOrderByName();
	}

	public void create(Perfil perfil) {

		perfilDAO.create(perfil);
	}
	
	public Perfil update(Perfil perfil) {

		return perfilDAO.update(perfil);
	}
 
	public Perfil delete(Perfil perfil) {

		return perfilDAO.delete(perfil);
	}
 

}
