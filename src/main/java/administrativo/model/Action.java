package administrativo.model;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;
 
/**
 * Representa uma determinada action do sistema que será utilizada no controle
 * de acesso e na geração dos menus personalizados. Ex: Controller = Categoria,
 * Action = list As permissões são determinadas pelo relacionamento de Actions
 * com Profiles
 * 
 * @author Saul Raposo
 * @version 1.0
 */
@Entity
@Table(name = "action", schema = "controle_acesso")
public class Action extends Model implements Serializable, Auditable {
	private static final long serialVersionUID = 1L;

	@Id
//	@SequenceGenerator(name = "action_id_seq", schema="controle_acesso" ,sequenceName = "action_id_seq")
//	@GeneratedValue(strategy = GenerationType.AUTO, generator = "action_id_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_action")
	private Long id;

	/* Nome da Action */
	@NotNull(message="Name: campo é obrigatório")
	@Size(min = 1, max = 30)
	private String name;

	/*
	 * Label da Action. Este label será utilizado para fins de exibição ao
	 * Usuário.
	 */
	@NotNull(message="Action: campo é obrigatório")
	@Size(min = 1, max = 30)
	private String label;

	/* Flag que indica se esta Action aparecerá no menu */
	@NotNull
	private Boolean appear = true;

	/*
	 * Controller à qual esta Action pertence
	 */
	@NotNull(message="Controller: campo é obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_controller")
	private Controller controller;
 
	
	@Column(name = "possui_calendario")
	private Boolean possuiCalendario = true;

	@ManyToMany(targetEntity = Action.class)
	@JoinTable(name = "permissao", schema = "controle_acesso", joinColumns = @JoinColumn(name = "id_action"), inverseJoinColumns = @JoinColumn(name = "id_perfil"))
	private List<Perfil> perfis;

	/*
	 * ActionCalendarios vinculados a este evento/action
	 */
	@OneToMany(mappedBy = "action", fetch = FetchType.LAZY)
	private List<ActionCalendario> actionCalendario;
	
	public Action() {
	}

	public Action(String name, String label, Boolean appear,
			Controller controller) {
		super();
		this.name = name;
		this.label = label;
		this.appear = appear;
		this.controller = controller;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Boolean getAppear() {
		return appear;
	}

	public void setAppear(Boolean appear) {
		this.appear = appear;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	
	
	// public Set<Profile> getProfiles() {
	// return profiles;
	// }
	//
	// public void setProfiles(Set<Profile> profiles) {
	// this.profiles = profiles;
	// }

	
	
	

	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append(" Action Id : ").append(id)
				.append(" Action Name : ").append(name)
				.append(" Action Label : ").append(label)
				.append(" Action Appear : ").append(appear)
				.append(" Action Controller : ").append(controller != null ? controller.getId() : null)
				.append(" Action Ativo : ").append(getAtivo())
				.append(" Action possuiCalendario : ").append(possuiCalendario);

		return sb.toString();
	}

	public List<ActionCalendario> getActionCalendario() {
		return actionCalendario;
	}

	public void setActionCalendario(List<ActionCalendario> actionCalendario) {
		this.actionCalendario = actionCalendario;
	}
 

//	public List<Calendario> getCalendarios() {
//		return calendarios;
//	}
//
//	public void setCalendarios(List<Calendario> calendarios) {
//		this.calendarios = calendarios;
//	}

	public Boolean getPossuiCalendario() {
		return possuiCalendario;
	}

	public void setPossuiCalendario(Boolean possuiCalendario) {
		this.possuiCalendario = possuiCalendario;
	}	
	
	@Override
	public boolean equals(Object object){
		if(object != null && object instanceof Action){
			return ((Action)object).getId() == this.id;
		}
		return false;
	}

	@Override
	public String toString() {
		return label ;
	}

}