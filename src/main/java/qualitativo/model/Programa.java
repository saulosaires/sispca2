package qualitativo.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;


 
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="programa", schema="planejamento")
public class Programa extends Model implements  Auditable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_programa")
	private Long id;
 

	@NotNull(message="Código: campo é obrigatório")
	private String codigo;

	@NotNull(message="Denominação: campo é obrigatório")
	private String denominacao;

	private String estrategia;

	@Column(name="horizonte_temporal")
	private String horizonteTemporal;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_orgao")
	@NotNull(message="Órgão: campo é obrigatório")
	private Orgao orgao = new Orgao();

 
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_unidade_orcamentaria")
	@NotNull(message="Unidade Orçamentária: campo é obrigatório")
	private UnidadeOrcamentaria unidadeOrcamentaria = new UnidadeOrcamentaria();

	@Column(name="justificativa")
	private String justificativa;

	@Temporal(TemporalType.DATE)
	@Column(name="mes_inicio")
	private Date mesInicio;

	@Temporal(TemporalType.DATE)
	@Column(name="mes_termino")
	private Date mesTermino;

	@Column(name="objetivo")
	private String objetivo;

	@Column(name="problema")
	private String problema;

	@Column(name="publico_alvo")
	private String publicoAlvo;

	@Column(name="responsavel")
	private String responsavel;

	@Column(name="tipo_politica")
	private String tipoPolitica;
 

	@Column(name="valor_anual")
	private BigDecimal valorAnual;

	@Column(name="valor_global_estimado")
	private BigDecimal valorGlobalEstimado;

 
	@OneToMany(mappedBy = "programa")
	private List<Acao> acoes;

 
	@ManyToMany(mappedBy="programas")
	private List<Diretriz> diretrizes = new ArrayList<>();
 
 
	@OneToMany(mappedBy="programa")
	private List<MacroObjetivo> macroObjetivos;
 
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_tipo_horizonte_temporal")
	@NotNull(message="Tipo Horizonte Temporal: campo é obrigatório")
	private TipoHorizonteTemporal tipoHorizonteTemporal = new TipoHorizonteTemporal();

 
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_tipo_programa")
	@NotNull(message="Tipo de Programa: campo é obrigatório")
	private TipoPrograma tipoPrograma = new TipoPrograma();

	 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
 

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDenominacao() {
		return this.denominacao;
	}

	public void setDenominacao(String denominacao) {
		this.denominacao = denominacao;
	}

	public String getEstrategia() {
		return this.estrategia;
	}

	public void setEstrategia(String estrategia) {
		this.estrategia = estrategia;
	}

	public String getHorizonteTemporal() {
		return this.horizonteTemporal;
	}

	public void setHorizonteTemporal(String horizonteTemporal) {
		this.horizonteTemporal = horizonteTemporal;
	}
 


	public String getJustificativa() {
		return this.justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
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

	public String getObjetivo() {
		return this.objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public String getProblema() {
		return this.problema;
	}

	public void setProblema(String problema) {
		this.problema = problema;
	}

	public String getPublicoAlvo() {
		return this.publicoAlvo;
	}

	public void setPublicoAlvo(String publicoAlvo) {
		this.publicoAlvo = publicoAlvo;
	}

	public String getResponsavel() {
		return this.responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getTipoPolitica() {
		return this.tipoPolitica;
	}

	public void setTipoPolitica(String tipoPolitica) {
		this.tipoPolitica = tipoPolitica;
	}

	public BigDecimal getValorAnual() {
		return this.valorAnual;
	}

	public void setValorAnual(BigDecimal valorAnual) {
		this.valorAnual = valorAnual;
	}

	public BigDecimal getValorGlobalEstimado() {
		return this.valorGlobalEstimado;
	}

	public void setValorGlobalEstimado(BigDecimal valorGlobalEstimado) {
		this.valorGlobalEstimado = valorGlobalEstimado;
	}

	public List<Acao> getAcoes() {
		return this.acoes;
	}

	public void setAcoes(List<Acao> acoes) {
		this.acoes = acoes;
	}

	public List<Diretriz> getDiretrizes() {
		return this.diretrizes;
	}

	public void setDiretrizes(List<Diretriz> diretrizes) {
		this.diretrizes = diretrizes;
	}

	public List<MacroObjetivo> getMacroObjetivos() {
		return this.macroObjetivos;
	}

	public void setMacroObjetivos(List<MacroObjetivo> macroObjetivos) {
		this.macroObjetivos = macroObjetivos;
	}

	public TipoHorizonteTemporal getTipoHorizonteTemporal() {
		return this.tipoHorizonteTemporal;
	}

	public void setTipoHorizonteTemporal(TipoHorizonteTemporal tipoHorizonteTemporal) {
		this.tipoHorizonteTemporal = tipoHorizonteTemporal;
	}	

	public TipoPrograma getTipoPrograma() {
		return tipoPrograma;
	}

	public void setTipoPrograma(TipoPrograma tipoPrograma) {
		this.tipoPrograma = tipoPrograma;
	}

	public Orgao getOrgao() {
		return orgao;
	}

	public void setOrgao(Orgao orgao) {
		this.orgao = orgao;
	}

	public UnidadeOrcamentaria getUnidadeOrcamentaria() {
		return unidadeOrcamentaria;
	}

	public void setUnidadeOrcamentaria(UnidadeOrcamentaria unidadeOrcamentaria) {
		this.unidadeOrcamentaria = unidadeOrcamentaria;
	}

	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append(" Programa Id: ").append(id).append(" Programa Denominação: ")
				.append(denominacao).append(" Programa Estratégia: ").append(estrategia)
				.append(" Programa Horizonte Temporal: ").append(horizonteTemporal)
				.append(" Programa Justificativa: ").append(this.justificativa)
				.append(" Programa Objetivo: ").append(this.objetivo)
				.append(" Programa Problema: ").append(this.problema)
				.append(" Programa Público Alvo: ").append(this.publicoAlvo)
				.append(" Programa Responsável: ").append(this.responsavel)
				.append(" Programa Tipo Política: ").append(this.tipoPolitica);

		return sb.toString();		
	}
	
	 

}