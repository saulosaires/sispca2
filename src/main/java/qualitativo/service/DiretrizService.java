package qualitativo.service;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import qualitativo.controller.DiretrizController;
import qualitativo.model.Diretriz;

public class DiretrizService  extends AbstractService<Diretriz> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2854975367734660857L;
 

	@Inject
	public DiretrizService(DiretrizController controller) {
		super(controller);
	}

	 

	 
}
