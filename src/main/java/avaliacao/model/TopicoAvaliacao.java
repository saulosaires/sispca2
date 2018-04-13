package avaliacao.model;

import java.io.Serializable;
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

 
@Entity
@Table(name = "topico_avaliacao", schema = "avaliacao")
public class TopicoAvaliacao extends Model implements Serializable, Auditable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_topico_avaliacao")
	private Long id;

	@Column(name = "descricao")
	private String descricao;

	@OneToMany(mappedBy = "topicoAvaliacao")
	private List<ExercicioTopicoAvaliacao> exercicioTopicoAvaliacaos;

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

	public List<ExercicioTopicoAvaliacao> getExercicioTopicoAvaliacaos() {
		return this.exercicioTopicoAvaliacaos;
	}

	public void setExercicioTopicoAvaliacaos(List<ExercicioTopicoAvaliacao> exercicioTopicoAvaliacaos) {
		this.exercicioTopicoAvaliacaos = exercicioTopicoAvaliacaos;
	}

	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append("Tópico Avaliação ID: " + id);
		sb.append(" Descrição: " + descricao);
		return sb.toString();
	}

}