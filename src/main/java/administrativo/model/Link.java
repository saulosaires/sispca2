package administrativo.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.internal.Nullable;

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

	@Column(name="url",length=1000,nullable=true)
	private String url;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_tipo_link",nullable=false)
	private TipoLink tipoLink = new TipoLink();
	
	@Column(name="mime",length=50,nullable=true)
	private String mime;
	
	@Column(name="filename",length=500,nullable=true)
	private String filename;
	
	private transient byte[] content;	
	private transient String extension;
		
	public Link() {}
	
	public Link(String titulo, String url, TipoLink tipoLink) {
		super();
		this.titulo = titulo;
		this.url = url;
		this.tipoLink = tipoLink;
	}

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
	
	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getMime() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}	
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
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