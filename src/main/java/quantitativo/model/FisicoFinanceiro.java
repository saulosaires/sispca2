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
import javax.validation.constraints.NotNull;

import administrativo.model.Exercicio;
import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;
import qualitativo.model.Acao;
 

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
	private double quantidade;

	@Column(name="valor")
	private double valor;
 
 
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_acao")
	@NotNull(message="Campo Obrigatório")
	private Acao acao;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_localizador")
	private Localizador localizador;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_regiao_municipio")
	private RegiaoMunicipio regiaoMunicipio;
	 

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

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public double getValor() {
		return this.valor;
	}

	
	public void setValor(double valor) {
		this.valor = valor;
	}
 

	public Acao getAcao() {
		return this.acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public Localizador getLocalizador() {
		return this.localizador;
	}

	public void setLocalizador(Localizador localizador) {
		this.localizador = localizador;
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