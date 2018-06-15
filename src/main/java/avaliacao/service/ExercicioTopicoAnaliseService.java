package avaliacao.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import avaliacao.dao.ExercicioTopicoAnaliseDAO;
import avaliacao.model.ExercicioTopicoAnalise;

public class ExercicioTopicoAnaliseService extends AbstractService<ExercicioTopicoAnalise> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4430777939964361258L;

	@Inject
	public ExercicioTopicoAnaliseService(ExercicioTopicoAnaliseDAO dao) {
		super(dao);
	}

	public List<ExercicioTopicoAnalise> buscarPorExercicicio(Long exercicioId) {
		
		return ((ExercicioTopicoAnaliseDAO) getDAO()).buscarPorExercicicio(exercicioId);
	}
	
	
}
