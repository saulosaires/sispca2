package qualitativo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import arquitetura.model.Model;


/**
 * The persistent class for the diretriz database table.
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="diretriz", schema="planejamento")
public class Diretriz extends Model implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_diretriz")
	private Integer id;

	private String descricao;

	@ManyToMany
	@JoinTable(
		name="diretrizPrograma"
		, joinColumns={
			@JoinColumn(name="id_diretriz")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_programa")
			}
		)
	private List<Programa> programas;
 
	
	public Diretriz() {
		//empty constructor
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Programa> getProgramas() {
		return this.programas;
	}

	public void setProgramas(List<Programa> programas) {
		this.programas = programas;
	}
 
	
	 
	
}