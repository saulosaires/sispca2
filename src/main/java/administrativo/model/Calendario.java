package administrativo.model;

import java.io.Serializable;

import javax.persistence.*;

import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;
import arquitetura.utils.FormatoUtils;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the calendario database table.
 * 
 */
@Entity
@Table(name = "calendario", schema = "planejamento")
public class Calendario extends Model implements Serializable, Auditable {
	private static final long serialVersionUID = 1L;

	/* Chave primária */
	@Id
//	@SequenceGenerator(name = "calendario_id_calendario_seq", sequenceName = "planejamento.calendario_id_calendario_seq")
//	@GeneratedValue(strategy = GenerationType.AUTO, generator = "calendario_id_calendario_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_calendario")
	private Long id;
 

	@Temporal(TemporalType.DATE)
	@Column(name="data_fim")
	private Date dataFim;

	@Temporal(TemporalType.DATE)
	@Column(name="data_inicio")
	private Date dataInicio;

	//bi-directional many-to-one association to Exercicio
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_exercicio")
	private Exercicio exercicio;
	
	@OneToMany(mappedBy = "calendario", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval=true)
	private List<ActionCalendario> listActionCalendario;
	/*
	 * Eventos que estão vinculados a este Calendario 
	 */
//	@ManyToMany(targetEntity = Action.class, cascade = { CascadeType.ALL })
//	@JoinTable(name = "action_calendario", schema = "planejamento", joinColumns = @JoinColumn(name = "id_calendario"), inverseJoinColumns = @JoinColumn(name = "id_action"))
//	private List<Action> actions;
	
	public Calendario() {
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
 

	public Date getDataFim() {
		return this.dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getDataInicio() {
		return this.dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Exercicio getExercicio() {
		return this.exercicio;
	}

	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}

//	public List<Action> getActions() {
//		return actions;
//	}
//
//	public void setActions(List<Action> actions) {
//		this.actions = actions;
//	}


	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append(" Calendario Id: ").append(id);
		sb.append(" Calendario Data Início: ").append(FormatoUtils.formataData(dataInicio));
		sb.append(" Calendario Data Fim: ").append(FormatoUtils.formataData(dataFim));
		sb.append(" Calendario Exercício: ").append(exercicio.getId());
		sb.append(" Calendario Ativo: ").append(getAtivo());
		
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object object){
		if(object != null && object instanceof Calendario){
			return ((Calendario)object).getId() == this.id;
		}
		return false;
	}

}