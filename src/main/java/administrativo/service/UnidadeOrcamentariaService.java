package administrativo.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import administrativo.controller.UnidadeOrcamentariaController;
import arquitetura.exception.JpaException;
import qualitativo.model.UnidadeOrcamentaria;
 

public class UnidadeOrcamentariaService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3288174422611664897L;

	private UnidadeOrcamentariaController controller;

	@Inject
	public UnidadeOrcamentariaService(UnidadeOrcamentariaController unidadeOrcamentariaController) {
		this.controller = unidadeOrcamentariaController;
	}

	public List<UnidadeOrcamentaria> findAll() {
		return controller.findAll();
	}

	 

	public void delete(UnidadeOrcamentaria unidadeOrcamentaria) throws JpaException {

		controller.delete(unidadeOrcamentaria);

	}

	public UnidadeOrcamentaria findById(Long id) {

		return controller.findById(id);

	}

	public UnidadeOrcamentaria create(UnidadeOrcamentaria unidadeOrcamentaria) throws JpaException {
		return controller.create(unidadeOrcamentaria);

	}

	public UnidadeOrcamentaria update(UnidadeOrcamentaria unidadeOrcamentaria) throws JpaException {
		return controller.update(unidadeOrcamentaria);

	}
	
}
