package qualitativo.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import qualitativo.controller.TipoAcaoController;
import qualitativo.model.TipoAcao;

public class TipoAcaoService extends AbstractService<TipoAcao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2854975367734660857L;

	@Inject
	public TipoAcaoService(TipoAcaoController controller) {
		super(controller);
	}

	public List<TipoAcao> findAllOrderByDecricao() {

		return ((TipoAcaoController) getController()).findAllOrderByDescricao();
	}

}
