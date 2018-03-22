package qualitativo.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Size;

import arquitetura.model.Model;

import java.util.List;


/**
 * The persistent class for the tipo_horizonte_temporal database table.
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="tipo_horizonte_temporal", schema="planejamento")
public class TipoHorizonteTemporal extends Model implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_tipo_horizonte_temporal")
	private Long id;
 
	@Size(min = 1)
	@Column(name = "descricao")
	private String descricao;

	//bi-directional many-to-one association to Acao
	@OneToMany(mappedBy="tipoHorizonteTemporal")
	private List<Acao> acaos;

	//bi-directional many-to-one association to Programa
	@OneToMany(mappedBy="tipoHorizonteTemporal")
	private List<Programa> programas;

	public TipoHorizonteTemporal() {}	

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
		acao.setTipoHorizonteTemporal(this);

		return acao;
	}

	public Acao removeAcao(Acao acao) {
		getAcaos().remove(acao);
		acao.setTipoHorizonteTemporal(null);

		return acao;
	}

	public List<Programa> getProgramas() {
		return this.programas;
	}

	public void setProgramas(List<Programa> programas) {
		this.programas = programas;
	}

	public Programa addPrograma(Programa programa) {
		getProgramas().add(programa);
		programa.setTipoHorizonteTemporal(this);

		return programa;
	}

	public Programa removePrograma(Programa programa) {
		getProgramas().remove(programa);
		programa.setTipoHorizonteTemporal(null);

		return programa;
	}
	
	@Override
	public boolean equals(Object object){
		if(object != null && object instanceof TipoHorizonteTemporal){
			return ((TipoHorizonteTemporal)object).getId() == this.id;
		}
		return false;
	}

}