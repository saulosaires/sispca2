package qualitativo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import arquitetura.model.Model;



@Entity
@Table(name="tipo_orcamento", schema="planejamento")
public class TipoOrcamento extends Model  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_tipo_orcamento")
	private Long id;
 
	@Column(name="descricao")
	private String descricao;

	@OneToMany(mappedBy="tipoOrcamento")
	private List<Acao> acaos;
 
	public TipoOrcamento() {}

	public TipoOrcamento(Long id) {this.id=id;}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
 
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Acao> getAcaos() {
		return this.acaos;
	}

	public void setAcaos(List<Acao> acaos) {
		this.acaos = acaos;
	}
 

}