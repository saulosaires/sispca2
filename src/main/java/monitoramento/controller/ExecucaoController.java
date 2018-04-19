package monitoramento.controller;

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
 
	public  Optional<Execucao>  findByAcaoAndRegiaoAndExercicioAndMes(Long acaoId,Long regiaoMunicipioId,Long exercicioId,Long mesId) {

		return ((ExecucaoDAO) getDao()).findByAcaoAndRegiaoAndExercicioAndMes(acaoId,regiaoMunicipioId,exercicioId,mesId);
	}

}
