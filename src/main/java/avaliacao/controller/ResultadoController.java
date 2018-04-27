package avaliacao.controller;

import java.util.List;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import avaliacao.dao.ResultadoDAO;
import avaliacao.model.Resultado;

public class ResultadoController extends AbstractController<Resultado>{

	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 6978813522637191526L;
 
	@Inject
	public ResultadoController(ResultadoDAO dao) {

		super(dao);
	}	 

	public List<Resultado> findByProgramaAndExercicio(Long programaId, Long exercicioId){
		return ((ResultadoDAO) getDao()).findByProgramaAndExercicio(programaId, exercicioId);
	}
 
	
}

