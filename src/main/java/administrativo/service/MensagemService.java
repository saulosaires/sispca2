package administrativo.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import administrativo.controller.MensagemController;
import administrativo.model.Mensagem;
import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;

public class MensagemService extends AbstractService<Mensagem> implements Serializable {

 
	/**
	 * 
	 */
	private static final long serialVersionUID = -156314605441094680L;
	
 
	@Inject
	public MensagemService( MensagemController controller) {
		super(controller);
	}
 



	public List<Mensagem> queryByTituloAndTexto(String titulo, String texto) {

		if (Utils.emptyParam(titulo) && Utils.emptyParam(texto)) {
			return findAll();
		} else {
			return ((MensagemController) getController()).queryByTituloAndTexto(titulo, texto);
		}
		
	}

}
