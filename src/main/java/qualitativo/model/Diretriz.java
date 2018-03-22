package qualitativo.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the diretriz database table.
 * 
 */
@Entity
@Table(name="diretriz", schema="planejamento")
public class Diretriz implements Serializable {
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
	
	private boolean ativo;
	
	public Diretriz() {
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

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	@Override
	public boolean equals(Object object){
		if(object != null && object instanceof Diretriz){
			return ((Diretriz)object).getId() == this.id;
		}
		return false;
	}
	
}