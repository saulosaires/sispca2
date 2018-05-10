package metas.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import metas.controller.AtividadeController;
import metas.model.Atividade;

public class AtividadeService extends AbstractService<Atividade> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3288174422611664897L;
 

	@Inject
	public AtividadeService(AtividadeController controller) {
		super(controller);
	}
 
	public List<Atividade> findByNomeAndUnidadeOrcamentaria(String nome, Long unidadeOrcamentaria){
		
		return ((AtividadeController)getController()).findByNomeAndUnidadeOrcamentaria(nome, unidadeOrcamentaria);
	}

	 
	
}
