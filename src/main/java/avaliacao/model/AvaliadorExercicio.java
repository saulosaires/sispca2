package avaliacao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import administrativo.model.Exercicio;
import administrativo.model.Usuario;
import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;


/**
 * The persistent class for the avaliador_exercicio database table.
 * 
 */
@Entity
@Table(name="avaliador_exercicio", schema="avaliacao")
public class AvaliadorExercicio extends Model  implements Serializable, Auditable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_avaliador_exercicio")
	@NotNull
	private Long id;

	@ManyToOne
	@JoinColumn(name="id_exercicio")
	@NotNull
	private Exercicio exercicio;

	@ManyToOne
	@JoinColumn(name="id_usuario")
	@NotNull
	private Usuario usuario;

	
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append("Avaliador Exercicio ID: "+id);
		sb.append("Exercicio ID: "+exercicio.getId());
		sb.append("Usuário ID: "+usuario.getId());
		return null;
	}

	

}