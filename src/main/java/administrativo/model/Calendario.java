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
 
	public Calendario() {
		// empty constructor
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