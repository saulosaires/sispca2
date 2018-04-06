package administrativo.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import administrativo.controller.LinkController;
import administrativo.model.Link;
import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;

public class LinkService extends AbstractService<Link> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2854975367734660857L;
 

	@Inject
	public LinkService(LinkController linkController) {
		super(linkController);
	}

	public List<Link> queryLinkByDescricaoAndURL(String titulo, String url) {

		if (Utils.emptyParam(titulo) && Utils.emptyParam(url)) {
			return findAll();
		} else {
			return ((LinkController) getController()).queryLinkByDescricaoAndURL(titulo, url);
		}

	}

 
}
