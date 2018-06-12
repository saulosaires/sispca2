package administrativo.service;

import java.util.List;

import javax.inject.Inject;

import administrativo.dao.MensagemDAO;
import administrativo.model.Mensagem;
import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;

public class MensagemService extends AbstractService<Mensagem> {

 
	/**
	 * 
	 */
	private static final long serialVersionUID = -156314605441094680L;
	
 
	@Inject
	public MensagemService( MensagemDAO dao) {
		super(dao);
	}
 



	public List<Mensagem> queryByTituloAndTexto(String titulo, String texto) {

		if (Utils.emptyParam(titulo) && Utils.emptyParam(texto)) {
			return findAll();
		} else {
			return ((MensagemDAO) getDAO()).queryByTituloAndTexto(titulo, texto);
		}
		
	}

}
