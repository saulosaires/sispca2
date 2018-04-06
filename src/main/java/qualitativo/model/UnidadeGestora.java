package qualitativo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;
 

/**
 * The persistent class for the unidade_gestora database table.
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="unidade_gestora", schema="comum")
public class UnidadeGestora extends Model implements Auditable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_unidade_gestora")
	private Long id;
 

	private String codigo;

	private String descricao;

	private String sigla;

	@ManyToOne
	@JoinColumn(name="id_unidade_orcamentaria")
	private UnidadeOrcamentaria unidadeOrcamentaria;

	public UnidadeGestora() {
		//empty constructor
	}	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

 

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSigla() {
		return this.sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public UnidadeOrcamentaria getUnidadeOrcamentaria() {
		return this.unidadeOrcamentaria;
	}

	public void setUnidadeOrcamentaria(UnidadeOrcamentaria unidadeOrcamentaria) {
		this.unidadeOrcamentaria = unidadeOrcamentaria;
	}

	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append(" Unidade Gestoria Id: ").append(this.id)
			.append(" Unidade Gestora Código: ").append(this.codigo)
			.append(" Unidade Gestora Descrição: ").append(this.descricao)
			.append(" Unidade Gestora Sigla: ").append(this.sigla)
			.append(" Unidade Gestora Data da Operação: "+new Date(System.currentTimeMillis()));
		return sb.toString();
	}
 

}