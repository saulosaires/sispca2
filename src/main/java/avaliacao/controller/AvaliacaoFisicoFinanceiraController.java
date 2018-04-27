package avaliacao.controller;

import java.util.List;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import avaliacao.dao.AvaliacaoFisicoFinanceiraDAO;
import avaliacao.model.AvaliacaoFisicoFinanceira;

public class AvaliacaoFisicoFinanceiraController extends AbstractController<AvaliacaoFisicoFinanceira>{

	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 6978813522637191526L;
 
	@Inject
	public AvaliacaoFisicoFinanceiraController(AvaliacaoFisicoFinanceiraDAO dao) {

		super(dao);
	}	 

	public List<AvaliacaoFisicoFinanceira> findByProgramaAndExercicio(Long programaId, Long exercicioId){
		return ((AvaliacaoFisicoFinanceiraDAO) getDao()).findByProgramaAndExercicio(programaId, exercicioId);
	}
 
	
}

