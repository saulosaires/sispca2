package relatorio.model;

import java.util.List;

import qualitativo.model.Acao;
import quantitativo.model.FisicoFinanceiro;

public class RelatorioQuantitativoAnual {

	private Acao acao;
	
	private List<FisicoFinanceiro> listFisicoFinanceiro;

	
	
	public RelatorioQuantitativoAnual(Acao acao, List<FisicoFinanceiro> listFisicoFinanceiro) {
		super();
		this.acao = acao;
		this.listFisicoFinanceiro = listFisicoFinanceiro;
	 
	}

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public List<FisicoFinanceiro> getListFisicoFinanceiro() {
		return listFisicoFinanceiro;
	}

	public void setListFisicoFinanceiro(List<FisicoFinanceiro> listFisicoFinanceiro) {
		this.listFisicoFinanceiro = listFisicoFinanceiro;
	}
	
	
	
	
	
}
