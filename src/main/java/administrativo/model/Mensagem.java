package administrativo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;
import arquitetura.utils.FormatoUtils;


/**
 * The persistent class for the categoria database table.
 * 
 */
@Entity
@Table(name="mensagem", schema="comum")
public class Mensagem extends Model implements Serializable, Auditable {
	private static final long serialVersionUID = 1L;

	@Id
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_mensagem")
	private Long id;

	@Column(name="titulo",length=80,nullable=false)
	private String titulo;
	
	@Column(name="texto",nullable=false)
	private String texto;

	@Column(name = "data_expiracao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataExpiracao;
	
	 
 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getDataExpiracao() {
		return dataExpiracao;
	}

	public void setDataExpiracao(Date dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}

	@Override
	public String getLogDetail() {		
		StringBuilder sb = new StringBuilder();
		sb.append(" Mensagem Id : ").append(id);
		sb.append(" Mensagem titulo : ").append(titulo);
		sb.append(" Mensagem Texto : ").append(texto);
		sb.append(" Mensagem Data Expiracao : ").append(FormatoUtils.formataData(dataExpiracao));
		sb.append(" Mensagem Ativo : ").append(getAtivo());
 
		return sb.toString();
	}

}