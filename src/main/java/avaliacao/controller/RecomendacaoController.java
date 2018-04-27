package avaliacao.controller;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import avaliacao.dao.RecomendacaoDAO;
import avaliacao.model.Recomendacao;

public class RecomendacaoController extends AbstractController<Recomendacao>{

	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 6978813522637191526L;
 
	@Inject
	public RecomendacaoController(RecomendacaoDAO dao) {

		super(dao);
	}	 

	public Recomendacao findByProgramaAndExercicio(Long programaId, Long exercicioId){
		return ((RecomendacaoDAO) getDao()).findByProgramaAndExercicio(programaId, exercicioId);
	}
 
	
}

