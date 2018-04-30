package qualitativo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import arquitetura.model.Model;

@Entity
@Table(name = "funcao", schema = "planejamento")
public class Funcao extends Model  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_funcao")
	private Long id;

	@Column(name = "codigo")
	private String codigo;

	@Column(name = "descricao")
	private String descricao;

	@OneToMany(mappedBy = "funcao")
	private List<Acao> acaos;

	@OneToMany(mappedBy = "funcao")
	private List<SubFuncao> subfuncaos;

	public Funcao() {}
	
	public Funcao(Long id) {
		this.id=id;
	}
	
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

	public List<SubFuncao> getSubfuncaos() {
		return this.subfuncaos;
	}

	public void setSubfuncaos(List<SubFuncao> subfuncaos) {
		this.subfuncaos = subfuncaos;
	}

}