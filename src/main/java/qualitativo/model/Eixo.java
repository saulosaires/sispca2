package qualitativo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import arquitetura.model.Model;

@Entity
@Table(name = "eixo", schema = "planejamento")
public class Eixo extends Model {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "descricao")
	private String descricao;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name="eixo_programa",
		schema="planejamento",
		joinColumns=@JoinColumn(name="id_eixo"),
		inverseJoinColumns=@JoinColumn(name="id_programa")
	)
	private List<Programa> programas = new ArrayList<>();	
	
	
	public Eixo() {
	}

	public Eixo(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Programa> getProgramas() {
		return programas;
	}

	public void setProgramas(List<Programa> programas) {
		this.programas = programas;
	}



	

}