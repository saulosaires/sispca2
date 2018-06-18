package quantitativo.model;

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
import javax.validation.constraints.NotNull;

import administrativo.model.Exercicio;
import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;
import qualitativo.model.Acao;
import qualitativo.model.UnidadeOrcamentaria;
 

@Entity
@Table(name="fisico_financeiro", schema="monitoramento")
public class FisicoFinanceiro extends Model implements  Auditable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_fisico_financeiro")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_exercicio",nullable=true)
	private Exercicio exercicio = new Exercicio();
	
	@Column(name="quantidade")
	private BigDecimal quantidade;

	@Column(name="valor")
	private BigDecimal valor;
 
 
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_acao")
	@NotNull(message="Campo Obrigatório")
	private Acao acao;

 
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_regiao_municipio")
	private RegiaoMunicipio regiaoMunicipio;
	 
	public FisicoFinanceiro() { }
	
	public FisicoFinanceiro(String regiaoDescricao,
							String unidadeOrcamentariaDescricao,
							String acaoCodigo,
							String acaoDenominacao,
							Integer exercicioAno,
							BigDecimal quantidade,
							BigDecimal valor
						   ) { 
 
		regiaoMunicipio = new RegiaoMunicipio();
		regiaoMunicipio.setRegiao(new Regiao());
		
		regiaoMunicipio.getRegiao().setDescricao(regiaoDescricao);
		
		acao = new Acao();
		
		acao.setCodigo(acaoCodigo);
		acao.setDenominacao(acaoDenominacao);
		
		acao.setUnidadeOrcamentaria(new UnidadeOrcamentaria());
		acao.getUnidadeOrcamentaria().setDescricao(unidadeOrcamentariaDescricao);
		
		exercicio = new Exercicio();
		exercicio.setAno(exercicioAno);
		
		this.quantidade=quantidade;
		this.valor=valor;
		
	}

	public FisicoFinanceiro(BigDecimal quantidade, BigDecimal valor, int exercicioAno) {

		exercicio = new Exercicio();
		exercicio.setAno(exercicioAno);

		acao = new Acao();
		acao.setExercicio(exercicio);
		 
		
		this.quantidade = quantidade;
		this.valor = valor;

	}
	
	public FisicoFinanceiro(
			   			    String unidadeOrcamentariaCodigo,
			   			    String unidadeOrcamentariaDescricao,
			   			    String acaoCodigo,
						    String acaoDenominacao,
						    Integer ano,
						    BigDecimal valor
			   			   ) {
		
		this.acao = new Acao();
		this.acao.setCodigo(acaoCodigo);
		this.acao.setDenominacao(acaoDenominacao);
		
		this.acao.setUnidadeOrcamentaria(new UnidadeOrcamentaria());
		this.acao.getUnidadeOrcamentaria().setCodigo(unidadeOrcamentariaCodigo);
		this.acao.getUnidadeOrcamentaria().setDescricao(unidadeOrcamentariaDescricao);
		 
		this.acao.setExercicio(new Exercicio()); 
		this.acao.getExercicio().setAno(ano);
 
	    this.valor=valor;
	}
	
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
 
	
	
	public Exercicio getExercicio() {
		return exercicio;
	}

	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
 

	public Acao getAcao() {
		return this.acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}
 
	public RegiaoMunicipio getRegiaoMunicipio() {
		return regiaoMunicipio;
	}

	public void setRegiaoMunicipio(RegiaoMunicipio regiaoMunicipio) {
		this.regiaoMunicipio = regiaoMunicipio;
	}	
	
	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append(" FisicoFinanceiro Id : ").append(id);
		sb.append(" exercicio : ").append(getExercicio().getId());
		sb.append(" acao : ").append(acao.getId());
		sb.append(" quantidade : ").append(quantidade);
		sb.append(" valor : ").append(valor);
		sb.append(" regiaoMunicipio : ").append(regiaoMunicipio.getId());
		sb.append(" ativo : ").append(getAtivo());
		return sb.toString();
		
	}
	
}