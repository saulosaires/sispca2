package avaliacao.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import avaliacao.controller.PainelAssociadoController;
import avaliacao.model.PainelAssociado;

public class PainelAssociadoService extends AbstractService<PainelAssociado> {

 
	/**
	 * 
	 */
	private static final long serialVersionUID = -156314605441094680L;
	
 
	@Inject
	public PainelAssociadoService(PainelAssociadoController controller) {
		super(controller);
	}
 

	public List<PainelAssociado> findByProgramaAndExercicio(Long programaId, Long exercicioId){
		return ((PainelAssociadoController) getController()).findByProgramaAndExercicio(programaId, exercicioId);
	}
 
	
}
