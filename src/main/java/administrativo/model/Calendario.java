package administrativo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;
import arquitetura.utils.FormatoUtils;


/**
 * The persistent class for the calendario database table.
 * 
 */
@Entity
@Table(name = "calendario", schema = "planejamento")
public class Calendario extends Model implements  Auditable {
	private static final long serialVersionUID = 1L;

 
	@Id
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_calendario")
	private Long id;
 

	@Temporal(TemporalType.DATE)
	@Column(name="data_fim")
	private Date dataFim;

	@Temporal(TemporalType.DATE)
	@Column(name="data_inicio")
	private Date dataInicio;

 
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_exercicio")
	private Exercicio exercicio;
	
	@OneToMany(mappedBy = "calendario", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval=true)
	private List<ActionCalendario> listActionCalendario;
  
	
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
	
	 

}