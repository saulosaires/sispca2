package avaliacao.model;

import java.io.Serializable;
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
@Table(name="exercicio_topico_avaliacao", schema="avaliacao")
public class ExercicioTopicoAvaliacao extends Model   implements Serializable, Auditable {
	private static final long serialVersionUID = 1L;

	@Id
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_exercicio_topico_avaliacao")
	private Long id;

	@ManyToOne
	@JoinColumn(name="id_exercicio")
	private Exercicio exercicio;

	//bi-directional many-to-one association to TopicoAvaliacao
	@ManyToOne
	@JoinColumn(name="id_topico_avaliacao")
	private TopicoAvaliacao topicoAvaliacao;

	//bi-directional many-to-one association to RespostaAvaliacao
	@OneToMany(mappedBy="exercicioTopicoAvaliacao")
	private List<AvaliacaoFisicoFinanceira> avaliacoesFisiocFinanceiras;
	
 

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public TopicoAvaliacao getTopicoAvaliacao() {
		return this.topicoAvaliacao;
	}

	public void setTopicoAvaliacao(TopicoAvaliacao topicoAvaliacao) {
		this.topicoAvaliacao = topicoAvaliacao;
	}	
	
	public Exercicio getExercicio() {
		return exercicio;
	}

	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}

	public List<AvaliacaoFisicoFinanceira> getAvaliacoesFisiocFinanceiras() {
		return avaliacoesFisiocFinanceiras;
	}

	public void setAvaliacoesFisiocFinanceiras(
			List<AvaliacaoFisicoFinanceira> avaliacoesFisiocFinanceiras) {
		this.avaliacoesFisiocFinanceiras = avaliacoesFisiocFinanceiras;
	}	
 

	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append(" Exercício Tópico Avaliação: "+id);
		sb.append(" Exercício ID: "+exercicio.getId());
		sb.append(" Tópico Avaliação ID: "+topicoAvaliacao.getId());
		return null;
	}
	
}