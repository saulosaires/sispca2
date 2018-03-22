package qualitativo.model;

import java.io.Serializable;

import javax.persistence.*;

import arquitetura.model.Model;

import java.util.List;


/**
 * The persistent class for the funcao database table.
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="funcao", schema="planejamento")
public class Funcao extends Model implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_funcao")
	private Long id;
 
	@Column(name="codigo")
	private String codigo;

	@Column(name="descricao")
	private String descricao;

	//bi-directional many-to-one association to Acao
	@OneToMany(mappedBy="funcao")
	private List<Acao> acaos;

	//bi-directional many-to-one association to Subfuncao
	@OneToMany(mappedBy="funcao")
	private List<Subfuncao> subfuncaos;

	public Funcao() {}	

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

	public Acao addAcao(Acao acao) {
		getAcaos().add(acao);
		acao.setFuncao(this);

		return acao;
	}

	public Acao removeAcao(Acao acao) {
		getAcaos().remove(acao);
		acao.setFuncao(null);

		return acao;
	}

	public List<Subfuncao> getSubfuncaos() {
		return this.subfuncaos;
	}

	public void setSubfuncaos(List<Subfuncao> subfuncaos) {
		this.subfuncaos = subfuncaos;
	}

	public Subfuncao addSubfuncao(Subfuncao subfuncao) {
		getSubfuncaos().add(subfuncao);
		subfuncao.setFuncao(this);

		return subfuncao;
	}

	public Subfuncao removeSubfuncao(Subfuncao subfuncao) {
		getSubfuncaos().remove(subfuncao);
		subfuncao.setFuncao(null);

		return subfuncao;
	}
	
	@Override
	public boolean equals(Object object){
		if(object != null && object instanceof Funcao){
			return ((Funcao)object).getId() == this.id;
		}
		return false;
	}

}