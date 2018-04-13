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
 * The persistent class for the topico_analise database table.
 * 
 */
@Entity
@Table(name="topico_analise", schema="avaliacao")
public class TopicoAnalise extends Model implements Serializable, Auditable {
	private static final long serialVersionUID = 1L;

	@Id
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_topico_analise")
	private Long id;

	@Column(name="descricao")
	private String descricao;

 	@OneToMany(mappedBy="topicoAnalise")
	private List<ExercicioTopicoAnalise> exercicioTopicoAnalises;
 	

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

	public List<ExercicioTopicoAnalise> getExercicioTopicoAnalises() {
		return this.exercicioTopicoAnalises;
	}

	public void setExercicioTopicoAnalises(List<ExercicioTopicoAnalise> exercicioTopicoAnalises) {
		this.exercicioTopicoAnalises = exercicioTopicoAnalises;
	}

	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append("Tópico Análise ID: "+id);
		sb.append(" Descricao: "+descricao);
		return sb.toString();
	}

}