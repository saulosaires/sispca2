package qualitativo.model;

import java.io.Serializable;
import javax.persistence.*;

import arquitetura.model.Model;

import java.util.List;


/**
 * The persistent class for the tipo_programa database table.
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="tipo_programa", schema="planejamento")
public class TipoPrograma extends Model implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_tipo_programa")
	private Integer id;
 
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="codigo")
	private char codigo;	

	//bi-directional many-to-one association to Programa
	@OneToMany(mappedBy="tipoPrograma")
	private List<Programa> programas;

	public TipoPrograma() {
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
	
	public char getCodigo() {
		return codigo;
	}

	public void setCodigo(char codigo) {
		this.codigo = codigo;
	}
 

}