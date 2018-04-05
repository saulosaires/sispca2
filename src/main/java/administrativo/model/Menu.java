package administrativo.model;

import java.util.ArrayList;
import java.util.List;

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
public class Menu extends Model{

	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 8204621574018445105L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name="label",length=80,nullable=false)
	private String label;
	
	@Column(name="link",length=150,nullable=false)
	private String link;

	@Column(name="name",length=150,nullable=false)
	private String name;
 
	@ManyToOne
	@JoinColumn(name = "id_menu_pai",nullable=true)
	private Menu pai;
	
	private transient List<Menu> subMenu= new ArrayList<>();
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Menu> getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(List<Menu> subMenu) {
		this.subMenu = subMenu;
	}

	 
	
	
}
