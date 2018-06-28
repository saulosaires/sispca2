package qualitativo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;
 
 
@Entity
@Table(name = "orgao", schema = "comum")
public class Orgao extends Model implements   Auditable  {
	private static final long serialVersionUID = 1L;

	@Id
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_orgao")
	private Long id;
 

	@NotNull(message = "Código: campo é obrigatório")
	private String codigo="";

	@NotNull(message = "Descrição: campo é obrigatório")
	private String descricao="";

	@NotNull(message = "Sigla: campo é obrigatório")
	private String sigla="";

	@OneToMany(mappedBy = "orgao", fetch = FetchType.LAZY)
	private List<UnidadeOrcamentaria> unidadesOrcamentarias;
	
	@OneToMany(mappedBy = "orgao", fetch = FetchType.LAZY)
	private List<Programa> programas;
 
	public Orgao() { }

	public Orgao(Long id) { this.id=id;}
	
	public Long getId() {
		return this.id;
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
	public String toString() {
		return getCodigo() +" - "+getDescricao();
	}
	
	@Override
	public String getLogDetail() {
	
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

	 

 
	 

}