package avaliacao.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import avaliacao.dao.IndicadorDesempenhoIntermediarioDAO;
import avaliacao.model.IndicadorDesempenhoIntermediario;

public class IndicadorDesempenhoIntermediarioService extends AbstractService<IndicadorDesempenhoIntermediario> {

 
	/**
	 * 
	 */
	private static final long serialVersionUID = -156314605441094680L;
	
 
	@Inject
	public IndicadorDesempenhoIntermediarioService(IndicadorDesempenhoIntermediarioDAO dao) {
		super(dao);
	}
 

	public List<IndicadorDesempenhoIntermediario> findByProgramaAndExercicio(Long programaId, Long exercicioId){
		return ((IndicadorDesempenhoIntermediarioDAO) getDAO()).findByProgramaAndExercicio(programaId, exercicioId);
	}
	
	
}
