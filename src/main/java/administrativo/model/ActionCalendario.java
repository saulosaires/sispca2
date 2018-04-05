package administrativo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "action_calendario", schema = "planejamento")
public class ActionCalendario extends Model implements Serializable, Auditable{
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_fim")
	private Date dataFim;

	@Temporal(TemporalType.DATE)
	@Column(name="data_inicio")
	private Date dataInicio;
	
		
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

		sb.append(" ActionCalendario Calendario: ").append(calendario.getId());
		sb.append(" ActionCalendario Ativo: ").append(getAtivo());

		return sb.toString();
	}
	 

}