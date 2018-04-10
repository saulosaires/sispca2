package administrativo.beans.link;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import administrativo.controller.TipoLinkController;
import administrativo.model.Link;
import administrativo.model.TipoLink;
import administrativo.service.LinkService;
import arquitetura.utils.Messages;
import arquitetura.utils.SispcaLogger;

@Named
@ViewScoped
public class LinkFormMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5940179508090218836L;

 	
	public static final String FAIL_SAVE_LINK_MSG      = "Falha inesperada ao tentar Salvar Link"; 
	public static final String SUCCESS_SAVE_LINK_MSG     = "Link salvo com Sucesso"; 
 	 
	private Link link = new Link();

	private List<TipoLink> listTipoLink;

	private transient UploadedFile arquivo;

	private LinkService linkService;
	private LinkValidate linkEditValidate;


	@Inject
	public LinkFormMBean(LinkService linkService, TipoLinkController tipoLinkController,LinkValidate linkEditValidate) {
		this.linkService = linkService;
		this.linkEditValidate=linkEditValidate;
		
		listTipoLink = tipoLinkController.findAll();
		link.setTipoLink(listTipoLink.get(0));

	}

	 

	public void handleFileUpload(FileUploadEvent event) {
		arquivo = event.getFile();
	}

	public String salvar() {

		try {

			if (!linkEditValidate.validar(link)) {
				return "";
			}

			linkEditValidate.beforeMerge(link,arquivo);

			linkService.create(link);

			Messages.addMessageInfo(SUCCESS_SAVE_LINK_MSG);
			
			return "linksArquivosList";
			
		} catch (Exception e) {
			SispcaLogger.logError(e.getLocalizedMessage());

			Messages.addMessageError(FAIL_SAVE_LINK_MSG);
		}

		return "";
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
