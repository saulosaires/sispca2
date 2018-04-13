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
import qualitativo.model.Indicador;
import qualitativo.model.Programa;

@Entity
@Table(name = "painel_associado", schema = "avaliacao")
public class PainelAssociado extends Model implements Serializable, Auditable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_painel_associado")
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@ManyToOne
	@JoinColumn(name = "id_exercicio")
	private Exercicio exercicio;

	@ManyToOne
	@JoinColumn(name = "id_indicador")
	private Indicador indicador;

	@ManyToOne
	@JoinColumn(name = "id_programa")
	private Programa programa;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

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

	public Indicador getIndicador() {
		return indicador;
	}

	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
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

	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append("Painel Associado ID: " + id);
		sb.append("Data: " + data);
		sb.append("Exercicio ID: " + exercicio.getId());
		sb.append("Indicador ID: " + indicador.getId());
		sb.append("Programa: " + programa.getId());
		sb.append(" Usuario ID: " + getId());
		return sb.toString();
	}

}