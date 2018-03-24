package monitoramento.model;

import java.io.Serializable;

import javax.persistence.*;

import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;
import arquitetura.utils.FormatoUtils;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the observacao database table.
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="observacao", schema="monitoramento")
public class Observacao extends Model implements Serializable, Auditable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_observacao")
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	private String descricao;
	
	private Boolean ativo;

	//bi-directional many-to-one association to Execucao
	@OneToMany(mappedBy="observacao")
	private List<Execucao> execucaos;

	public Observacao() {
		this.ativo = Boolean.TRUE;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Execucao> getExecucaos() {
		return this.execucaos;
	}

	public void setExecucaos(List<Execucao> execucaos) {
		this.execucaos = execucaos;
	}

 

	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append(" Observação id: ").append(id);
		sb.append(" Observacao Descricao: ").append(descricao);
		sb.append(" Observacao Data: ").append(FormatoUtils.formataData(getData()));
		sb.append(" Observacao Ativo: ").append(ativo);
		return sb.toString();
	}

}