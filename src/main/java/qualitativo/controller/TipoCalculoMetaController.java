package qualitativo.controller;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import qualitativo.dao.TipoCalculoMetaDAO;
import qualitativo.model.TipoCalculoMeta;

public class TipoCalculoMetaController  extends AbstractController<TipoCalculoMeta>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1344916015518730440L;
 
	@Inject
	public TipoCalculoMetaController(TipoCalculoMetaDAO dao) {

		super(dao);
	}

 
	 
	

}
