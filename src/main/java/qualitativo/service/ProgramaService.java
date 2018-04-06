package qualitativo.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import qualitativo.controller.ProgramaController;
import qualitativo.model.Programa;

public class ProgramaService extends AbstractService<Programa>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2854975367734660857L;
 
	@Inject
	public ProgramaService(ProgramaController controller) {
		super(controller);
	}

	public List<Programa> findAllOrderByDenominacao() {
		
		return((ProgramaController)getController()).findAllOrderByDenominacao();
	}

}
