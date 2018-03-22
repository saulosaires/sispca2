package quantitativo.model;

import java.io.Serializable;

import javax.persistence.*;

import arquitetura.model.Model;

import java.util.List;


/**
 * The persistent class for the localizador database table.
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="localizador", schema="planejamento")
public class Localizador extends Model implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_localizador")
	private Integer idLocalizador;

	@Column(name="descricao")
	private String descricao;
	 

	@Column(name="id_unidade_orcamentaria")
	private Integer idUnidadeOrcamentaria;

	//bi-directional many-to-one association to FisicoFinanceiro
	@OneToMany(mappedBy="localizador")
	private List<FisicoFinanceiro> fisicoFinanceiros;

	//bi-directional one-to-one association to Localizador
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_localizador")
	private Localizador localizador1;

	//bi-directional one-to-one association to Localizador
	@OneToOne(mappedBy="localizador1", fetch=FetchType.LAZY)
	private Localizador localizador2;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_regiao")
	private Regiao regiao;

	public Localizador() {
	}

	public Integer getIdLocalizador() {
		return this.idLocalizador;
	}

	public void setIdLocalizador(Integer idLocalizador) {
		this.idLocalizador = idLocalizador;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getIdUnidadeOrcamentaria() {
		return this.idUnidadeOrcamentaria;
	}

	public void setIdUnidadeOrcamentaria(Integer idUnidadeOrcamentaria) {
		this.idUnidadeOrcamentaria = idUnidadeOrcamentaria;
	}

	public List<FisicoFinanceiro> getFisicoFinanceiros() {
		return this.fisicoFinanceiros;
	}

	public void setFisicoFinanceiros(List<FisicoFinanceiro> fisicoFinanceiros) {
		this.fisicoFinanceiros = fisicoFinanceiros;
	}

	public FisicoFinanceiro addFisicoFinanceiro(FisicoFinanceiro fisicoFinanceiro) {
		getFisicoFinanceiros().add(fisicoFinanceiro);
		fisicoFinanceiro.setLocalizador(this);

		return fisicoFinanceiro;
	}

	public FisicoFinanceiro removeFisicoFinanceiro(FisicoFinanceiro fisicoFinanceiro) {
		getFisicoFinanceiros().remove(fisicoFinanceiro);
		fisicoFinanceiro.setLocalizador(null);

		return fisicoFinanceiro;
	}

	public Localizador getLocalizador1() {
		return this.localizador1;
	}

	public void setLocalizador1(Localizador localizador1) {
		this.localizador1 = localizador1;
	}

	public Localizador getLocalizador2() {
		return this.localizador2;
	}

	public void setLocalizador2(Localizador localizador2) {
		this.localizador2 = localizador2;
	}

	public Regiao getRegiao() {
		return this.regiao;
	}

	public void setRegiao(Regiao regiao) {
		this.regiao = regiao;
	}
  
	public Integer getId() {
	 
		return idLocalizador;
	}

}