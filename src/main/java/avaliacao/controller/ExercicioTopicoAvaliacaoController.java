package avaliacao.controller;

import java.util.List;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import avaliacao.dao.ExercicioTopicoAvaliacaoDAO;
import avaliacao.model.ExercicioTopicoAvaliacao;

public class ExercicioTopicoAvaliacaoController extends AbstractController<ExercicioTopicoAvaliacao>{

	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 6978813522637191526L;
 
	@Inject
	public ExercicioTopicoAvaliacaoController(ExercicioTopicoAvaliacaoDAO dao) {

		super(dao);
	}	 

	public List<ExercicioTopicoAvaliacao> findByExercicio(Long exercicioId){
		return ((ExercicioTopicoAvaliacaoDAO) getDao()).findByExercicio(exercicioId);
	}
 
	
}

