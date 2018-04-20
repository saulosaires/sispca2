package monitoramento.beans.fisicofinanceiro.mensal;

import monitoramento.model.Execucao;

public class RelatorioExecucao {
	
	private Long regiaoId;
	
	private String codigo_orgao;
	
	private String orgao;
	
	private String codigo_unidade_orcamentaria;
	
	private String unidade_orcamentaria;
	
	private String codigo_programa;
	
	private String programa;
	
	private String regiaoMunicipio;
	
	private String regiao;
	
	private String municipio;
	
	private String codigo_acao;
	
	private String acao;
	
	private String produto_acao;
	
	private String unidade_medida;
	
	
	
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
	
	private String obsjan;
	
	private String obsfev;
	
	private String obsmar;
	
	private String obsabr;
	
	private String obsmai;
	
	private String obsjun;
	
	private String obsjul;
	
	private String obsago;
	
	private String obsset;
	
	private String obsout;
	
	private String obsnov;
	
	private String obsdez;
	
	public RelatorioExecucao(Execucao e){
		
		setData(e);
	}
	
	public void setData(Execucao e) {
		

		if( e.getAcao()!=null) {
		
			if(e.getAcao().getUnidadeOrcamentaria()!=null) {
				
				codigo_unidade_orcamentaria = e.getAcao().getUnidadeOrcamentaria().getCodigo();		
				unidade_orcamentaria 		= e.getAcao().getUnidadeOrcamentaria().getDescricao();
				
				if(e.getAcao().getUnidadeOrcamentaria().getOrgao()!=null) {
					codigo_orgao = e.getAcao().getUnidadeOrcamentaria().getOrgao().getCodigo();
					orgao 		 = e.getAcao().getUnidadeOrcamentaria().getOrgao().getDescricao();
				}
				
			}
				

			codigo_acao = e.getAcao().getCodigo();
			
			acao = e.getAcao().getDenominacao();
			
			produto_acao = e.getAcao().getProduto();
			
			unidade_medida = e.getAcao().getUnidadeMedida().getDescricao();
			
			codigo_programa = e.getAcao().getPrograma().getCodigo();
			programa 		= e.getAcao().getPrograma().getDenominacao();
		
		}
		
		
		if(e.getRegiaoMunicipio()!=null) {
			
			regiaoMunicipio =  e.getRegiaoMunicipio().getLabel();
			
			if(e.getRegiaoMunicipio().getRegiao()!=null) {
				regiao = e.getRegiaoMunicipio().getRegiao().getDescricao();
				regiaoId= e.getRegiaoMunicipio().getRegiao().getId();
			}
			if(e.getRegiaoMunicipio().getMunicipio()!=null)
				municipio = e.getRegiaoMunicipio().getMunicipio().getDescricao();
		
		}


		int mesId  = e.getMes().getId().intValue();
		
		double qtd   = e.getQuantidade();
		double valor = e.getValor();
		
		switch(mesId) {
		
		case 1:{
			qtdjan= qtd;
			valorjan=valor;
			obsjan= e.getObservacao();
			break;
		 }

		case 2:{
			qtdfev= qtd;
			valorfev=valor;
			obsfev= e.getObservacao();
			break;
		 }
		case 3:{
			qtdmar= qtd;
			valormar=valor;
			obsmar= e.getObservacao();
			break;
		 }
		case 4:{
			qtdabr= qtd;
			valorabr=valor;
			obsabr= e.getObservacao();
			break;
		 }
		case 5:{
			qtdmai= qtd;
			valormai=valor;
			obsmai= e.getObservacao();
			break;
		 }
		case 6:{
			qtdjun= qtd;
			valorjun=valor;
			obsjun= e.getObservacao();
			break;
		 }
		case 7:{
			qtdjul= qtd;
			valorjul=valor;
			obsjul= e.getObservacao();
			break;
		 }
		case 8:{
			qtdago= qtd;
			valorago=valor;
			obsago= e.getObservacao();
			break;
		 }
		case 9:{
			qtdset= qtd;
			valorset=valor;
			obsset= e.getObservacao();
			break;
		 }
		case 10:{
			qtdout= qtd;
			valorout=valor;
			obsout= e.getObservacao();
			break;
		 }
		case 11:{
			qtdnov= qtd;
			valornov=valor;
			obsnov= e.getObservacao();
			break;
		 }
		case 12:{
			qtddez= qtd;
			valordez=valor;
			obsdez= e.getObservacao();
			break;
		 }
		
		
		}
		
		
	}
	
	
	
	
	public String getCodigo_orgao() {
		return codigo_orgao;
	}

	public void setCodigo_orgao(String codigo_orgao) {
		this.codigo_orgao = codigo_orgao;
	}

	public String getOrgao() {
		return orgao;
	}

	public void setOrgao(String orgao) {
		this.orgao = orgao;
	}

	public String getCodigo_unidade_orcamentaria() {
		return codigo_unidade_orcamentaria;
	}

	public void setCodigo_unidade_orcamentaria(String codigo_unidade_orcamentaria) {
		this.codigo_unidade_orcamentaria = codigo_unidade_orcamentaria;
	}

	public String getUnidade_orcamentaria() {
		return unidade_orcamentaria;
	}

	public void setUnidade_orcamentaria(String unidade_orcamentaria) {
		this.unidade_orcamentaria = unidade_orcamentaria;
	}

	public String getRegiaoMunicipio() {
		return regiaoMunicipio;
	}

	public void setRegiaoMunicipio(String regiaoMunicipio) {
		this.regiaoMunicipio = regiaoMunicipio;
	}

	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public String getCodigo_programa() {
		return codigo_programa;
	}

	public void setCodigo_programa(String codigo_programa) {
		this.codigo_programa = codigo_programa;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public String getProduto_acao() {
		return produto_acao;
	}

	public void setProduto_acao(String produto_acao) {
		this.produto_acao = produto_acao;
	}

	public String getUnidade_medida() {
		return unidade_medida;
	}

	public void setUnidade_medida(String unidade_medida) {
		this.unidade_medida = unidade_medida;
	}

	public String getCodigo_acao() {
		return codigo_acao;
	}

	public void setCodigo_acao(String codigo_acao) {
		this.codigo_acao = codigo_acao;
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

	public String getObsjan() {
		return obsjan;
	}

	public void setObsjan(String obsjan) {
		this.obsjan = obsjan;
	}

	public String getObsfev() {
		return obsfev;
	}

	public void setObsfev(String obsfev) {
		this.obsfev = obsfev;
	}

	public String getObsmar() {
		return obsmar;
	}

	public void setObsmar(String obsmar) {
		this.obsmar = obsmar;
	}

	public String getObsabr() {
		return obsabr;
	}

	public void setObsabr(String obsabr) {
		this.obsabr = obsabr;
	}

	public String getObsmai() {
		return obsmai;
	}

	public void setObsmai(String obsmai) {
		this.obsmai = obsmai;
	}

	public String getObsjun() {
		return obsjun;
	}

	public void setObsjun(String obsjun) {
		this.obsjun = obsjun;
	}

	public String getObsjul() {
		return obsjul;
	}

	public void setObsjul(String obsjul) {
		this.obsjul = obsjul;
	}

	public String getObsago() {
		return obsago;
	}

	public void setObsago(String obsago) {
		this.obsago = obsago;
	}

	public String getObsset() {
		return obsset;
	}

	public void setObsset(String obsset) {
		this.obsset = obsset;
	}

	public String getObsout() {
		return obsout;
	}

	public void setObsout(String obsout) {
		this.obsout = obsout;
	}

	public String getObsnov() {
		return obsnov;
	}

	public void setObsnov(String obsnov) {
		this.obsnov = obsnov;
	}

	public String getObsdez() {
		return obsdez;
	}

	public void setObsdez(String obsdez) {
		this.obsdez = obsdez;
	}

	public Long getRegiaoId() {
		return regiaoId;
	}

	public void setRegiaoId(Long regiaoId) {
		this.regiaoId = regiaoId;
	}		

	
	
	
}
