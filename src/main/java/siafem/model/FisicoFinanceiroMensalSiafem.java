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

// TODO depois que migrar os dados eu posso usar chave estrangeira, mas agora vou so mapear as colunas diretamente	
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="unidade_gestora")
//	private UnidadeGestora unidadeGestora;
//
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="unidade_orcamentaria")
//	private UnidadeOrcamentaria unidadeOrcamentaria;
//	
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="programa")
//	private Programa programa;
//	
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="id_acao")
//	private Acao acao;

	@Column(name="unidade_gestora")
	private Long unidadeGestora=0l;	
	
	@Column(name="unidade_orcamentaria")
	private Long unidadeOrcamentaria=0l;	
	
	@Column(name="programa")
	private String programa;	
	
	@Column(name="id_acao")
	private Long acao=0l;	
	
	@Column(name="dotacao_inicial")
	private BigDecimal dotacaoInicial;
	
	@Column(name="disponivel")
	private BigDecimal disponivel;
	
	@Column(name="empenhado")
	private BigDecimal empenhado;

	@Column(name="ano")
	private Integer ano;

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

	public Long getAcao() {
		return acao;
	}

	public void setAcao(Long acao) {
		this.acao = acao;
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
	 
	
	
	
	
}
