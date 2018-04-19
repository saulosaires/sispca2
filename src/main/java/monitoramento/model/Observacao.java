package monitoramento.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
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

	@Column(name="descricao")
	private String descricao;
	  
	@OneToOne(mappedBy="observacao",fetch=FetchType.LAZY)
	private Execucao execucao;
 

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
 
	public Execucao getExecucao() {
		return execucao;
	}

	public void setExecucao(Execucao execucao) {
		this.execucao = execucao;
	}

	@PrePersist
    public void onPrePersist() {

		data = new Date();
	}
       
    @PreUpdate
    public void onPreUpdate() { 
    	data = new Date();
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