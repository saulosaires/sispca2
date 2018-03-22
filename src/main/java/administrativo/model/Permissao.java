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

/*
 * Representa as Permissões que um determinado Perfil possui com relação às Actions, Datas e Locais
 * 
 * @author Saul Raposo
 * @version 1.0
 */
@Entity
@Table(name = "permissao", schema = "controle_acesso")
public class Permissao {

	/* Chave primária */
	@Id
//	@SequenceGenerator(name = "action_perfil_id_seq", sequenceName = "controle_acesso.action_perfil_id_seq")
//	@GeneratedValue(strategy = GenerationType.AUTO, generator = "action_perfil_id_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_permissao")
	private Long id;
	
	/*
	 * Perfil correspondente a esta permissão
	 */
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_perfil")
	private Perfil perfil;
	
	/*
	 * Actions que este perfil possui acesso
	 */
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_action")
	private Action action;

	public Permissao() {
	}

	public Permissao(Perfil perfil, Action action) {
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

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
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
