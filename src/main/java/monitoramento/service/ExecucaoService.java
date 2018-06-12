package monitoramento.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;

import arquitetura.exception.JpaException;
import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;
import monitoramento.beans.fisicofinanceiro.mensal.RelatorioExecucao;
import monitoramento.controller.ExecucaoController;
import monitoramento.model.Execucao;
import monitoramento.model.RelatorioDetalhamentoAcaoExecucaoMensal;
import qualitativo.model.Mes;

public class ExecucaoService extends AbstractService<Execucao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public ExecucaoService(ExecucaoController controller) {
		super(controller);
	}

	private List<RelatorioExecucao> execucaoToRelatorioExecucao(List<Execucao> execucoes){
	
		 Map<Long,RelatorioExecucao> map = new HashMap<>();
		 
		 for(Execucao e : execucoes) {	
			 
			 if(map.containsKey(e.getRegiaoMunicipio().getId())) {
				 map.get(e.getRegiaoMunicipio().getId()).setData(e);
				 
			 }else {                                                                                    
				 map.put(e.getRegiaoMunicipio().getId(), new RelatorioExecucao(e));
			 }

		 }
		 
		 List<RelatorioExecucao> list = new ArrayList<>(map.size());

		 list.addAll(map.values());
		 
		 list.sort((RelatorioExecucao o1, RelatorioExecucao o2)-> o1.getRegiaoMunicipioId().compareTo(o2.getRegiaoMunicipioId()));
		  
		 
		return list;
		
	}
	
	public  List<RelatorioExecucao>   findByAcaoAndExercicio(Long acaoId,Long exercicioId) {
 		 
		 return execucaoToRelatorioExecucao(((ExecucaoController) getController()).findByAcaoAndExercicio(acaoId,exercicioId));
	}

	public List<RelatorioExecucao> relatorioMonitoramento(Long exercicio,List<Long> orgaoId, Long unidadeOrcamentaria,Long programa,Long acao,Long tipoRegiao,Long regiao,Long regiaoMunicipio) {
		
		return execucaoToRelatorioExecucao(
										   ((ExecucaoController) getController()).relatorioMonitoramento(exercicio, 
																										 orgaoId, 
																										 unidadeOrcamentaria, 
																										 programa, 
																										 acao, 
																										 tipoRegiao, 
																										 regiao, 
																										 regiaoMunicipio)
										   );
	}
	
	
	public  Optional<Execucao>  findByAcaoAndRegiaoAndExercicioAndMes(Long acaoId,Long regiaoMunicipioId,Long exercicioId,Long mesId) {

		return ((ExecucaoController) getController()).findByAcaoAndRegiaoAndExercicioAndMes(acaoId,regiaoMunicipioId,exercicioId,mesId);
	}

	public  BigDecimal  findTotalValorExecutadoByAcao(Long unidadeGestoraId, Long unidadeOrcamentariaId, Long acaoId,Long exercicioVigenteId, Long mesId) {

		return ((ExecucaoController) getController()).findTotalValorExecutadoByAcao(unidadeGestoraId, unidadeOrcamentariaId, acaoId,exercicioVigenteId, mesId);
	}
	
	public RelatorioDetalhamentoAcaoExecucaoMensal calculaPlanejamentoMensalByMesAndExercicioAndAcao(List<Mes> meses, Long exercicio,Long acao){
		
		RelatorioDetalhamentoAcaoExecucaoMensal detalhamentoAcaoExecucaoMensal = new RelatorioDetalhamentoAcaoExecucaoMensal();
		for(Mes mes: meses) {
			
			BigDecimal valor = ((ExecucaoController) getController()).calculaExecutadoMensalByMesAndExercicioAndAcao(mes.getId(), exercicio,acao);
			
			detalhamentoAcaoExecucaoMensal.setValor(mes.getNumeroMes(), valor);
		}
		
		
		return detalhamentoAcaoExecucaoMensal;
	}

	
	
	public Execucao merge(Execucao execucao) throws JpaException {
		
		if(execucao==null)return execucao;
		
		if(Utils.invalidId(execucao.getId())) {
			return create(execucao);
		}else {
			return update(execucao);
		}
		
	}
	
	
}
