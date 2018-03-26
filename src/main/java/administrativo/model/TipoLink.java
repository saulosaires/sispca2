package administrativo.model;

import java.io.Serializable;
import javax.persistence.*;

import arquitetura.model.Model;

import java.util.List;


/**
 * The persistent class for the tipo_link database table.
 * 
 */
@Entity
@Table(name="tipo_link", schema="comum")
public class TipoLink extends Model implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_tipo_link")
	private Long id;
 
	@Column(name="descricao",length=50,nullable=false)
	private String descricao;

	@OneToMany(mappedBy="tipoLink")
	private List<Link> links;
 
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

	public List<Link> getLinks() {
		return this.links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	@Override
	public boolean equals(Object object){
		if(object != null && object instanceof TipoLink){
			return ((TipoLink)object).getId() == this.id;
		}
		return false;
	}
}