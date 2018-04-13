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
@Table(name="avaliacao_fisico_financeira", schema="avaliacao")
public class AvaliacaoFisicoFinanceira extends Model implements Serializable, Auditable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_avaliacao_financeira")
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@Column(name="resposta")
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
	@JoinColumn(name="id_exercicio_topico_avaliacao")
	private ExercicioTopicoAvaliacao exercicioTopicoAvaliacao;
 

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

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public ExercicioTopicoAvaliacao getExercicioTopicoAvaliacao() {
		return exercicioTopicoAvaliacao;
	}

	public void setExercicioTopicoAvaliacao(
			ExercicioTopicoAvaliacao exercicioTopicoAvaliacao) {
		this.exercicioTopicoAvaliacao = exercicioTopicoAvaliacao;
	}
 

	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append("Avaliação Físico Financeira ID: "+id);
		sb.append("Data: "+data);
		sb.append("Exercicio ID: "+exercicio.getId());
		sb.append("Programa ID: "+programa.getId());
		sb.append("Usuário ID: "+usuario.getId());
		sb.append("Exercicio Tópico Avaliacação ID: "+exercicioTopicoAvaliacao.getId());
		return sb.toString();
	}

}