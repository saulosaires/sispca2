package administrativo.model;

import java.io.Serializable;

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

import arquitetura.model.Model;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "menu", schema = "controle_acesso")
public class Menu extends Model implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name="name",length=80,nullable=false)
	private String label;
	
	@Column(name="name",length=150,nullable=false)
	private String link;
	
 
	@ManyToOne
	@JoinColumn(name = "id_menu_pai",nullable=false)
	private Menu pai;
	
	public Menu() {
	
	}

	public Menu(String label, String link) {
		super();
		this.label = label;
		this.link = link;
	}
	
	public Menu(String label, String link, Menu pai) {
		super();
		this.label = label;
		this.link = link;
		this.pai=pai;
	}



	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Menu getPai() {
		return pai;
	}

	public void setPai(Menu pai) {
		this.pai = pai;
	}

	 
	
	
}
