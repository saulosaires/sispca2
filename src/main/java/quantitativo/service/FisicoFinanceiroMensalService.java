package quantitativo.service;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import arquitetura.exception.JpaException;
import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;
import quantitativo.controller.FisicoFinanceiroMensalController;
import quantitativo.model.FisicoFinanceiroMensal;

public class FisicoFinanceiroMensalService extends AbstractService<FisicoFinanceiroMensal> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public FisicoFinanceiroMensalService(FisicoFinanceiroMensalController controller) {
		super(controller);
	}

	public Optional<FisicoFinanceiroMensal> findByRegiaoMunicipioAndExercicioAndAcaoAndMes(Long regiaoMunicipioId,Long exercicioId,Long acaoId,Long mesId){
		
		return ((FisicoFinanceiroMensalController)getController()).findByRegiaoMunicipioAndExercicioAndAcaoAndMes(regiaoMunicipioId,exercicioId,acaoId,mesId);
	}
	 
	
	public FisicoFinanceiroMensal merge(FisicoFinanceiroMensal fisicoFinanceiroMensal) throws JpaException {
		
		if(fisicoFinanceiroMensal==null)return fisicoFinanceiroMensal;
		
		if(Utils.invalidId(fisicoFinanceiroMensal.getId())) {
			return create(fisicoFinanceiroMensal);
		}else {
			return update(fisicoFinanceiroMensal);
		}
		
	}

	public List<FisicoFinanceiroMensal> findByAcao(Long acaoId) {
		
		return ((FisicoFinanceiroMensalController)getController()).findByAcao(acaoId);
	}
	
	public Double findTotalValorFinanceiroPlanejadoByAcao(Long unidadeGestoraId, Long unidadeOrcamentariaId, Long acaoId,Long exercicioVigenteId, Long mesId) {
		
		return ((FisicoFinanceiroMensalController)getController()).findTotalValorFinanceiroPlanejadoByAcao(unidadeGestoraId, unidadeOrcamentariaId, acaoId, exercicioVigenteId, mesId);
	}	
	
}
