package avaliacao.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import administrativo.model.Exercicio;
import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;
import qualitativo.model.Programa;
import qualitativo.model.UnidadeMedida;

/**
 * The persistent class for the indicador_desempenho_intermediario database
 * table.
 * 
 */
@Entity
@Table(name = "indicador_desempenho_intermediario", schema = "avaliacao")
public class IndicadorDesempenhoIntermediario extends Model implements Serializable, Auditable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ind_desemp_intermed")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_exercicio")
	private Exercicio exercicio;

	@ManyToOne
	@JoinColumn(name = "id_programa")
	private Programa programa;

	@ManyToOne
	@JoinColumn(name = "id_unidade_medida")
	private UnidadeMedida unidadeMedida= new UnidadeMedida();
 

	@Temporal(TemporalType.DATE)
	@Column(name = "ref_data_apuracao")
	private Date refDataApuracao;

	@Column(name = "ref_valor")
	private Double refValor;

	@Column(name = "resultado_ano_apurado")
	private Double resultadoAnoApurado;

	@Column(name = "resultado_ano_esperado")
	private Double resultadoAnoEsperado;

	@Column(name = "fonte")
	private String fonte;

	@Column(name = "formula")
	private String formula;

	@Column(name = "indicador")
	private String indicador;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Exercicio getExercicio() {
		return exercicio;
	}

	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}

	public String getIndicador() {
		return indicador;
	}

	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public UnidadeMedida getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}
 

	public Date getRefDataApuracao() {
		return refDataApuracao;
	}

	public void setRefDataApuracao(Date refDataApuracao) {
		this.refDataApuracao = refDataApuracao;
	}

	public Double getRefValor() {
		return refValor;
	}

	public void setRefValor(Double refValor) {
		this.refValor = refValor;
	}

	public Double getResultadoAnoApurado() {
		return resultadoAnoApurado;
	}

	public void setResultadoAnoApurado(Double resultadoAnoApurado) {
		this.resultadoAnoApurado = resultadoAnoApurado;
	}

	public Double getResultadoAnoEsperado() {
		return resultadoAnoEsperado;
	}

	public void setResultadoAnoEsperado(Double resultadoAnoEsperado) {
		this.resultadoAnoEsperado = resultadoAnoEsperado;
	}
 

	public String getFonte() {
		return fonte;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append(" Indicador Desempenho Intermediário ID: " + id);
		sb.append(" Indicador: " + indicador);
		sb.append(" Unidade de Medida: " + (unidadeMedida != null ? unidadeMedida.getId().toString() : ""));
		sb.append(" Exercicio ID: " + (exercicio != null ? exercicio.getId().toString() : ""));
		 
		sb.append(" Referência de Data de Apuração: " + refDataApuracao);
		sb.append(" Referência de Valor: " + refValor);
		sb.append(" Resultado Ano Apurado: " + resultadoAnoApurado);
		sb.append(" Resultado Ano Esperado: " + resultadoAnoEsperado);
		sb.append(" Fonte: " + fonte);
		return sb.toString();
	}

}