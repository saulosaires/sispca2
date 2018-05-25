package quantitativo.model;

import arquitetura.enums.Meses;

public class RelatorioDetalhamentoAcaoFinanceiroMensal {

 	
	private Double valorJaneiro;
	private Double valorFevereiro;
	private Double valorMarco;
	private Double valorAbril;
	private Double valorMaio;
	private Double valorJunho;
	private Double valorJulho;
	private Double valorAgosto;
	private Double valorSetembro;
	private Double valorOutubro;
	private Double valorNovembro;
	private Double valorDezembro;
	 
	
	public void setValor(int numeroMes,Double valor){
	
		if(valor==null) valor = 0d;
		 
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
	
 
	public Double getValorJaneiro() {
		return valorJaneiro;
	}
	public void setValorJaneiro(Double valorJaneiro) {
		this.valorJaneiro = valorJaneiro;
	}
	public Double getValorFevereiro() {
		return valorFevereiro;
	}
	public void setValorFevereiro(Double valorFevereiro) {
		this.valorFevereiro = valorFevereiro;
	}
	public Double getValorMarco() {
		return valorMarco;
	}
	public void setValorMarco(Double valorMarco) {
		this.valorMarco = valorMarco;
	}
	public Double getValorAbril() {
		return valorAbril;
	}
	public void setValorAbril(Double valorAbril) {
		this.valorAbril = valorAbril;
	}
	public Double getValorMaio() {
		return valorMaio;
	}
	public void setValorMaio(Double valorMaio) {
		this.valorMaio = valorMaio;
	}
	public Double getValorJunho() {
		return valorJunho;
	}
	public void setValorJunho(Double valorJunho) {
		this.valorJunho = valorJunho;
	}
	public Double getValorJulho() {
		return valorJulho;
	}
	public void setValorJulho(Double valorJulho) {
		this.valorJulho = valorJulho;
	}
	public Double getValorAgosto() {
		return valorAgosto;
	}
	public void setValorAgosto(Double valorAgosto) {
		this.valorAgosto = valorAgosto;
	}
	public Double getValorSetembro() {
		return valorSetembro;
	}
	public void setValorSetembro(Double valorSetembro) {
		this.valorSetembro = valorSetembro;
	}
	public Double getValorOutubro() {
		return valorOutubro;
	}
	public void setValorOutubro(Double valorOutubro) {
		this.valorOutubro = valorOutubro;
	}
	public Double getValorNovembro() {
		return valorNovembro;
	}
	public void setValorNovembro(Double valorNovembro) {
		this.valorNovembro = valorNovembro;
	}
	public Double getValorDezembro() {
		return valorDezembro;
	}
	public void setValorDezembro(Double valorDezembro) {
		this.valorDezembro = valorDezembro;
	}
	
	
	
	
	
	
}
