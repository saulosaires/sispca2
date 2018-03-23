package qualitativo.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;
import arquitetura.utils.FormatoUtils;
import quantitativo.model.FisicoFinanceiro;

import java.util.Date;
import java.util.List;
 
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="acao", schema="planejamento")
public class Acao extends Model implements Serializable, Auditable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_acao")
	private Long id;
 
	@Column(name="base_legal")
	private String baseLegal;

	@Column(name="calculo_meta")
	private String calculoMeta;

	@NotNull(message="Código: campo é obrigatório")
	private String codigo;

	@NotNull(message="Denominação: campo é obrigatório")
	private String denominacao;

	@NotNull(message="Descrição: campo é obrigatório")
	private String descricao;

	@Column(name="detalhamento_implementacao")
	private String detalhamentoImplementacao;

	@Column(name="especificao_produto")
	private String especificaoProduto;

	private String finalidade;

	@Column(name="forma_implementacao")
	private String formaImplementacao;

	@Column(name="horizonte_temporal")
	private String horizonteTemporal;

	@ManyToOne
	@JoinColumn(name="id_orgao")
	private Orgao orgao;

	@ManyToOne
	@JoinColumn(name="id_unidade_orcamentaria")
	@NotNull(message="Unidade Orçamentária: campo é obrigatório")
	private UnidadeOrcamentaria unidadeOrcamentaria;

	@Column(name="impacto_idh")
	private String impactoIdh;

	@Column(name="medido_despesa")
	private Boolean medidoDespesa;

	@Temporal(TemporalType.DATE)
	@Column(name="mes_inicio")
	private Date mesInicio;

	@Temporal(TemporalType.DATE)
	@Column(name="mes_termino")
	private Date mesTermino;

	private String produto;
	
	@Column(name="medido_despesa_")
	private String medidoDespesaDep;

	private String repercussao;

	@Column(name="tipo_acao")
	private String tipoAcaoDep;

	@Column(name="tipo_orcamento")
	private String tipoOrcamentoDep;

	private String valores;

	//bi-directional many-to-one association to Funcao
	@ManyToOne
	@JoinColumn(name="id_funcao")
	@NotNull(message="Função: campo é obrigatório")
	private Funcao funcao;

	//bi-directional many-to-one association to Programa
	@ManyToOne
	@JoinColumn(name="id_programa")
	@NotNull(message="Programa: campo é obrigatório")
	private Programa programa;

	//bi-directional many-to-one association to Subfuncao
	@ManyToOne
	@JoinColumn(name="id_subfuncao")
	@NotNull(message="Subfunção: campo é obrigatório")
	private Subfuncao subfuncao;

	//bi-directional many-to-one association to TipoAcao
	@ManyToOne
	@JoinColumn(name="id_tipo_acao")
	private TipoAcao tipoAcao;

	//bi-directional many-to-one association to TipoCalculoMeta
	@ManyToOne
	@JoinColumn(name="id_tipo_calculo_meta")
	private TipoCalculoMeta tipoCalculoMeta;

	//bi-directional many-to-one association to TipoFormaImplementacao
	@ManyToOne
	@JoinColumn(name="id_tipo_forma_implementacao")
	private TipoFormaImplementacao tipoFormaImplementacao;

	//bi-directional many-to-one association to TipoHorizonteTemporal
	@ManyToOne
	@JoinColumn(name="id_tipo_horizonte_temporal")
	private TipoHorizonteTemporal tipoHorizonteTemporal;

	//bi-directional many-to-one association to TipoOrcamento
	@ManyToOne
	@JoinColumn(name="id_tipo_orcamento")
	private TipoOrcamento tipoOrcamento;

	//bi-directional many-to-one association to UnidadeMedida
	@ManyToOne
	@JoinColumn(name="id_unidade_medida")
	private UnidadeMedida unidadeMedida;

	//bi-directional many-to-one association to EtapasExecucao
	@OneToMany(mappedBy="acao")
	private List<EtapasExecucao> etapasExecucaos;

	//bi-directional many-to-one association to FisicoFinanceiro
	@OneToMany(mappedBy="acao")
	private List<FisicoFinanceiro> fisicoFinanceiros;
	
	@OneToMany(mappedBy="acao")
	private List<PlanoInterno> planoInternos;
	
	private String observacao;

	public Acao() {
		//empty
	}	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
 

	public String getBaseLegal() {
		return this.baseLegal;
	}

	public void setBaseLegal(String baseLegal) {
		this.baseLegal = baseLegal;
	}

	public String getCalculoMeta() {
		return this.calculoMeta;
	}

	public void setCalculoMeta(String calculoMeta) {
		this.calculoMeta = calculoMeta;
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

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDetalhamentoImplementacao() {
		return this.detalhamentoImplementacao;
	}

	public void setDetalhamentoImplementacao(String detalhamentoImplementacao) {
		this.detalhamentoImplementacao = detalhamentoImplementacao;
	}

	public String getEspecificaoProduto() {
		return this.especificaoProduto;
	}

	public void setEspecificaoProduto(String especificaoProduto) {
		this.especificaoProduto = especificaoProduto;
	}

	public String getFinalidade() {
		return this.finalidade;
	}

	public void setFinalidade(String finalidade) {
		this.finalidade = finalidade;
	}

	public String getFormaImplementacao() {
		return this.formaImplementacao;
	}

	public void setFormaImplementacao(String formaImplementacao) {
		this.formaImplementacao = formaImplementacao;
	}

	public String getHorizonteTemporal() {
		return this.horizonteTemporal;
	}

	public void setHorizonteTemporal(String horizonteTemporal) {
		this.horizonteTemporal = horizonteTemporal;
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

	public String getImpactoIdh() {
		return this.impactoIdh;
	}

	public void setImpactoIdh(String impactoIdh) {
		this.impactoIdh = impactoIdh;
	}	

	public Boolean getMedidoDespesa() {
		return medidoDespesa;
	}

	public void setMedidoDespesa(Boolean medidoDespesa) {
		this.medidoDespesa = medidoDespesa;
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

	public String getRepercussao() {
		return this.repercussao;
	}

	public void setRepercussao(String repercussao) {
		this.repercussao = repercussao;
	}	
	
	public String getTipoAcaoDep() {
		return tipoAcaoDep;
	}

	public void setTipoAcaoDep(String tipoAcaoDep) {
		this.tipoAcaoDep = tipoAcaoDep;
	}

	public TipoAcao getTipoAcao() {
		return tipoAcao;
	}

	public void setTipoAcao(TipoAcao tipoAcao) {
		this.tipoAcao = tipoAcao;
	}

	public String getValores() {
		return this.valores;
	}

	public void setValores(String valores) {
		this.valores = valores;
	}

	public Funcao getFuncao() {
		return this.funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	public Programa getPrograma() {
		return this.programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public Subfuncao getSubfuncao() {
		return this.subfuncao;
	}

	public void setSubfuncao(Subfuncao subfuncao) {
		this.subfuncao = subfuncao;
	}

	public TipoAcao getTipoAcaoBean() {
		return this.tipoAcao;
	}

	public void setTipoAcaoBean(TipoAcao tipoAcaoBean) {
		this.tipoAcao = tipoAcaoBean;
	}

	public TipoCalculoMeta getTipoCalculoMeta() {
		return this.tipoCalculoMeta;
	}

	public void setTipoCalculoMeta(TipoCalculoMeta tipoCalculoMeta) {
		this.tipoCalculoMeta = tipoCalculoMeta;
	}

	public TipoFormaImplementacao getTipoFormaImplementacao() {
		return this.tipoFormaImplementacao;
	}

	public void setTipoFormaImplementacao(TipoFormaImplementacao tipoFormaImplementacao) {
		this.tipoFormaImplementacao = tipoFormaImplementacao;
	}

	public TipoHorizonteTemporal getTipoHorizonteTemporal() {
		return this.tipoHorizonteTemporal;
	}

	public void setTipoHorizonteTemporal(TipoHorizonteTemporal tipoHorizonteTemporal) {
		this.tipoHorizonteTemporal = tipoHorizonteTemporal;
	}	

	public String getTipoOrcamentoDep() {
		return tipoOrcamentoDep;
	}

	public void setTipoOrcamentoDep(String tipoOrcamentoDep) {
		this.tipoOrcamentoDep = tipoOrcamentoDep;
	}

	public TipoOrcamento getTipoOrcamento() {
		return tipoOrcamento;
	}

	public void setTipoOrcamento(TipoOrcamento tipoOrcamento) {
		this.tipoOrcamento = tipoOrcamento;
	}

	public UnidadeMedida getUnidadeMedida() {
		return this.unidadeMedida;
	}

	public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public List<EtapasExecucao> getEtapasExecucaos() {
		return this.etapasExecucaos;
	}

	public void setEtapasExecucaos(List<EtapasExecucao> etapasExecucaos) {
		this.etapasExecucaos = etapasExecucaos;
	}

	public List<FisicoFinanceiro> getFisicoFinanceiros() {
		return this.fisicoFinanceiros;
	}

	public void setFisicoFinanceiros(List<FisicoFinanceiro> fisicoFinanceiros) {
		this.fisicoFinanceiros = fisicoFinanceiros;
	}	

	public String getMedidoDespesaDep() {
		return medidoDespesaDep;
	}

	public void setMedidoDespesaDep(String medidoDespesaDep) {
		this.medidoDespesaDep = medidoDespesaDep;
	}	

	public List<PlanoInterno> getPlanoInternos() {
		return planoInternos;
	}

	public void setPlanoInternos(List<PlanoInterno> planoInternos) {
		this.planoInternos = planoInternos;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append(" Ação Id : ").append(id);
		sb.append(" Ação Código: ").append(codigo);
		sb.append(" Ação Denominação: ").append(denominacao);
		sb.append(" Ação Programa: ").append(programa != null ? programa.getId() : "");
		sb.append(" Ação Função: ").append(funcao != null ? funcao.getId() : "");
		sb.append(" Ação SubFunção: ").append(subfuncao != null ? subfuncao.getId() : "");
		sb.append(" Ação Unidade Orcamentaria").append(unidadeOrcamentaria != null ? unidadeOrcamentaria.getId() : "");
		sb.append(" Ação Medidor de Despesas: ").append(medidoDespesa);
		sb.append(" Ação Produto: ").append(produto);
		sb.append(" Ação Unidade Medida: ").append(unidadeMedida != null ? unidadeMedida.getId() : "");
		sb.append(" Ação Especificação Produto: ").append(especificaoProduto);
		sb.append(" Ação Tipo: ").append(tipoAcao != null ? tipoAcao.getId() : "");
		sb.append(" Ação Tipo Horizonte Temporal: ").append(tipoHorizonteTemporal != null ? tipoHorizonteTemporal.getId() : "");
		sb.append(" Ação Tipo Forma Implementação: ").append(tipoFormaImplementacao != null ? tipoFormaImplementacao.getId() : "");
		sb.append(" Ação Tipo Orçamento: ").append(tipoOrcamento != null ? tipoOrcamento.getId() : "");
		sb.append(" Ação Tipo Calculo da Meta: ").append(tipoCalculoMeta != null ? tipoCalculoMeta.getId() : "");
		sb.append(" Ação base legal: ").append(baseLegal);
		sb.append(" Ação Detalhamento Implementação: ").append(detalhamentoImplementacao);
		sb.append(" Ação Data Inicio").append(mesInicio != null ? FormatoUtils.formataData(mesInicio): mesInicio);
		sb.append(" Ação Data Termino").append(mesTermino != null ? FormatoUtils.formataData(mesTermino) : mesTermino);
		sb.append(" Ação Finalidade").append(finalidade);
		sb.append(" Ação Descrição: ").append(descricao);
		sb.append(" Ação Repercussão: ").append(repercussao);
		sb.append(" Ação Orgao").append(orgao != null ? orgao.getId() : "");
		sb.append(" Ação Cálculo Meta: ").append(calculoMeta);
		sb.append(" Acao Ativo : ").append(getAtivo());
		sb.append(" Ação Data da Operação: ").append(FormatoUtils.formataData(new Date()));
		
		return sb.toString();
		
	}
	
 

}