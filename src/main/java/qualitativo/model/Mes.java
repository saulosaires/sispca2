package qualitativo.model;

import java.io.Serializable;
import javax.persistence.*;

import arquitetura.model.Model;


/**
 * The persistent class for the mes database table.
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="mes", schema="comum")
public class Mes  extends Model implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_mes")
	private Long id;

	private String descricao;

	@Column(name="numero_mes")
	private Integer numeroMes;
 

	public Mes() {
	}	

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
 
	@Override
	public boolean equals(Object object){
		if(object != null && object instanceof Mes){
			return ((Mes)object).getId() == this.id;
		}
		return false;
	}

}