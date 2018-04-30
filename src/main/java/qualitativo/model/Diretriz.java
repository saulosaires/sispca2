package qualitativo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import arquitetura.model.Model;


/**
 * The persistent class for the diretriz database table.
 * 
 */
@Entity
@Table(name="diretriz", schema="planejamento")
public class Diretriz extends Model  {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 190586051296173639L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_diretriz")
	private Long id;

	@Column(name="descricao")
	private String descricao;
 
	public Diretriz() {}
	
	public Diretriz(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return this.id;
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
 
	
	 
	
}