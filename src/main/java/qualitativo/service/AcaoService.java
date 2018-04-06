package qualitativo.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;
import qualitativo.controller.AcaoController;
import qualitativo.model.Acao;

public class AcaoService  extends AbstractService<Acao> implements Serializable {

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

	 
}
