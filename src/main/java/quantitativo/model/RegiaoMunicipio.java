package quantitativo.model;

import java.beans.Transient;
import java.math.BigDecimal;
import java.util.List;

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

import arquitetura.model.Model;
import arquitetura.utils.MathUtils;
import monitoramento.model.Execucao;

@Entity
@Table(name="regiao_municipio", schema="comum")
public class RegiaoMunicipio extends Model{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_regiao_municipio")
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_regiao")
	private Regiao regiao;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_municipio")
	private Municipio municipio;
 
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="regiaoMunicipio")
	private  List<FisicoFinanceiro> fisicoFinanceiro;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="regiaoMunicipio")
	private  List<FisicoFinanceiroMensal> fisicoFinanceiroMensal;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="regiaoMunicipio")
	private  List<Execucao> execucoes;
	
	private transient Double[] valoresMeses;
	
	private transient Double[] quantidade;
	
	private transient BigDecimal valorAnterior;
	
	private transient BigDecimal quantidadeAnterior;
	
	private transient BigDecimal valorAtual;
	
	private transient BigDecimal quantidadeAtual;
	
	private transient BigDecimal valorVigente;
	
	private transient BigDecimal quantidadeVigente;
	
	private transient BigDecimal valorTotal = MathUtils.getZeroBigDecimal();
	
	private transient BigDecimal quantidadeTotal = MathUtils.getZeroBigDecimal();
	
 	
	private transient String label;
 
	public RegiaoMunicipio(){
		
	}
	
	public RegiaoMunicipio(Long id){
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Regiao getRegiao() {
		return regiao;
	}

	public void setRegiao(Regiao regiao) {
		this.regiao = regiao;
	}
	
	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public String getLabel() {
		
		if(regiao!=null && municipio==null) {
			label = regiao.getDescricao();
		}
		
		if(regiao!=null && municipio!=null) {
			label=municipio.getDescricao();
		}
		
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Transient
	public List<FisicoFinanceiro> getFisicoFinanceiro() {
		return fisicoFinanceiro;
	}
	
	@Transient
	public void setFisicoFinanceiro(List<FisicoFinanceiro> fisicoFinanceiro) {
		this.fisicoFinanceiro = fisicoFinanceiro;
	}

	@Transient
	public List<FisicoFinanceiroMensal> getFisicoFinanceiroMensal() {
		return fisicoFinanceiroMensal;
	}
	
	@Transient
	public void setFisicoFinanceiroMensal(
			List<FisicoFinanceiroMensal> fisicoFinanceiroMensal) {
		this.fisicoFinanceiroMensal = fisicoFinanceiroMensal;
	}
	
	@Transient
	public List<Execucao> getExecucoes() {
		return execucoes;
	}
	
	@Transient
	public void setExecucoes(List<Execucao> execucoes) {
		this.execucoes = execucoes;
	}
	
	@Transient
	public Double[] getValoresMeses() {
		return valoresMeses;
	}
	
	@Transient
	public void setValoresMeses(Double[] valoresMeses) {
		this.valoresMeses = valoresMeses;
	}
	
	@Transient
	public Double[] getQuantidade() {
		return quantidade;
	}
	
	@Transient
	public void setQuantidade(Double[] quantidade) {
		this.quantidade = quantidade;
	}
	@Transient
	public BigDecimal getValorAtual() {
		return valorAtual;
	}
	@Transient
	public void setValorAtual(BigDecimal valorAtual) {
		this.valorAtual = valorAtual;
	}
	@Transient
	public BigDecimal getQuantidadeAtual() {
		return quantidadeAtual;
	}
	@Transient
	public void setQuantidadeAtual(BigDecimal quantidadeAtual) {
		this.quantidadeAtual = quantidadeAtual;
	}
	@Transient
	public BigDecimal getValorVigente() {
		return valorVigente;
	}
	@Transient
	public void setValorVigente(BigDecimal valorVigente) {
		this.valorVigente = valorVigente;
	}
	@Transient
	public BigDecimal getQuantidadeVigente() {
		return quantidadeVigente;
	}
	@Transient
	public void setQuantidadeVigente(BigDecimal quantidadeVigente) {
		this.quantidadeVigente = quantidadeVigente;
	}
	@Transient
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	@Transient
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	@Transient
	public BigDecimal getQuantidadeTotal() {
		return quantidadeTotal;
	}
	@Transient
	public void setQuantidadeTotal(BigDecimal quantidadeTotal) {
		this.quantidadeTotal = quantidadeTotal;
	}
	@Transient
	public BigDecimal getValorAnterior() {
		return valorAnterior;
	}
	@Transient
	public void setValorAnterior(BigDecimal valorAnterior) {
		this.valorAnterior = valorAnterior;
	}
	@Transient
	public BigDecimal getQuantidadeAnterior() {
		return quantidadeAnterior;
	}
	@Transient
	public void setQuantidadeAnterior(BigDecimal quantidadeAnterior) {
		this.quantidadeAnterior = quantidadeAnterior;
	}
 
	
	@Override
	public String toString() {

		if(regiao!=null && municipio!=null) {
			return regiao.getDescricao()+" - "+municipio.getDescricao();
		}

		if(regiao!=null && municipio==null) {
			return  regiao.getDescricao();
		}

		if(regiao==null && municipio!=null) {
			return  municipio.getDescricao();
		}

		return getId().toString();
		
	}
	

}
