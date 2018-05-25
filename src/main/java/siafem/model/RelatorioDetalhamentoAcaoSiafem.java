package siafem.model;

import qualitativo.model.Acao;

public class RelatorioDetalhamentoAcaoSiafem {
 
	
	private RelatorioDetalhamentoAcaoValue dotacalInicial;
	private RelatorioDetalhamentoAcaoValue empenhado;
	private RelatorioDetalhamentoAcaoValue liquidado;
	private RelatorioDetalhamentoAcaoValue disponivel;
	
	
	
	
	public RelatorioDetalhamentoAcaoSiafem( 
											RelatorioDetalhamentoAcaoValue dotacalInicial,
											RelatorioDetalhamentoAcaoValue empenhado, 
											RelatorioDetalhamentoAcaoValue liquidado,
											RelatorioDetalhamentoAcaoValue disponivel) {
		
		super();
		 
		this.dotacalInicial = dotacalInicial;
		this.empenhado = empenhado;
		this.liquidado = liquidado;
		this.disponivel = disponivel;
	}
	 
	public RelatorioDetalhamentoAcaoValue getDotacalInicial() {
		return dotacalInicial;
	}
	public void setDotacalInicial(RelatorioDetalhamentoAcaoValue dotacalInicial) {
		this.dotacalInicial = dotacalInicial;
	}
	public RelatorioDetalhamentoAcaoValue getEmpenhado() {
		return empenhado;
	}
	public void setEmpenhado(RelatorioDetalhamentoAcaoValue empenhado) {
		this.empenhado = empenhado;
	}
	public RelatorioDetalhamentoAcaoValue getLiquidado() {
		return liquidado;
	}
	public void setLiquidado(RelatorioDetalhamentoAcaoValue liquidado) {
		this.liquidado = liquidado;
	}
	public RelatorioDetalhamentoAcaoValue getDisponivel() {
		return disponivel;
	}
	public void setDisponivel(RelatorioDetalhamentoAcaoValue disponivel) {
		this.disponivel = disponivel;
	}	
	 
	
	
	
	
}
