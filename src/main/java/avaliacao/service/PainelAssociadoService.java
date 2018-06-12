package avaliacao.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import avaliacao.dao.PainelAssociadoDAO;
import avaliacao.model.PainelAssociado;

public class PainelAssociadoService extends AbstractService<PainelAssociado> {

 
	/**
	 * 
	 */
	private static final long serialVersionUID = -156314605441094680L;
	
 
	@Inject
	public PainelAssociadoService(PainelAssociadoDAO dao) {
		super(dao);
	}
 

	public List<PainelAssociado> findByProgramaAndExercicio(Long programaId, Long exercicioId){
		return ((PainelAssociadoDAO) getDAO()).findByProgramaAndExercicio(programaId, exercicioId);
	}
 
	
}
