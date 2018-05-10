package metas.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import metas.controller.CompromissoController;
import metas.model.Compromisso;

public class CompromissoService extends AbstractService<Compromisso> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3288174422611664897L;
 

	@Inject
	public CompromissoService(CompromissoController controller) {
		super(controller);
	}
 
public List<Compromisso> findByAtividade(Long atividadeId){
		
		return ((CompromissoController)getController()).findByAtividade(atividadeId);
	}
 

	 
	
}
