package monitoramento.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import administrativo.model.Exercicio;
import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;
import qualitativo.model.Acao;
import qualitativo.model.Mes;
import qualitativo.model.Orgao;
import qualitativo.model.Programa;
import qualitativo.model.UnidadeMedida;
import qualitativo.model.UnidadeOrcamentaria;
import quantitativo.model.Municipio;
import quantitativo.model.Regiao;
import quantitativo.model.RegiaoMunicipio;
 


@Entity
@Table(name = "execucao", schema = "monitoramento")
public class Execucao extends Model implements  Auditable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_execucao")
	private Long id;
 

	@ManyToOne
	@JoinColumn(name = "id_acao")
	private Acao acao;

 
	@ManyToOne
	@JoinColumn(name="id_mes")
	private Mes mes;

	@ManyToOne
	@JoinColumn(name = "id_regiao_municipio")
	private RegiaoMunicipio regiaoMunicipio;

	@Column(name = "valor")
	private BigDecimal valor;
	
	@Column(name = "quantidade")
	private BigDecimal quantidade;
	
	@Column(name = "observacao",length=1000)
	private String observacao;
	
	@ManyToOne
	@JoinColumn(name="id_exercicio")
	private Exercicio exercicio;

	public Execucao(){ }
	
	public Execucao(	
					Long regiaoMunicipioId,
					String regiaoDescricao,
					String municipioDescricao,
					Long mesId,
			
					BigDecimal quantidade,
					BigDecimal valor,
					String observacao,
		    
					String orgaoCodigo,
					String orgaoDescricao,
			
					String unidadeOrcamentariaCodigo,
					String unidadeOrcamentariaDescricao,
			
					String programaCodigo,
					String programaDenominacao,
			 
					String acaoCodigo,
					String acaoDenominacao,
					String acaoProduto,
			
					String unidadeMedidaDescricao
					){
		
		this.regiaoMunicipio = new RegiaoMunicipio(regiaoMunicipioId);
		
		if(regiaoDescricao!=null) {
			this.regiaoMunicipio.setRegiao(new Regiao());
			this.regiaoMunicipio.getRegiao().setDescricao(regiaoDescricao);
		}
		
		if(municipioDescricao!=null) {
			this.regiaoMunicipio.setMunicipio(new Municipio());
			this.regiaoMunicipio.getMunicipio().setDescricao(municipioDescricao);
		}
		
		this.mes = new Mes(mesId);
		
		this.quantidade= quantidade;
		this.valor=valor;
		this.observacao=observacao;
		
		this.acao = new Acao();
		this.acao.setCodigo(acaoCodigo);
		this.acao.setDenominacao(acaoDenominacao); 
		this.acao.setProduto(acaoProduto);
		this.acao.setUnidadeMedida(new UnidadeMedida());
		this.acao.getUnidadeMedida().setDescricao(unidadeMedidaDescricao);
		
		this.acao.setPrograma(new Programa());
		this.acao.getPrograma().setCodigo(programaCodigo);
		this.acao.getPrograma().setDenominacao(programaDenominacao);
		
		this.acao.setUnidadeOrcamentaria(new UnidadeOrcamentaria());
		this.acao.getUnidadeOrcamentaria().setCodigo(unidadeOrcamentariaCodigo);
		this.acao.getUnidadeOrcamentaria().setDescricao(unidadeOrcamentariaDescricao);
		
		this.acao.getUnidadeOrcamentaria().setOrgao(new Orgao());
		this.acao.getUnidadeOrcamentaria().getOrgao().setCodigo(orgaoCodigo);
		this.acao.getUnidadeOrcamentaria().getOrgao().setDescricao(orgaoDescricao);
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
 
	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

 
	public Mes getMes() {
		return mes;
	}

	public void setMes(Mes mes) {
		this.mes = mes;
	}

	public RegiaoMunicipio getRegiaoMunicipio() {
		return regiaoMunicipio;
	}

	public void setRegiaoMunicipio(RegiaoMunicipio regiaoMunicipio) {
		this.regiaoMunicipio = regiaoMunicipio;
	}	

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
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
		sb.append(" Execucao Id : ").append(id);
		sb.append(" Execucao Acao : ").append(acao.getId());
		sb.append(" Execucao Mes : ").append(mes.getId());
		sb.append(" Execucao RegiaoMunicipio : ").append(regiaoMunicipio.getId());
		sb.append(" Execucao Valor : ").append(valor);
		sb.append(" Execucao Quantidade : ").append(quantidade);
		sb.append(" Execucao Ativo : ").append(getAtivo());
		sb.append(" Execucao Exercicio : ").append(exercicio.getId());
		sb.append(" Execucao Observacao : ").append(observacao);

		return sb.toString();
	}	
	
}