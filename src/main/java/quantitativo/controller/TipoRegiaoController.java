package quantitativo.controller;

import java.util.List;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import quantitativo.dao.TipoRegiaoDAO;
import quantitativo.model.TipoRegiao;

public class TipoRegiaoController extends AbstractController<TipoRegiao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public TipoRegiaoController(TipoRegiaoDAO dao) {
		super(dao);

	}

	public List<TipoRegiao> findAllOrderByDescricao() {

		return ((TipoRegiaoDAO) getDao()).findAllOrderByDescricao();
	}

}
