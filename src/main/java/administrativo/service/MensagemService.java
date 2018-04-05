package administrativo.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import administrativo.controller.MensagemController;
import administrativo.model.Mensagem;
import arquitetura.exception.JpaException;
import arquitetura.utils.Utils;

public class MensagemService implements Serializable {

 
	/**
	 * 
	 */
	private static final long serialVersionUID = -156314605441094680L;
	
	private MensagemController mensagemController;

	@Inject
	public MensagemService( MensagemController mensagemController) {
		this.mensagemController = mensagemController;
	}

 

	public Mensagem findById(Long id) {

		return mensagemController.findById(id);

	}

	public void create(Mensagem mensagem) throws JpaException {

		mensagemController.create(mensagem);

	}

	public Mensagem update(Mensagem mensagem) throws JpaException {

		return mensagemController.update(mensagem);

	}

	public Mensagem delete(Mensagem mensagem) throws JpaException {

		return mensagemController.delete(mensagem);

	}



	public List<Mensagem> queryByTituloAndTexto(String titulo, String texto) {

		if (Utils.emptyParam(titulo) && Utils.emptyParam(texto)) {
			return mensagemController.findAll();
		} else {
			return mensagemController.queryByTituloAndTexto(titulo, texto);
		}
		
	}

}
