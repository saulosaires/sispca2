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
	
	
	public List<Link> queryLinkByDescricaoAndURL(String titulo, String url){
		
		return linkDAO.queryLinkByDescricaoAndURL(titulo, url);
	}
	

}
