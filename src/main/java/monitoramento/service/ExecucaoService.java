package monitoramento.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import arquitetura.exception.JpaException;
import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;
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

	public  List<Execucao>  findByAcaoAndExercicio(Long acaoId,Long exercicioId) {

		return ((ExecucaoController) getController()).findByAcaoAndExercicio(acaoId,exercicioId);
	}

	
	public  Optional<Execucao>  findByAcaoAndRegiaoAndExercicioAndMes(Long acaoId,Long regiaoMunicipioId,Long exercicioId,Long mesId) {

		return ((ExecucaoController) getController()).findByAcaoAndRegiaoAndExercicioAndMes(acaoId,regiaoMunicipioId,exercicioId,mesId);
	}

	public  Double  findTotalValorExecutadoByAcao(Long unidadeGestoraId, Long unidadeOrcamentariaId, Long acaoId,Long exercicioVigenteId, Long mesId) {

		return ((ExecucaoController) getController()).findTotalValorExecutadoByAcao(unidadeGestoraId, unidadeOrcamentariaId, acaoId,exercicioVigenteId, mesId);
	}
	
	public RelatorioDetalhamentoAcaoExecucaoMensal calculaPlanejamentoMensalByMesAndExercicioAndAcao(List<Mes> meses, Long exercicio,Long acao){
		
		RelatorioDetalhamentoAcaoExecucaoMensal detalhamentoAcaoExecucaoMensal = new RelatorioDetalhamentoAcaoExecucaoMensal();
		for(Mes mes: meses) {
			
			Double valor = ((ExecucaoController) getController()).calculaExecutadoMensalByMesAndExercicioAndAcao(mes.getId(), exercicio,acao);
			
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
