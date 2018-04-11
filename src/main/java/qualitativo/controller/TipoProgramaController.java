package qualitativo.controller;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import qualitativo.dao.TipoProgramaDAO;
import qualitativo.model.TipoPrograma;

public class TipoProgramaController extends AbstractController<TipoPrograma> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public TipoProgramaController(TipoProgramaDAO dao) {
		super(dao);

	}

	 

}
