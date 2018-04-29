package quantitativo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import arquitetura.model.Model;

@Entity
@Table(name="tipo_regiao", schema="comum")
public class TipoRegiao extends Model{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_tipo_regiao")
	private Long id;
	
	@Column(name="descricao")
	private String descricao;
 
	
	@OneToMany(mappedBy="tipoRegiao",fetch=FetchType.LAZY)
	private List<Regiao> listRegiao;
 
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
 

	public List<Regiao> getListRegiao() {
		return listRegiao;
	}

	public void setListRegiao(List<Regiao> listRegiao) {
		this.listRegiao = listRegiao;
	}
	
}
