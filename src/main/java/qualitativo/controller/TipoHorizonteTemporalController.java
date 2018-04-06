package qualitativo.controller;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import qualitativo.dao.TipoHorizonteTemporalDAO;
import qualitativo.model.TipoHorizonteTemporal;

public class TipoHorizonteTemporalController  extends AbstractController<TipoHorizonteTemporal>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1344916015518730440L;
 
	@Inject
	public TipoHorizonteTemporalController(TipoHorizonteTemporalDAO dao) {

		super(dao);
	}

 
	 
	

}
