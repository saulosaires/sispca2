package qualitativo.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import qualitativo.controller.EixoController;
import qualitativo.model.Eixo;

public class EixoService extends AbstractService<Eixo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2854975367734660857L;

	@Inject
	public EixoService(EixoController controller) {
		super(controller);
	}

	public List<Eixo> findAllOrderByDecricao() {

		return ((EixoController) getController()).findAllOrderByDescricao();
	}

}
