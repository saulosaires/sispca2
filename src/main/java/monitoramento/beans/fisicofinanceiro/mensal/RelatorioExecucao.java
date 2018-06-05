package monitoramento.beans.fisicofinanceiro.mensal;

import java.math.BigDecimal;

import arquitetura.utils.MathUtils;
import monitoramento.model.Execucao;

public class RelatorioExecucao {
	
	private Long regiaoMunicipioId;
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
			regiaoMunicipioId =  e.getRegiaoMunicipio().getId();
			if(e.getRegiaoMunicipio().getRegiao()!=null) {
				regiao = e.getRegiaoMunicipio().getRegiao().getDescricao();
				regiaoId= e.getRegiaoMunicipio().getRegiao().getId();
			}
			if(e.getRegiaoMunicipio().getMunicipio()!=null)
				municipio = e.getRegiaoMunicipio().getMunicipio().getDescricao();
		
		}


		int mesId  = e.getMes().getId().intValue();
		
		BigDecimal qtd   = e.getQuantidade();
		BigDecimal valor = e.getValor();
		
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

	public Long getRegiaoMunicipioId() {
		return regiaoMunicipioId;
	}

	public void setRegiaoMunicipioId(Long regiaoMunicipioId) {
		this.regiaoMunicipioId = regiaoMunicipioId;
	}		

	
	
	
}
