package administrativo.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import administrativo.controller.LinkController;
import administrativo.model.Link;
import arquitetura.utils.Utils;

public class LinkService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2854975367734660857L;

	private LinkController linkController;

	@Inject
	public LinkService(LinkController linkController) {
		this.linkController = linkController;
	}

	public List<Link> queryLinkByDescricaoAndURL(String titulo, String url) {

		if (Utils.emptyParam(titulo) && Utils.emptyParam(url)) {
			return linkController.findAll();
		} else {
			return linkController.queryLinkByDescricaoAndURL(titulo, url);
		}

	}

	public Link findById(Long id) {

		return linkController.findById(id);

	}

	public void create(Link link) {

		linkController.create(link);

	}

	public Link update(Link link) {

		return linkController.update(link);

	}

	public Link delete(Link link) {

		return linkController.delete(link);

	}

}
