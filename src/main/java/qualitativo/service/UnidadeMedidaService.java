package qualitativo.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import qualitativo.controller.UnidadeMedidaController;
import qualitativo.model.UnidadeMedida;

public class UnidadeMedidaService extends AbstractService<UnidadeMedida> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2854975367734660857L;

	@Inject
	public UnidadeMedidaService(UnidadeMedidaController controller) {
		super(controller);
	}

	public List<UnidadeMedida> findAllOrderByDecricao() {

		return ((UnidadeMedidaController) getController()).findAllOrderByDescricao();
	}

}