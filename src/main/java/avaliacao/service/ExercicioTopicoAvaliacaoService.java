package avaliacao.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import avaliacao.dao.ExercicioTopicoAvaliacaoDAO;
import avaliacao.model.ExercicioTopicoAvaliacao;

public class ExercicioTopicoAvaliacaoService extends AbstractService<ExercicioTopicoAvaliacao> {

 
	/**
	 * 
	 */
	private static final long serialVersionUID = -156314605441094680L;
	
 
	@Inject
	public ExercicioTopicoAvaliacaoService(ExercicioTopicoAvaliacaoDAO dao) {
		super(dao);
	}
 

	public List<ExercicioTopicoAvaliacao> findByExercicio(Long exercicioId){
		return ((ExercicioTopicoAvaliacaoDAO) getDAO()).findByExercicio(exercicioId);
	}
	
	
}
