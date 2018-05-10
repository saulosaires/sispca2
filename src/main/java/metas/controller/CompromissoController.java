package metas.controller;

import java.util.List;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import metas.dao.CompromissoDAO;
import metas.model.Compromisso;

public class CompromissoController extends AbstractController<Compromisso>{
 
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1l;
 

	@Inject
	public CompromissoController(CompromissoDAO dao) {

		super(dao);
	}

	public List<Compromisso> findByAtividade(Long atividadeId){
		
		return ((CompromissoDAO)getDao()).findByAtividade(atividadeId);
	}
 
 

}
