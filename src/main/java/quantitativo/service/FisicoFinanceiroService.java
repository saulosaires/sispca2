package quantitativo.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;
import quantitativo.controller.FisicoFinanceiroController;
import quantitativo.model.FisicoFinanceiro;

public class FisicoFinanceiroService extends AbstractService<FisicoFinanceiro> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public FisicoFinanceiroService(FisicoFinanceiroController controller) {
		super(controller);
	}

}
