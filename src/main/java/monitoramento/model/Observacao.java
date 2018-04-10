package monitoramento.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;
import arquitetura.utils.FormatoUtils;


/**
 * The persistent class for the observacao database table.
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="observacao", schema="monitoramento")
public class Observacao extends Model implements  Auditable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_observacao")
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	private String descricao;
	 

	//bi-directional many-to-one association to Execucao
	@OneToMany(mappedBy="observacao")
	private List<Execucao> execucaos;
 

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
		sb.append(" Observacao Ativo: ").append(getAtivo());
		return sb.toString();
	}

}