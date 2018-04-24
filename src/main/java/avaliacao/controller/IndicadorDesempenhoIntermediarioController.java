package avaliacao.controller;

import java.util.List;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import avaliacao.dao.IndicadorDesempenhoIntermediarioDAO;
import avaliacao.model.IndicadorDesempenhoIntermediario;

public class IndicadorDesempenhoIntermediarioController extends AbstractController<IndicadorDesempenhoIntermediario>{

	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 6978813522637191526L;
 
	@Inject
	public IndicadorDesempenhoIntermediarioController(IndicadorDesempenhoIntermediarioDAO dao) {

		super(dao);
	}	 

	public List<IndicadorDesempenhoIntermediario> findByProgramaAndExercicio(Long programaId, Long exercicioId){
		return ((IndicadorDesempenhoIntermediarioDAO) getDao()).findByProgramaAndExercicio(programaId, exercicioId);
	}
 
	
}

