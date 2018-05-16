package administrativo.beans.link;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import administrativo.controller.TipoLinkController;
import administrativo.model.Link;
import administrativo.model.TipoLink;
import administrativo.service.LinkService;
import arquitetura.utils.Messages;
import arquitetura.utils.SispcaLogger;
import arquitetura.utils.Utils;

@Named
@ViewScoped
public class LinkEditMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5940179508090218836L;
 	
 	public static final String FAIL_UPDATE_LINK_MSG    = "Falha inesperada ao tentar Atualizar Link";

 	public static final String SUCCESS_UPDATE_LINK_MSG   = "Link atualizado com Sucesso";
	

 	
	private Long linkId;

	private Link link = new Link();

	private List<TipoLink> listTipoLink;

 

	private LinkService linkService;
	private LinkValidate linkEditValidate;


	@Inject
	public LinkEditMBean(LinkService linkService, TipoLinkController tipoLinkController,LinkValidate linkEditValidate) {
		this.linkService = linkService;
		this.linkEditValidate=linkEditValidate;
		
		listTipoLink = tipoLinkController.findAll();
		link.setTipoLink(listTipoLink.get(0));

	}

	public void init() {

		if (!Utils.invalidId(linkId)) {

			link = linkService.findById(linkId);

		}

	}

	public void handleFileUpload(FileUploadEvent event) {
		link.setContent(event.getFile().getContents());
		link.setMime(event.getFile().getContentType());
		link.setFilename(FilenameUtils.getBaseName(event.getFile().getFileName()));
		link.setExtension(FilenameUtils.getExtension(event.getFile().getFileName()));
	}

 
	public String atualizar() {

		try {

			if (!linkEditValidate.validar(link)) {
				return "";
			}

			linkEditValidate.beforeMerge(link);

			link=linkService.update(link);

			Messages.addMessageInfo(SUCCESS_UPDATE_LINK_MSG);
			
			return "linksArquivosList";
			
		} catch (Exception e) {
			SispcaLogger.logError(e);

			Messages.addMessageError(FAIL_UPDATE_LINK_MSG);
		}

		return "";
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

	 

}
