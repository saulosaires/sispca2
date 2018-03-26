package administrativo.beans.link;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import administrativo.controller.TipoLinkController;
import administrativo.model.Link;
import administrativo.model.TipoLink;
import administrativo.service.LinkService;
import arquitetura.utils.Utils;

@Named
@ViewScoped
public class LinkFormMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5940179508090218836L;

	private Long linkId;

	private Link link;

	private List<TipoLink> listTipoLink;

	private transient UploadedFile arquivo;
	
	private LinkService linkService;
 
	private static final  String OUTCOME_UPDATE="linksArquivosUpdate";

	@Inject
	public LinkFormMBean(LinkService linkService, TipoLinkController tipoLinkController) {
		this.linkService = linkService;

		listTipoLink = tipoLinkController.findAll();

		link = new Link();
	}

	public void init() {

		if (!Utils.invalidId(linkId)) {

			link = linkService.findById(linkId);

		}

	}
	
	public String salvar() {
		
		return OUTCOME_UPDATE;
	}
	
	public String atualizar() {
		
		return OUTCOME_UPDATE;
	}

	public Long getLinkId() {
		return linkId;
	}

	public void setLinkId(Long linkId) {
		this.linkId = linkId;
	}

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
	}

	public List<TipoLink> getListTipoLink() {
		return listTipoLink;
	}

	public void setListTipoLink(List<TipoLink> listTipoLink) {
		this.listTipoLink = listTipoLink;
	}

	public UploadedFile getArquivo() {
		return arquivo;
	}

	public void setArquivo(UploadedFile arquivo) {
		this.arquivo = arquivo;
	}
	
	
	

}
