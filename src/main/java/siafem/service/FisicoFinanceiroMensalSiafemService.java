package siafem.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.inject.Inject;

import administrativo.model.Exercicio;
import arquitetura.service.AbstractService;
import qualitativo.model.Programa;
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


	public BigDecimal calculaDotacaoInicialPorUoProg(String programaCodigo, Integer anoVigente){
		
		return ((FisicoFinanceiroMensalSiafemController)getController()).calculaDotacaoInicialPorUoProg(programaCodigo, anoVigente);
	}
	
	public BigDecimal calculaDotacaoAtualPorUoProg(String programaCodigo , Integer anoVigente){
		
		return ((FisicoFinanceiroMensalSiafemController)getController()).calculaDotacaoAtualPorUoProg(programaCodigo, anoVigente);
	}	
	
	public List<FisicoFinanceiroMensalSiafem> analiseFisicoFinanceiro(Programa programa, Exercicio exercicio){
		
		List<FisicoFinanceiroMensalSiafem> listFisicoFinanceiroMensalSiafem = ((FisicoFinanceiroMensalSiafemController)getController()).analiseFisicoFinanceiro(programa, exercicio);
		
		for(FisicoFinanceiroMensalSiafem financeiroMensalSiafem: listFisicoFinanceiroMensalSiafem) {

			if(financeiroMensalSiafem.getTipoCalculoMetaId()==null)continue;
			
			if(financeiroMensalSiafem.getTipoCalculoMetaId().intValue()==1) {
 
				financeiroMensalSiafem.setPlanejado( calculaQuantidadeCumulativoPlanejada(financeiroMensalSiafem.getAcaoId(),exercicio.getId()));
				financeiroMensalSiafem.setExecutado(calculaQuantidadeCumulativoExecutada(financeiroMensalSiafem.getAcaoId(),exercicio.getId()));

			}else{
				
				financeiroMensalSiafem.setPlanejado( calculaQuantidadeNaoCumulativoPlanejada(financeiroMensalSiafem.getAcaoId(),exercicio.getId()));
				financeiroMensalSiafem.setExecutado(calculaQuantidadeNaoCumulativoExecutada(financeiroMensalSiafem.getAcaoId(),exercicio.getId()));
				
			}
 			
			calculaEficacia(financeiroMensalSiafem);
			calculaLiquidadoSobreAtual(financeiroMensalSiafem);
			calculaEficiencia(financeiroMensalSiafem);
		}
		
		
		return listFisicoFinanceiroMensalSiafem;
	}

	public Double calculaQuantidadeCumulativoPlanejada(Long acaoId, Long exercicioId){	
		return ((FisicoFinanceiroMensalSiafemController)getController()).calculaQuantidadeCumulativoPlanejada(acaoId,exercicioId);
	}
	
	public Double calculaQuantidadeNaoCumulativoPlanejada(Long acaoId, Long exercicioId){
		return ((FisicoFinanceiroMensalSiafemController)getController()).calculaQuantidadeNaoCumulativoPlanejada(acaoId,exercicioId);
	}
	
	public Double calculaQuantidadeCumulativoExecutada(Long acaoId, Long exercicioId){
		return ((FisicoFinanceiroMensalSiafemController)getController()).calculaQuantidadeCumulativoExecutada(acaoId,exercicioId);
	}
	
	public Double calculaQuantidadeNaoCumulativoExecutada(Long acaoId, Long exercicioId){
		return ((FisicoFinanceiroMensalSiafemController)getController()).calculaQuantidadeNaoCumulativoExecutada(acaoId,exercicioId);
	}
		
	private void calculaEficiencia(FisicoFinanceiroMensalSiafem financeiroMensalSiafem) {
		
		Double eficacia 			   = financeiroMensalSiafem.getEficacia();
		BigDecimal liquidadoSobreAtual = financeiroMensalSiafem.getLiquidadoSobreAtual();
		
		if(eficacia!=null && eficacia.intValue()>0 && liquidadoSobreAtual!=null && liquidadoSobreAtual.intValue()>0) {
			
			financeiroMensalSiafem.setEficiencia(BigDecimal.valueOf(financeiroMensalSiafem.getEficacia().doubleValue()).divide(financeiroMensalSiafem.getLiquidadoSobreAtual(),2, RoundingMode.HALF_UP));
		}else{
			financeiroMensalSiafem.setEficiencia(BigDecimal.valueOf(0.00).setScale(2));
		}
		
	}
	
	private void calculaEficacia(FisicoFinanceiroMensalSiafem financeiroMensalSiafem) {
		
		Double planejado = financeiroMensalSiafem.getPlanejado();
		Double executado = financeiroMensalSiafem.getExecutado();
		
		if(planejado!=null && planejado>0 && executado!=null && executado>0) {
			financeiroMensalSiafem.setEficacia(planejado/executado);
		}else {
			financeiroMensalSiafem.setEficacia(Double.valueOf(0.0));
		}
		
	}

	private void calculaLiquidadoSobreAtual(FisicoFinanceiroMensalSiafem financeiroMensalSiafem) {
		
		 BigDecimal dotacaoInicial = financeiroMensalSiafem.getDotacaoInicial();
		 BigDecimal empenhado = financeiroMensalSiafem.getEmpenhado();
		
		if(empenhado!=null && empenhado.intValue()>0 && dotacaoInicial!=null && dotacaoInicial.intValue()>0) {
			financeiroMensalSiafem.setLiquidadoSobreAtual(empenhado.divide(dotacaoInicial,RoundingMode.HALF_UP));
		}else {
			financeiroMensalSiafem.setLiquidadoSobreAtual(BigDecimal.valueOf(0.0).setScale(2));
		}
		
	}
	
	
	
}
