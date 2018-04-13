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
@Table(name="custo", schema="avaliacao")
public class Custo  extends Model implements Serializable, Auditable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_custo")
	private Long id;
	
	private Boolean ativo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@Column(name="resposta",nullable=false)
	private String resposta;

	@ManyToOne
	@JoinColumn(name="id_exercicio")
	private Exercicio exercicio;

	@ManyToOne
	@JoinColumn(name="id_programa")
	private Programa programa;

	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="id_exercicio_topico_custo")
	private ExercicioTopicoCusto exercicioTopicoCusto;
 

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}	

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
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

	public ExercicioTopicoCusto getExercicioTopicoCusto() {
		return exercicioTopicoCusto;
	}

	public void setExercicioTopicoCusto(ExercicioTopicoCusto exercicioTopicoCusto) {
		this.exercicioTopicoCusto = exercicioTopicoCusto;
	}
	
	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append("Custo ID: "+id);
		sb.append("Exercício ID: "+exercicio.getId());
		sb.append("Programa ID: "+programa.getId());
		sb.append("Usuário ID: "+usuario.getId());
		sb.append("Resposta: "+resposta);
		sb.append("Exercicio Tópico Custo ID: "+exercicioTopicoCusto.getId());
		return sb.toString();
	}

}