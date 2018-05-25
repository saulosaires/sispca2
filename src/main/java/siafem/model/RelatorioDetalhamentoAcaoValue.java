package siafem.model;

import java.math.BigDecimal;

import arquitetura.enums.Meses;
import arquitetura.utils.MathUtils;

public class RelatorioDetalhamentoAcaoValue {

 	
	private BigDecimal valorJaneiro;
	private BigDecimal valorFevereiro;
	private BigDecimal valorMarco;
	private BigDecimal valorAbril;
	private BigDecimal valorMaio;
	private BigDecimal valorJunho;
	private BigDecimal valorJulho;
	private BigDecimal valorAgosto;
	private BigDecimal valorSetembro;
	private BigDecimal valorOutubro;
	private BigDecimal valorNovembro;
	private BigDecimal valorDezembro;
	 
	
	public void setValor(int numeroMes,BigDecimal valor){
	
		if(valor==null) valor = MathUtils.getZeroBigDecimal();
		 
		switch(numeroMes) {
		
			case Meses.JANEIRO: 
				valorJaneiro=valor;
			break;
			case Meses.FEVEREIRO: 
				valorFevereiro=valor;
			break;
			case Meses.MARCO: 
				valorMarco=valor;
			break;
			case Meses.ABRIL: 
				valorAbril=valor;
			break;
			case Meses.MAIO: 
				valorMaio=valor;
			break;
			case Meses.JUNHO: 
				valorJunho=valor;
			break;
			case Meses.JULHO: 
				valorJulho=valor;
			break;
			case Meses.AGOSTO: 
				valorAgosto=valor;
			break;
			case Meses.SETEMBRO: 
				valorSetembro=valor;
			break;
			case Meses.OUTUBRO: 
				valorOutubro=valor;
			break;
			case Meses.NOVEMBRO: 
				valorNovembro=valor;
			break;
			case Meses.DEZEMBRO: 
				valorDezembro=valor;
			break;
			
			
		
		}
		
	}
	
 
	public BigDecimal getValorJaneiro() {
		return valorJaneiro;
	}
	public void setValorJaneiro(BigDecimal valorJaneiro) {
		this.valorJaneiro = valorJaneiro;
	}
	public BigDecimal getValorFevereiro() {
		return valorFevereiro;
	}
	public void setValorFevereiro(BigDecimal valorFevereiro) {
		this.valorFevereiro = valorFevereiro;
	}
	public BigDecimal getValorMarco() {
		return valorMarco;
	}
	public void setValorMarco(BigDecimal valorMarco) {
		this.valorMarco = valorMarco;
	}
	public BigDecimal getValorAbril() {
		return valorAbril;
	}
	public void setValorAbril(BigDecimal valorAbril) {
		this.valorAbril = valorAbril;
	}
	public BigDecimal getValorMaio() {
		return valorMaio;
	}
	public void setValorMaio(BigDecimal valorMaio) {
		this.valorMaio = valorMaio;
	}
	public BigDecimal getValorJunho() {
		return valorJunho;
	}
	public void setValorJunho(BigDecimal valorJunho) {
		this.valorJunho = valorJunho;
	}
	public BigDecimal getValorJulho() {
		return valorJulho;
	}
	public void setValorJulho(BigDecimal valorJulho) {
		this.valorJulho = valorJulho;
	}
	public BigDecimal getValorAgosto() {
		return valorAgosto;
	}
	public void setValorAgosto(BigDecimal valorAgosto) {
		this.valorAgosto = valorAgosto;
	}
	public BigDecimal getValorSetembro() {
		return valorSetembro;
	}
	public void setValorSetembro(BigDecimal valorSetembro) {
		this.valorSetembro = valorSetembro;
	}
	public BigDecimal getValorOutubro() {
		return valorOutubro;
	}
	public void setValorOutubro(BigDecimal valorOutubro) {
		this.valorOutubro = valorOutubro;
	}
	public BigDecimal getValorNovembro() {
		return valorNovembro;
	}
	public void setValorNovembro(BigDecimal valorNovembro) {
		this.valorNovembro = valorNovembro;
	}
	public BigDecimal getValorDezembro() {
		return valorDezembro;
	}
	public void setValorDezembro(BigDecimal valorDezembro) {
		this.valorDezembro = valorDezembro;
	}
	
	
	
	
	
	
}
