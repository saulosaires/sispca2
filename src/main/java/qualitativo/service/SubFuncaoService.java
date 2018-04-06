package qualitativo.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import qualitativo.controller.SubFuncaoController;
import qualitativo.model.SubFuncao;

public class SubFuncaoService extends AbstractService<SubFuncao>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2854975367734660857L;

	@Inject
	public SubFuncaoService(SubFuncaoController controller) {
		super(controller);
	}

	public List<SubFuncao> findAllOrderByCodigo() {

		return ((SubFuncaoController) getController()).findAllOrderByCodigo();
	}

}
