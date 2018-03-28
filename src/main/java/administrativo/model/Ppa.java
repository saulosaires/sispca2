package administrativo.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import arquitetura.model.Model;

import java.util.List;

 
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "ppa", schema = "planejamento")
public class Ppa extends Model implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ppa")
	private Integer id;

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

	// "Ppa" tem muitos "Exercicio"
	 @OneToMany(mappedBy="ppa")
	 private List<Exercicio> exercicios;

	@Column(name = "periodicidade_avaliacao")
	private Integer periodicidadeAvaliacao;

	@Column(name = "periodicidade_revisao")
	private Integer periodicidadeRevisao;
 

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
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
	
	 

}