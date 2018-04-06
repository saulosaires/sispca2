package administrativo.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import administrativo.dao.LinkDAO;
import administrativo.model.Link;
import arquitetura.controller.AbstractController;

public class LinkController extends AbstractController<Link> implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1344916015518730440L;
 
	
	@Inject
	LinkController(LinkDAO linkDAO) {
		super(linkDAO);
		 
	}
 
	
	public List<Link> queryLinkByDescricaoAndURL(String titulo, String url){
		
		return ((LinkDAO) getDao()).queryLinkByDescricaoAndURL(titulo, url);
	}
	

}
