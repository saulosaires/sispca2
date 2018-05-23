package quantitativo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import arquitetura.exception.JpaException;
import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;
import qualitativo.model.Acao;
import quantitativo.controller.FisicoFinanceiroController;
import quantitativo.model.FisicoFinanceiro;
import relatorio.model.RelatorioQuantitativoAnual;

public class FisicoFinanceiroService extends AbstractService<FisicoFinanceiro> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public FisicoFinanceiroService(FisicoFinanceiroController controller) {
		super(controller);
	}

	public List<FisicoFinanceiro> totalPorUnidadeOrcamentaria(Long unidadeOrcamentariaId, Long ppaId){
		return  ((FisicoFinanceiroController)getController()).totalPorUnidadeOrcamentaria(unidadeOrcamentariaId, ppaId);
	}

	
	public List<FisicoFinanceiro> findByAcaoAndPpa(String acaoCodigo, Long ppaId){
		return ((FisicoFinanceiroController)getController()).findByAcaoAndPpa(acaoCodigo,ppaId);
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

	public List<RelatorioQuantitativoAnual>  relatorioQuantitativoAnual(List<Acao> listAcoes,Long ppaId) {
	 
		List<RelatorioQuantitativoAnual> listRel = new ArrayList<>();
		
		for(Acao acao: listAcoes) {
			
			List<FisicoFinanceiro> listFisicoFinanceiro = findByAcaoAndPpa(acao.getCodigo(), ppaId);
			
			listRel.add(new RelatorioQuantitativoAnual(acao, listFisicoFinanceiro));
			
		}
		
		
		return listRel;
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
