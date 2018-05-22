package quantitativo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;

import arquitetura.exception.JpaException;
import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;
import quantitativo.controller.FisicoFinanceiroMensalController;
import quantitativo.model.FisicoFinanceiroMensal;
import quantitativo.model.RelatorioFisicoFinanceiro;

public class FisicoFinanceiroMensalService extends AbstractService<FisicoFinanceiroMensal> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public FisicoFinanceiroMensalService(FisicoFinanceiroMensalController controller) {
		super(controller);
	}

	public List<RelatorioFisicoFinanceiro> relatorioPlanejamentoMensal(Long orgaoId,Long unidadeOrcamentariaId,Long programaId,Long acaoId, Long tipoRegiaoId, Long regiaoId,Long regiaoMunicipioId,Long exercicioId){
		
		List<FisicoFinanceiroMensal> listFisicoFinanceiro = ((FisicoFinanceiroMensalController)getController()).relatorioPlanejamentoMensal(orgaoId, unidadeOrcamentariaId, programaId, acaoId, tipoRegiaoId, regiaoId, regiaoMunicipioId, exercicioId);

		Map<Long,RelatorioFisicoFinanceiro> map = new HashMap<>();
	
	
		for(FisicoFinanceiroMensal financeiroMensal: listFisicoFinanceiro) {
			
			 if(map.containsKey(financeiroMensal.getRegiaoMunicipio().getId())) {
				 map.get(financeiroMensal.getRegiaoMunicipio().getId()).setData(financeiroMensal);
				 
			 }else {                                                                                    
				 map.put(financeiroMensal.getRegiaoMunicipio().getId(), new RelatorioFisicoFinanceiro(financeiroMensal));
			 }
			
		}		
	
		
		List<RelatorioFisicoFinanceiro> list = new ArrayList<>(map.size());

		list.addAll(map.values());
 
		return list;
		
		 
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
