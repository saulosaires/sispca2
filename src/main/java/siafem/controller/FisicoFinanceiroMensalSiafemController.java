package siafem.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import administrativo.model.Exercicio;
import arquitetura.controller.AbstractController;
import qualitativo.model.Programa;
import siafem.dao.FisicoFinanceiroMensalSiafemDAO;
import siafem.model.FisicoFinanceiroMensalSiafem;

public class FisicoFinanceiroMensalSiafemController extends AbstractController<FisicoFinanceiroMensalSiafem> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public FisicoFinanceiroMensalSiafemController(FisicoFinanceiroMensalSiafemDAO dao) {
		super(dao);

	}

	public BigDecimal calculaDotacaoInicialPorUoProg(Long programaId, Integer anoVigente){
		
		return ((FisicoFinanceiroMensalSiafemDAO)getDao()).calculaDotacaoInicialPorUoProg(programaId, anoVigente);
	}
	
	
	public BigDecimal calculaDotacaoAtualPorUoProg(Long programaId, Integer anoVigente){
		
		return ((FisicoFinanceiroMensalSiafemDAO)getDao()).calculaDotacaoAtualPorUoProg(programaId, anoVigente);
	}

	
	public List<FisicoFinanceiroMensalSiafem> analiseFisicoFinanceiro(Programa programa, Exercicio exercicio){
		
		return ((FisicoFinanceiroMensalSiafemDAO)getDao()).analiseFisicoFinanceiro(programa,exercicio);
	}

}
