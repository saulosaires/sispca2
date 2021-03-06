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

import arquitetura.utils.MathUtils;
import qualitativo.model.Acao;
import qualitativo.model.Mes;
import qualitativo.model.Programa;
import qualitativo.model.TipoAcao;
import qualitativo.model.UnidadeGestora;
import qualitativo.model.UnidadeMedida;
import qualitativo.model.UnidadeOrcamentaria;

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
	
	@Column(name="natureza_descricao")
	private String naturezaDescricao;
	
	@Column(name="regiao")
	private Integer regiao;
		
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_acao",nullable=true)
	private Acao acao;
 
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_unidade_gestora",nullable=true)
	private UnidadeGestora unidadeGestora;
	
	@Column(name="unidade_gestora")
	private String unidadeGestoraCodigo;	
	
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
	
	private transient BigDecimal planejado = MathUtils.getZeroBigDecimal();
	private transient BigDecimal executado = MathUtils.getZeroBigDecimal();
	private transient BigDecimal eficacia  = MathUtils.getZeroBigDecimal(); 
	
	private transient BigDecimal pagoSobreDisponivel = MathUtils.getZeroBigDecimal();
	private transient BigDecimal empenhadoSobreDisponivel = MathUtils.getZeroBigDecimal();
	private transient BigDecimal liquidadoSobreAtual = MathUtils.getZeroBigDecimal();
	
	private transient BigDecimal eficiencia = MathUtils.getZeroBigDecimal();
	private transient BigDecimal saldo = MathUtils.getZeroBigDecimal();
	
	public FisicoFinanceiroMensalSiafem() {}

	//relatorio Despesas Executadas POR Unidade Unidade
	public FisicoFinanceiroMensalSiafem(
			 							 Long acaoId,
										 String acaoCodigo,
										 String acaoProduto,
										 String acaoDenominacao,
										  
										 Long tipoCalculoMetaId,
										 
										 String unidadeGestoraCodigo, 
										 String unidadeGestoraSigla,
										 String unidadeGestoraDescricao, 
										 
										 String unidadeOrcamentariaCodigo, 
										 String unidadeOrcamentariaDescricao, 
										 
										 String programaCodigo,
										 String programaDenominacao,
										 
										 String unidadeMedidaDescricao,
										  
									     BigDecimal dotacaoInicial,
									     BigDecimal disponivel,
									     BigDecimal empenhado,
									     BigDecimal liquidado,
									     BigDecimal pago
										) {
							
							this.acao = new Acao(acaoId);
							this.acao.setCodigo(acaoCodigo);
							this.acao.setProduto(acaoProduto);
							this.acao.setDenominacao(acaoDenominacao);
							
							this.tipoCalculoMetaId = tipoCalculoMetaId;
 							
							this.acao.setPrograma(new Programa());
							this.acao.getPrograma().setCodigo(programaCodigo);
							this.acao.getPrograma().setDenominacao(programaDenominacao);
							
							this.acao.setUnidadeOrcamentaria(new UnidadeOrcamentaria());
							
							this.acao.getUnidadeOrcamentaria().setCodigo(unidadeOrcamentariaCodigo);
							this.acao.getUnidadeOrcamentaria().setDescricao(unidadeOrcamentariaDescricao);
							
							this.acao.getUnidadeOrcamentaria().setUnidadeGestoraCodigo(unidadeGestoraCodigo);
							this.acao.getUnidadeOrcamentaria().setUnidadeGestoraSigla(unidadeGestoraSigla);	
							this.acao.getUnidadeOrcamentaria().setUnidadeGestoraDescricao(unidadeGestoraDescricao);
							
							this.acao.setUnidadeMedida(new UnidadeMedida());
							this.acao.getUnidadeMedida().setDescricao(unidadeMedidaDescricao);
							
							this.dotacaoInicial = dotacaoInicial;
							this.disponivel = disponivel;
							this.empenhado = empenhado;
							this.liquidado = liquidado;
							this.pago	= pago;	



}	
	
	//relatorio Despesas Executadas Acao
	public FisicoFinanceiroMensalSiafem(
			 							 Long acaoId,
										 String acaoCodigo,
										 String acaoProduto,
										 String acaoDenominacao,
										  
										 Long tipoCalculoMetaId,
										 String tipoAcaoSigla,
										  
										 String unidadeGestoraSigla,
										  
										 String unidadeMedidaDescricao,
										  
									     BigDecimal dotacaoInicial,
									     BigDecimal disponivel,
									     BigDecimal empenhado,
									     BigDecimal liquidado,
									     BigDecimal pago
										) {
							
							this.acao = new Acao(acaoId);
							this.acao.setCodigo(acaoCodigo);
							this.acao.setProduto(acaoProduto);
							this.acao.setDenominacao(acaoDenominacao);
							
							this.tipoCalculoMetaId = tipoCalculoMetaId;
							
							this.acao.setTipoAcao(new TipoAcao());
							this.acao.getTipoAcao().setSigla(tipoAcaoSigla);
							
							this.acao.setPrograma(new Programa());
							this.acao.getPrograma().setDenominacao(programaDenominacao);
							
							this.acao.setUnidadeOrcamentaria(new UnidadeOrcamentaria());
							this.acao.getUnidadeOrcamentaria().setUnidadeGestoraCodigo(unidadeGestoraCodigo);
							this.acao.getUnidadeOrcamentaria().setUnidadeGestoraSigla(unidadeGestoraSigla);	
							
							this.acao.setUnidadeMedida(new UnidadeMedida());
							this.acao.getUnidadeMedida().setDescricao(unidadeMedidaDescricao);
							
							this.dotacaoInicial = dotacaoInicial;
							this.disponivel = disponivel;
							this.empenhado = empenhado;
							this.liquidado = liquidado;
							this.pago	= pago;	



}
	
	
	//relatorio Execução Financeira por Programas e Ações
	public FisicoFinanceiroMensalSiafem(
			 							 Long acaoId,
										 String acaoCodigo,
										 String acaoProduto,
										 String acaoDenominacao,
										  
										 Long tipoCalculoMetaId,
										
										 String programaObjetivo,
										 String programaCodigo,
										 String programaDenominacao, 
										 
										 String unidadeorcamentariaCodigo,
										 String unidadeorcamentariaDescricao, 
										  
										 String unidadeMedidaDescricao,
										  
									     BigDecimal dotacaoInicial,
									     BigDecimal disponivel,
									     BigDecimal empenhado,
									     BigDecimal liquidado,
									     BigDecimal pago
										) {
							
							this.acao = new Acao(acaoId);
							this.acao.setCodigo(acaoCodigo);
							this.acao.setProduto(acaoProduto);
							this.acao.setDenominacao(acaoDenominacao);
							
							this.tipoCalculoMetaId = tipoCalculoMetaId;
							
							this.acao.setPrograma(new Programa());
							this.acao.getPrograma().setObjetivo(programaObjetivo);
							this.acao.getPrograma().setCodigo(programaCodigo);
							this.acao.getPrograma().setDenominacao(programaDenominacao);
							
							this.acao.setUnidadeOrcamentaria(new UnidadeOrcamentaria());
							this.acao.getUnidadeOrcamentaria().setCodigo(unidadeorcamentariaCodigo);
							this.acao.getUnidadeOrcamentaria().setDescricao(unidadeorcamentariaDescricao);
							
							this.acao.setUnidadeMedida(new UnidadeMedida());
							this.acao.getUnidadeMedida().setDescricao(unidadeMedidaDescricao);
							
							this.dotacaoInicial = dotacaoInicial;
							this.disponivel = disponivel;
							this.empenhado = empenhado;
							this.liquidado = liquidado;
							this.pago	= pago;	



}
			
	public FisicoFinanceiroMensalSiafem(
										  String programaCodigo,
										  String programaDenominacao,
										  
									      BigDecimal dotacaoInicial,
									      BigDecimal disponivel,
									      BigDecimal empenhado,
									      BigDecimal liquidado,
									      BigDecimal pago) {
							
							
			this.acao = new Acao();

			this.acao.setPrograma(new Programa());
			this.acao.getPrograma().setCodigo(programaCodigo);
			this.acao.getPrograma().setDenominacao(programaDenominacao);
			
			this.dotacaoInicial = dotacaoInicial;
			this.disponivel = disponivel;
			this.empenhado = empenhado;
			this.liquidado = liquidado;
			this.pago	= pago;	
							
							
							
	}	
		
	public FisicoFinanceiroMensalSiafem(
			
										  String unidadeGestoraCodigo,
										  
								 		  String unidadeGestoraSigla,
								 		  String unidadeGestoraDescricao,
								
										  String unidadeOrcamentariaCodigo,
										  String unidadeOrcamentariaDescricao,
							
										  String programaCodigo,
										  String programaDenominacao,
											
										  Long acaoId,
										  String acaoCodigo,
										  String acaoProduto,
										  String acaoDenominacao,
										  String acaoObservacao,
									 
										  String unidadeMedidaDescricao,
									  
										  Long tipoCalculoMetaId,
										  
									      BigDecimal dotacaoInicial,
									      BigDecimal disponivel,
									      BigDecimal empenhado,
									      BigDecimal liquidado,
									      BigDecimal pago
										) {
							
							
							this.acao = new Acao(acaoId);
							this.acao.setCodigo(acaoCodigo);
							this.acao.setProduto(acaoProduto);
							this.acao.setDenominacao(acaoDenominacao);
							this.acao.setObservacao(acaoObservacao);
							
							this.acao.setUnidadeOrcamentaria(new UnidadeOrcamentaria());
							this.acao.getUnidadeOrcamentaria().setCodigo(unidadeOrcamentariaCodigo);
							this.acao.getUnidadeOrcamentaria().setDescricao(unidadeOrcamentariaDescricao);
							
							this.acao.setPrograma(new Programa());
							this.acao.getPrograma().setCodigo(programaCodigo);
							this.acao.getPrograma().setDenominacao(programaDenominacao);
							
							this.acao.getUnidadeOrcamentaria().setUnidadeGestoraCodigo(unidadeGestoraCodigo);
							this.acao.getUnidadeOrcamentaria().setUnidadeGestoraSigla(unidadeGestoraSigla);	
							this.acao.getUnidadeOrcamentaria().setUnidadeGestoraDescricao(unidadeGestoraDescricao);
							
							this.acao.setUnidadeMedida(new UnidadeMedida());
							this.acao.getUnidadeMedida().setDescricao(unidadeMedidaDescricao);
							
							this.tipoCalculoMetaId = tipoCalculoMetaId;
							
							this.dotacaoInicial = dotacaoInicial;
							this.disponivel = disponivel;
							this.empenhado = empenhado;
							this.liquidado = liquidado;
							this.pago	= pago;	
							
							
							
	}
	
	public FisicoFinanceiroMensalSiafem(
			
										  String unidadeGestoraCodigo,
								 		  String unidadeGestoraSigla,
								 		  String unidadeGestoraDescricao,
								
										  String unidadeOrcamentariaCodigo,
										  String unidadeOrcamentariaDescricao,
							
										  String programaCodigo,
										  String programaDenominacao,
											
										  String acaoCodigo,
										  Long acaoId,
										  String acaoDenominacao,
									 
										  String planoInternoCodigo,
										  String planoInternoTitulo,
			 	 						  
										  String fonte,
										  String natureza,
									  
									      BigDecimal dotacaoInicial,
									      BigDecimal disponivel,
									      BigDecimal empenhado,
									      BigDecimal liquidado,
									      BigDecimal pago
										) {
							
							
							this.acao = new Acao();
							this.acao.setId(acaoId);
							this.acao.setCodigo(acaoCodigo);
							this.acao.setDenominacao(acaoDenominacao);
							
							this.acao.setUnidadeOrcamentaria(new UnidadeOrcamentaria());
							this.acao.getUnidadeOrcamentaria().setCodigo(unidadeOrcamentariaCodigo);
							this.acao.getUnidadeOrcamentaria().setDescricao(unidadeOrcamentariaDescricao);
							
							this.acao.setPrograma(new Programa());
							this.acao.getPrograma().setCodigo(programaCodigo);
							this.acao.getPrograma().setDenominacao(programaDenominacao);
							
							this.acao.getUnidadeOrcamentaria().setUnidadeGestoraCodigo(unidadeGestoraCodigo);
							this.acao.getUnidadeOrcamentaria().setUnidadeGestoraSigla(unidadeGestoraSigla);
							
							this.acao.getUnidadeOrcamentaria().setUnidadeGestoraDescricao(unidadeGestoraDescricao);
 							
							this.planoInterno = planoInternoCodigo;	
							this.planoInternoDescricao = planoInternoTitulo;
							
							this.fonte    = fonte;
							this.natureza = natureza;
							
							
							this.dotacaoInicial = dotacaoInicial;
							this.disponivel = disponivel;
							this.empenhado = empenhado;
							this.liquidado = liquidado;
							this.pago	= pago;	
							
							
							
							}
		
	public FisicoFinanceiroMensalSiafem(
									      String naturezaDescricao,
									  
									      BigDecimal dotacaoInicial,
									      BigDecimal disponivel,
									      BigDecimal empenhado,
									      BigDecimal liquidado,
									      BigDecimal pago
										) {
			
			
			
		this.naturezaDescricao = naturezaDescricao;
		
		this.dotacaoInicial = dotacaoInicial;
		this.disponivel = disponivel;
		this.empenhado = empenhado;
		this.liquidado = liquidado;
		this.pago	= pago;	
	
	}
		
	public FisicoFinanceiroMensalSiafem(
			
										  String unidadeGestoraCodigo,
								 		  String unidadeGestoraSigla,
								 		  String unidadeGestoraDescricao,
								
										  String unidadeOrcamentariaCodigo,
										  String unidadeOrcamentariaDescricao,
							 
										  String programaCodigo,
										  String programaDenominacao,
											
										  String acaoCodigo,
										  Long acaoId,
										  String acaoDenominacao,
									 
									      String naturezaDescricao,
									  
									      BigDecimal dotacaoInicial,
									      BigDecimal disponivel,
									      BigDecimal empenhado,
									      BigDecimal liquidado,
									      BigDecimal pago
										) {
		
 		
		  this.acao = new Acao();
		  this.acao.setId(acaoId);
		  this.acao.setCodigo(acaoCodigo);
		  this.acao.setDenominacao(acaoDenominacao);
		  
		  this.acao.setUnidadeOrcamentaria(new UnidadeOrcamentaria());
		  this.acao.getUnidadeOrcamentaria().setCodigo(unidadeOrcamentariaCodigo);
		  this.acao.getUnidadeOrcamentaria().setDescricao(unidadeOrcamentariaDescricao);
		  
		  this.acao.setPrograma(new Programa());
		  this.acao.getPrograma().setCodigo(programaCodigo);
		  this.acao.getPrograma().setDenominacao(programaDenominacao);
		  
		  this.acao.getUnidadeOrcamentaria().setUnidadeGestoraCodigo(unidadeGestoraCodigo);
		  this.acao.getUnidadeOrcamentaria().setUnidadeGestoraSigla(unidadeGestoraSigla);
		  this.acao.getUnidadeOrcamentaria().setUnidadeGestoraDescricao(unidadeGestoraDescricao);
		  
		  this.naturezaDescricao = naturezaDescricao;
	  
		  this.dotacaoInicial = dotacaoInicial;
		  this.disponivel = disponivel;
		  this.empenhado = empenhado;
		  this.liquidado = liquidado;
		  this.pago	= pago;	
 
		
		
	}
		
	public FisicoFinanceiroMensalSiafem(
									     Mes mes,
									     String unidadeGestoraCodigo,
									    
									     UnidadeGestora unidadeGestora,
									     String codigoUnidadeOrcamentaria, 
									     String programa,
									     String acaoCodigo,
									     String acaDescricao,
									     String planoInterno,
									     String planoInternoDescricao,
									     String fonte,
									     String natureza,
									     String naturezaDescricao,
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
		 this.unidadeGestoraCodigo = unidadeGestoraCodigo;
		 this.unidadeGestora= unidadeGestora;
		 this.unidadeOrcamentaria = codigoUnidadeOrcamentaria;
		 this.programa = programa;
		 
		 this.acaoCodigo = acaoCodigo;
		 this.acaDescricao = acaDescricao;
		 this.planoInterno = planoInterno;
		 this.planoInternoDescricao = planoInternoDescricao;
		 this.fonte = fonte;
		 this.natureza = natureza;
		 this.naturezaDescricao = naturezaDescricao;
		 this.regiao = regiao;
		 
		 this.acao = acao;
		 this.dotacaoInicial = dotacaoInicial;
		 this.disponivel = disponivel;
		 this.empenhado = empenhado;
		 this.liquidado = liquidado;
		 this.pago = pago;
		 this.ano = ano;
		
		
	}
	
	public FisicoFinanceiroMensalSiafem(
										 String unidadeOrcamentariaCodigo,
										 String unidadeOrcamentariaDescricao,
										 String programa,	
										 String acaoCodigo,
										 String acaoDenominacao,
										 String acaoProduto,
										 String unidadeMedidaDescricao
										) {
		
		this.acao = new Acao();
		this.acao.setCodigo(acaoCodigo);
		this.acao.setDenominacao(acaoDenominacao);
		this.acao.setProduto(acaoProduto);
		
		this.acao.setUnidadeOrcamentaria(new UnidadeOrcamentaria());
		this.acao.getUnidadeOrcamentaria().setCodigo(unidadeOrcamentariaCodigo);
		this.acao.getUnidadeOrcamentaria().setDescricao(unidadeOrcamentariaDescricao);
		this.acao.getUnidadeOrcamentaria().setProgramaDescricao(programa);
		
		this.acao.setUnidadeMedida(new UnidadeMedida());
		this.acao.getUnidadeMedida().setDescricao(unidadeMedidaDescricao);
		
	 
		
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

	 

	public UnidadeGestora getUnidadeGestora() {
		return unidadeGestora;
	}

	public void setUnidadeGestora(UnidadeGestora unidadeGestora) {
		this.unidadeGestora = unidadeGestora;
	}

	public String getUnidadeGestoraCodigo() {
		return unidadeGestoraCodigo;
	}

	public void setUnidadeGestoraCodigo(String unidadeGestoraCodigo) {
		this.unidadeGestoraCodigo = unidadeGestoraCodigo;
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

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public BigDecimal getEmpenhadoSobreDisponivel() {
		return empenhadoSobreDisponivel;
	}

	public void setEmpenhadoSobreDisponivel(BigDecimal empenhadoSobreDisponivel) {
		this.empenhadoSobreDisponivel = empenhadoSobreDisponivel;
	}

	public BigDecimal getPagoSobreDisponivel() {
		return pagoSobreDisponivel;
	}

	public void setPagoSobreDisponivel(BigDecimal pagoSobreDisponivel) {
		this.pagoSobreDisponivel = pagoSobreDisponivel;
	}

	public String getNaturezaDescricao() {
		return naturezaDescricao;
	}

	public void setNaturezaDescricao(String naturezaDescricao) {
		this.naturezaDescricao = naturezaDescricao;
	}

 
	
	
}
