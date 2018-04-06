package qualitativo.controller;

import java.util.List;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import qualitativo.dao.TipoAcaoDAO;
import qualitativo.model.TipoAcao;

public class TipoAcaoController extends AbstractController<TipoAcao>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1344916015518730440L;

	@Inject
	public TipoAcaoController(TipoAcaoDAO dao) {
		super(dao);
		 
	}

	public List<TipoAcao> findAllOrderByDescricao() {
	
		return((TipoAcaoDAO)getDao()).findAllOrderByDescricao();
	}
 
	

}
