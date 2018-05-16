package administrativo.beans.link;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import administrativo.model.Link;
import administrativo.service.LinkService;
import arquitetura.utils.Messages;
import arquitetura.utils.SessionUtils;
import arquitetura.utils.SispcaLogger;

@Named
@ViewScoped
public class LinkListMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5940179508090218836L;

	public static final String FAIL_DELETE_LINK_MSG = "Falha inesperada ao tentar Deletar Link";
	public static final String SUCCESS_DELETE_LINK_MSG = "Link deletado com Sucesso";
	public static final String FAIL_SEARCH = "Falha ao pesquisar Links";

	private String titulo;
	private String url;
	private List<Link> listLinks;

	private LinkService linkService;
	
	private boolean linkAtualizar;
	private boolean linkDeletar;
	private boolean linkSalvar;
	
	@Inject
	public LinkListMBean(LinkService linkService) {
		this.linkService = linkService;
		
		linkAtualizar = SessionUtils.containsKey("linkAtualizar"); 
		linkDeletar   = SessionUtils.containsKey("linkDeletar");
		linkSalvar    = SessionUtils.containsKey("linkSalvar");
	}

	public void queryLinkByDescricaoAndURL() {

		try {
			listLinks = linkService.queryLinkByDescricaoAndURL(titulo, url);

		} catch (Exception e) { 
			SispcaLogger.logError(e);

			Messages.addMessageError(FAIL_SEARCH);

		}
	}

	public String deletar(Link link) {

		try {

			linkService.delete(link);

			Messages.addMessageInfo(SUCCESS_DELETE_LINK_MSG);

		} catch (Exception e) {
			SispcaLogger.logError(e);

			Messages.addMessageError(FAIL_DELETE_LINK_MSG);

		}

		queryLinkByDescricaoAndURL();

		return "";

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

	public boolean isLinkAtualizar() {
		return linkAtualizar;
	}

	public void setLinkAtualizar(boolean linkAtualizar) {
		this.linkAtualizar = linkAtualizar;
	}

	public boolean isLinkDeletar() {
		return linkDeletar;
	}

	public void setLinkDeletar(boolean linkDeletar) {
		this.linkDeletar = linkDeletar;
	}

	public boolean isLinkSalvar() {
		return linkSalvar;
	}

	public void setLinkSalvar(boolean linkSalvar) {
		this.linkSalvar = linkSalvar;
	}

	
	
}
