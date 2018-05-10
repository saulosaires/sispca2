package metas.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import arquitetura.model.Model;

@Entity
@Table(name = "compromisso", schema = "metassintetico")
public class Compromisso extends Model implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="codigo")
	private String codigo;
	
	@Column(name="compromisso")
	private String compromisso;

	@Column(name="descricao")
	private String descricao;

	@ManyToMany(mappedBy="compromissos")
	private List<EixoTematico> eixosTematicos = new ArrayList<>();
	
	@ManyToMany(mappedBy="compromissos")
	private Set<Atividade> atividades;;
	
	 

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCompromisso() {
		return this.compromisso;
	}

	public void setCompromisso(String compromisso) {
		this.compromisso = compromisso;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<EixoTematico> getEixosTematicos() {
		return eixosTematicos;
	}

	public void setEixosTematicos(List<EixoTematico> eixosTematicos) {
		this.eixosTematicos = eixosTematicos;
	}

	public Set<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(Set<Atividade> atividades) {
		this.atividades = atividades;
	}
 

}