package administrativo.service;

import javax.inject.Inject;

import administrativo.dao.TipoLinkDAO;
import administrativo.model.TipoLink;
import arquitetura.service.AbstractService;

public class TipoLinkService extends AbstractService<TipoLink> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public TipoLinkService(TipoLinkDAO dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}

}
