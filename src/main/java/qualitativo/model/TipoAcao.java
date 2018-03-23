package qualitativo.model;

import java.io.Serializable;
import javax.persistence.*;

import arquitetura.model.Model;

import java.util.List;


/**
 * The persistent class for the tipo_acao database table.
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="tipo_acao", schema="planejamento")
public class TipoAcao extends Model implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_tipo_acao")
	private Long id;
 
	@Column(name="descricao")
	private String descricao;

	@OneToMany(mappedBy="tipoAcao")
	private List<Acao> acaos;

	public TipoAcao() {
		//empty constructor
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
 
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Acao> getAcaos() {
		return this.acaos;
	}

	public void setAcaos(List<Acao> acaos) {
		this.acaos = acaos;
	}
 
}