package qualitativo.model;

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
 * The persistent class for the tipo_programa database table.
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="tipo_programa", schema="planejamento")
public class TipoPrograma extends Model {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_tipo_programa")
	private Long id;
 
	@Column(name="descricao")
	private String descricao;
	 
	@OneToMany(mappedBy="tipoPrograma")
	private List<Programa> programas;
 
	public TipoPrograma() {}
	
	public TipoPrograma(Long id) {this.id=id;}
	
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

	public List<Programa> getProgramas() {
		return this.programas;
	}

	public void setProgramas(List<Programa> programas) {
		this.programas = programas;
	}	
 
 

}