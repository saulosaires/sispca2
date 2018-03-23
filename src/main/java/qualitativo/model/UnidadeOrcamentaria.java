package qualitativo.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the unidade_orcamentaria database table.
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="unidade_orcamentaria", schema="comum")
public class UnidadeOrcamentaria extends Model implements Serializable, Auditable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_unidade_orcamentaria")
	private Long id;
 

	@NotNull(message="Código: campo é obrigatório")
	@Column(name="codigo",length=10)
	private String codigo;

	@NotNull(message="Descrição: campo é obrigatório")
	@Column(name="descricao")
	private String descricao;

	@ManyToOne
	@JoinColumn(name="id_orgao")
	@NotNull(message="Órgão: campo é obrigatório")
	private Orgao orgao;

	@NotNull(message ="Sigla: campo é obrigatório")
	@Column(name="sigla",length=20)
	private String sigla;

	@OneToMany(mappedBy="unidadeOrcamentaria")
	private List<UnidadeGestora> unidadeGestoras;
	
	@OneToMany(mappedBy="unidadeOrcamentaria")
	private List<Programa> programas;
	
	@OneToMany(mappedBy="unidadeOrcamentaria")
	private List<Acao> acaos;

	public UnidadeOrcamentaria() {
		//empty constructor
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Orgao getOrgao() {
		return orgao;
	}

	public void setOrgao(Orgao orgao) {
		this.orgao = orgao;
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

	public String getSigla() {
		return this.sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public List<UnidadeGestora> getUnidadeGestoras() {
		return this.unidadeGestoras;
	}

	public void setUnidadeGestoras(List<UnidadeGestora> unidadeGestoras) {
		this.unidadeGestoras = unidadeGestoras;
	}	

	public List<Programa> getProgramas() {
		return programas;
	}

	public void setProgramas(List<Programa> programas) {
		this.programas = programas;
	}	
	
	public List<Acao> getAcaos() {
		return acaos;
	}

	public void setAcaos(List<Acao> acaos) {
		this.acaos = acaos;
	}

	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append("Unidade Orçamentária Id : ").append(id).append("Unidade Codigo: ")
				.append(codigo).append(" Unidade Descricao : ").append(descricao)
				.append(" Unidade Sigla : ").append(sigla)
				.append(" Unidade Orçamentária Data da Operação: "+new Date(System.currentTimeMillis()));

		return sb.toString();	
	}

 
	
}