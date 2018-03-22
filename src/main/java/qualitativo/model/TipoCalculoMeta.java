package qualitativo.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the tipo_calculo_meta database table.
 * 
 */
@Entity
@Table(name="tipo_calculo_meta", schema="planejamento")
public class TipoCalculoMeta implements Serializable {
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
	
	@Override
	public boolean equals(Object object){
		if(object != null && object instanceof TipoCalculoMeta){
			return ((TipoCalculoMeta)object).getId() == this.id;
		}
		return false;
	}

}