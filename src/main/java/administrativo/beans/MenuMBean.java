package administrativo.beans;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.model.Menu;
import administrativo.service.MenuService;

@Named
@SessionScoped
public class MenuMBean implements Serializable{
 

	/**
	 * 
	 */
	private static final long serialVersionUID = -6457168508738709185L;
 
	
	private  List<Menu> menu;
	
	@Inject
	public MenuMBean(MenuService menuService) {
		
		menu = menuService.findRoot();
		
		Iterator<Menu> it = menu.iterator();
		
		while(it.hasNext()) {
			
			Menu m= it.next();
			
			List<Menu> subMenu = menuService.findChildMenu(m.getId());
			
			m.setSubMenu(subMenu);
		}
 
		
	}

	public List<Menu> getMenu() {
		return menu;
	}

	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}
	
	
	
	
}
