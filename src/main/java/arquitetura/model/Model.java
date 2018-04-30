package arquitetura.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract  class Model implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3183497410944567260L;
	
	@Column(name = "ativo")
	private Boolean ativo = true;

 

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {

		this.ativo = ativo;
	}

}
