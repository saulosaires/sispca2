package qualitativo.controller;

import java.util.List;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import qualitativo.dao.UnidadeMedidaDAO;
import qualitativo.model.UnidadeMedida;

public class UnidadeMedidaController extends AbstractController<UnidadeMedida>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1344916015518730440L;

	@Inject
	public UnidadeMedidaController(UnidadeMedidaDAO dao) {
		super(dao);
		 
	}

	public List<UnidadeMedida> findAllOrderByDescricao() {
	
		return((UnidadeMedidaDAO)getDao()).findAllOrderByDescricao();
	}
 
	

}
