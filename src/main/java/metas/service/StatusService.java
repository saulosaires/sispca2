package metas.service;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import metas.controller.StatusController;
import metas.model.Status;

public class StatusService extends AbstractService<Status> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3288174422611664897L;
 

	@Inject
	public StatusService(StatusController controller) {
		super(controller);
	}
 
 

	 
	
}
