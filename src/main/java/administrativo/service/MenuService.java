package administrativo.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import administrativo.controller.MenuController;
import administrativo.model.Menu;

public class MenuService implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4923079177213762511L;
	MenuController menuController;
	
	@Inject
	public MenuService(MenuController menuController){
		this.menuController=menuController;
	}
	
	
	public List<Menu> findRoot(){
		
		return menuController.findRoot();
	}
	
	public List<Menu> findChildMenu(Long idRoot){
		
		return menuController.findChildMenu(idRoot);
	}
	
	
	
}
