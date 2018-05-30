package quantitativo.model;

import java.math.BigDecimal;

import arquitetura.utils.MathUtils;

public class RelatorioFisicoFinanceiro {
 
	private Long id;
	
	private String orgaoCodigo;
	private String orgaoDescricao;
	
	private String unidadeOrcamentariaCodigo;
	private String unidadeOrcamentariaDescricao;
	
	private String programaCodigo;
	private String programaDenominacao;
	
	private String acaoCodigo;
	private String acaoDenominacao;
	private String acaoProduto;
	
	private String unidadeMedidaDescricao;	
	
	private String regiaoMunicipio;	
	
	private BigDecimal qtdjan=MathUtils.getZeroBigDecimal();
	
	private BigDecimal valorjan=MathUtils.getZeroBigDecimal();
	
	private BigDecimal qtdfev=MathUtils.getZeroBigDecimal();
	
	private BigDecimal valorfev=MathUtils.getZeroBigDecimal();
	
	private BigDecimal qtdmar=MathUtils.getZeroBigDecimal();
	
	private BigDecimal valormar=MathUtils.getZeroBigDecimal();
	
	private BigDecimal qtdabr=MathUtils.getZeroBigDecimal();
	
	private BigDecimal valorabr=MathUtils.getZeroBigDecimal();
	
	private BigDecimal qtdmai=MathUtils.getZeroBigDecimal();
	
	private BigDecimal valormai=MathUtils.getZeroBigDecimal();
	
	private BigDecimal qtdjun=MathUtils.getZeroBigDecimal();
	
	private BigDecimal valorjun=MathUtils.getZeroBigDecimal();
	
	private BigDecimal qtdjul=MathUtils.getZeroBigDecimal();
	
	private BigDecimal valorjul=MathUtils.getZeroBigDecimal();
	
	private BigDecimal qtdago=MathUtils.getZeroBigDecimal();
	
	private BigDecimal valorago=MathUtils.getZeroBigDecimal();
	
	private BigDecimal qtdset=MathUtils.getZeroBigDecimal();
	
	private BigDecimal valorset=MathUtils.getZeroBigDecimal();
	
	private BigDecimal qtdout=MathUtils.getZeroBigDecimal();
	
	private BigDecimal valorout=MathUtils.getZeroBigDecimal();
	
	private BigDecimal qtdnov=MathUtils.getZeroBigDecimal();
	
	private BigDecimal valornov=MathUtils.getZeroBigDecimal();
	
	private BigDecimal qtddez=MathUtils.getZeroBigDecimal();
	
	private BigDecimal valordez=MathUtils.getZeroBigDecimal();
	
	 
	
	public RelatorioFisicoFinanceiro(FisicoFinanceiroMensal fisicoFinanceiro){
		
		setData(fisicoFinanceiro);
	}
	
	public void setData(FisicoFinanceiroMensal fisicoFinanceiro) {


		if( fisicoFinanceiro.getAcao()!=null) {
		
			if(fisicoFinanceiro.getAcao().getUnidadeOrcamentaria()!=null) {
				
				unidadeOrcamentariaCodigo    = fisicoFinanceiro.getAcao().getUnidadeOrcamentaria().getCodigo();		
				unidadeOrcamentariaDescricao = fisicoFinanceiro.getAcao().getUnidadeOrcamentaria().getDescricao();
				
				if(fisicoFinanceiro.getAcao().getUnidadeOrcamentaria().getOrgao()!=null) {
					orgaoCodigo     = fisicoFinanceiro.getAcao().getUnidadeOrcamentaria().getOrgao().getCodigo();
					orgaoDescricao  = fisicoFinanceiro.getAcao().getUnidadeOrcamentaria().getOrgao().getDescricao();
				}
				
			}
				

			acaoCodigo = fisicoFinanceiro.getAcao().getCodigo();
			
			acaoDenominacao = fisicoFinanceiro.getAcao().getDenominacao();
			
			acaoProduto = fisicoFinanceiro.getAcao().getProduto();
			
			if(fisicoFinanceiro.getAcao().getUnidadeMedida()!=null)
			unidadeMedidaDescricao = fisicoFinanceiro.getAcao().getUnidadeMedida().getDescricao();
			
			if(fisicoFinanceiro.getAcao().getPrograma()!=null) {
				programaCodigo      = fisicoFinanceiro.getAcao().getPrograma().getCodigo();
				programaDenominacao = fisicoFinanceiro.getAcao().getPrograma().getDenominacao();
			}
		}
		
		if(fisicoFinanceiro.getRegiaoMunicipio()!=null)
			regiaoMunicipio = fisicoFinanceiro.getRegiaoMunicipio().toString();
	 


		int mesId  = fisicoFinanceiro.getMes().getId().intValue();
		
		BigDecimal qtd   = fisicoFinanceiro.getQuantidade();
		BigDecimal valor = fisicoFinanceiro.getValor();
		
		switch(mesId) {
		
		case 1:
			qtdjan= qtd;
			valorjan=valor;
			break;
		 

		case 2:
			qtdfev= qtd;
			valorfev=valor;
			break;
		 
		case 3:
			qtdmar= qtd;
			valormar=valor;
			break;
		 
		
		case 4:
			qtdabr= qtd;
			valorabr=valor;
			break;
		 
		
		case 5:
			qtdmai= qtd;
			valormai=valor;
			break;
		 
		
		case 6:
			qtdjun= qtd;
			valorjun=valor;
			break;
		 
		
		case 7:
			qtdjul= qtd;
			valorjul=valor;
			break;
		 
		
		case 8:
			qtdago= qtd;
			valorago=valor;
			break;
		 
		case 9:
			qtdset= qtd;
			valorset=valor;
			break;
		 
		case 10:
			qtdout= qtd;
			valorout=valor;
			break;
		 
		case 11:
			qtdnov= qtd;
			valornov=valor;
			break;
		 
		case 12:
			qtddez= qtd;
			valordez=valor;
			break;
		 
		
		default:
			qtddez   = MathUtils.getZeroBigDecimal();
			valordez = MathUtils.getZeroBigDecimal();
			break;
		}
		
		
	}


	public BigDecimal getQtdjan() {
		return qtdjan;
	}

	public void setQtdjan(BigDecimal qtdjan) {
		this.qtdjan = qtdjan;
	}

	public BigDecimal getValorjan() {
		return valorjan;
	}

	public void setValorjan(BigDecimal valorjan) {
		this.valorjan = valorjan;
	}

	public BigDecimal getQtdfev() {
		return qtdfev;
	}

	public void setQtdfev(BigDecimal qtdfev) {
		this.qtdfev = qtdfev;
	}

	public BigDecimal getValorfev() {
		return valorfev;
	}

	public void setValorfev(BigDecimal valorfev) {
		this.valorfev = valorfev;
	}

	public BigDecimal getQtdmar() {
		return qtdmar;
	}

	public void setQtdmar(BigDecimal qtdmar) {
		this.qtdmar = qtdmar;
	}

	public BigDecimal getValormar() {
		return valormar;
	}

	public void setValormar(BigDecimal valormar) {
		this.valormar = valormar;
	}

	public BigDecimal getQtdabr() {
		return qtdabr;
	}

	public void setQtdabr(BigDecimal qtdabr) {
		this.qtdabr = qtdabr;
	}

	public BigDecimal getValorabr() {
		return valorabr;
	}

	public void setValorabr(BigDecimal valorabr) {
		this.valorabr = valorabr;
	}

	public BigDecimal getQtdmai() {
		return qtdmai;
	}

	public void setQtdmai(BigDecimal qtdmai) {
		this.qtdmai = qtdmai;
	}

	public BigDecimal getValormai() {
		return valormai;
	}

	public void setValormai(BigDecimal valormai) {
		this.valormai = valormai;
	}

	public BigDecimal getQtdjun() {
		return qtdjun;
	}

	public void setQtdjun(BigDecimal qtdjun) {
		this.qtdjun = qtdjun;
	}

	public BigDecimal getValorjun() {
		return valorjun;
	}

	public void setValorjun(BigDecimal valorjun) {
		this.valorjun = valorjun;
	}

	public BigDecimal getQtdjul() {
		return qtdjul;
	}

	public void setQtdjul(BigDecimal qtdjul) {
		this.qtdjul = qtdjul;
	}

	public BigDecimal getValorjul() {
		return valorjul;
	}

	public void setValorjul(BigDecimal valorjul) {
		this.valorjul = valorjul;
	}

	public BigDecimal getQtdago() {
		return qtdago;
	}

	public void setQtdago(BigDecimal qtdago) {
		this.qtdago = qtdago;
	}

	public BigDecimal getValorago() {
		return valorago;
	}

	public void setValorago(BigDecimal valorago) {
		this.valorago = valorago;
	}

	public BigDecimal getQtdset() {
		return qtdset;
	}

	public void setQtdset(BigDecimal qtdset) {
		this.qtdset = qtdset;
	}

	public BigDecimal getValorset() {
		return valorset;
	}

	public void setValorset(BigDecimal valorset) {
		this.valorset = valorset;
	}

	public BigDecimal getQtdout() {
		return qtdout;
	}

	public void setQtdout(BigDecimal qtdout) {
		this.qtdout = qtdout;
	}

	public BigDecimal getValorout() {
		return valorout;
	}

	public void setValorout(BigDecimal valorout) {
		this.valorout = valorout;
	}

	public BigDecimal getQtdnov() {
		return qtdnov;
	}

	public void setQtdnov(BigDecimal qtdnov) {
		this.qtdnov = qtdnov;
	}

	public BigDecimal getValornov() {
		return valornov;
	}

	public void setValornov(BigDecimal valornov) {
		this.valornov = valornov;
	}

	public BigDecimal getQtddez() {
		return qtddez;
	}

	public void setQtddez(BigDecimal qtddez) {
		this.qtddez = qtddez;
	}

	public BigDecimal getValordez() {
		return valordez;
	}

	public void setValordez(BigDecimal valordez) {
		this.valordez = valordez;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrgaoCodigo() {
		return orgaoCodigo;
	}

	public void setOrgaoCodigo(String orgaoCodigo) {
		this.orgaoCodigo = orgaoCodigo;
	}

	public String getOrgaoDescricao() {
		return orgaoDescricao;
	}

	public void setOrgaoDescricao(String orgaoDescricao) {
		this.orgaoDescricao = orgaoDescricao;
	}

	public String getUnidadeOrcamentariaCodigo() {
		return unidadeOrcamentariaCodigo;
	}

	public void setUnidadeOrcamentariaCodigo(String unidadeOrcamentariaCodigo) {
		this.unidadeOrcamentariaCodigo = unidadeOrcamentariaCodigo;
	}

	public String getUnidadeOrcamentariaDescricao() {
		return unidadeOrcamentariaDescricao;
	}

	public void setUnidadeOrcamentariaDescricao(String unidadeOrcamentariaDescricao) {
		this.unidadeOrcamentariaDescricao = unidadeOrcamentariaDescricao;
	}

	public String getProgramaCodigo() {
		return programaCodigo;
	}

	public void setProgramaCodigo(String programaCodigo) {
		this.programaCodigo = programaCodigo;
	}

	public String getProgramaDenominacao() {
		return programaDenominacao;
	}

	public void setProgramaDenominacao(String programaDenominacao) {
		this.programaDenominacao = programaDenominacao;
	}

	public String getAcaoCodigo() {
		return acaoCodigo;
	}

	public void setAcaoCodigo(String acaoCodigo) {
		this.acaoCodigo = acaoCodigo;
	}

	public String getAcaoDenominacao() {
		return acaoDenominacao;
	}

	public void setAcaoDenominacao(String acaoDenominacao) {
		this.acaoDenominacao = acaoDenominacao;
	}

	public String getAcaoProduto() {
		return acaoProduto;
	}

	public void setAcaoProduto(String acaoProduto) {
		this.acaoProduto = acaoProduto;
	}

	public String getUnidadeMedidaDescricao() {
		return unidadeMedidaDescricao;
	}

	public void setUnidadeMedidaDescricao(String unidadeMedidaDescricao) {
		this.unidadeMedidaDescricao = unidadeMedidaDescricao;
	}

	public String getRegiaoMunicipio() {
		return regiaoMunicipio;
	}

	public void setRegiaoMunicipio(String regiaoMunicipio) {
		this.regiaoMunicipio = regiaoMunicipio;
	}



	 
	
	
	
}
