package avaliacao.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.exception.JpaException;
import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;
import avaliacao.dao.AvaliacaoFisicoFinanceiraDAO;
import avaliacao.model.AvaliacaoFisicoFinanceira;

public class AvaliacaoFisicoFinanceiraService extends AbstractService<AvaliacaoFisicoFinanceira> {

 
	/**
	 * 
	 */
	private static final long serialVersionUID = -156314605441094680L;
	
 
	@Inject
	public AvaliacaoFisicoFinanceiraService(AvaliacaoFisicoFinanceiraDAO dao) {
		super(dao);
	}
 

	public List<AvaliacaoFisicoFinanceira> findByProgramaAndExercicio(Long programaId, Long exercicioId){
		return ((AvaliacaoFisicoFinanceiraDAO) getDAO()).findByProgramaAndExercicio(programaId, exercicioId);
	}
 
	
	public AvaliacaoFisicoFinanceira merge(AvaliacaoFisicoFinanceira avalFisFinanc) throws JpaException {

		if(Utils.invalidId(avalFisFinanc.getId())) {
			 return create(avalFisFinanc);
		}else {
			return update(avalFisFinanc);
		}
		
	}
	
	
}
