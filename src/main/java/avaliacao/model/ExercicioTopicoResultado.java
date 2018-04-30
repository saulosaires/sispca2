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

 
@Entity
@Table(name="exercicio_topico_resultado", schema="avaliacao")
public class ExercicioTopicoResultado extends Model  implements  Auditable {
	private static final long serialVersionUID = 1L;

	@Id
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_exercicio_topico_resultado")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_exercicio")
	private Exercicio exercicio;

 	@ManyToOne
	@JoinColumn(name="id_topico_resultado")
	private TopicoResultado topicoResultado;

 	@OneToMany(mappedBy="exercicioTopicoResultado")
	private List<Resultado> resultados;
 
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

	public TopicoResultado getTopicoResultado() {
		return topicoResultado;
	}

	public void setTopicoResultado(TopicoResultado topicoResultado) {
		this.topicoResultado = topicoResultado;
	}

	public List<Resultado> getResultados() {
		return resultados;
	}

	public void setResultados(List<Resultado> resultados) {
		this.resultados = resultados;
	}
 
	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append("Exercício Tópico Resultado ID: "+id);
		sb.append("Exercício ID: "+exercicio.getId());
		sb.append("Tópico Resultado ID: "+topicoResultado.getId());
		return null;
	}	

}