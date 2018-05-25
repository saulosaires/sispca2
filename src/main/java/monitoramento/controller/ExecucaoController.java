package monitoramento.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import monitoramento.dao.ExecucaoDAO;
import monitoramento.model.Execucao;

public class ExecucaoController extends AbstractController<Execucao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public ExecucaoController(ExecucaoDAO dao) {
		super(dao);

	}
 
	public  List<Execucao>  findByAcaoAndExercicio(Long acaoId,Long exercicioId) {

		return ((ExecucaoDAO) getDao()).findByAcaoAndExercicio(acaoId,exercicioId);
	}

	
	public  Optional<Execucao>  findByAcaoAndRegiaoAndExercicioAndMes(Long acaoId,Long regiaoMunicipioId,Long exercicioId,Long mesId) {

		return ((ExecucaoDAO) getDao()).findByAcaoAndRegiaoAndExercicioAndMes(acaoId,regiaoMunicipioId,exercicioId,mesId);
	}
	
	public  Double  findTotalValorExecutadoByAcao(Long unidadeGestoraId, Long unidadeOrcamentariaId, Long acaoId,Long exercicioVigenteId, Long mesId) {

		return ((ExecucaoDAO) getDao()).findTotalValorExecutadoByAcao(unidadeGestoraId, unidadeOrcamentariaId, acaoId,exercicioVigenteId, mesId);
	}

	public Double calculaExecutadoMensalByMesAndExercicioAndAcao(Long mes, Long exercicio,Long acao){

		return ((ExecucaoDAO) getDao()).calculaExecutadoMensalByMesAndExercicioAndAcao(mes, exercicio, acao);
		
	}
	
}
