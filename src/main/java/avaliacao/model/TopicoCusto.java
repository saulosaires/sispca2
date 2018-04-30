package avaliacao.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;
/**
 * The persistent class for the topico_custo database table.
 * 
 */
@Entity
@Table(name="topico_custo", schema="avaliacao")
public class TopicoCusto extends Model implements  Auditable {
	private static final long serialVersionUID = 1L;

	@Id
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_topico_custo")
	private Long id;

	@Column(name="descricao")
	private String descricao;

 	@OneToMany(mappedBy="topicoCusto")
	private List<ExercicioTopicoCusto> exercicioTopicoCustos;
 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<ExercicioTopicoCusto> getExercicioTopicoCustos() {
		return this.exercicioTopicoCustos;
	}

	public void setExercicioTopicoCustos(List<ExercicioTopicoCusto> exercicioTopicoCustos) {
		this.exercicioTopicoCustos = exercicioTopicoCustos;
	}

	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append("Tópico Custo ID: "+id);
		sb.append("Descrição: "+descricao);
		return sb.toString();
	}
	
}