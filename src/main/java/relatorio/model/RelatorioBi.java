package relatorio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import arquitetura.model.Model;
import qualitativo.model.UnidadeOrcamentaria;

@Entity
@Table(name="relatorio_bi", schema="comum")
public class RelatorioBi extends Model   {

	private static final long serialVersionUID = 1L;
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_relatorio_bi")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_unidade_orcamentaria")
	private UnidadeOrcamentaria unidadeOrcamentaria;
		
	@Column(name="descricao",length=200)
	private String descricao;
	
	@Column(name="caminho",length=200)
	private String caminho;
 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UnidadeOrcamentaria getUnidadeOrcamentaria() {
		return unidadeOrcamentaria;
	}

	public void setUnidadeOrcamentaria(UnidadeOrcamentaria unidadeOrcamentaria) {
		this.unidadeOrcamentaria = unidadeOrcamentaria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
  
 
}
