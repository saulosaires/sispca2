package administrativo.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import administrativo.dao.LinkDAO;
import administrativo.model.Link;

public class LinkController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1344916015518730440L;

	private LinkDAO linkDAO;

	@Inject
	public LinkController(LinkDAO linkDAO) {

		this.linkDAO = linkDAO;
	}

	public Link findById(Long id) {

		return linkDAO.findOne(id);
	}
	
	public List<Link> findAll() {

		return linkDAO.findAll();
	}

	public void create(Link link) {

		linkDAO.create(link);
	}
	
	public Link update(Link link) {

		return linkDAO.update(link);
	}

	public Link delete(Link link) {

		return linkDAO.delete(link);
	}
	
	public List<Link> queryLinkByDescricaoAndURL(String titulo, String url){
		
		return linkDAO.queryLinkByDescricaoAndURL(titulo, url);
	}
	

}
