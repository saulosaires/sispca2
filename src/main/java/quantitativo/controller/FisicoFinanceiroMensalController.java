package quantitativo.controller;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import quantitativo.dao.FisicoFinanceiroMensalDAO;
import quantitativo.model.FisicoFinanceiroMensal;

public class FisicoFinanceiroMensalController extends AbstractController<FisicoFinanceiroMensal> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public FisicoFinanceiroMensalController(FisicoFinanceiroMensalDAO dao) {
		super(dao);

	}
	
	public List<FisicoFinanceiroMensal> relatorioPlanejamentoMensal(Long orgaoId,Long unidadeOrcamentariaId,Long programaId,Long acaoId, Long tipoRegiaoId, Long regiaoId,Long regiaoMunicipioId,Long exercicioId){
		
		return ((FisicoFinanceiroMensalDAO)getDao()).relatorioPlanejamentoMensal(orgaoId, unidadeOrcamentariaId, programaId, acaoId, tipoRegiaoId, regiaoId, regiaoMunicipioId, exercicioId);
	}

	
	public Optional<FisicoFinanceiroMensal> findByRegiaoMunicipioAndExercicioAndAcaoAndMes(Long regiaoMunicipioId,Long exercicioId,Long acaoId,Long mesId){
		
		return ((FisicoFinanceiroMensalDAO)getDao()).findByRegiaoMunicipioAndExercicioAndAcaoAndMes(regiaoMunicipioId,exercicioId,acaoId,mesId);
	}


	public List<FisicoFinanceiroMensal> findByAcao(Long acaoId) {
		return ((FisicoFinanceiroMensalDAO)getDao()).findByAcao(acaoId);
	}
	
	public Double findTotalValorFinanceiroPlanejadoByAcao(Long unidadeGestoraId, Long unidadeOrcamentariaId, Long acaoId,Long exercicioVigenteId, Long mesId) {
		return ((FisicoFinanceiroMensalDAO)getDao()).findTotalValorFinanceiroPlanejadoByAcao(unidadeGestoraId, unidadeOrcamentariaId, acaoId, exercicioVigenteId, mesId);
	}

	
}
