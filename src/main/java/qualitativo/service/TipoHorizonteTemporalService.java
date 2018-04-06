package qualitativo.service;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import qualitativo.controller.TipoHorizonteTemporalController;
import qualitativo.model.TipoHorizonteTemporal;

public class TipoHorizonteTemporalService  extends AbstractService<TipoHorizonteTemporal> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2854975367734660857L;
 

	@Inject
	public TipoHorizonteTemporalService(TipoHorizonteTemporalController controller) {
		super(controller);
	}
 

	 
}
