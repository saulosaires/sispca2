package administrativo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import administrativo.enums.NivelAcesso;
import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;
 
 
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "perfil", schema = "controle_acesso")
public class Perfil extends Model implements Serializable, Auditable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_perfil")
	private Long id;
	@NotNull
	@Size(min = 1, max = 30)
	@Column(unique=true)
	private String name;

	/*
	 * Permissões vinculadas a este perfil
	 */
	@ManyToMany
	  @JoinTable(
	      name="perfil_permissao",
	      joinColumns=@JoinColumn(name="id_perfil", referencedColumnName="id_perfil"),
	      inverseJoinColumns=@JoinColumn(name="id_permissao", referencedColumnName="id_permissao"))
	private List<Permissao> permissoes;

 
	@Enumerated(EnumType.STRING)
	@Column(name = "nivel_acesso")
	private NivelAcesso nivelAcesso;
 
	
	public Perfil() {
	}

	public Perfil(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}
 

	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append(" Perfil Id : ").append(id);
		sb.append(" Perfil name : ").append(name);
		sb.append(" Perfil NivelAcesso : ").append(nivelAcesso.name());
		sb.append(" Perfil Ativo : ").append(getAtivo());

		return sb.toString();
	}
 

	public NivelAcesso getNivelAcesso() {
		return nivelAcesso;
	}

	public void setNivelAcesso(NivelAcesso nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}
	
	 
	
}