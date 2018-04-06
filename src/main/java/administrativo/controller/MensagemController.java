package administrativo.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import administrativo.dao.MensagemDAO;
import administrativo.model.Mensagem;
import arquitetura.controller.AbstractController;

public class MensagemController extends AbstractController<Mensagem> implements Serializable{

 
	/**
	 * 
	 */
	private static final long serialVersionUID = 6978813522637191526L;
 
	@Inject
	public MensagemController(MensagemDAO mensagemDAO) {

		super(mensagemDAO);
	}

	 

	public List<Mensagem> queryByTituloAndTexto(String titulo, String texto) {
		return ((MensagemDAO) getDao()).queryByTituloAndTexto(titulo, texto);
	}
 
	

}
