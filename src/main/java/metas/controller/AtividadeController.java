package metas.controller;

import java.util.List;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import metas.dao.AtividadeDAO;
import metas.model.Atividade;

public class AtividadeController extends AbstractController<Atividade>{
 
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1l;
 

	@Inject
	public AtividadeController(AtividadeDAO dao) {

		super(dao);
	}

	public List<Atividade> findByNomeAndUnidadeOrcamentaria(String nome, Long unidadeOrcamentaria){
		
		return ((AtividadeDAO)getDao()).findByNomeAndUnidadeOrcamentaria(nome, unidadeOrcamentaria);
	}
 
 

}
