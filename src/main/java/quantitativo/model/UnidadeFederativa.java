package quantitativo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="unidade_federativa", schema="comum")
public class UnidadeFederativa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4784007885251028034L;

	@Id
	@SequenceGenerator(name = "seq_unidade_federativa_id", sequenceName = "comum.seq_unidade_federativa_id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_unidade_federativa_id")
	@Column(name="id_unidade_federativa")
	private Integer id;
	
	private String descricao;
	
	@Column(name="codigo_uf")
	private String codigo;
	
	private String sigla;

	public UnidadeFederativa(){ 
		//Empty constructor
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
}
