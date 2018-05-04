package qualitativo.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;
import qualitativo.controller.AcaoController;
import qualitativo.model.Acao;

public class AcaoService  extends AbstractService<Acao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2854975367734660857L;
 

	@Inject
	public AcaoService(AcaoController acaoController) {
		super(acaoController);
	}

	public List<Acao> buscar(String codigo, String denominacao,Long unidadeOrcamentariaId,Long programaId) {

		if (Utils.emptyParam(codigo) && Utils.emptyParam(denominacao) &&Utils.invalidId(unidadeOrcamentariaId) && Utils.invalidId(programaId)) {
			return findAll();
		} else {
			return ((AcaoController) getController()).buscar(codigo, denominacao, unidadeOrcamentariaId, programaId);
		}

	}

	public List<Acao> buscarByExercicio(Long exercicioId) {

		if (Utils.invalidId(exercicioId)) {
			return new ArrayList<>();
		} else {
			return ((AcaoController) getController()).buscarByExercicio( exercicioId);
		}

	}

	public List<Acao> buscarByUnidadeOrcamentaria(Long unidadeOrcamentariaId) {

		if (Utils.invalidId(unidadeOrcamentariaId)) {
			return new ArrayList<>();
		} else {
			return ((AcaoController) getController()).buscarByUnidadeOrcamentaria( unidadeOrcamentariaId);
		}

	}	
	
}
