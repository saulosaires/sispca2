package administrativo.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import administrativo.model.Menu;

@Named
@SessionScoped
public class MenuMBean implements Serializable{

//	"Planejamento Qualitativo"
//	"Planejamento Quantitativo"
//	"Monitoramento"
//	"Avaliação"
//	"Revisão"
//	"Relatórios"
//	"Administrativo"
//	"Lista Relatórios"
//	"Gráficos"
//	"Metas"

	private Menu menuAdministrativo;
	
	private  List<Menu> menu;
	
	public MenuMBean() {
		
		menu = new ArrayList<>(10);
		
		menuAdministrativo = new Menu("Administrativo", "",new ArrayList<>());
		
		menuAdministrativo.getSubMenu().add(new Menu("Exercicio", "/private/administrativo/exercicio/list.xhtml"));
		
		
		
		menu.add(menuAdministrativo);
	}

	public List<Menu> getMenu() {
		return menu;
	}

	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}
	
	
	
	
}
