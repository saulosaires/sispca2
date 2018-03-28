package administrativo.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import administrativo.dao.UnidadeOrcamentariaDAO;
import qualitativo.model.UnidadeOrcamentaria;

public class UnidadeOrcamentariaController implements Serializable {
 
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 3597150160739474071L;
	private UnidadeOrcamentariaDAO dao;

	@Inject
	public UnidadeOrcamentariaController(UnidadeOrcamentariaDAO dao) {

		this.dao = dao;
	}

	public UnidadeOrcamentaria findById(Long id) {

		return dao.findOne(id);
	}
	
	public List<UnidadeOrcamentaria> findAll() {

		return dao.findAllOrderBySigla();
	}

	public void create(UnidadeOrcamentaria unidadeOrcamentaria) {

		dao.create(unidadeOrcamentaria);
	}
	
	public UnidadeOrcamentaria update(UnidadeOrcamentaria unidadeOrcamentaria) {

		return dao.update(unidadeOrcamentaria);
	}
 
	public UnidadeOrcamentaria delete(UnidadeOrcamentaria unidadeOrcamentaria) {

		return dao.delete(unidadeOrcamentaria);
	}
 

}
