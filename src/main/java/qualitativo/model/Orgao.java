package qualitativo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;
 

/**
 * The persistent class for the orgao database table.
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "orgao", schema = "comum")
public class Orgao extends Model implements Serializable, Auditable, Comparable<Orgao>, Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
//	@SequenceGenerator(name = "seq_orgao_id_orgao", sequenceName = "comum.seq_orgao_id_orgao")
//	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_orgao_id_orgao")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_orgao")
	private Long id;

	@Column(columnDefinition = "boolean default true")
	private Boolean ativo;

	@NotNull(message = "Código: campo é obrigatório")
	private String codigo;

	@NotNull(message = "Descrição: campo é obrigatório")
	private String descricao;

	@NotNull(message = "Sigla: campo é obrigatório")
	private String sigla;

	@OneToMany(mappedBy = "orgao", fetch = FetchType.LAZY)
	private List<UnidadeOrcamentaria> unidadesOrcamentarias;
	
	@OneToMany(mappedBy = "orgao", fetch = FetchType.LAZY)
	private List<Programa> programas;
	
	public Orgao() {
		this.ativo = Boolean.TRUE;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
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

	public List<UnidadeOrcamentaria> getUnidadesOrcamentarias() {
		return unidadesOrcamentarias;
	}

	public void setUnidadesOrcamentarias(
			List<UnidadeOrcamentaria> unidadesOrcamentarias) {
		this.unidadesOrcamentarias = unidadesOrcamentarias;
	}

	public List<Programa> getProgramas() {
		return programas;
	}

	public void setProgramas(List<Programa> programas) {
		this.programas = programas;
	}

	@Override
	public String getLogDetail() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append(" Orgao Id : ")
				.append(id)
				.append(" Orgao Codigo: ")
				.append(codigo)
				.append(" Orgao Descricao : ")
				.append(descricao)
				.append(" Orgao Sigla : ")
				.append(sigla)
				.append(" Orgão Data da Operação: "
						+ new Date(System.currentTimeMillis()));
		return sb.toString();
	}

	public int compareTo(Orgao orgao) {
		return this.sigla.compareTo(orgao.sigla);
	}
	
	@Override
	public boolean equals(Object object){
		if(object != null && object instanceof Orgao){
			return ((Orgao)object).getId() == this.id;
		}
		return false;
	}

}