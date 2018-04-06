package qualitativo.controller;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import qualitativo.dao.TipoOrcamentoDAO;
import qualitativo.model.TipoOrcamento;

public class TipoOrcamentoController  extends AbstractController<TipoOrcamento>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1344916015518730440L;
 
	@Inject
	public TipoOrcamentoController(TipoOrcamentoDAO dao) {

		super(dao);
	}

 
	 
	

}
