package administrativo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import arquitetura.model.Model;

 
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ppa", schema = "planejamento")
public class Ppa extends Model {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ppa")
	private Long id;

	@Column(name = "ano_fim")
	private Integer anoFim;

	@Column(name = "ano_inicio")
	private Integer anoInicio;
 

	@NotNull(message = "Descrição: campo é obrigatório")
	@Size(min = 1, max = 30)
	@Column(name = "descricao")
	private String descricao;

	@NotNull
	@Size(min = 1, max = 15)
	@Column(unique = true,name="sigla")
	private String sigla;

	 @OneToMany(mappedBy="ppa",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	 private List<Exercicio> exercicios;

	@Column(name = "periodicidade_avaliacao")
	private Integer periodicidadeAvaliacao;

	@Column(name = "periodicidade_revisao")
	private Integer periodicidadeRevisao;
 
	public Ppa() {}
	
	public Ppa(Long id) {
		this.id=id;
	}
	
	public Ppa(Long id,String sigla,String descricao) {
		this.id=id;
		this.sigla=sigla;
		this.descricao=descricao;
	}
	

	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAnoFim() {
		return this.anoFim;
	}

	public void setAnoFim(Integer anoFim) {
		this.anoFim = anoFim;
	}

	public Integer getAnoInicio() {
		return this.anoInicio;
	}

	public void setAnoInicio(Integer anoInicio) {
		this.anoInicio = anoInicio;
	}

 
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Integer getPeriodicidadeAvaliacao() {
		return periodicidadeAvaliacao;
	}

	public void setPeriodicidadeAvaliacao(Integer periodicidadeAvaliacao) {
		this.periodicidadeAvaliacao = periodicidadeAvaliacao;
	}

	public Integer getPeriodicidadeRevisao() {
		return periodicidadeRevisao;
	}

	public void setPeriodicidadeRevisao(Integer periodicidadeRevisao) {
		this.periodicidadeRevisao = periodicidadeRevisao;
	}

	public List<Exercicio> getExercicios() {
		return exercicios;
	}

	public void setExercicios(List<Exercicio> exercicios) {
		this.exercicios = exercicios;
	}
	
	 

}