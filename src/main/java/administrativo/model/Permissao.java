package administrativo.model;

 

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import arquitetura.model.Model;

 
@Entity
@Table(name = "permissao", schema = "controle_acesso")
public class Permissao extends Model implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5433001663834493143L;
 
	@Id
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_permissao")
	private Long id;
 
	@ManyToMany
	  @JoinTable(
	      name="perfil_permissao",schema="controle_acesso",
	    		  inverseJoinColumns =@JoinColumn(name="id_perfil", referencedColumnName="id_perfil"),
	    		  joinColumns=@JoinColumn(name="id_permissao", referencedColumnName="id_permissao"))
	private List<Perfil> perfil=new ArrayList<>();
	
	
	 //TODO undo this, set nullable tp false
	@Column(name="acao",length=100,nullable=true)
	private String acao;
	
	@Column(name="descricao",length=150,nullable=true)
	private String descricao; 

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
 
	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public List<Perfil> getPerfil() {
		return perfil;
	}

	public void setPerfil(List<Perfil> perfil) {
		this.perfil = perfil;
	}

    public String getPerfilLabel() {
    
    	if(!perfil.isEmpty()) {
    		StringJoiner sj = new StringJoiner(",");
    	
    		for(Perfil p:perfil) {
    			sj.add(p.getName());
    		}
    		
    		return sj.toString();
    	
    	}
    	
    	return "";
    }
	
    
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public boolean equals(Object obj) {
		 
		if(!(obj instanceof Permissao)) {
			return false;
		}
		
		Permissao p = (Permissao) obj;
		
		return ((p.getId().equals(this.getId())) && (p.getAcao().equals(this.getAcao()))) ?true:false;
			 
	}
 
	@Override
	public int hashCode() {
		 
		return Objects.hash(id,acao);
	}
	
}
