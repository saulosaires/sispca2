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
import monitoramento.dao.ExecucaoDAO;
import monitoramento.model.Execucao;
import monitoramento.model.RelatorioDetalhamentoAcaoExecucaoMensal;
import qualitativo.model.Mes;

public class ExecucaoService extends AbstractService<Execucao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public ExecucaoService(ExecucaoDAO dao) {
		super(dao);
	}

	private List<RelatorioExecucao> execucaoToRelatorioExecucao(List<Execucao> execucoes){
	
		 Map<String,RelatorioExecucao> map = new HashMap<>();
		 
		 for(Execucao e : execucoes) {	
			 
			 if(map.containsKey(e.getRegiaoMunicipio().getId().toString()+e.getAcao().getCodigo())) {
				 map.get(e.getRegiaoMunicipio().getId().toString()+e.getAcao().getCodigo()).setData(e);
				 
			 }else {                                                                                    
				 map.put(e.getRegiaoMunicipio().getId().toString()+e.getAcao().getCodigo(), new RelatorioExecucao(e));
			 }

		 }
		 
		 List<RelatorioExecucao> list = new ArrayList<>(map.size());

		 list.addAll(map.values());
		 
		 list.sort((RelatorioExecucao o1, RelatorioExecucao o2)-> o1.getRegiaoMunicipioId().compareTo(o2.getRegiaoMunicipioId()));
		  
		 
		return list;
		
	}
	
	public  List<RelatorioExecucao>   findByAcaoAndExercicio(Long acaoId,Long exercicioId) {
 		 
		 return execucaoToRelatorioExecucao(((ExecucaoDAO) getDAO()).findByAcaoAndExercicio(acaoId,exercicioId));
	}

	public List<RelatorioExecucao> relatorioMonitoramento(Long exercicio,List<Long> orgaoId, Long unidadeOrcamentaria,Long programa,Long acao,Long tipoRegiao,Long regiao,Long regiaoMunicipio) {
		
		return execucaoToRelatorioExecucao(
										   ((ExecucaoDAO) getDAO()).relatorioMonitoramento(exercicio, 
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

		return ((ExecucaoDAO) getDAO()).findByAcaoAndRegiaoAndExercicioAndMes(acaoId,regiaoMunicipioId,exercicioId,mesId);
	}

	public  BigDecimal  findTotalValorExecutadoByAcao(Long unidadeGestoraId, Long unidadeOrcamentariaId, Long acaoId,Long exercicioVigenteId, Long mesId) {

		return ((ExecucaoDAO) getDAO()).findTotalValorExecutadoByAcao(unidadeGestoraId, unidadeOrcamentariaId, acaoId,exercicioVigenteId, mesId);
	}
	
	public RelatorioDetalhamentoAcaoExecucaoMensal calculaPlanejamentoMensalByMesAndExercicioAndAcao(List<Mes> meses, Long exercicio,Long acao){
		
		RelatorioDetalhamentoAcaoExecucaoMensal detalhamentoAcaoExecucaoMensal = new RelatorioDetalhamentoAcaoExecucaoMensal();
		for(Mes mes: meses) {
			
			BigDecimal valor = ((ExecucaoDAO) getDAO()).calculaExecutadoMensalByMesAndExercicioAndAcao(mes.getId(), exercicio,acao);
			
			detalhamentoAcaoExecucaoMensal.setValor(mes.getNumeroMes(), valor);
		}
		
		
		return detalhamentoAcaoExecucaoMensal;
	}

	public List<Execucao> exportarBI(Long exercicioId) {
		
		return ((ExecucaoDAO) getDAO()).exportarBI(exercicioId);
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
