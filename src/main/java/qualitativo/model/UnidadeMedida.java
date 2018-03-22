package qualitativo.model;

import java.io.Serializable;

import javax.persistence.*;

import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;

import java.util.List;


/**
 * The persistent class for the unidade_medida database table.
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="unidade_medida", schema="planejamento")
public class UnidadeMedida extends Model implements Serializable, Auditable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_unidade")
	private Long id;
 
	@Column(name="codigo")
	private Integer codigo;

	@Column(name="descricao")
	private String descricao;

	@Column(name="sigla")
	private String sigla;

	//bi-directional many-to-one association to Acao
	@OneToMany(mappedBy="unidadeMedida")
	private List<Acao> acaos;

	//bi-directional many-to-one association to EtapasExecucao
	@OneToMany(mappedBy="unidadeMedida")
	private List<EtapasExecucao> etapasExecucaos;

	public UnidadeMedida() {}	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
 
	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
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

	public List<Acao> getAcaos() {
		return this.acaos;
	}

	public void setAcaos(List<Acao> acaos) {
		this.acaos = acaos;
	}

	public List<EtapasExecucao> getEtapasExecucaos() {
		return this.etapasExecucaos;
	}

	public void setEtapasExecucaos(List<EtapasExecucao> etapasExecucaos) {
		this.etapasExecucaos = etapasExecucaos;
	}

	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append("Unidade de Medida id: ").append(this.id)
			.append(" Unidade de Medida Descricao").append(this.descricao)
			.append(" Unidade de Medida Sigla").append(this.sigla);
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object object){
		if(object != null && object instanceof UnidadeMedida){
			return ((UnidadeMedida)object).getId() == this.id;
		}
		return false;
	}

}