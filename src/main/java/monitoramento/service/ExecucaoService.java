package monitoramento.service;

import java.util.Optional;

import javax.inject.Inject;

import arquitetura.exception.JpaException;
import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;
import monitoramento.controller.ExecucaoController;
import monitoramento.model.Execucao;

public class ExecucaoService extends AbstractService<Execucao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public ExecucaoService(ExecucaoController controller) {
		super(controller);
	}

	public  Optional<Execucao>  findByAcaoAndRegiaoAndExercicioAndMes(Long acaoId,Long regiaoMunicipioId,Long exercicioId,Long mesId) {

		return ((ExecucaoController) getController()).findByAcaoAndRegiaoAndExercicioAndMes(acaoId,regiaoMunicipioId,exercicioId,mesId);
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
