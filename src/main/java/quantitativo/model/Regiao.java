package quantitativo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import arquitetura.model.Model;


/**
 * The persistent class for the regiao database table.
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="regiao", schema="comum")
public class Regiao extends Model  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_regiao")
	private Long id;

	@Column(name="algarismo")
	private String algarismo;

	@Column(name="descricao")
	private String descricao;
 
	
	//bi-directional many-to-one association to Localizador
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_tipo_regiao")
	private TipoRegiao tipoRegiao;
 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAlgarismo() {
		return this.algarismo;
	}

	public void setAlgarismo(String algarismo) {
		this.algarismo = algarismo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoRegiao getTipoRegiao() {
		return this.tipoRegiao;
	}

	public void setTipoRegiao(TipoRegiao tipoRegiao) {
		this.tipoRegiao = tipoRegiao;
	}
 
}