package administrativo.service;

import java.util.List;

import javax.inject.Inject;

import administrativo.dao.LinkDAO;
import administrativo.model.Link;
import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;

public class LinkService extends AbstractService<Link> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2854975367734660857L;
 

	@Inject
	public LinkService(LinkDAO dao) {
		super(dao);
	}

	public List<Link> queryLinkByDescricaoAndURL(String titulo, String url) {

		if (Utils.emptyParam(titulo) && Utils.emptyParam(url)) {
			return findAll();
		} else {
			return ((LinkDAO) getDAO()).queryLinkByDescricaoAndURL(titulo, url);
		}

	}

 
}
