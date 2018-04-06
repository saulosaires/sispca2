package qualitativo.controller;

import java.util.List;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import qualitativo.dao.FuncaoDAO;
import qualitativo.model.Funcao;

public class FuncaoController extends AbstractController<Funcao>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1344916015518730440L;

	@Inject
	public FuncaoController(FuncaoDAO dao) {
		super(dao);
		 
	}

	public List<Funcao> findAllOrderByCodigo() {
	
		return((FuncaoDAO)getDao()).findAllOrderByCodigo();
	}
 
	

}
