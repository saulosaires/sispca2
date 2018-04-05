package administrativo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import arquitetura.model.Model;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "acesso_View", schema = "controle_acesso")
public class AcessoView extends Model implements Serializable {

	@Id
	@Column(name = "name", length = 150, nullable = false)
	private String name;

	@Column(name = "link", length = 150, nullable = false)
	private String link;

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
