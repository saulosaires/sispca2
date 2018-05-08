package administrativo.beans.link;

import java.io.IOException;
import java.io.Serializable;

import org.primefaces.model.UploadedFile;

import administrativo.model.Link;
import arquitetura.interfaces.Validate;
import arquitetura.utils.FileUtil;
import arquitetura.utils.Messages;
import arquitetura.utils.Utils;

public class LinkValidate implements Validate<Link>,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6174506586329370046L;
	public static final String FILE_REQUIRED_MSG 	   = "Arquivo é um campo obrigatório";
	public static final String URL_REQUIRED_MSG 	   = "Url é um campo obrigatório";
	public static final String TITULO_REQUIRED_MSG 	   = "Título é um campo obrigatório";

	private static final String TIPO_LINK_EXTERNO = "EXTERNO";
	private static final String TIPO_LINK_ARQUIVO = "ARQUIVO";
	
	@Override
	public boolean validar(Link link) {

		if (TIPO_LINK_EXTERNO.equals(link.getTipoLink().getDescricao()) && Utils.emptyParam(link.getUrl())) {

			Messages.addMessageError(URL_REQUIRED_MSG);
			return false;

		}else if (TIPO_LINK_ARQUIVO.equals(link.getTipoLink().getDescricao()) && link.getContent()==null) {

			Messages.addMessageError(FILE_REQUIRED_MSG);
			return false;

		}
 
		if (Utils.emptyParam(link.getTitulo())) {
			Messages.addMessageError(TITULO_REQUIRED_MSG);
			return false;
		}

		return true;
	}
	
	
	public void beforeMerge(Link link) throws IOException {

		  if (TIPO_LINK_ARQUIVO.equals(link.getTipoLink().getDescricao()) ) {
			
			  String url = FileUtil.uploadArquivo(link.getContent(), link.getMime(), link.getFilename(), link.getExtension());
			  
			  link.setUrl(url);
	
		}else {
			link.setContent(null);
			link.setMime(null);
			link.setFilename(null);
		}

	}


}
