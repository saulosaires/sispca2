package qualitativo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import arquitetura.model.Model;


/**
 * The persistent class for the mes database table.
 * 
 */
@Entity
@Table(name="mes", schema="comum")
public class Mes  extends Model  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_mes")
	private Long id;

	@Column(name="descricao")
	private String descricao;

	@Column(name="numero_mes")
	private Integer numeroMes;
  
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

	public Integer getNumeroMes() {
		return this.numeroMes;
	}

	public void setNumeroMes(Integer numeroMes) {
		this.numeroMes = numeroMes;
	}
  

}