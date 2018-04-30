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
import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;
import qualitativo.model.Programa;

 
@Entity
@Table(name="analise", schema="avaliacao")
public class Analise extends Model implements Serializable, Auditable {
	private static final long serialVersionUID = 1L;

	@Id
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_analise")
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data; 

	@ManyToOne
	@JoinColumn(name="id_exercicio_topico_analise")
	private ExercicioTopicoAnalise exercicioTopicoAnalise;
	
	@ManyToOne
	@JoinColumn(name="id_exercicio")
	private Exercicio exercicio;

	@ManyToOne
	@JoinColumn(name="id_programa")
	private Programa programa;

	 
	
	@Column(name="resposta")
	private String resposta;
	  

	
	
	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public Date getData() {
		return data;
	}




	public void setData(Date data) {
		this.data = data;
	}




	public ExercicioTopicoAnalise getExercicioTopicoAnalise() {
		return exercicioTopicoAnalise;
	}




	public void setExercicioTopicoAnalise(ExercicioTopicoAnalise exercicioTopicoAnalise) {
		this.exercicioTopicoAnalise = exercicioTopicoAnalise;
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

 




	public String getResposta() {
		return resposta;
	}




	public void setResposta(String resposta) {
		this.resposta = resposta;
	}




	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append(" Análise ID: "+id);
		sb.append(" Data: "+data);
		sb.append(" Exercicio ID: "+exercicio.getId());
		sb.append(" Programa ID: "+programa.getId());
	 
		sb.append(" Exercício Tópico Análise: "+exercicioTopicoAnalise.getId());
		return sb.toString();
	}	

}