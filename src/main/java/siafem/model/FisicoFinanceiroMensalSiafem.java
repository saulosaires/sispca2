package siafem.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import qualitativo.model.Acao;
import qualitativo.model.Mes;

@Entity
@Table(name="financeiro_mensal", schema="siafem")
public class FisicoFinanceiroMensalSiafem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_financeiro_mensal_detalhado")
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="mes")
	private Mes mes;
 
	@Column(name="acao_codigo")
	private String acaoCodigo;	
	
	@Column(name="acao_descricao")
	private String acaDescricao;	
	
	@Column(name="plano_interno")
	private String planoInterno;		

	@Column(name="plano_interno_descricao")
	private String planoInternoDescricao;	
	
	@Column(name="fonte")
	private String fonte;	
	
	@Column(name="natureza")
	private String natureza;
	
	@Column(name="regiao")
	private Integer regiao;
		
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_acao",nullable=true)
	private Acao acao;
 
	@Column(name="unidade_gestora")
	private String unidadeGestora;	
	
	@Column(name="unidade_orcamentaria")
	private String unidadeOrcamentaria;	
	
	@Column(name="programa")
	private String programa;	
	
	@Column(name="dotacao_inicial")
	private BigDecimal dotacaoInicial;
	
	@Column(name="disponivel")
	private BigDecimal disponivel;

	@Column(name="liquidado")
	private BigDecimal liquidado;
 
	@Column(name="empenhado")
	private BigDecimal empenhado;

	@Column(name="pago")
	private BigDecimal pago;
	
	@Column(name="ano")
	private Integer ano;

	private  transient String programaDenominacao;
	
	private transient String codigoUnidadeOrcamentaria;
	private transient String descricaoUnidadeOrcamentaria;
	 
	private transient Long tipoCalculoMetaId;
	private transient String unidadeMedida;
	
	private transient BigDecimal planejado;
	private transient BigDecimal executado;
	private transient BigDecimal eficacia; 
 
	private transient BigDecimal liquidadoSobreAtual;
	
	private transient BigDecimal eficiencia;
	
	public FisicoFinanceiroMensalSiafem() {}
	
	
	public FisicoFinanceiroMensalSiafem(
									     Mes mes,
									     String unidadeGestora,
									     String codigoUnidadeOrcamentaria, 
									     String programa,
									     String acaoCodigo,
									     String acaDescricao,
									     String planoInterno,
									     String planoInternoDescricao,
									     String fonte,
									     String natureza,
									     Integer regiao,
									     Acao acao,
									     BigDecimal dotacaoInicial,
									     BigDecimal disponivel,
									     BigDecimal empenhado,
									     BigDecimal liquidado,
									     BigDecimal pago,
									     Integer ano
										) {
		
		
		 this.mes=mes;
		 this.unidadeGestora = unidadeGestora;
		 this.unidadeOrcamentaria = codigoUnidadeOrcamentaria;
		 this.programa = programa;
		 
		 this.acaoCodigo = acaoCodigo;
		 this.acaDescricao = acaDescricao;
		 this.planoInterno = planoInterno;
		 this.planoInternoDescricao = planoInternoDescricao;
		 this.fonte = fonte;
		 this.natureza = natureza;
		 this.regiao = regiao;
		 
		 this.acao = acao;
		 this.dotacaoInicial = dotacaoInicial;
		 this.disponivel = disponivel;
		 this.empenhado = empenhado;
		 this.liquidado = liquidado;
		 this.pago = pago;
		 this.ano = ano;
		
		
	}
	
	
	
	
	
 	public FisicoFinanceiroMensalSiafem(String programa,
										String codigoUnidadeOrcamentaria,  
									    String descricaoUnidadeOrcamentaria,
									    String acaoCodigo,
									    Long acaoId,
									    String acaoDenominacao,
									    String acaoProduto,
									    Long tipoCalculoMetaId,  
									    String unidadeMedida,
									    BigDecimal dotacaoInicial,
										BigDecimal disponivel, 
										BigDecimal empenhado,
										BigDecimal liquidado
										) {
		super();
		this.codigoUnidadeOrcamentaria = codigoUnidadeOrcamentaria;
		this.descricaoUnidadeOrcamentaria=descricaoUnidadeOrcamentaria;
		this.programa = programa;
		
		this.acao = new Acao();
		this.acao.setId(acaoId);
		this.acao.setDenominacao(acaoDenominacao);
		this.acao.setProduto(acaoProduto);
		this.acao.setCodigo(acaoCodigo);
		this.acao.getTipoCalculoMeta().setId(tipoCalculoMetaId);
		
		this.dotacaoInicial = dotacaoInicial;
		this.disponivel = disponivel;
		this.liquidado = liquidado;
		this.empenhado = empenhado;
		
 
		this.tipoCalculoMetaId=tipoCalculoMetaId;
		this.unidadeMedida=unidadeMedida;
	}

 	public FisicoFinanceiroMensalSiafem(
									    BigDecimal dotacaoInicial,
										BigDecimal disponivel, 
										BigDecimal empenhado,
										BigDecimal liquidado
										) {
			super();
			      
			this.dotacaoInicial = dotacaoInicial;
			this.disponivel = disponivel;
			this.liquidado = liquidado;
			this.empenhado = empenhado;
		 
 	}

 	public FisicoFinanceiroMensalSiafem(
										String codigoUnidadeOrcamentaria,  
									    String descricaoUnidadeOrcamentaria,
							 			String programa,
							 			String programaDenominacao,
									    BigDecimal dotacaoInicial,
										BigDecimal disponivel, 
										BigDecimal empenhado,
										BigDecimal liquidado,
										BigDecimal pago
										) {
			super();
			
			this.codigoUnidadeOrcamentaria = codigoUnidadeOrcamentaria;
			this.descricaoUnidadeOrcamentaria = descricaoUnidadeOrcamentaria;
			this.programa = programa;
			this.programaDenominacao = programaDenominacao;
			
			this.dotacaoInicial = dotacaoInicial;
			this.disponivel = disponivel;
			this.liquidado = liquidado;
			this.empenhado = empenhado;
			this.pago = pago;				
  }
 	

 	public FisicoFinanceiroMensalSiafem(
										String codigoUnidadeOrcamentaria,  
									    String descricaoUnidadeOrcamentaria,
							 			String programa,
							 			String programaDenominacao,
							 			Mes mes,
									    BigDecimal dotacaoInicial,
										BigDecimal disponivel, 
										BigDecimal empenhado,
										BigDecimal liquidado,
										BigDecimal pago
										) {
			super();
			      
			this.codigoUnidadeOrcamentaria = codigoUnidadeOrcamentaria;
			this.descricaoUnidadeOrcamentaria = descricaoUnidadeOrcamentaria;
			this.programa = programa;
			this.programaDenominacao = programaDenominacao;
			this.mes = mes;
			this.dotacaoInicial = dotacaoInicial;
			this.disponivel = disponivel;
			this.liquidado = liquidado;
			this.empenhado = empenhado;
		    this.pago = pago;
 	}
 	

 	
 	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Mes getMes() {
		return mes;
	}

	public void setMes(Mes mes) {
		this.mes = mes;
	}

	public String getUnidadeGestora() {
		return unidadeGestora;
	}

	public void setUnidadeGestora(String unidadeGestora) {
		this.unidadeGestora = unidadeGestora;
	}

	public String getUnidadeOrcamentaria() {
		return unidadeOrcamentaria;
	}

	public void setUnidadeOrcamentaria(String unidadeOrcamentaria) {
		this.unidadeOrcamentaria = unidadeOrcamentaria;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}
 
	public BigDecimal getDotacaoInicial() {
		return dotacaoInicial;
	}

	public void setDotacaoInicial(BigDecimal dotacaoInicial) {
		this.dotacaoInicial = dotacaoInicial;
	}

	public BigDecimal getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(BigDecimal disponivel) {
		this.disponivel = disponivel;
	}

	public BigDecimal getEmpenhado() {
		return empenhado;
	}

	public void setEmpenhado(BigDecimal empenhado) {
		this.empenhado = empenhado;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public BigDecimal getLiquidado() {
		return liquidado;
	}

	public void setLiquidado(BigDecimal liquidado) {
		this.liquidado = liquidado;
	}


	public String getDescricaoUnidadeOrcamentaria() {
		return descricaoUnidadeOrcamentaria;
	}


	public void setDescricaoUnidadeOrcamentaria(String descricaoUnidadeOrcamentaria) {
		this.descricaoUnidadeOrcamentaria = descricaoUnidadeOrcamentaria;
	}

  

	public Acao getAcao() {
		return acao;
	}


	public void setAcao(Acao acao) {
		this.acao = acao;
	}


 

	public Long getTipoCalculoMetaId() {
		return tipoCalculoMetaId;
	}


	public void setTipoCalculoMetaId(Long tipoCalculoMetaId) {
		this.tipoCalculoMetaId = tipoCalculoMetaId;
	}


	public BigDecimal getPlanejado() {
		return planejado;
	}


	public void setPlanejado(BigDecimal planejado) {
		this.planejado = planejado;
	}


	public BigDecimal getExecutado() {
		return executado;
	}


	public void setExecutado(BigDecimal executado) {
		this.executado = executado;
	}


	public String getUnidadeMedida() {
		return unidadeMedida;
	}


	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}


	public BigDecimal getEficacia() {
		return eficacia;
	}


	public void setEficacia(BigDecimal eficacia) {
		this.eficacia = eficacia;
	}
 

	public BigDecimal getLiquidadoSobreAtual() {
		return liquidadoSobreAtual;
	}


	public void setLiquidadoSobreAtual(BigDecimal liquidadoSobreAtual) {
		this.liquidadoSobreAtual = liquidadoSobreAtual;
	}


	public BigDecimal getEficiencia() {
		return eficiencia;
	}


	public void setEficiencia(BigDecimal eficiencia) {
		this.eficiencia = eficiencia;
	}

	public String getCodigoUnidadeOrcamentaria() {
		return codigoUnidadeOrcamentaria;
	}

	public void setCodigoUnidadeOrcamentaria(String codigoUnidadeOrcamentaria) {
		this.codigoUnidadeOrcamentaria = codigoUnidadeOrcamentaria;
	}

	public BigDecimal getPago() {
		return pago;
	}

	public void setPago(BigDecimal pago) {
		this.pago = pago;
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


	public String getAcaDescricao() {
		return acaDescricao;
	}


	public void setAcaDescricao(String acaDescricao) {
		this.acaDescricao = acaDescricao;
	}


	public String getPlanoInterno() {
		return planoInterno;
	}


	public void setPlanoInterno(String planoInterno) {
		this.planoInterno = planoInterno;
	}


	public String getPlanoInternoDescricao() {
		return planoInternoDescricao;
	}


	public void setPlanoInternoDescricao(String planoInternoDescricao) {
		this.planoInternoDescricao = planoInternoDescricao;
	}


	public String getFonte() {
		return fonte;
	}


	public void setFonte(String fonte) {
		this.fonte = fonte;
	}


	public String getNatureza() {
		return natureza;
	}


	public void setNatureza(String natureza) {
		this.natureza = natureza;
	}


	public Integer getRegiao() {
		return regiao;
	}


	public void setRegiao(Integer regiao) {
		this.regiao = regiao;
	}

 
	
	
}
