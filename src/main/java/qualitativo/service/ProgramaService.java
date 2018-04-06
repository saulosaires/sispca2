package qualitativo.service;

import java.io.Serializable;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import qualitativo.controller.ProgramaController;
import qualitativo.model.Programa;

public class ProgramaService extends AbstractService<Programa> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2854975367734660857L;
 
	@Inject
	public ProgramaService(ProgramaController controller) {
		super(controller);
	}

 

}
