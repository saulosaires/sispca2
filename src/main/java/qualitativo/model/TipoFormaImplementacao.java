package qualitativo.model;

import java.io.Serializable;
import javax.persistence.*;

import arquitetura.model.Model;

import java.util.List;


/**
 * The persistent class for the tipo_forma_implementacao database table.
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="tipo_forma_implementacao", schema="planejamento")
public class TipoFormaImplementacao extends Model implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_tipo_forma_implementacao")
	private Long id;

	@Column(name="descricao")
	private String descricao;

	@OneToMany(mappedBy="tipoFormaImplementacao")
	private List<Acao> acaos;

	public TipoFormaImplementacao() {
		//empty constructor
	}	

	public Long getId() {
		return id;
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

	public Acao addAcao(Acao acao) {
		getAcaos().add(acao);
		acao.setTipoFormaImplementacao(this);
		return acao;
	}

	public Acao removeAcao(Acao acao) {
		getAcaos().remove(acao);
		acao.setTipoFormaImplementacao(null);
		return acao;
	}
	
 

}