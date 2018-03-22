package arquitetura.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Model {

	@Column(name = "ativo")
	private Boolean ativo = true;

	public Model() {
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {

		this.ativo = ativo;
	}

}
