
package siafem.service;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import administrativo.model.Exercicio;
import arquitetura.enums.ACAO;
import arquitetura.enums.TipoCalculoMeta;
import arquitetura.exception.JpaException;
import arquitetura.service.AbstractService;
import arquitetura.utils.MathUtils;
import grafico.model.RelatorioLiquidadoAcumuladoFisicoFinanceiro;
import qualitativo.model.Mes;
import qualitativo.model.Programa;
import siafem.controller.FisicoFinanceiroMensalSiafemController;
import siafem.enums.NaturezaDespeza;
import siafem.model.FisicoFinanceiroMensalSiafem;
import siafem.model.RelatorioDetalhamentoAcaoSiafem;
import siafem.model.RelatorioDetalhamentoAcaoValue;

public class FisicoFinanceiroMensalSiafemService extends AbstractService<FisicoFinanceiroMensalSiafem> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public FisicoFinanceiroMensalSiafemService(FisicoFinanceiroMensalSiafemController controller) {
		super(controller);
	}

	public void create(List<FisicoFinanceiroMensalSiafem> listSiafem) throws JpaException {
		
		((FisicoFinanceiroMensalSiafemController)getController()).create(listSiafem);
	}
	
	
	public int deleteByYear(Integer exercicio) throws JpaException{
		return ((FisicoFinanceiroMensalSiafemController)getController()).deleteByYear(exercicio);
	}

	public BigDecimal calculaDotacaoInicialByProgAndAno(String programaCodigo, Integer anoVigente){
		
		return ((FisicoFinanceiroMensalSiafemController)getController()).calculaDotacaoInicialByProgAndAno(programaCodigo, anoVigente);
	}
	
	public BigDecimal calculaDotacaoAtualByProgAndAno(String programaCodigo , Integer anoVigente){
		
		return ((FisicoFinanceiroMensalSiafemController)getController()).calculaDotacaoAtualByProgAndAno(programaCodigo, anoVigente);
	}	
	
	public BigDecimal calculaEmpenhadoByProgAndAno(String programaCodigo , Integer anoVigente){
		
		return ((FisicoFinanceiroMensalSiafemController)getController()).calculaEmpenhadoByProgAndAno(programaCodigo, anoVigente);
	}	
	
	public BigDecimal calculaLiquidadoByProgAndAno(String programaCodigo , Integer anoVigente){
		
		return ((FisicoFinanceiroMensalSiafemController)getController()).calculaLiquidadoByProgAndAno(programaCodigo, anoVigente);
	}	
	
	public BigDecimal calculaPagoByProgAndAno(String programaCodigo , Integer anoVigente){
		
		return ((FisicoFinanceiroMensalSiafemController)getController()).calculaPagoByProgAndAno(programaCodigo, anoVigente);
	}	
	
	public List<FisicoFinanceiroMensalSiafem> valorFisicoFinanceiro(String unidadeGestora, String unidadeOrcamentaria, Long acao, Integer exercicio){
		
		return ((FisicoFinanceiroMensalSiafemController)getController()).valorFisicoFinanceiro(unidadeGestora,unidadeOrcamentaria,acao,exercicio);
		
	}
		
	
	public List<FisicoFinanceiroMensalSiafem> analiseFisicoFinanceiro(Programa programa, Exercicio exercicio){
		
		List<FisicoFinanceiroMensalSiafem> listFisicoFinanceiroMensalSiafem = ((FisicoFinanceiroMensalSiafemController)getController()).analiseFisicoFinanceiro(programa, exercicio);
		
		for(FisicoFinanceiroMensalSiafem financeiroMensalSiafem: listFisicoFinanceiroMensalSiafem) {

			if(financeiroMensalSiafem.getTipoCalculoMetaId()==null)continue;
			
			if(TipoCalculoMeta.ACUMULATIVA.isAcumulativa(financeiroMensalSiafem.getTipoCalculoMetaId())) {
 
				financeiroMensalSiafem.setPlanejado( BigDecimal.valueOf(calculaQuantidadeCumulativoPlanejada(financeiroMensalSiafem.getAcao().getId(),exercicio.getId())));
				financeiroMensalSiafem.setExecutado( BigDecimal.valueOf(calculaQuantidadeCumulativoExecutada(financeiroMensalSiafem.getAcao().getId(),exercicio.getId())));

			}else{
				
				financeiroMensalSiafem.setPlanejado( BigDecimal.valueOf(calculaQuantidadeNaoCumulativoPlanejada(financeiroMensalSiafem.getAcao().getId(),exercicio.getId())));
				financeiroMensalSiafem.setExecutado( BigDecimal.valueOf(calculaQuantidadeNaoCumulativoExecutada(financeiroMensalSiafem.getAcao().getId(),exercicio.getId())));
				
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
		
		BigDecimal eficacia 		   = financeiroMensalSiafem.getEficacia();
		BigDecimal liquidadoSobreAtual = financeiroMensalSiafem.getLiquidadoSobreAtual();
		
		if(eficacia!=null && eficacia.doubleValue()>0 && liquidadoSobreAtual!=null && liquidadoSobreAtual.doubleValue()>0) {
				
			financeiroMensalSiafem.setEficiencia(MathUtils.divide(financeiroMensalSiafem.getEficacia(), financeiroMensalSiafem.getLiquidadoSobreAtual()));
			
		}else{
			
			financeiroMensalSiafem.setEficiencia(MathUtils.getZeroBigDecimal());
		}
		
	}
	
	private void calculaEficacia(FisicoFinanceiroMensalSiafem financeiroMensalSiafem) {
		
		BigDecimal planejado = financeiroMensalSiafem.getPlanejado();
		BigDecimal executado = financeiroMensalSiafem.getExecutado();
		
		if(planejado!=null && planejado.doubleValue()>0 && executado!=null && executado.doubleValue()>0) {
			
			financeiroMensalSiafem.setEficacia(MathUtils.divide(executado,planejado));
			
		}else {
			
			financeiroMensalSiafem.setEficacia(MathUtils.getZeroBigDecimal());
		}
		
	}

	private void calculaLiquidadoSobreAtual(FisicoFinanceiroMensalSiafem financeiroMensalSiafem) {
		
		 BigDecimal liquidado = financeiroMensalSiafem.getLiquidado();
		 BigDecimal disponivel = financeiroMensalSiafem.getDisponivel();
		
		if(liquidado!=null && liquidado.intValue()>0 && disponivel!=null && disponivel.intValue()>0) {
			
			financeiroMensalSiafem.setLiquidadoSobreAtual(MathUtils.divide(liquidado,disponivel));
		}else {
			financeiroMensalSiafem.setLiquidadoSobreAtual(MathUtils.getZeroBigDecimal());
		}
		
	}

	private void calculaPagoSobreDisponivel(FisicoFinanceiroMensalSiafem financeiroMensalSiafem) {
		
		 BigDecimal pago = financeiroMensalSiafem.getPago();
		 BigDecimal disponivel = financeiroMensalSiafem.getDisponivel();
		
		if(pago!=null && pago.intValue()>0 && disponivel!=null && disponivel.intValue()>0) {
			
			financeiroMensalSiafem.setPagoSobreDisponivel(MathUtils.divide(pago,disponivel));
		}else {
			financeiroMensalSiafem.setPagoSobreDisponivel(MathUtils.getZeroBigDecimal());
		}
		
	}

	
	private void calculaEmpenhadoSobreDisponivel(FisicoFinanceiroMensalSiafem financeiroMensalSiafem) {
		
		 BigDecimal empenhado = financeiroMensalSiafem.getEmpenhado();
		 BigDecimal disponivel = financeiroMensalSiafem.getDisponivel();
		
		if(empenhado!=null && empenhado.intValue()>0 && disponivel!=null && disponivel.intValue()>0) {
			
			financeiroMensalSiafem.setEmpenhadoSobreDisponivel(MathUtils.divide(empenhado,disponivel));
		}else {
			financeiroMensalSiafem.setEmpenhadoSobreDisponivel(MathUtils.getZeroBigDecimal());
		}
		
	}
	
	private void calculaSaldo(FisicoFinanceiroMensalSiafem financeiroMensalSiafem) {
				
		 BigDecimal empenhado = financeiroMensalSiafem.getEmpenhado();
		 BigDecimal disponivel = financeiroMensalSiafem.getDisponivel();
		
		if(empenhado!=null && empenhado.intValue()>0 && disponivel!=null && disponivel.intValue()>0) {
			
			financeiroMensalSiafem.setSaldo(disponivel.subtract(empenhado));
		}else {
			financeiroMensalSiafem.setSaldo(MathUtils.getZeroBigDecimal());
		}
		
	}
	
	public BigDecimal calculaMediaEficaciaAvaliacaoFisicoFinanceira(List<FisicoFinanceiroMensalSiafem> listFisicoFinanceiroMensalSiafem) {
		
		BigDecimal mediaEficaciaFisicoFinanceira   = MathUtils.getZeroBigDecimal();
		
		int quantidadeGestaoPrograma = 0;
		
		for (FisicoFinanceiroMensalSiafem avalFisFinan: listFisicoFinanceiroMensalSiafem){
			
			if (avalFisFinan.getEficacia()!=null){
				mediaEficaciaFisicoFinanceira  =mediaEficaciaFisicoFinanceira.add(avalFisFinan.getEficacia());
			}
 			
			if (avalFisFinan.getAcao()!=null && avalFisFinan.getAcao().getCodigo()!=null && avalFisFinan.getAcao().getCodigo().contains(ACAO.GESTAO_PROGRAMA.codigo())) {
				quantidadeGestaoPrograma++;
			}
			
		}
		
		int divisor= (listFisicoFinanceiroMensalSiafem.size()-quantidadeGestaoPrograma);
		
		if (divisor>0){
			mediaEficaciaFisicoFinanceira = MathUtils.divide(mediaEficaciaFisicoFinanceira,new BigDecimal(divisor));
		}
 
	
		return mediaEficaciaFisicoFinanceira;
	}

	public BigDecimal calculaEficienciaMediaAvaliacaoFisicoFinanceira(List<FisicoFinanceiroMensalSiafem> listFisicoFinanceiroMensalSiafem) {
	
		BigDecimal mediaEficienciaFisicoFinanceira = MathUtils.getZeroBigDecimal();
		
		int quantidadeGestaoPrograma = 0;
		
		for (FisicoFinanceiroMensalSiafem avalFisFinan: listFisicoFinanceiroMensalSiafem){
 			if(avalFisFinan.getEficiencia()!=null){
				mediaEficienciaFisicoFinanceira = mediaEficienciaFisicoFinanceira.add(avalFisFinan.getEficiencia());
			}
			
			if (avalFisFinan.getAcao()!=null && avalFisFinan.getAcao().getCodigo()!=null && avalFisFinan.getAcao().getCodigo().contains(ACAO.GESTAO_PROGRAMA.codigo())) {
				quantidadeGestaoPrograma++;
			}
			
		}
		
		int divisor= (listFisicoFinanceiroMensalSiafem.size()-quantidadeGestaoPrograma);
		
 
		if (divisor>0){
			mediaEficienciaFisicoFinanceira =MathUtils.divide( mediaEficienciaFisicoFinanceira,new BigDecimal(divisor));
		}		
		
		
		
		return mediaEficienciaFisicoFinanceira;
	}
	
	
	public List<FisicoFinanceiroMensalSiafem> analiseFisicoFinanceiro(String unidadeOrcamentaria,String programa, Integer exercicio){
		return ((FisicoFinanceiroMensalSiafemController)getController()).analiseFisicoFinanceiro(unidadeOrcamentaria, programa, exercicio);
	}
	
	public List<FisicoFinanceiroMensalSiafem> analiseFisicoFinanceiroPorMes(String unidadeOrcamentaria,String programa, Integer exercicio){
		return ((FisicoFinanceiroMensalSiafemController)getController()).analiseFisicoFinanceiroPorMes(unidadeOrcamentaria, programa, exercicio);
	}
	
	public RelatorioLiquidadoAcumuladoFisicoFinanceiro calculaLiquidadoAcumuladoByUnidadeAndProgAndMesAndAno(String unidadeOrcamentaria,String programa, List<Mes> meses, Integer ano) {
		
		RelatorioLiquidadoAcumuladoFisicoFinanceiro relatorioLiquidadoAcumuladoFisicoFinanceiro = new RelatorioLiquidadoAcumuladoFisicoFinanceiro();
		
		for(Mes mes : meses) {
			
			BigDecimal liquidado = controller().calculaLiquidadoByUnidadeAndProgAndMesAndAno(unidadeOrcamentaria,programa, mes.getNumeroMes(), ano);
			
			relatorioLiquidadoAcumuladoFisicoFinanceiro.setLiquidado(liquidado, mes.getNumeroMes());
		}
		
		
		relatorioLiquidadoAcumuladoFisicoFinanceiro.calculaAcumulado();
		
		
		return relatorioLiquidadoAcumuladoFisicoFinanceiro;
	}	
	
	public RelatorioDetalhamentoAcaoSiafem  relatorioDetalhamentoAcao(List<Mes> meses, String unidadeOrcamentaria, Long acao, Integer ano){
		
		List<FisicoFinanceiroMensalSiafem> listDetalhamentoAcao = controller().relatorioDetalhamentoAcao(unidadeOrcamentaria, acao, ano);
		
		
		RelatorioDetalhamentoAcaoValue dotacalInicial = new RelatorioDetalhamentoAcaoValue();
		RelatorioDetalhamentoAcaoValue empenhado = new RelatorioDetalhamentoAcaoValue();
		RelatorioDetalhamentoAcaoValue liquidado = new RelatorioDetalhamentoAcaoValue();
		RelatorioDetalhamentoAcaoValue disponivel = new RelatorioDetalhamentoAcaoValue();
		
		for(FisicoFinanceiroMensalSiafem fSiafem : listDetalhamentoAcao) {
			
			  for(Mes mes: meses) {
				  
				  FisicoFinanceiroMensalSiafem fisicoFinanceiroSiafem = calculaDetalhamentoMensalByMesAndAnoAndProgramaAndUnidadeAndAcao(
																																		 mes.getId(), 
																												    			    	 ano,
																												    			    	 fSiafem.getAcao().getUnidadeOrcamentaria().getProgramaDescricao(),
																												    			    	 fSiafem.getAcao().getUnidadeOrcamentaria().getCodigo(),
																												    			    	 fSiafem.getAcao().getCodigo()
																												    			    	 );
				  
				  
				  dotacalInicial.setValor(mes.getNumeroMes(),fisicoFinanceiroSiafem.getDotacaoInicial());
				  	   empenhado.setValor(mes.getNumeroMes(),fisicoFinanceiroSiafem.getEmpenhado());
				  	   liquidado.setValor(mes.getNumeroMes(),fisicoFinanceiroSiafem.getLiquidado());
				      disponivel.setValor(mes.getNumeroMes(),fisicoFinanceiroSiafem.getDisponivel());
				  
				  
			  }
 			
			 
		}
 		
		
		return new RelatorioDetalhamentoAcaoSiafem(dotacalInicial,empenhado,liquidado,disponivel);
	}
	
	public FisicoFinanceiroMensalSiafem  calculaDetalhamentoMensalByMesAndAnoAndProgramaAndUnidadeAndAcao(Long mes, Integer ano,String programaCodigo,String unidadeOrcamentariaCodigo,String acaocodigo){
		return controller().calculaDetalhamentoMensalByMesAndAnoAndProgramaAndUnidadeAndAcao(mes, ano,programaCodigo,unidadeOrcamentariaCodigo,acaocodigo);
	}
	
	public List<FisicoFinanceiroMensalSiafem> relatorioFinanceiroNaturezaDespesa(Long unidadeGestora, Long unidadeOrcamentaria, Long acao, NaturezaDespeza natureza, Integer ano){
		
		 List<FisicoFinanceiroMensalSiafem> listFisicoFinanceiro = controller().relatorioFinanceiroNaturezaDespesa(unidadeGestora, unidadeOrcamentaria, acao, natureza,ano);
		 
		 
		 for(FisicoFinanceiroMensalSiafem fisicoFinanceiroMensalSiafem: listFisicoFinanceiro) {
			 
			 calculaSaldo(fisicoFinanceiroMensalSiafem);
			 calculaEmpenhadoSobreDisponivel(fisicoFinanceiroMensalSiafem);
			 calculaLiquidadoSobreAtual(fisicoFinanceiroMensalSiafem);
			 calculaPagoSobreDisponivel(fisicoFinanceiroMensalSiafem);
		 }
		 
		 		 
		 return listFisicoFinanceiro;
	}
	
	public List<FisicoFinanceiroMensalSiafem> totalPorNaturezaDespesa(Long unidadeGestora, Long unidadeOrcamentaria, Long acao, NaturezaDespeza natureza, Integer ano){
		
		 List<FisicoFinanceiroMensalSiafem> listFisicoFinanceiro = controller().totalPorNaturezaDespesa(unidadeGestora, unidadeOrcamentaria, acao, natureza,ano);
		 
		 
		 for(FisicoFinanceiroMensalSiafem fisicoFinanceiroMensalSiafem: listFisicoFinanceiro) {
			 
			 calculaSaldo(fisicoFinanceiroMensalSiafem);
			 calculaEmpenhadoSobreDisponivel(fisicoFinanceiroMensalSiafem);
			 calculaLiquidadoSobreAtual(fisicoFinanceiroMensalSiafem);
			 calculaPagoSobreDisponivel(fisicoFinanceiroMensalSiafem);
		 }
		 
		 		 
		 return listFisicoFinanceiro;
	}

	
	
	
	private FisicoFinanceiroMensalSiafemController controller() {
		
		return (FisicoFinanceiroMensalSiafemController)getController();
	}
	
}
 
