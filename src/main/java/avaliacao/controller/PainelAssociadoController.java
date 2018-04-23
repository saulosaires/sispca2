package avaliacao.controller;

import java.util.List;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import avaliacao.dao.PainelAssociadoDAO;
import avaliacao.model.PainelAssociado;

public class PainelAssociadoController extends AbstractController<PainelAssociado>{

	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 6978813522637191526L;
 
	@Inject
	public PainelAssociadoController(PainelAssociadoDAO dao) {

		super(dao);
	}	 

	public List<PainelAssociado> findByProgramaAndExercicio(Long programaId, Long exercicioId){
		return ((PainelAssociadoDAO) getDao()).findByProgramaAndExercicio(programaId, exercicioId);
	}
  
	
}

