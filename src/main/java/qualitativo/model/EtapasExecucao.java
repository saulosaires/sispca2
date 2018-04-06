package qualitativo.model;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;


/**
 * The persistent class for the etapas_execucao database table.
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="etapas_execucao", schema="monitoramento")
public class EtapasExecucao extends Model implements  Auditable {
	private static final long serialVersionUID = 1L;

	@Id
//	@SequenceGenerator(name = "seq_etapas_execucao_id_etapa_execucao", sequenceName = "monitoramento.seq_etapas_execucao_id_etapa_execucao")
//	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_etapas_execucao_id_etapa_execucao")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_etapa_execucao")
	private Long id;
 
	@Column(name="comentario")
	private String comentario;

	@NotNull(message="Denominação: campo é obrigatório")
	private String denominacao;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_unidade_orcamentaria")
	private UnidadeOrcamentaria unidadeOrcamentaria;

	@Temporal(TemporalType.DATE)
	@Column(name="mes_inicio")
	private Date mesInicio;

	@Temporal(TemporalType.DATE)
	@Column(name="mes_termino")
	private Date mesTermino;

	private String produto;

	private Double quantidade;

	@Column(name="tipo_calculo")
	private String tipoCalculo;

	//bi-directional many-to-one association to Acao
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_acao")
	@NotNull(message="Ação: campo é obrigatório")
	private Acao acao;

	//bi-directional many-to-one association to PlanoInterno
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_plano_interno")
	@NotNull(message="Plano Interno: campo é obrigatório")
	private PlanoInterno planoInterno;

	//bi-directional many-to-one association to TipoCalculoMeta
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_tipo_calculo_meta")
	private TipoCalculoMeta tipoCalculoMeta;

	//bi-directional many-to-one association to UnidadeMedida
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_unidade_medida")
	private UnidadeMedida unidadeMedida;

	public EtapasExecucao() {
		// empty constructor for this class
		
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
 

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getDenominacao() {
		return this.denominacao;
	}

	public void setDenominacao(String denominacao) {
		this.denominacao = denominacao;
	}	

	public UnidadeOrcamentaria getUnidadeOrcamentaria() {
		return unidadeOrcamentaria;
	}
	
	public void setUnidadeOrcamentaria(UnidadeOrcamentaria unidadeOrcamentaria) {
		this.unidadeOrcamentaria = unidadeOrcamentaria;
	}

	public Date getMesInicio() {
		return this.mesInicio;
	}

	public void setMesInicio(Date mesInicio) {
		this.mesInicio = mesInicio;
	}

	public Date getMesTermino() {
		return this.mesTermino;
	}

	public void setMesTermino(Date mesTermino) {
		this.mesTermino = mesTermino;
	}

	public String getProduto() {
		return this.produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public Double getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public String getTipoCalculo() {
		return this.tipoCalculo;
	}

	public void setTipoCalculo(String tipoCalculo) {
		this.tipoCalculo = tipoCalculo;
	}

	public Acao getAcao() {
		return this.acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public PlanoInterno getPlanoInterno() {
		return this.planoInterno;
	}

	public void setPlanoInterno(PlanoInterno planoInterno) {
		this.planoInterno = planoInterno;
	}

	public TipoCalculoMeta getTipoCalculoMeta() {
		return this.tipoCalculoMeta;
	}

	public void setTipoCalculoMeta(TipoCalculoMeta tipoCalculoMeta) {
		this.tipoCalculoMeta = tipoCalculoMeta;
	}

	public UnidadeMedida getUnidadeMedida() {
		return this.unidadeMedida;
	}

	public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}



	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append("Etapas de Execução id: ").append(this.id)
			.append(" Etapas de Execução Comentário: ").append(this.comentario)
			.append(" Etapas de Execução Denominação: ").append(this.denominacao)
			.append(" Etapas de Execução Produto: ").append(this.produto)
			.append(" Etapas de Execução Quantidade: ").append(this.quantidade);
		return sb.toString();
	}
	
 

}