package qualitativo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;

 
@Entity
@Table(name="indicadores", schema="planejamento")
public class Indicador extends Model implements  Auditable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_indicadores")
	private Long id;
 
	@Column(name="base_geografica")
	private String baseGeografica;

	@Temporal(TemporalType.DATE)
	@Column(name="data_apuracao")
	private Date dataApuracao;

	@Column(name="fonte_indice")
	private String fonteIndice;

	@Column(name="formula")
	private String formula;

	@Column(name="indicador")
	private String indicador;

	@Column(name="indice_recente")
	private Double indiceRecente;

	@Column(name="indices")
	private String indices;

	@Column(name="periodicidade")
	private String periodicidade;
 	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBaseGeografica() {
		return this.baseGeografica;
	}

	public void setBaseGeografica(String baseGeografica) {
		this.baseGeografica = baseGeografica;
	}

	public Date getDataApuracao() {
		return this.dataApuracao;
	}

	public void setDataApuracao(Date dataApuracao) {
		this.dataApuracao = dataApuracao;
	}

	public String getFonteIndice() {
		return this.fonteIndice;
	}

	public void setFonteIndice(String fonteIndice) {
		this.fonteIndice = fonteIndice;
	}

	public String getFormula() {
		return this.formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}	
 

	public String getIndicador() {
		return this.indicador;
	}

	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

	public Double getIndiceRecente() {
		return this.indiceRecente;
	}

	public void setIndiceRecente(Double indiceRecente) {
		this.indiceRecente = indiceRecente;
	}

	public String getIndices() {
		return this.indices;
	}

	public void setIndices(String indices) {
		this.indices = indices;
	}

	public String getPeriodicidade() {
		return this.periodicidade;
	}

	public void setPeriodicidade(String periodicidade) {
		this.periodicidade = periodicidade;
	}
 

	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append(" Indicador id: ").append(this.id)
			.append(" Indicador Base Geográfica: ").append(this.baseGeografica)
			.append(" Indicador Fonte de Índice: ").append(this.fonteIndice)
			.append(" Indicador Fórmula: ").append(this.formula)
			.append(" Indicador: ").append(this.indicador)
			.append(" Indicador Indices: ").append(this.indices)
			.append(" Indicador Periodicidade: ").append(this.periodicidade);
		return sb.toString();
	}
 

}