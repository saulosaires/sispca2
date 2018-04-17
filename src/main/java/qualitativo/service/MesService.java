package qualitativo.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import qualitativo.controller.MesController;
import qualitativo.model.Mes;

public class MesService extends AbstractService<Mes> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public MesService(MesController controller) {
		super(controller);
	}

	public List<Mes> findallOrderById() {

		return ((MesController) getController()).findallOrderById();
	}

}
