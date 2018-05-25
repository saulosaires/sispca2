package monitoramento.model;

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
import quantitativo.model.Localizador;
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
	@JoinColumn(name = "id_localizador")
	private Localizador localizador;

	@ManyToOne
	@JoinColumn(name="id_mes")
	private Mes mes;

	@ManyToOne
	@JoinColumn(name = "id_regiao_municipio")
	private RegiaoMunicipio regiaoMunicipio;

	@Column(name = "valor")
	private Double valor;
	
	@Column(name = "quantidade")
	private Double quantidade;
	
	@Column(name = "observacao",length=1000)
	private String observacao;
	
	@ManyToOne
	@JoinColumn(name="id_exercicio")
	private Exercicio exercicio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
 
	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public Localizador getLocalizador() {
		return localizador;
	}

	public void setLocalizador(Localizador localizador) {
		this.localizador = localizador;
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

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
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