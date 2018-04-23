package avaliacao.controller;

import java.util.List;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import avaliacao.dao.DiretrizAssociadaDAO;
import avaliacao.model.DiretrizAssociada;

public class DiretrizAssociadaController extends AbstractController<DiretrizAssociada>{

	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 6978813522637191526L;
 
	@Inject
	public DiretrizAssociadaController(DiretrizAssociadaDAO dao) {

		super(dao);
	}	 

	public List<DiretrizAssociada> findByProgramaAndExercicio(Long programaId, Long exercicioId){
		return ((DiretrizAssociadaDAO) getDao()).findByProgramaAndExercicio(programaId, exercicioId);
	}
 
	
}

