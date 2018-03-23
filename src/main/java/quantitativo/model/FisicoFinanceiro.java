package quantitativo.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;
import qualitativo.model.Acao;
 

/**
 * The persistent class for the fisico_financeiro database table.
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="fisico_financeiro", schema="monitoramento")
public class FisicoFinanceiro extends Model implements Serializable, Auditable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_fisico_financeiro")
	private Long id;

	@Column(name="ano")
	private Integer ano; 
	
	@Column(name="quantidade")
	private double quantidade;

	@Column(name="valor")
	private double valor;
 
 
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_acao")
	@NotNull(message="Campo Obrigatório")
	private Acao acao;

	//bi-directional many-to-one association to Localizador
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_localizador")
	private Localizador localizador;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_regiao_municipio")
	private RegiaoMunicipio regiaoMunicipio;
	
	public FisicoFinanceiro() { 
		//empty constructor
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAno() {
		return this.ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}	

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public double getValor() {
		return this.valor;
	}

	
	public void setValor(double valor) {
		this.valor = valor;
	}
 

	public Acao getAcao() {
		return this.acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public Localizador getLocalizador() {
		return this.localizador;
	}

	public void setLocalizador(Localizador localizador) {
		this.localizador = localizador;
	}

	public RegiaoMunicipio getRegiaoMunicipio() {
		return regiaoMunicipio;
	}

	public void setRegiaoMunicipio(RegiaoMunicipio regiaoMunicipio) {
		this.regiaoMunicipio = regiaoMunicipio;
	}	
	
	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append(" FisicoFinanceiro Id : ").append(id);
		sb.append(" ano : ").append(ano);
		sb.append(" acao : ").append(acao.getId());
		sb.append(" quantidade : ").append(quantidade);
		sb.append(" valor : ").append(valor);
		sb.append(" regiaoMunicipio : ").append(regiaoMunicipio.getId());
		sb.append(" ativo : ").append(getAtivo());
		return sb.toString();
		
	}
	
}