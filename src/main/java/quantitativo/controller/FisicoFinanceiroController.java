package quantitativo.controller;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import quantitativo.dao.FisicoFinanceiroDAO;
import quantitativo.model.FisicoFinanceiro;

public class FisicoFinanceiroController extends AbstractController<FisicoFinanceiro> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public FisicoFinanceiroController(FisicoFinanceiroDAO dao) {
		super(dao);

	}

}
