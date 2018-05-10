package metas.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import arquitetura.model.Model;
import metas.enuns.IconeEixo;

@Entity
@Table(name="eixo_tematico", schema = "metassintetico")
public class EixoTematico extends Model implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="nome")
	private String nome;

	@Transient
	private IconeEixo icone;

	@ManyToMany( fetch = FetchType.LAZY )
	@JoinTable(
		name="eixo_tematico_compromisso",
		schema="metas",
		joinColumns=@JoinColumn(name="eixo_tematico_id"),
		inverseJoinColumns=@JoinColumn(name="compromisso_id")
	)
	private List<Compromisso> compromissos = new ArrayList<>();

	 

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public IconeEixo getIcone() {
		return icone;
	}

	public void setIcone(IconeEixo icone) {
		this.icone = icone;
	}

	public List<Compromisso> getCompromissos() {
		return compromissos;
	}

	public void setCompromissos(List<Compromisso> compromissos) {
		this.compromissos = compromissos;
	}

}