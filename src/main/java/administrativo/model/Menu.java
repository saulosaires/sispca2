package administrativo.model;

import java.util.List;

public class Menu {

	
	private String label;
	private String link;
	
	private List<Menu> subMenu;
	
	public Menu() {
	
	}

	public Menu(String label, String link) {
		super();
		this.label = label;
		this.link = link;
	}
	
	public Menu(String label, String link, List<Menu> subMenu) {
		super();
		this.label = label;
		this.link = link;
		this.subMenu=subMenu;
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

	public List<Menu> getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(List<Menu> subMenu) {
		this.subMenu = subMenu;
	}
	
	
	
}
