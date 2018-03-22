package administrativo.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
 
@Entity
@Table(name = "permissao", schema = "controle_acesso")
public class Permissao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_permissao")
	private Long id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_perfil")
	private Perfil perfil;

	@Column(unique = true,length=80,nullable=false)
	private String action;

	public Permissao() {
	}

	public Permissao(Perfil perfil, String action) {
		super();
		this.perfil = perfil;
		this.action = action;
	}

	public Long getId() {
		return id;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	@Override
	public boolean equals(Object object){
		if(object != null && object instanceof Permissao){
			return ((Permissao)object).getId() == this.id;
		}
		return false;
	}
	
}
