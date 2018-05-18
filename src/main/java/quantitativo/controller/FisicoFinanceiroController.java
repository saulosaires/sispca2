package quantitativo.controller;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import quantitativo.dao.FisicoFinanceiroDAO;
import quantitativo.model.FisicoFinanceiro;

public class FisicoFinanceiroController extends AbstractController<FisicoFinanceiro> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public FisicoFinanceiroController(FisicoFinanceiroDAO dao) {
		super(dao);

	}

	public List<FisicoFinanceiro> findByAcao(Long acaoId){
		
		return ((FisicoFinanceiroDAO)getDao()).findByAcao(acaoId);
	}
	
	public Optional<FisicoFinanceiro> findByRegiaoMunicipioAndExercicioAndAcao(Long regiaoMunicipioId,Long exercicioId,Long acaoId){
		
		return ((FisicoFinanceiroDAO)getDao()).findByRegiaoMunicipioAndExercicioAndAcao(regiaoMunicipioId,exercicioId,acaoId);
	}
	

	public List<FisicoFinanceiro>  relatorioPlanejadoPorAno(Long regiaoId,Long municipioId,Long unidadeOrcamentariaId,Long exercicioId){
		
		return ((FisicoFinanceiroDAO)getDao()).relatorioPlanejadoPorAno(regiaoId,municipioId,unidadeOrcamentariaId,exercicioId);
	
	}
	
	public List<FisicoFinanceiro>  relatorioPlanejadoPorRegiao(Long regiaoId,Long unidadeOrcamentariaId,Long exercicioId){
		
		return ((FisicoFinanceiroDAO)getDao()).relatorioPlanejadoPorRegiao(regiaoId,unidadeOrcamentariaId,exercicioId);
	
	}

	
	
}
