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


/**
 * The persistent class for the tipo_calculo_meta database table.
 * 
 */
@Entity
@Table(name="tipo_calculo_meta", schema="planejamento")
public class TipoCalculoMeta extends Model  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_tipo_calculo_meta")
	private Long id;
 
	@Column(name="descricao")
	private String descricao;

	//bi-directional many-to-one association to Acao
	@OneToMany(mappedBy="tipoCalculoMeta")
	private List<Acao> acaos;

	//bi-directional many-to-one association to EtapasExecucao
	@OneToMany(mappedBy="tipoCalculoMeta")
	private List<EtapasExecucao> etapasExecucaos;

	public TipoCalculoMeta() {}

	public TipoCalculoMeta(Long id) {this.id=id;}
	

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

	public List<EtapasExecucao> getEtapasExecucaos() {
		return this.etapasExecucaos;
	}

	public void setEtapasExecucaos(List<EtapasExecucao> etapasExecucaos) {
		this.etapasExecucaos = etapasExecucaos;
	}
}