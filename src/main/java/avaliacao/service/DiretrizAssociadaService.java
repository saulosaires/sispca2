package avaliacao.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import avaliacao.dao.DiretrizAssociadaDAO;
import avaliacao.model.DiretrizAssociada;

public class DiretrizAssociadaService extends AbstractService<DiretrizAssociada> {

 
	/**
	 * 
	 */
	private static final long serialVersionUID = -156314605441094680L;
	
 
	@Inject
	public DiretrizAssociadaService(DiretrizAssociadaDAO dao) {
		super(dao);
	}
 

	public List<DiretrizAssociada> findByProgramaAndExercicio(Long programaId, Long exercicioId){
		return ((DiretrizAssociadaDAO) getDAO()).findByProgramaAndExercicio(programaId, exercicioId);
	}
	
	
}
