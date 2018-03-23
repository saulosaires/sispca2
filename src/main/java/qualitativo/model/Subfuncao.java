package qualitativo.model;

import java.io.Serializable;
import javax.persistence.*;

import arquitetura.model.Model;

import java.util.List;


/**
 * The persistent class for the subfuncao database table.
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="subfuncao", schema="planejamento")
public class Subfuncao extends Model implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_subfuncao")
	private Long id;

	@Column(name="codigo")
	private String codigo;

	@Column(name="descricao")
	private String descricao;
	 

	//bi-directional many-to-one association to Acao
	@OneToMany(mappedBy="subfuncao")
	private List<Acao> acaos;

	//bi-directional many-to-one association to Funcao
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_funcao")
	private Funcao funcao;

	public Subfuncao() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public Funcao getFuncao() {
		return this.funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}
 

}