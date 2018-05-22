package quantitativo.model;

import java.beans.Transient;
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
	
	private transient Double valorAnterior;
	
	private transient Double quantidadeAnterior;
	
	private transient Double valorAtual;
	
	private transient Double quantidadeAtual;
	
	private transient Double valorVigente;
	
	private transient Double quantidadeVigente;
	
	private transient Double valorTotal;
	
	private transient Double quantidadeTotal;
	
	private transient boolean comitado;
	
	private transient String label;
 
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
	public Double getValorAtual() {
		return valorAtual;
	}
	@Transient
	public void setValorAtual(Double valorAtual) {
		this.valorAtual = valorAtual;
	}
	@Transient
	public Double getQuantidadeAtual() {
		return quantidadeAtual;
	}
	@Transient
	public void setQuantidadeAtual(Double quantidadeAtual) {
		this.quantidadeAtual = quantidadeAtual;
	}
	@Transient
	public Double getValorVigente() {
		return valorVigente;
	}
	@Transient
	public void setValorVigente(Double valorVigente) {
		this.valorVigente = valorVigente;
	}
	@Transient
	public Double getQuantidadeVigente() {
		return quantidadeVigente;
	}
	@Transient
	public void setQuantidadeVigente(Double quantidadeVigente) {
		this.quantidadeVigente = quantidadeVigente;
	}
	@Transient
	public Double getValorTotal() {
		return valorTotal;
	}
	@Transient
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	@Transient
	public Double getQuantidadeTotal() {
		return quantidadeTotal;
	}
	@Transient
	public void setQuantidadeTotal(Double quantidadeTotal) {
		this.quantidadeTotal = quantidadeTotal;
	}
	@Transient
	public Double getValorAnterior() {
		return valorAnterior;
	}
	@Transient
	public void setValorAnterior(Double valorAnterior) {
		this.valorAnterior = valorAnterior;
	}
	@Transient
	public Double getQuantidadeAnterior() {
		return quantidadeAnterior;
	}
	@Transient
	public void setQuantidadeAnterior(Double quantidadeAnterior) {
		this.quantidadeAnterior = quantidadeAnterior;
	}
	@Transient
	public boolean isComitado() {
		return comitado;
	}
	@Transient
	public void setComitado(boolean comitado) {
		this.comitado = comitado;
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
