package avaliacao.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import administrativo.model.Exercicio;
import administrativo.model.Usuario;
import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;
import qualitativo.model.Programa;


@Entity
@Table(name="resultado", schema="avaliacao")
public class Resultado extends Model implements Serializable, Auditable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_resultado")
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data; 

	@ManyToOne
	@JoinColumn(name="id_exercicio")
	private Exercicio exercicio;

	@ManyToOne
	@JoinColumn(name="id_programa")
	private Programa programa;

 
	
	@Column(name="resposta")
	private String resposta;
	
	@ManyToOne
	@JoinColumn(name="id_exercicio_topico_resultado")
	private ExercicioTopicoResultado exercicioTopicoResultado;
 
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

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	 
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
 

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public ExercicioTopicoResultado getExercicioTopicoResultado() {
		return exercicioTopicoResultado;
	}

	public void setExercicioTopicoResultado(
			ExercicioTopicoResultado exercicioTopicoResultado) {
		this.exercicioTopicoResultado = exercicioTopicoResultado;
	}

	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append("Resultado ID: "+id);
		sb.append("Exercicio ID: "+exercicio.getId());
		sb.append("Programa ID: "+programa.getId());
	 
		sb.append("Exercício Tópico Resultado: "+exercicioTopicoResultado.getId());
		return sb.toString();
	}	

}