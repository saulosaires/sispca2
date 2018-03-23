package administrativo.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;

 
/**
 * Representa um determinado Controller do sistema que será utilizada no
 * controle de acesso e na geração dos menus personalizados. Ex: Controller =
 * Categoria, Action = list As permissões são determinadas pelo relacionamento
 * de Actions com Profiles
 * 
 * @author Saul Raposo
 */
@Entity
@Table(name = "controller", schema = "controle_acesso")
public class Controller extends Model implements Serializable, Auditable {
	private static final long serialVersionUID = 1L;

	/* Chave primária */
	@Id
//	@SequenceGenerator(name = "controller_id_seq", sequenceName = "controle_acesso.controller_id_seq")
//	@GeneratedValue(strategy = GenerationType.AUTO, generator = "controller_id_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_controller")
	private Long id;

	/* Nome do Controller */
	@NotNull(message="Name: campo é obrigatório")
	@Size(min = 1, max = 50)
	private String name;

	/*
	 * Label do Controller. Este label será utilizado para fins de exibição ao
	 * Usuário.
	 */
	@NotNull(message="Label: campo é obrigatório")
	@Size(min = 1, max = 50)
	private String label;
	
	/*
	 * Módulo ao qual este Controller pertence
	 */
	@NotNull(message="Módulo: campo é obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_modulo")
	private Modulo modulo;
 
	
	public Controller() {

	}
	
	public Controller(String name, String label) {
		super();
		this.name = name;
		this.label = label;
		modulo = new Modulo();
	}

	@Override
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
	 
	
	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append(" Controller Id : ").append(id);
		sb.append(" Controller Name : ").append(name);
		sb.append(" Controller Label : ").append(label);
		sb.append(" Controller Modulo : ").append(modulo.getId());
		sb.append(" Controller Ativo : ").append(getAtivo());

		return sb.toString();

	}
 

}