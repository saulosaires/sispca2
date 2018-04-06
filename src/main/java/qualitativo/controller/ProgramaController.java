package qualitativo.controller;

import java.util.List;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import qualitativo.dao.ProgramaDAO;
import qualitativo.model.Programa;

public class ProgramaController extends AbstractController<Programa> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1344916015518730440L;

	@Inject
	public ProgramaController(ProgramaDAO dao) {
		super(dao);
		 
	}

	public List<Programa> findAllOrderByDenominacao() {
	
		return((ProgramaDAO)getDao()).findAllOrderByDenominacao();
	}
 
	

}
