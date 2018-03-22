package administrativo.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;

 
/**
 * Módulo ao qual o Controller pertence
 * 
 * @author Saul Raposo
 */
@Entity
@Table(name = "modulo", schema = "controle_acesso")
public class Modulo extends Model implements Serializable, Auditable {
	private static final long serialVersionUID = 1L;

	/* Chave primária */
	@Id
//	@SequenceGenerator(name = "modulo_id_seq", sequenceName = "controle_acesso.modulo_id_seq")
//	@GeneratedValue(strategy = GenerationType.AUTO, generator = "modulo_id_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_modulo")
	private Long id;

	/* Nome do Módulo */
	@NotNull
	@Size(min = 1, max = 30)
	private String nome;
	
	/* Label do Módulo */
	@NotNull
	@Size(min = 1, max = 200)
	private String label;
	
	@Column(name="nivel")
	private Integer nivel;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_modulo_pai")
	private Modulo modulo;
 
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	 
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
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
		sb.append(" Modulo Id : ").append(id);
		sb.append(" Modulo Nome: ").append(nome);
		sb.append(" Modulo Label: ").append(label);
		sb.append(" Modulo Nivel: ").append(nivel);
		sb.append(" Modulo Pai: ").append(modulo.getId());
		sb.append(" Modulo Ativo: ").append(getAtivo());
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object object){
		if(object != null && object instanceof Modulo){
			return ((Modulo)object).getId() == this.id;
		}
		return false;
	}

}