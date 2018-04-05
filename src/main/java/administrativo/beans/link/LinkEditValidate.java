package administrativo.beans.link;

import java.io.Serializable;

import administrativo.model.Link;
import arquitetura.interfaces.Validate;
import arquitetura.utils.Messages;
import arquitetura.utils.Utils;

public class LinkEditValidate implements Validate<Link>,Serializable{

	public static final String URL_REQUIRED_MSG 	   = "Url é um campo obrigatório";
	public static final String TITULO_REQUIRED_MSG 	   = "Título é um campo obrigatório";

	private static final String TIPO_LINK_EXTERNO = "EXTERNO";

	
	@Override
	public boolean validar(Link link) {

		if (TIPO_LINK_EXTERNO.equals(link.getTipoLink().getDescricao()) && Utils.emptyParam(link.getUrl())) {

			Messages.addMessageError(URL_REQUIRED_MSG);
			return false;

		}

		if (Utils.emptyParam(link.getTitulo())) {
			Messages.addMessageError(TITULO_REQUIRED_MSG);
			return false;
		}

		return true;
	}

}
