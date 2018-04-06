package administrativo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import arquitetura.model.Model;


/**
 * The persistent class for the tipo_link database table.
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="tipo_link", schema="comum")
public class TipoLink extends Model {
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
 
}