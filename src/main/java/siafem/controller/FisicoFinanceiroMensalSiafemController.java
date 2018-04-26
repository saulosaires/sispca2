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

	public BigDecimal calculaDotacaoInicialPorUoProg(String programaCodigo, Integer anoVigente){	
		return ((FisicoFinanceiroMensalSiafemDAO)getDao()).calculaDotacaoInicialPorUoProg(programaCodigo, anoVigente);
	}
	
	public BigDecimal calculaDotacaoAtualPorUoProg(String programaCodigo, Integer anoVigente){	
		return ((FisicoFinanceiroMensalSiafemDAO)getDao()).calculaDotacaoAtualPorUoProg(programaCodigo, anoVigente);
	}

	public List<FisicoFinanceiroMensalSiafem> analiseFisicoFinanceiro(Programa programa, Exercicio exercicio){
		return ((FisicoFinanceiroMensalSiafemDAO)getDao()).analiseFisicoFinanceiro(programa,exercicio);
	}

	public Double calculaQuantidadeCumulativoPlanejada(Long acaoId, Long exercicioId){	
		return ((FisicoFinanceiroMensalSiafemDAO)getDao()).calculaQuantidadeCumulativoPlanejada(acaoId,exercicioId);
	}
	
	public Double calculaQuantidadeNaoCumulativoPlanejada(Long acaoId, Long exercicioId){
		return ((FisicoFinanceiroMensalSiafemDAO)getDao()).calculaQuantidadeNaoCumulativoPlanejada(acaoId,exercicioId);
	}
	
	public Double calculaQuantidadeCumulativoExecutada(Long acaoId, Long exercicioId){
		return ((FisicoFinanceiroMensalSiafemDAO)getDao()).calculaQuantidadeCumulativoExecutada(acaoId,exercicioId);
	}
	
	public Double calculaQuantidadeNaoCumulativoExecutada(Long acaoId, Long exercicioId){
		return ((FisicoFinanceiroMensalSiafemDAO)getDao()).calculaQuantidadeNaoCumulativoExecutada(acaoId,exercicioId);
	}
	
}
