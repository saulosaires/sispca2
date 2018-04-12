package quantitativo.controller;

import java.util.List;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import quantitativo.dao.RegiaoDAO;
import quantitativo.model.Regiao;

public class RegiaoController extends AbstractController<Regiao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public RegiaoController(RegiaoDAO dao) {
		super(dao);

	}
	
	public List<Regiao> findAllOrderByDescricao() {

		return ((RegiaoDAO) getDao()).findAllOrderByDescricao();
	}


	public List<Regiao> findAllByTipoRegiao(Long tipoRegiaoId) {

		return ((RegiaoDAO) getDao()).findAllByTipoRegiao(tipoRegiaoId);
	}

}
