package avaliacao.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import avaliacao.controller.AnaliseController;
import avaliacao.model.Analise;

public class AnaliseService extends AbstractService<Analise> {

 
	/**
	 * 
	 */
	private static final long serialVersionUID = -156314605441094680L;
	
 
	@Inject
	public AnaliseService(AnaliseController controller) {
		super(controller);
	}
 

	public List<Analise> findByProgramaAndExercicio(Long programaId, Long exercicioId){
		return ((AnaliseController) getController()).findByProgramaAndExercicio(programaId, exercicioId);
	}
	
	
}
