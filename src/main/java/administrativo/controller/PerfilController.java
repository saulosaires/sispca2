package administrativo.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import administrativo.dao.PerfilDAO;
import administrativo.model.Perfil;
import arquitetura.exception.JpaException;

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

	public Perfil create(Perfil perfil) throws JpaException {

		return perfilDAO.create(perfil);
	}
	
	public Perfil update(Perfil perfil) throws JpaException {

		return perfilDAO.update(perfil);
	}
 
	public Perfil delete(Perfil perfil) throws JpaException {

		return perfilDAO.delete(perfil);
	}

	public Optional<Perfil> findByDescription(String desc) {
		 
		return perfilDAO.findByDescription(desc);
	}
 

}