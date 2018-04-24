package avaliacao.controller;

import java.util.List;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import avaliacao.dao.AnaliseDAO;
import avaliacao.model.Analise;

public class AnaliseController extends AbstractController<Analise>{

	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 6978813522637191526L;
 
	@Inject
	public AnaliseController(AnaliseDAO dao) {

		super(dao);
	}	 

	public List<Analise> findByProgramaAndExercicio(Long programaId, Long exercicioId){
		return ((AnaliseDAO) getDao()).findByProgramaAndExercicio(programaId, exercicioId);
	}
 
	
}

