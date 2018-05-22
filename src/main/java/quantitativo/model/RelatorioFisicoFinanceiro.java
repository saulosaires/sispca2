package quantitativo.model;

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
	
	private Double qtdjan=0d;
	
	private Double valorjan=0d;
	
	private Double qtdfev=0d;
	
	private Double valorfev=0d;
	
	private Double qtdmar=0d;
	
	private Double valormar=0d;
	
	private Double qtdabr=0d;
	
	private Double valorabr=0d;
	
	private Double qtdmai=0d;
	
	private Double valormai=0d;
	
	private Double qtdjun=0d;
	
	private Double valorjun=0d;
	
	private Double qtdjul=0d;
	
	private Double valorjul=0d;
	
	private Double qtdago=0d;
	
	private Double valorago=0d;
	
	private Double qtdset=0d;
	
	private Double valorset=0d;
	
	private Double qtdout=0d;
	
	private Double valorout=0d;
	
	private Double qtdnov=0d;
	
	private Double valornov=0d;
	
	private Double qtddez=0d;
	
	private Double valordez=0d;
	
	 
	
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
		
		double qtd   = fisicoFinanceiro.getQuantidade();
		double valor = fisicoFinanceiro.getValor();
		
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
			qtddez= 0d;
			valordez=0d;
			break;
		}
		
		
	}


	public Double getQtdjan() {
		return qtdjan;
	}

	public void setQtdjan(Double qtdjan) {
		this.qtdjan = qtdjan;
	}

	public Double getValorjan() {
		return valorjan;
	}

	public void setValorjan(Double valorjan) {
		this.valorjan = valorjan;
	}

	public Double getQtdfev() {
		return qtdfev;
	}

	public void setQtdfev(Double qtdfev) {
		this.qtdfev = qtdfev;
	}

	public Double getValorfev() {
		return valorfev;
	}

	public void setValorfev(Double valorfev) {
		this.valorfev = valorfev;
	}

	public Double getQtdmar() {
		return qtdmar;
	}

	public void setQtdmar(Double qtdmar) {
		this.qtdmar = qtdmar;
	}

	public Double getValormar() {
		return valormar;
	}

	public void setValormar(Double valormar) {
		this.valormar = valormar;
	}

	public Double getQtdabr() {
		return qtdabr;
	}

	public void setQtdabr(Double qtdabr) {
		this.qtdabr = qtdabr;
	}

	public Double getValorabr() {
		return valorabr;
	}

	public void setValorabr(Double valorabr) {
		this.valorabr = valorabr;
	}

	public Double getQtdmai() {
		return qtdmai;
	}

	public void setQtdmai(Double qtdmai) {
		this.qtdmai = qtdmai;
	}

	public Double getValormai() {
		return valormai;
	}

	public void setValormai(Double valormai) {
		this.valormai = valormai;
	}

	public Double getQtdjun() {
		return qtdjun;
	}

	public void setQtdjun(Double qtdjun) {
		this.qtdjun = qtdjun;
	}

	public Double getValorjun() {
		return valorjun;
	}

	public void setValorjun(Double valorjun) {
		this.valorjun = valorjun;
	}

	public Double getQtdjul() {
		return qtdjul;
	}

	public void setQtdjul(Double qtdjul) {
		this.qtdjul = qtdjul;
	}

	public Double getValorjul() {
		return valorjul;
	}

	public void setValorjul(Double valorjul) {
		this.valorjul = valorjul;
	}

	public Double getQtdago() {
		return qtdago;
	}

	public void setQtdago(Double qtdago) {
		this.qtdago = qtdago;
	}

	public Double getValorago() {
		return valorago;
	}

	public void setValorago(Double valorago) {
		this.valorago = valorago;
	}

	public Double getQtdset() {
		return qtdset;
	}

	public void setQtdset(Double qtdset) {
		this.qtdset = qtdset;
	}

	public Double getValorset() {
		return valorset;
	}

	public void setValorset(Double valorset) {
		this.valorset = valorset;
	}

	public Double getQtdout() {
		return qtdout;
	}

	public void setQtdout(Double qtdout) {
		this.qtdout = qtdout;
	}

	public Double getValorout() {
		return valorout;
	}

	public void setValorout(Double valorout) {
		this.valorout = valorout;
	}

	public Double getQtdnov() {
		return qtdnov;
	}

	public void setQtdnov(Double qtdnov) {
		this.qtdnov = qtdnov;
	}

	public Double getValornov() {
		return valornov;
	}

	public void setValornov(Double valornov) {
		this.valornov = valornov;
	}

	public Double getQtddez() {
		return qtddez;
	}

	public void setQtddez(Double qtddez) {
		this.qtddez = qtddez;
	}

	public Double getValordez() {
		return valordez;
	}

	public void setValordez(Double valordez) {
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
