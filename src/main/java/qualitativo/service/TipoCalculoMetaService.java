package qualitativo.service;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import qualitativo.dao.TipoCalculoMetaDAO;
import qualitativo.model.TipoCalculoMeta;

public class TipoCalculoMetaService  extends AbstractService<TipoCalculoMeta> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2854975367734660857L;
 

	@Inject
	public TipoCalculoMetaService(TipoCalculoMetaDAO dao) {
		super(dao);
	}
 

	 
}
