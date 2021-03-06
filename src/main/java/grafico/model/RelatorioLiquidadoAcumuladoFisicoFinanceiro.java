package grafico.model;

import java.math.BigDecimal;

import arquitetura.enums.Meses;
import arquitetura.utils.MathUtils;

public class RelatorioLiquidadoAcumuladoFisicoFinanceiro {

	
	private BigDecimal liquidadoJaneiro = MathUtils.getZeroBigDecimal();
	private BigDecimal acumuladoJaneiro = MathUtils.getZeroBigDecimal();
	
	private BigDecimal liquidadoFevereiro = MathUtils.getZeroBigDecimal();
	private BigDecimal acumuladoFevereiro = MathUtils.getZeroBigDecimal();

	private BigDecimal liquidadoMarco = MathUtils.getZeroBigDecimal();
	private BigDecimal acumuladoMarco = MathUtils.getZeroBigDecimal();
	
	private BigDecimal liquidadoAbril = MathUtils.getZeroBigDecimal();
	private BigDecimal acumuladoAbril = MathUtils.getZeroBigDecimal();
	
	private BigDecimal liquidadoMaio = MathUtils.getZeroBigDecimal();
	private BigDecimal acumuladoMaio = MathUtils.getZeroBigDecimal();

	private BigDecimal liquidadoJunho = MathUtils.getZeroBigDecimal();
	private BigDecimal acumuladoJunho = MathUtils.getZeroBigDecimal();

	private BigDecimal liquidadoJulho = MathUtils.getZeroBigDecimal();
	private BigDecimal acumuladoJulho = MathUtils.getZeroBigDecimal();

	private BigDecimal liquidadoAgosto = MathUtils.getZeroBigDecimal();
	private BigDecimal acumuladoAgosto = MathUtils.getZeroBigDecimal();

	private BigDecimal liquidadoSetembro = MathUtils.getZeroBigDecimal();
	private BigDecimal acumuladoSetembro = MathUtils.getZeroBigDecimal();

	private BigDecimal liquidadoOutubro = MathUtils.getZeroBigDecimal();
	private BigDecimal acumuladoOutubro = MathUtils.getZeroBigDecimal();

	private BigDecimal liquidadoNovembro = MathUtils.getZeroBigDecimal();
	private BigDecimal acumuladoNovembro = MathUtils.getZeroBigDecimal();

	private BigDecimal liquidadoDezembro = MathUtils.getZeroBigDecimal();
	private BigDecimal acumuladoDezembro = MathUtils.getZeroBigDecimal();
	
	
	public void setLiquidado(BigDecimal liquidado, int id) {
		
		switch (id) {

		case Meses.JANEIRO: 
			liquidadoJaneiro = liquidado;
			break;
		
		case Meses.FEVEREIRO: 
			liquidadoFevereiro = liquidado;
			break;
		
		case Meses.MARCO: 
			liquidadoMarco = liquidado;
			break;
		
		case Meses.ABRIL: 
			liquidadoAbril = liquidado;
			break;
		
		case Meses.MAIO: 
			liquidadoMaio = liquidado;
			break;
		
		case Meses.JUNHO: 
			liquidadoJunho = liquidado;
			break;
		
		case Meses.JULHO: 
			liquidadoJulho = liquidado;
			break;
		
		case Meses.AGOSTO: 
			liquidadoAgosto = liquidado;
			break;
		
		case Meses.SETEMBRO: 
			liquidadoSetembro = liquidado;
			break;
		
		case Meses.OUTUBRO: 
			liquidadoOutubro = liquidado;
			break;
		
		case Meses.NOVEMBRO: 
			liquidadoNovembro = liquidado;
			break;
		
		case Meses.DEZEMBRO: 
			liquidadoDezembro = liquidado;
			break;
		
		default: 
			break;
		

		}
		
		
		
	}
	
	public void calculaAcumulado() {
		
		
		acumuladoJaneiro   = liquidadoJaneiro;		
		acumuladoFevereiro = liquidadoFevereiro.add(acumuladoJaneiro);	
		acumuladoMarco     = liquidadoMarco.add(acumuladoFevereiro);		
		acumuladoAbril     = liquidadoAbril.add(acumuladoMarco);		
		acumuladoMaio      = liquidadoMaio.add(acumuladoAbril);		
		acumuladoJunho     = liquidadoJunho.add(acumuladoMaio);		
		acumuladoJulho     = liquidadoJulho.add(acumuladoJunho);		
		acumuladoAgosto    = liquidadoAgosto.add(acumuladoJulho);		
		acumuladoSetembro  = liquidadoSetembro.add(acumuladoAgosto);		
		acumuladoOutubro   = liquidadoOutubro.add(acumuladoSetembro);		
		acumuladoNovembro  = liquidadoNovembro.add(acumuladoOutubro);		
		acumuladoDezembro  = liquidadoDezembro.add(acumuladoNovembro);
		
	}
	
	public BigDecimal getLiquidadoJaneiro() {
		return liquidadoJaneiro;
	}
	public void setLiquidadoJaneiro(BigDecimal liquidadoJaneiro) {
		this.liquidadoJaneiro = liquidadoJaneiro;
	}
	public BigDecimal getAcumuladoJaneiro() {
		return acumuladoJaneiro;
	}
	public void setAcumuladoJaneiro(BigDecimal acumuladoJaneiro) {
		this.acumuladoJaneiro = acumuladoJaneiro;
	}
	public BigDecimal getLiquidadoFevereiro() {
		return liquidadoFevereiro;
	}
	public void setLiquidadoFevereiro(BigDecimal liquidadoFevereiro) {
		this.liquidadoFevereiro = liquidadoFevereiro;
	}
	public BigDecimal getAcumuladoFevereiro() {
		return acumuladoFevereiro;
	}
	public void setAcumuladoFevereiro(BigDecimal acumuladoFevereiro) {
		this.acumuladoFevereiro = acumuladoFevereiro;
	}
	public BigDecimal getLiquidadoMarco() {
		return liquidadoMarco;
	}
	public void setLiquidadoMarco(BigDecimal liquidadoMarco) {
		this.liquidadoMarco = liquidadoMarco;
	}
	public BigDecimal getAcumuladoMarco() {
		return acumuladoMarco;
	}
	public void setAcumuladoMarco(BigDecimal acumuladoMarco) {
		this.acumuladoMarco = acumuladoMarco;
	}
	public BigDecimal getLiquidadoAbril() {
		return liquidadoAbril;
	}
	public void setLiquidadoAbril(BigDecimal liquidadoAbril) {
		this.liquidadoAbril = liquidadoAbril;
	}
	public BigDecimal getAcumuladoAbril() {
		return acumuladoAbril;
	}
	public void setAcumuladoAbril(BigDecimal acumuladoAbril) {
		this.acumuladoAbril = acumuladoAbril;
	}
	public BigDecimal getLiquidadoMaio() {
		return liquidadoMaio;
	}
	public void setLiquidadoMaio(BigDecimal liquidadoMaio) {
		this.liquidadoMaio = liquidadoMaio;
	}
	public BigDecimal getAcumuladoMaio() {
		return acumuladoMaio;
	}
	public void setAcumuladoMaio(BigDecimal acumuladoMaio) {
		this.acumuladoMaio = acumuladoMaio;
	}
	public BigDecimal getLiquidadoJunho() {
		return liquidadoJunho;
	}
	public void setLiquidadoJunho(BigDecimal liquidadoJunho) {
		this.liquidadoJunho = liquidadoJunho;
	}
	public BigDecimal getAcumuladoJunho() {
		return acumuladoJunho;
	}
	public void setAcumuladoJunho(BigDecimal acumuladoJunho) {
		this.acumuladoJunho = acumuladoJunho;
	}
	public BigDecimal getLiquidadoJulho() {
		return liquidadoJulho;
	}
	public void setLiquidadoJulho(BigDecimal liquidadoJulho) {
		this.liquidadoJulho = liquidadoJulho;
	}
	public BigDecimal getAcumuladoJulho() {
		return acumuladoJulho;
	}
	public void setAcumuladoJulho(BigDecimal acumuladoJulho) {
		this.acumuladoJulho = acumuladoJulho;
	}
	public BigDecimal getLiquidadoAgosto() {
		return liquidadoAgosto;
	}
	public void setLiquidadoAgosto(BigDecimal liquidadoAgosto) {
		this.liquidadoAgosto = liquidadoAgosto;
	}
	public BigDecimal getAcumuladoAgosto() {
		return acumuladoAgosto;
	}
	public void setAcumuladoAgosto(BigDecimal acumuladoAgosto) {
		this.acumuladoAgosto = acumuladoAgosto;
	}
	public BigDecimal getLiquidadoSetembro() {
		return liquidadoSetembro;
	}
	public void setLiquidadoSetembro(BigDecimal liquidadoSetembro) {
		this.liquidadoSetembro = liquidadoSetembro;
	}
	public BigDecimal getAcumuladoSetembro() {
		return acumuladoSetembro;
	}
	public void setAcumuladoSetembro(BigDecimal acumuladoSetembro) {
		this.acumuladoSetembro = acumuladoSetembro;
	}
	public BigDecimal getLiquidadoOutubro() {
		return liquidadoOutubro;
	}
	public void setLiquidadoOutubro(BigDecimal liquidadoOutubro) {
		this.liquidadoOutubro = liquidadoOutubro;
	}
	public BigDecimal getAcumuladoOutubro() {
		return acumuladoOutubro;
	}
	public void setAcumuladoOutubro(BigDecimal acumuladoOutubro) {
		this.acumuladoOutubro = acumuladoOutubro;
	}
	public BigDecimal getLiquidadoNovembro() {
		return liquidadoNovembro;
	}
	public void setLiquidadoNovembro(BigDecimal liquidadoNovembro) {
		this.liquidadoNovembro = liquidadoNovembro;
	}
	public BigDecimal getAcumuladoNovembro() {
		return acumuladoNovembro;
	}
	public void setAcumuladoNovembro(BigDecimal acumuladoNovembro) {
		this.acumuladoNovembro = acumuladoNovembro;
	}
	public BigDecimal getLiquidadoDezembro() {
		return liquidadoDezembro;
	}
	public void setLiquidadoDezembro(BigDecimal liquidadoDezembro) {
		this.liquidadoDezembro = liquidadoDezembro;
	}
	public BigDecimal getAcumuladoDezembro() {
		return acumuladoDezembro;
	}
	public void setAcumuladoDezembro(BigDecimal acumuladoDezembro) {
		this.acumuladoDezembro = acumuladoDezembro;
	}
	
	
	

	
	
	
	
}
