package qualitativo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;


/**
 * The persistent class for the plano_interno database table.
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="plano_interno", schema="planejamento")
public class PlanoInterno extends Model implements  Auditable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_plano_interno")
	private Long id;
	
	@Column(name="cod")
	private String cod;

	@Column(columnDefinition="boolean default true")
	private Boolean ativo;
	
	@Column(name="titulo")
	private String titulo;
	
	@Column(name="objetivo")
	private String objetivo;
	
	@ManyToOne
	@JoinColumn(name="id_acao")
	private Acao acao;
	
	//bi-directional many-to-one association to EtapasExecucao
	@OneToMany(mappedBy="planoInterno")
	private List<EtapasExecucao> etapasExecucaos;

	public PlanoInterno() {
		this.ativo = Boolean.TRUE;	
	}	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	 

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public List<EtapasExecucao> getEtapasExecucaos() {
		return this.etapasExecucaos;
	}

	public void setEtapasExecucaos(List<EtapasExecucao> etapasExecucaos) {
		this.etapasExecucaos = etapasExecucaos;
	}

	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append(" Plano Interno Id : ").append(id).append(" Plano Interno Sigla: ")
				.append(this.cod);

		return sb.toString();		
	}
 
}