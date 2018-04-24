package avaliacao.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import avaliacao.controller.IndicadorDesempenhoIntermediarioController;
import avaliacao.model.IndicadorDesempenhoIntermediario;

public class IndicadorDesempenhoIntermediarioService extends AbstractService<IndicadorDesempenhoIntermediario> {

 
	/**
	 * 
	 */
	private static final long serialVersionUID = -156314605441094680L;
	
 
	@Inject
	public IndicadorDesempenhoIntermediarioService(IndicadorDesempenhoIntermediarioController controller) {
		super(controller);
	}
 

	public List<IndicadorDesempenhoIntermediario> findByProgramaAndExercicio(Long programaId, Long exercicioId){
		return ((IndicadorDesempenhoIntermediarioController) getController()).findByProgramaAndExercicio(programaId, exercicioId);
	}
	
	
}
