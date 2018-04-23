package qualitativo.controller;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import qualitativo.dao.DiretrizDAO;
import qualitativo.model.Diretriz;

public class DiretrizController  extends AbstractController<Diretriz>{

  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public DiretrizController(DiretrizDAO dao) {

		super(dao);
	}

 
	
 
	

}
