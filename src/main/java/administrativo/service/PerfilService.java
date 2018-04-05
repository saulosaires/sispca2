package administrativo.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import administrativo.controller.PerfilController;
import administrativo.model.Perfil;
import arquitetura.exception.JpaException;

public class PerfilService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3288174422611664897L;

	PerfilController perfilController;

	@Inject
	public PerfilService(PerfilController perfilController) {
		this.perfilController = perfilController;
	}

	public List<Perfil> findAll() {
		return perfilController.findAll();
	}

	 

	public void delete(Perfil perfil) throws JpaException {

		perfilController.delete(perfil);

	}

	public Perfil findById(Long id) {

		return perfilController.findById(id);

	}

	public Optional<Perfil> findByDescription(String desc){
		return perfilController.findByDescription(desc);
	}
	
	public Perfil create(Perfil perfil) throws JpaException {
		return perfilController.create(perfil);

	}

	public Perfil update(Perfil perfil) throws JpaException {
		return perfilController.update(perfil);

	}
	
}
