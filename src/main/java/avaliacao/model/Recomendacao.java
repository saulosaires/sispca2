package avaliacao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import administrativo.model.Exercicio;
import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;
import qualitativo.model.Programa;

 
@Entity
@Table(name = "recomendacao", schema = "avaliacao")
public class Recomendacao extends Model implements  Auditable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_recomendacao")
	private Long id;

	@Column(name = "descricao")
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "id_exercicio")
	private Exercicio exercicio;

	@ManyToOne
	@JoinColumn(name = "id_programa")
	private Programa programa;
 

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

 

	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append("Recomendacao ID: " + id);
		sb.append("Exercicio ID: " + exercicio.getId());
		sb.append("Programa ID: " + programa.getId());
		 
		return sb.toString();
	}

}