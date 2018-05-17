package qualitativo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import administrativo.model.Exercicio;
import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;
import arquitetura.utils.FormatoUtils;
import quantitativo.model.FisicoFinanceiro;
 
@Entity
@Table(name="acao", schema="planejamento")
public class Acao extends Model implements  Auditable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_acao")
	private Long id;
 
	@Column(name="base_legal",length=10000)
	private String baseLegal;

	@Column(name="calculo_meta",length=4)
	private String calculoMeta;

	@NotNull(message="Código: campo é obrigatório")
	@Column(name="codigo",length=5)
	private String codigo;

	@NotNull(message="Denominação: campo é obrigatório")
	@Column(name="denominacao",length=10000)
	private String denominacao;

	@NotNull(message="Descrição: campo é obrigatório")
	@Column(name="descricao",length=10000)
	private String descricao;

	@Column(name="detalhamento_implementacao",length=10000)
	private String detalhamentoImplementacao;

	@Column(name="especificao_produto",length=10000)
	private String especificaoProduto;

	@Column(name="finalidade",length=10000)
	private String finalidade;

	@Column(name="forma_implementacao",length=4)
	private String formaImplementacao;

	@Column(name="horizonte_temporal",length=4)
	private String horizonteTemporal;


	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_unidade_orcamentaria")
	@NotNull(message="Unidade Orçamentária: campo é obrigatório")
	private UnidadeOrcamentaria unidadeOrcamentaria = new UnidadeOrcamentaria();

	@Column(name="impacto_idh",length=10000)
	private String impactoIdh;

	@Column(name="medido_despesa")
	private Boolean medidoDespesa;

	@Temporal(TemporalType.DATE)
	@Column(name="mes_inicio")
	private Date mesInicio;

	@Temporal(TemporalType.DATE)
	@Column(name="mes_termino")
	private Date mesTermino;

	@Column(name="produto",length=10000)
	private String produto;
 
	@Column(name="repercussao",length=10000)
	private String repercussao;

	@Column(name="tipo_acao",length=4)
	private String tipoAcaoDep;

	@Column(name="tipo_orcamento",length=4)
	private String tipoOrcamentoDep;

	@Column(name="valores",length=10000)
	private String valores;

	@ManyToOne
	@JoinColumn(name="id_funcao")
	@NotNull(message="Função: campo é obrigatório")
	private Funcao funcao = new Funcao();

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_programa",nullable=false)
	private Programa programa = new Programa();

	@ManyToOne
	@JoinColumn(name="id_subfuncao")
	@NotNull(message="Subfunção: campo é obrigatório")
	private SubFuncao subfuncao = new SubFuncao();

	@ManyToOne
	@JoinColumn(name="id_tipo_acao")
	private TipoAcao tipoAcao = new TipoAcao();

	@ManyToOne
	@JoinColumn(name="id_tipo_calculo_meta")
	private TipoCalculoMeta tipoCalculoMeta = new TipoCalculoMeta();

	@ManyToOne
	@JoinColumn(name="id_tipo_forma_implementacao")
	private TipoFormaImplementacao tipoFormaImplementacao = new TipoFormaImplementacao();

	@ManyToOne
	@JoinColumn(name="id_tipo_horizonte_temporal")
	private TipoHorizonteTemporal tipoHorizonteTemporal = new TipoHorizonteTemporal();

	@ManyToOne
	@JoinColumn(name="id_tipo_orcamento")
	private TipoOrcamento tipoOrcamento = new TipoOrcamento();


	@ManyToOne
	@JoinColumn(name="id_unidade_medida")
	private UnidadeMedida unidadeMedida = new UnidadeMedida();


	@OneToMany(mappedBy="acao")
	private List<EtapasExecucao> etapasExecucaos;


	@OneToMany(mappedBy="acao")
	private List<FisicoFinanceiro> fisicoFinanceiros;
	
	@OneToMany(mappedBy="acao")
	private List<PlanoInterno> planoInternos;
	
	@Column(name="observacao",length=2000)
	private String observacao;
 
	@ManyToOne
	@JoinColumn(name="id_exercicio") 
	private Exercicio exercicio;

	
	public Acao() {}
	
	public Acao(Long id) {
		this.id=id;
		this.codigo = "";
		this.denominacao = "";
		this.descricao = "";
	}
	
	public Acao(String orgaoCodigo,
			    String orgaoDescricao,
			    String unidadeOrcamentariaCodigo,
			    String unidadeOrcamentariaDescricao,
			    String programaCodigo,
			    String programaDenominacao,
			    String funcaoCodigo,
			    String funcaoDescricao,
			    String subFuncaoCodigo,
			    String subFuncaoDescricao,
			    String codigo,
			    String denominacao,
			    String finalidade,
			    String descricao){
		
		this.codigo = codigo;
		this.denominacao = denominacao;
		this.finalidade = finalidade;
		this.descricao = descricao;
		
		programa = new Programa();
		programa.setCodigo(programaCodigo);
		programa.setDenominacao(programaDenominacao);
		
		funcao = new Funcao();
		funcao.setCodigo(funcaoCodigo);
		funcao.setDescricao(funcaoDescricao);
		
		subfuncao = new SubFuncao();
		subfuncao.setCodigo(subFuncaoCodigo);
		subfuncao.setDescricao(subFuncaoDescricao);
		
		unidadeOrcamentaria = new UnidadeOrcamentaria();
		unidadeOrcamentaria.setCodigo(unidadeOrcamentariaCodigo);
		unidadeOrcamentaria.setDescricao(unidadeOrcamentariaDescricao);
		
		unidadeOrcamentaria.setOrgao(new Orgao());
		
		unidadeOrcamentaria.getOrgao().setCodigo(orgaoCodigo);
		unidadeOrcamentaria.getOrgao().setDescricao(orgaoDescricao);
	

}
	
	
	public Acao(String orgaoCodigo,
			    String orgaoDescricao,
			    String unidadeOrcamentariaCodigo,
			    String unidadeOrcamentariaDescricao,
			    String programaCodigo,
			    String programaDenominacao,
			    String codigo,
			    String denominacao,
			    String finalidade,
			    String descricao){
		
		this.codigo = codigo;
		this.denominacao = denominacao;
		this.finalidade = finalidade;
		this.descricao = descricao;
		
		programa = new Programa();
		programa.setCodigo(programaCodigo);
		programa.setDenominacao(programaDenominacao);
		
		unidadeOrcamentaria = new UnidadeOrcamentaria();
		unidadeOrcamentaria.setCodigo(unidadeOrcamentariaCodigo);
		unidadeOrcamentaria.setDescricao(unidadeOrcamentariaDescricao);
		
		unidadeOrcamentaria.setOrgao(new Orgao());
		
		unidadeOrcamentaria.getOrgao().setCodigo(orgaoCodigo);
		unidadeOrcamentaria.getOrgao().setDescricao(orgaoDescricao);
		
 
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

	public SubFuncao getSubfuncao() {
		return this.subfuncao;
	}

	public void setSubfuncao(SubFuncao subfuncao) {
		this.subfuncao = subfuncao;
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

	
	
	
	public Exercicio getExercicio() {
		return exercicio;
	}

	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
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
	
		sb.append(" Ação Cálculo Meta: ").append(calculoMeta);
		sb.append(" Acao Ativo : ").append(getAtivo());
		sb.append(" Ação Data da Operação: ").append(FormatoUtils.formataData(new Date()));
		
		return sb.toString();
		
	}
	
 

}