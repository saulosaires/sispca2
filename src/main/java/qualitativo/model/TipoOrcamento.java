package qualitativo.model;

import java.io.Serializable;
import javax.persistence.*;

import arquitetura.model.Model;

import java.util.List;



@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="tipo_orcamento", schema="planejamento")
public class TipoOrcamento extends Model implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_tipo_orcamento")
	private Long id;
 
	@Column(name="descricao")
	private String descricao;

	@OneToMany(mappedBy="tipoOrcamento")
	private List<Acao> acaos;

	public TipoOrcamento() {
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
 

}