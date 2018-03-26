package administrativo.model;

 

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/*
 * Representa as Permissões que um determinado Perfil possui com relação às Actions, Datas e Locais
 * 
 * @author Saul Raposo
 * @version 1.0
 */
@Entity
@Table(name = "permissao", schema = "controle_acesso")
public class Permissao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5433001663834493143L;
 
	@Id
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_permissao")
	private Long id;
 
	@ManyToMany(mappedBy="permissoes")
	private List<Perfil> perfil;
	
	 
	@Column(name="action",length=100)//TODO CHANGE TO NOT NULL
	private String action;
 

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}



	public List<Perfil> getPerfil() {
		return perfil;
	}



	public void setPerfil(List<Perfil> perfil) {
		this.perfil = perfil;
	}


	
	
 
	
}
