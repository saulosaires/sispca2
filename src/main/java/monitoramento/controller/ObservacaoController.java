package monitoramento.controller;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import monitoramento.dao.ObservacaoDAO;
import monitoramento.model.Observacao;

public class ObservacaoController extends AbstractController<Observacao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public ObservacaoController(ObservacaoDAO dao) {
		super(dao);

	}
 
	 

}
