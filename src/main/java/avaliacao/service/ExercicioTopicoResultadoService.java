package avaliacao.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import avaliacao.dao.ExercicioTopicoResultadoDAO;
import avaliacao.model.ExercicioTopicoResultado;

public class ExercicioTopicoResultadoService  extends AbstractService<ExercicioTopicoResultado> {

	@Inject
	public ExercicioTopicoResultadoService(ExercicioTopicoResultadoDAO dao) {
		super(dao);
	}

	public List<ExercicioTopicoResultado> buscarPorExercicicio(Long exercicioId) {
		
		return ((ExercicioTopicoResultadoDAO) getDAO()).buscarPorExercicicio(exercicioId);
	}	
	
	
}
