package avaliacao.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import administrativo.model.Exercicio;
import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;


/**
 * The persistent class for the exercicio_topico_custo database table.
 * 
 */
@Entity
@Table(name="exercicio_topico_custo", schema="avaliacao")
public class ExercicioTopicoCusto extends Model  implements  Auditable {
	private static final long serialVersionUID = 1L;

	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_exercicio_topico_custo")
	private Long id;

	@ManyToOne
	@JoinColumn(name="id_exercicio")
	private Exercicio exercicio;

	//bi-directional many-to-one association to TopicoCusto
	@ManyToOne
	@JoinColumn(name="id_topico_custo")
	private TopicoCusto topicoCusto;

	//bi-directional many-to-one association to RespostaCusto
	@OneToMany(mappedBy="exercicioTopicoCusto")
	private List<Custo> custos;
	 
	public Long getId() {
		return id;
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

	public TopicoCusto getTopicoCusto() {
		return topicoCusto;
	}

	public void setTopicoCusto(TopicoCusto topicoCusto) {
		this.topicoCusto = topicoCusto;
	}	

	public List<Custo> getCustos() {
		return custos;
	}

	public void setCustos(List<Custo> custos) {
		this.custos = custos;
	}	
 
	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append("Exercício Tópico Custo ID: "+id);
		sb.append("Exercício ID: "+exercicio.getId());
		sb.append("Tópico Custo ID: "+topicoCusto.getId());
		return sb.toString();
	}	

}