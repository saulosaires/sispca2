package qualitativo.controller;

import java.io.Serializable;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import qualitativo.dao.ProgramaDAO;
import qualitativo.model.Programa;

public class ProgramaController extends AbstractController<Programa> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1344916015518730440L;

	@Inject
	public ProgramaController(ProgramaDAO dao) {
		super(dao);
		 
	}

 
 
	

}
