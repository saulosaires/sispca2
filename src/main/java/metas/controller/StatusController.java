package metas.controller;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import metas.dao.StatusDAO;
import metas.model.Status;

public class StatusController extends AbstractController<Status>{
 
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1l;
 

	@Inject
	public StatusController(StatusDAO dao) {

		super(dao);
	}

	 
 

}
