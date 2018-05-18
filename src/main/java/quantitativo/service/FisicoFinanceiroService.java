package quantitativo.service;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import arquitetura.exception.JpaException;
import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;
import quantitativo.controller.FisicoFinanceiroController;
import quantitativo.model.FisicoFinanceiro;

public class FisicoFinanceiroService extends AbstractService<FisicoFinanceiro> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public FisicoFinanceiroService(FisicoFinanceiroController controller) {
		super(controller);
	}

	public List<FisicoFinanceiro> findByAcao(Long acaoId){
		
		return ((FisicoFinanceiroController)getController()).findByAcao(acaoId);
	}
	
	
	public Optional<FisicoFinanceiro> findByRegiaoMunicipioAndExercicioAndAcao(Long regiaoMunicipioId,Long exercicioId,Long acaoId){
		
		return ((FisicoFinanceiroController)getController()).findByRegiaoMunicipioAndExercicioAndAcao(regiaoMunicipioId,exercicioId,acaoId);
	}
	
	public List<FisicoFinanceiro>  relatorioPlanejadoPorAno(Long regiaoId,Long municipioId,Long unidadeOrcamentariaId,Long exercicioId){
		
		return ((FisicoFinanceiroController)getController()).relatorioPlanejadoPorAno(regiaoId,municipioId,unidadeOrcamentariaId,exercicioId);
	
	}
	
	public List<FisicoFinanceiro>  relatorioPlanejadoPorRegiao(Long regiaoId,Long unidadeOrcamentariaId,Long exercicioId){
		
		return ((FisicoFinanceiroController)getController()).relatorioPlanejadoPorRegiao(regiaoId,unidadeOrcamentariaId,exercicioId);
	
	}

	
	
	public FisicoFinanceiro merge(FisicoFinanceiro fisicoFinanceiro) throws JpaException {
		
		if(fisicoFinanceiro==null)return fisicoFinanceiro;
		
		if(Utils.invalidId(fisicoFinanceiro.getId())) {
			return create(fisicoFinanceiro);
		}else {
			return update(fisicoFinanceiro);
		}
		
	}
	
}
