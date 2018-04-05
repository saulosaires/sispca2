package administrativo.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import administrativo.dao.MensagemDAO;
import administrativo.model.Mensagem;
import arquitetura.exception.JpaException;

public class MensagemController implements Serializable {

 
	/**
	 * 
	 */
	private static final long serialVersionUID = 6978813522637191526L;
	private MensagemDAO mensagemDAO;

	@Inject
	public MensagemController(MensagemDAO mensagemDAO) {

		this.mensagemDAO = mensagemDAO;
	}

	public Mensagem findById(Long id) {

		return mensagemDAO.findOne(id);
	}
	
	public List<Mensagem> findAll() {

		return mensagemDAO.findAll();
	}

	public void create(Mensagem mensagem) throws JpaException {

		mensagemDAO.create(mensagem);
	}
	
	public Mensagem update(Mensagem mensagem) throws JpaException {

		return mensagemDAO.update(mensagem);
	}

	public Mensagem delete(Mensagem mensagem) throws JpaException {

		return mensagemDAO.delete(mensagem);
	}

	public List<Mensagem> queryByTituloAndTexto(String titulo, String texto) {
		return mensagemDAO.queryByTituloAndTexto(titulo, texto);
	}
 
	

}
