package administrativo.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import administrativo.controller.MenuController;
import administrativo.model.Menu;
import arquitetura.service.AbstractService;

public class MenuService extends AbstractService<Menu> implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4923079177213762511L;
 	
	@Inject
	public MenuService(MenuController menuController){
		super(menuController);
	}
	
	
	public List<Menu> findRoot(){
		
		return ((MenuController) getController()).findRoot();
	}
	
	public List<Menu> findChildMenu(Long idRoot){
		
		return ((MenuController) getController()).findChildMenu(idRoot);
	}
	
	
	
}
