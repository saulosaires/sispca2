package siafem.service;

import java.math.BigDecimal;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import siafem.controller.FisicoFinanceiroMensalSiafemController;
import siafem.model.FisicoFinanceiroMensalSiafem;

public class FisicoFinanceiroMensalSiafemService extends AbstractService<FisicoFinanceiroMensalSiafem> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public FisicoFinanceiroMensalSiafemService(FisicoFinanceiroMensalSiafemController controller) {
		super(controller);
	}


	public BigDecimal calculaDotacaoInicialPorUoProg(Long programaId, Integer anoVigente){
		
		return ((FisicoFinanceiroMensalSiafemController)getController()).calculaDotacaoInicialPorUoProg(programaId, anoVigente);
	}
	
	public BigDecimal calculaDotacaoAtualPorUoProg(Long programaId, Integer anoVigente){
		
		return ((FisicoFinanceiroMensalSiafemController)getController()).calculaDotacaoAtualPorUoProg(programaId, anoVigente);
	}	
}
