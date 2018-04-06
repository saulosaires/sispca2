package administrativo.controller;

import java.io.Serializable;

import javax.inject.Inject;

import administrativo.dao.TipoLinkDAO;
import administrativo.model.TipoLink;
import arquitetura.controller.AbstractController;

public class TipoLinkController extends AbstractController<TipoLink> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1344916015518730440L;

	@Inject
	public TipoLinkController(TipoLinkDAO tipoLinkDAO) {

		super(tipoLinkDAO);
	}

}
