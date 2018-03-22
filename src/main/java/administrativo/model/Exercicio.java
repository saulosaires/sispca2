package administrativo.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;
 

/**
 * The persistent class for the exercicio database table.
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "exercicio", schema = "planejamento")
public class Exercicio extends Model implements Serializable, Auditable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_exercicio")
	private Long id;
 

	 
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_ppa")
	@NotNull(message="PPA: campo é obrigatório")
	private Ppa ppa;
	
	private Integer ano;
	
	private Boolean vigente;	

	public Exercicio() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
 

	public Ppa getPpa() {
		return ppa;
	}

	public void setPpa(Ppa ppa) {
		this.ppa = ppa;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Boolean getVigente() {
		return vigente;
	}

	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}

	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append(" Exercicio Id: ").append(id);
		sb.append(" Exercicio Ppa: ").append(ppa.getId());
		sb.append(" Exercicio Ano: ").append(ano);
		sb.append(" Exercicio Ativo: ").append(getAtivo());
		sb.append(" Exercicio Vigente: ").append(vigente);
	
		return sb.toString();
	}	
	
	@Override
	public boolean equals(Object object){
		if(object != null && object instanceof Exercicio){
			return ((Exercicio)object).getId() == this.id;
		}
		return false;
	}

}