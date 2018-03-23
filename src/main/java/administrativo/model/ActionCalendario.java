package administrativo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;
import arquitetura.utils.FormatoUtils;

 
/**
 * @author Saul Raposo
 * @version 1.0
 */
@Entity
@Table(name = "action_calendario", schema = "planejamento")
public class ActionCalendario extends Model implements Serializable, Auditable{
	private static final long serialVersionUID = 1L;

	/* Chave primária */
	@Id
//	@SequenceGenerator(name = "action_calendario_id_seq", sequenceName = "planejamento.action_calendario_id_seq")
//	@GeneratedValue(strategy = GenerationType.AUTO, generator = "action_calendario_id_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_fim")
	private Date dataFim;

	@Temporal(TemporalType.DATE)
	@Column(name="data_inicio")
	private Date dataInicio;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinColumn(name="id_action")
	private Action action;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_calendario")
	private Calendario calendario;
 
	

	public Date getDataFim() {
		return dataFim;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public Calendario getCalendario() {
		return calendario;
	}

	public void setCalendario(Calendario calendario) {
		this.calendario = calendario;
	}
 

	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append(" ActionCalendario Id: ").append(id);
		sb.append(" ActionCalendario Data Inicio: ").append(FormatoUtils.formataData(dataInicio));
		sb.append(" ActionCalendario Data Fim: ").append(FormatoUtils.formataData(dataFim));
		sb.append(" ActionCalendario Action: ").append(action.getId());
		sb.append(" ActionCalendario Calendario: ").append(calendario.getId());
		sb.append(" ActionCalendario Ativo: ").append(getAtivo());

		return sb.toString();
	}
	 

}