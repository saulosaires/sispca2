package administrativo.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import administrativo.controller.MenuController;
import administrativo.model.Menu;

public class MenuService implements Serializable{

	
	
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
