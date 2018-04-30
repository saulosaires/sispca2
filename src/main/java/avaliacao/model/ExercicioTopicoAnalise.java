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
 * The persistent class for the exercicio_topico_analise database table.
 * 
 */
@Entity
@Table(name="exercicio_topico_analise", schema="avaliacao")
public class ExercicioTopicoAnalise extends Model  implements  Auditable {
	private static final long serialVersionUID = 1L;

	@Id
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_exercicio_topico_analise")
	private Long id;

	@ManyToOne
	@JoinColumn(name="id_exercicio")
	private Exercicio exercicio;

 
	@ManyToOne
	@JoinColumn(name="id_topico_analise")
	private TopicoAnalise topicoAnalise;

	@OneToMany(mappedBy="exercicioTopicoAnalise")
	private List<Analise> analises;
	 

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

	public TopicoAnalise getTopicoAnalise() {
		return topicoAnalise;
	}

	public void setTopicoAnalise(TopicoAnalise topicoAnalise) {
		this.topicoAnalise = topicoAnalise;
	}	

	public List<Analise> getAnalises() {
		return analises;
	}

	public void setAnalises(List<Analise> analises) {
		this.analises = analises;
	}	
 

	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append(" Exercicio Topico Analise ID: "+id);
		sb.append(" Exercicio ID: "+exercicio.getId());
		sb.append(" Tópico Análise ID: "+topicoAnalise.getId());
		return sb.toString();
	}	

}