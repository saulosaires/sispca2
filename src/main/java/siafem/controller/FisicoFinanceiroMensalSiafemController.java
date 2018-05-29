package siafem.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import administrativo.model.Exercicio;
import arquitetura.controller.AbstractController;
import arquitetura.exception.JpaException;
import qualitativo.model.Programa;
import siafem.dao.FisicoFinanceiroMensalSiafemDAO;
import siafem.enums.NaturezaDespeza;
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

	public void create(List<FisicoFinanceiroMensalSiafem> listSiafem) throws JpaException {

		((FisicoFinanceiroMensalSiafemDAO)getDao()).create(listSiafem);
	}	
	
	public int deleteByYear(Integer exercicio) {
		return ((FisicoFinanceiroMensalSiafemDAO)getDao()).deleteByYear(exercicio);
	}
	
	public List<FisicoFinanceiroMensalSiafem> analiseFisicoFinanceiro(Programa programa, Exercicio exercicio){
		return ((FisicoFinanceiroMensalSiafemDAO)getDao()).analiseFisicoFinanceiro(programa,exercicio);
	}

	public List<FisicoFinanceiroMensalSiafem> analiseFisicoFinanceiro(String unidadeOrcamentaria,String programa, Integer exercicio){
		return ((FisicoFinanceiroMensalSiafemDAO)getDao()).analiseFisicoFinanceiro(unidadeOrcamentaria, programa, exercicio);
	}

	public List<FisicoFinanceiroMensalSiafem> analiseFisicoFinanceiroPorMes(String unidadeOrcamentaria,String programa, Integer exercicio){
		return ((FisicoFinanceiroMensalSiafemDAO)getDao()).analiseFisicoFinanceiroPorMes(unidadeOrcamentaria, programa, exercicio);
	}
	
	
	public List<FisicoFinanceiroMensalSiafem> valorFisicoFinanceiro(String unidadeGestora, String unidadeOrcamentaria, Long acao, Integer exercicio){
		return ((FisicoFinanceiroMensalSiafemDAO)getDao()).valorFisicoFinanceiro(unidadeGestora,unidadeOrcamentaria,acao,exercicio);
	}
	
	public BigDecimal calculaDotacaoInicialByProgAndAno(String programaCodigo, Integer anoVigente){	
		return ((FisicoFinanceiroMensalSiafemDAO)getDao()).calculaDotacaoInicialByProgAndAno(programaCodigo, anoVigente);
	}
	
	public BigDecimal calculaDotacaoAtualByProgAndAno(String programaCodigo, Integer anoVigente){	
		return ((FisicoFinanceiroMensalSiafemDAO)getDao()).calculaDotacaoAtualByProgAndAno(programaCodigo, anoVigente);
	}

	public BigDecimal calculaEmpenhadoByProgAndAno(String programaCodigo, Integer anoVigente){	
		return ((FisicoFinanceiroMensalSiafemDAO)getDao()).calculaEmpenhadoByProgAndAno(programaCodigo, anoVigente);
	}
	
	public BigDecimal calculaLiquidadoByProgAndAno(String programaCodigo, Integer anoVigente) {
		return ((FisicoFinanceiroMensalSiafemDAO)getDao()).calculaLiquidadoByProgAndAno(programaCodigo,anoVigente);
	}
	
	public BigDecimal calculaPagoByProgAndAno(String programaCodigo, Integer anoVigente) {
		return ((FisicoFinanceiroMensalSiafemDAO)getDao()).calculaPagoByProgAndAno(programaCodigo,anoVigente);
	}	
	
	
	public Double calculaQuantidadeCumulativoPlanejada(Long acaoId, Long exercicioId){	
		return ((FisicoFinanceiroMensalSiafemDAO)getDao()).calculaQuantidadeAcumulativoPlanejada(acaoId,exercicioId);
	}
	
	public Double calculaQuantidadeNaoCumulativoPlanejada(Long acaoId, Long exercicioId){
		return ((FisicoFinanceiroMensalSiafemDAO)getDao()).calculaQuantidadeNaoAcumulativoPlanejada(acaoId,exercicioId);
	}
	
	public Double calculaQuantidadeCumulativoExecutada(Long acaoId, Long exercicioId){
		return ((FisicoFinanceiroMensalSiafemDAO)getDao()).calculaQuantidadeAcumulativoExecutada(acaoId,exercicioId);
	}
	
	public Double calculaQuantidadeNaoCumulativoExecutada(Long acaoId, Long exercicioId){
		return ((FisicoFinanceiroMensalSiafemDAO)getDao()).calculaQuantidadeNaoAcumulativoExecutada(acaoId,exercicioId);
	}

	public BigDecimal calculaLiquidadoByUnidadeAndProgAndMesAndAno(String unidadeOrcamentaria,String programa, Integer mes, Integer ano) {
		return ((FisicoFinanceiroMensalSiafemDAO)getDao()).calculaLiquidadoByUnidadeAndProgAndMesAndAno(unidadeOrcamentaria,programa, mes, ano);
	}
 
	public FisicoFinanceiroMensalSiafem  calculaDetalhamentoMensalByMesAndAnoAndProgramaAndUnidadeAndAcao(Long mes, Integer ano,String programaCodigo,String unidadeOrcamentariaCodigo,String acaoCodigo){
		return ((FisicoFinanceiroMensalSiafemDAO)getDao()).calculaDetalhamentoMensalByMesAndAnoAndProgramaAndUnidadeAndAcao(mes, ano,programaCodigo,unidadeOrcamentariaCodigo,acaoCodigo);
	}
	
	public List<FisicoFinanceiroMensalSiafem>  relatorioDetalhamentoAcao(String unidadeOrcamentaria, Long acao, Integer ano){
		return ((FisicoFinanceiroMensalSiafemDAO)getDao()).relatorioDetalhamentoAcao(unidadeOrcamentaria, acao, ano);
	}
 
	public List<FisicoFinanceiroMensalSiafem> relatorioFinanceiroNaturezaDespesa(Long unidadeGestora, Long unidadeOrcamentaria, Long acao, NaturezaDespeza natureza, Integer ano){
		return ((FisicoFinanceiroMensalSiafemDAO)getDao()).relatorioFinanceiroNaturezaDespesa(unidadeGestora, unidadeOrcamentaria, acao, natureza,ano);
	}

	public List<FisicoFinanceiroMensalSiafem> totalPorNaturezaDespesa(Long unidadeGestora, Long unidadeOrcamentaria, Long acao, NaturezaDespeza natureza, Integer ano){
		return ((FisicoFinanceiroMensalSiafemDAO)getDao()).totalPorNaturezaDespesa(unidadeGestora, unidadeOrcamentaria, acao, natureza,ano);
	}
	
	public List<FisicoFinanceiroMensalSiafem> relatorioFinanceiroPlanoInterno(Long unidadeGestora, Long unidadeOrcamentaria, Long acao,  Integer ano){
		return ((FisicoFinanceiroMensalSiafemDAO)getDao()).relatorioFinanceiroPlanoInterno(unidadeGestora, unidadeOrcamentaria, acao, ano);
	}
	
	
}
