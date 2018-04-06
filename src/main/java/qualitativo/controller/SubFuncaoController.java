package qualitativo.controller;

import java.util.List;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import qualitativo.dao.SubFuncaoDAO;
import qualitativo.model.SubFuncao;

public class SubFuncaoController extends AbstractController<SubFuncao>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1344916015518730440L;

	@Inject
	public SubFuncaoController(SubFuncaoDAO dao) {
		super(dao);
		 
	}

	public List<SubFuncao> findAllOrderByCodigo() {
	
		return((SubFuncaoDAO)getDao()).findAllOrderByCodigo();
	}
 
	

}
