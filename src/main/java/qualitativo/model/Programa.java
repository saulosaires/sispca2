package qualitativo.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


 
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="programa", schema="planejamento")
public class Programa extends Model implements Serializable, Auditable {
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

	@ManyToOne
	@JoinColumn(name="id_orgao")
	@NotNull(message="Órgão: campo é obrigatório")
	private Orgao orgao;

	@Column(name="id_programa_novo")
	private Integer idProgramaNovo;

	@ManyToOne
	@JoinColumn(name="id_unidade_orcamentaria")
	@NotNull(message="Unidade Orçamentária: campo é obrigatório")
	private UnidadeOrcamentaria unidadeOrcamentaria;

	private String justificativa;

	@Temporal(TemporalType.DATE)
	@Column(name="mes_inicio")
	private Date mesInicio;

	@Temporal(TemporalType.DATE)
	@Column(name="mes_termino")
	private Date mesTermino;

	private String objetivo;

	private String problema;

	@Column(name="publico_alvo")
	private String publicoAlvo;

	private String responsavel;

	@Column(name="tipo_politica")
	private String tipoPolitica;

	@Column(name="tipo_programa")
	private String tipoPrograma_;

	@Column(name="valor_anual")
	private BigDecimal valorAnual;

	@Column(name="valor_global_estimado")
	private BigDecimal valorGlobalEstimado;

 
	@OneToMany(mappedBy = "programa")
	private List<Acao> acoes;

 
	@ManyToMany(mappedBy="programas")
	private List<Diretriz> diretrizs;
 
 
	@OneToMany(mappedBy="programa")
	private List<MacroObjetivo> macroObjetivos;
 
	@ManyToOne
	@JoinColumn(name="id_tipo_horizonte_temporal")
	@NotNull(message="Tipo Horizonte Temporal: campo é obrigatório")
	private TipoHorizonteTemporal tipoHorizonteTemporal;

 
	@ManyToOne
	@JoinColumn(name="id_tipo_programa")
	@NotNull(message="Tipo de Programa: campo é obrigatório")
	private TipoPrograma tipoPrograma;

	public Programa() { }	

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

	public Integer getIdProgramaNovo() {
		return this.idProgramaNovo;
	}

	public void setIdProgramaNovo(Integer idProgramaNovo) {
		this.idProgramaNovo = idProgramaNovo;
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

	public List<Diretriz> getDiretrizs() {
		return this.diretrizs;
	}

	public void setDiretrizs(List<Diretriz> diretrizs) {
		this.diretrizs = diretrizs;
	}

	/*
	public List<Indicador> getIndicadores() {
		return this.indicadores;
	}

	public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
	}
	*/

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

	public String getTipoPrograma_() {
		return tipoPrograma_;
	}

	public void setTipoPrograma_(String tipoPrograma_) {
		this.tipoPrograma_ = tipoPrograma_;
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
	
	@Override
	public boolean equals(Object object){
		if(object != null && object instanceof Programa){
			return ((Programa)object).getId() == this.id;
		}
		return false;
	}

}