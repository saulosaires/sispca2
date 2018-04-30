package quantitativo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 

@Entity
@Table(name="fisico_financeiro_mensal", schema="planejamento")
public class FisicoFinanceiroMensal extends Model implements  Auditable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_fisico_financeiro_mensal")
	private Long id;
	
	@Column(name="quantidade")
	private Double quantidade=0D;
	
	@Column(name="valor")
	private Double valor=0D;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_regiao_municipio")
	private RegiaoMunicipio regiaoMunicipio;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_mes")
	private Mes mes;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_acao")
	private Acao acao;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_exercicio")
	private Exercicio exercicio;
 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public RegiaoMunicipio getRegiaoMunicipio() {
		return regiaoMunicipio;
	}

	public void setRegiaoMunicipio(RegiaoMunicipio regiaoMunicipio) {
		this.regiaoMunicipio = regiaoMunicipio;
	}

	public Mes getMes() {
		return mes;
	}

	public void setMes(Mes mes) {
		this.mes = mes;
	}

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
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
		sb.append(" FisicoFinanceiroMensal Id : ").append(id);
		sb.append(" mes : ").append(mes.getId());
		sb.append(" Acao : ").append(acao.getId());
		sb.append(" quantidade : ").append(quantidade);
		sb.append(" valor : ").append(valor);
		sb.append(" regiaoMunicipio : ").append(regiaoMunicipio.getId());
		sb.append(" exercicio : ").append(exercicio.getId());
		sb.append(" ativo : ").append(getAtivo());
		
		return sb.toString();
	}
	
}
