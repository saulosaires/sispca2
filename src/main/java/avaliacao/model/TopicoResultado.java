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


/**
 * The persistent class for the topico_resultado database table.
 * 
 */
@Entity
@Table(name="topico_resultado", schema="avaliacao")
public class TopicoResultado extends Model implements Serializable, Auditable {
	private static final long serialVersionUID = 1L;

	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_topico_resultado")
	private Long id;

	@Column(name="descricao")
	private String descricao;

	//bi-directional many-to-one association to ExercicioTopicoResultado
	@OneToMany(mappedBy="topicoResultado")
	private List<ExercicioTopicoResultado> exercicioTopicoResultados;
 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<ExercicioTopicoResultado> getExercicioTopicoResultados() {
		return exercicioTopicoResultados;
	}

	public void setExercicioTopicoResultados(
			List<ExercicioTopicoResultado> exercicioTopicoResultados) {
		this.exercicioTopicoResultados = exercicioTopicoResultados;
	}

	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append("Tópico Resultado ID: "+id);
		sb.append("Descrição: "+descricao);
		return sb.toString();
	}	

}