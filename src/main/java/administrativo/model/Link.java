package administrativo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;


@Entity
@Table(name="link", schema="comum")
public class Link extends Model implements  Auditable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_link")
	private Long id;
 
 
	@Column(name="titulo",length=500,nullable=false)
	private String titulo;

	@Column(name="url",length=1000,nullable=false)
	private String url;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_tipo_link",nullable=false)
	private TipoLink tipoLink = new TipoLink();

	 
	public Long getId() {
		return this.id;
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

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}	
 

	public TipoLink getTipoLink() {
		return tipoLink;
	}

	public void setTipoLink(TipoLink tipoLink) {
		this.tipoLink = tipoLink;
	}

	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append("Link Id: ").append(id);
		sb.append(" Link Título: ").append(titulo);
		sb.append(" Link URL: ").append(url);
		sb.append(" Link TipoLink: ").append(tipoLink.getId());

		return sb.toString();
	}
 

}