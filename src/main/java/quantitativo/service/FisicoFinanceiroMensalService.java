package quantitativo.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;

import javax.inject.Inject;

import arquitetura.exception.JpaException;
import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;
import qualitativo.model.Mes;
import quantitativo.dao.FisicoFinanceiroMensalDAO;
import quantitativo.model.FisicoFinanceiroMensal;
import quantitativo.model.RelatorioDetalhamentoAcaoFinanceiroMensal;
import quantitativo.model.RelatorioFisicoFinanceiro;


public class FisicoFinanceiroMensalService extends AbstractService<FisicoFinanceiroMensal> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public FisicoFinanceiroMensalService(FisicoFinanceiroMensalDAO dao) {
		super(dao);
	}

	private FisicoFinanceiroMensalDAO dao() {
		
		return (FisicoFinanceiroMensalDAO)getDAO();
	}
	
	
	public List<RelatorioFisicoFinanceiro> relatorioPlanejamentoMensal(List<Long> listOrgaoId,Long unidadeOrcamentariaId,Long programaId,Long acaoId, Long tipoRegiaoId, Long regiaoId,Long regiaoMunicipioId,Long exercicioId){
		
		List<FisicoFinanceiroMensal> listFisicoFinanceiro = dao().relatorioPlanejamentoMensal(listOrgaoId, unidadeOrcamentariaId, programaId, acaoId, tipoRegiaoId, regiaoId, regiaoMunicipioId, exercicioId);

 		Map<String,RelatorioFisicoFinanceiro> map = new HashMap<>();
	
	
		for(FisicoFinanceiroMensal financeiroMensal: listFisicoFinanceiro) {
			
			 
			StringJoiner joiner = new StringJoiner("");
			
			//Como nao estou fazendo o group by no sql, vou fazer aqui, poderia fazer o group by no sql, 
			//porem teria que ir consultar para cada mes o valor/qtd, o que iria gerar mais consultas da ordem de N*12*2
			
			joiner.add((financeiroMensal.getAcao().getUnidadeOrcamentaria().getOrgao().getCodigo()))
				  .add((financeiroMensal.getAcao().getUnidadeOrcamentaria().getCodigo()))
				  .add(financeiroMensal.getAcao().getPrograma().getCodigo())
				  .add(financeiroMensal.getAcao().getCodigo())
				  .add(financeiroMensal.getRegiaoMunicipio().getId().toString());
			
			String id =joiner.toString();
			
			 if(map.containsKey(id)) {
				 map.get(id).setData(financeiroMensal);
				 
			 }else {                                                                                    
				 map.put(id, new RelatorioFisicoFinanceiro(financeiroMensal));
			 }
			
		}		
	
		
		List<RelatorioFisicoFinanceiro> list = new ArrayList<>(map.size());

		list.addAll(map.values());
 
		return list;
		
		 
	}

	
	public Optional<FisicoFinanceiroMensal> findByRegiaoMunicipioAndExercicioAndAcaoAndMes(Long regiaoMunicipioId,Long exercicioId,Long acaoId,Long mesId){
		
		return dao().findByRegiaoMunicipioAndExercicioAndAcaoAndMes(regiaoMunicipioId,exercicioId,acaoId,mesId);
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
		
		return dao().findByAcao(acaoId);
	}
	
	public BigDecimal findTotalValorFinanceiroPlanejadoByAcao(Long unidadeGestoraId, Long unidadeOrcamentariaId, Long acaoId,Long exercicioVigenteId, Long mesId) {
		
		return dao().findTotalValorFinanceiroPlanejadoByAcao(unidadeGestoraId, unidadeOrcamentariaId, acaoId, exercicioVigenteId, mesId);
	}	
	
	public RelatorioDetalhamentoAcaoFinanceiroMensal calculaPlanejamentoMensalByMesAndExercicioAndAcao(List<Mes> meses, Long exercicio,Long acao){
		
		RelatorioDetalhamentoAcaoFinanceiroMensal detalhamentoAcaoFinanceiroMensal = new RelatorioDetalhamentoAcaoFinanceiroMensal();
		for(Mes mes: meses) {
			
			BigDecimal valor = dao().calculaPlanejamentoMensalByMesAndExercicioAndAcao(mes.getId(), exercicio,acao);
			
			detalhamentoAcaoFinanceiroMensal.setValor(mes.getNumeroMes(), valor);
		}
		
		
		return detalhamentoAcaoFinanceiroMensal;
	}
	
	
}
