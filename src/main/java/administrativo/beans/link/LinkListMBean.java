package administrativo.beans.link;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.model.Link;
import administrativo.service.LinkService;

@Named
@ViewScoped
public class LinkListMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5940179508090218836L;

	private String titulo;
	private String url;
	private List<Link> listLinks;

	private LinkService linkService;

	@Inject
	public LinkListMBean(LinkService linkService) {
		this.linkService = linkService;
	}

	public void queryLinkByDescricaoAndURL() {

		
		listLinks = linkService.queryLinkByDescricaoAndURL(titulo, url);
		
	}

	public void delete(Link link) {
		
	}
	
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Link> getListLinks() {
		return listLinks;
	}

	public void setListLinks(List<Link> listLinks) {
		this.listLinks = listLinks;
	}

}
