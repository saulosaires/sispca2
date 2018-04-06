package administrativo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;
 
 
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "perfil", schema = "controle_acesso")
public class Perfil extends Model implements  Auditable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_perfil")
	private Long id;
	
	@NotNull
	@Size(min = 1, max = 30)
	@Column(unique=true)
	private String name;

 
	@ManyToMany(mappedBy="perfil",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	private List<Permissao> permissoes=new ArrayList<>();

 
	@ManyToMany(mappedBy="perfis",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Usuario> usuarios=new ArrayList<>();

 
	
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

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
	

	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append(" Perfil Id : ").append(id);
		sb.append(" Perfil name : ").append(name);
		 
		sb.append(" Perfil Ativo : ").append(getAtivo());

		return sb.toString();
	}
 
 
	
	 
	
}