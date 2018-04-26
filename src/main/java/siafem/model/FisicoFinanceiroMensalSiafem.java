package siafem.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import qualitativo.model.Acao;
import qualitativo.model.Mes;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="financeiro_mensal", schema="siafem")
public class FisicoFinanceiroMensalSiafem implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_financeiro_mensal_detalhado")
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="mes")
	private Mes mes;

	/*
 TODO depois que migrar os dados eu posso usar chave estrangeira, mas agora vou so mapear as colunas diretamente	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="unidade_gestora")
	private UnidadeGestora unidadeGestora;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="unidade_orcamentaria")
	private UnidadeOrcamentaria unidadeOrcamentaria;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="programa")
	private Programa programa;
	*/
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_acao",nullable=true)
	private Acao acao;

	
	
	@Column(name="unidade_gestora")
	private Long unidadeGestora=0l;	
	
	@Column(name="unidade_orcamentaria")
	private Long unidadeOrcamentaria=0l;	
	
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

	@Column(name="ano")
	private Integer ano;

	private transient String codigoUnidadeOrcamentaria;
	private transient String descricaoUnidadeOrcamentaria;
	
	private transient Long acaoId;
	private transient String acaoDenominacao;
	private transient String acaoCodigo;
	private transient String acaoProduto;
	private transient Long tipoCalculoMetaId;
	private transient String unidadeMedida;
	
	private transient Double planejado;
	private transient Double executado;
	private transient Double eficacia;
	
	private transient BigDecimal dotacaoAtual;
 
	private transient BigDecimal liquidadoSobreAtual;
	
	private transient BigDecimal eficiencia;
	
	public FisicoFinanceiroMensalSiafem() {}
	
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
										BigDecimal liquidado, 
										BigDecimal empenhado) {
		super();
		this.codigoUnidadeOrcamentaria = codigoUnidadeOrcamentaria;
		this.descricaoUnidadeOrcamentaria=descricaoUnidadeOrcamentaria;
		this.programa = programa;
		
		this.acao = new Acao();
		this.acao.setId(acaoId);
		this.acao.setDenominacao(acaoDenominacao);
		this.acao.setProduto(acaoProduto);
		this.acao.getTipoCalculoMeta().setId(tipoCalculoMetaId);
		
		this.dotacaoInicial = dotacaoInicial;
		this.disponivel = disponivel;
		this.liquidado = liquidado;
		this.empenhado = empenhado;
		
		this.acaoId=acaoId;
		this.acaoCodigo=acaoCodigo;
		this.acaoDenominacao=acaoDenominacao;
		this.acaoProduto=acaoProduto;
		this.tipoCalculoMetaId=tipoCalculoMetaId;
		this.unidadeMedida=unidadeMedida;
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

	public Long getUnidadeGestora() {
		return unidadeGestora;
	}

	public void setUnidadeGestora(Long unidadeGestora) {
		this.unidadeGestora = unidadeGestora;
	}

	public Long getUnidadeOrcamentaria() {
		return unidadeOrcamentaria;
	}

	public void setUnidadeOrcamentaria(Long unidadeOrcamentaria) {
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


	public Long getAcaoId() {
		return acaoId;
	}


	public void setAcaoId(Long acaoId) {
		this.acaoId = acaoId;
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


	public Long getTipoCalculoMetaId() {
		return tipoCalculoMetaId;
	}


	public void setTipoCalculoMetaId(Long tipoCalculoMetaId) {
		this.tipoCalculoMetaId = tipoCalculoMetaId;
	}


	public Double getPlanejado() {
		return planejado;
	}


	public void setPlanejado(Double planejado) {
		this.planejado = planejado;
	}


	public Double getExecutado() {
		return executado;
	}


	public void setExecutado(Double executado) {
		this.executado = executado;
	}


	public String getUnidadeMedida() {
		return unidadeMedida;
	}


	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}


	public Double getEficacia() {
		return eficacia;
	}


	public void setEficacia(Double eficacia) {
		this.eficacia = eficacia;
	}


	public BigDecimal getDotacaoAtual() {
		return dotacaoAtual;
	}


	public void setDotacaoAtual(BigDecimal dotacaoAtual) {
		this.dotacaoAtual = dotacaoAtual;
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

	public String getAcaoCodigo() {
		return acaoCodigo;
	}

	public void setAcaoCodigo(String acaoCodigo) {
		this.acaoCodigo = acaoCodigo;
	}
	 
	
	
	
	
}
