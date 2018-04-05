package administrativo.beans.link;

import java.io.IOException;
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
import arquitetura.utils.FileUtil;
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
	private static final String TIPO_LINK_ARQUIVO = "ARQUIVO";
 	
	 
	private Link link = new Link();

	private List<TipoLink> listTipoLink;

	private transient UploadedFile arquivo;

	private LinkService linkService;
	private LinkEditValidate linkEditValidate;


	@Inject
	public LinkFormMBean(LinkService linkService, TipoLinkController tipoLinkController,LinkEditValidate linkEditValidate) {
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

			beforeMerge(link);

			linkService.create(link);

			Messages.addMessageInfo(SUCCESS_SAVE_LINK_MSG);
			
			return "linksArquivosList";
			
		} catch (Exception e) {
			SispcaLogger.logError(e.getLocalizedMessage());

			Messages.addMessageError(FAIL_SAVE_LINK_MSG);
		}

		return "";
	}

	 
		
	private void beforeMerge(Link link) throws IOException {

		if (TIPO_LINK_ARQUIVO.equals(link.getTipoLink().getDescricao())) {

			String url = FileUtil.uploadArquivo(arquivo);

			link.setUrl(url);
		}

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
