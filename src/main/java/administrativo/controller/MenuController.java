package administrativo.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import administrativo.dao.MenuDAO;
import administrativo.model.Menu;

public class MenuController implements Serializable {

	
	private MenuDAO menuDAO;
	
	@Inject 
	MenuController(MenuDAO menuDAO){
		this.menuDAO=menuDAO;
	}
	
	public List<Menu> findRoot(){
		
		return menuDAO.findRoot();
	}
	
	public List<Menu> findChildMenu(Long idRoot){
		
		return menuDAO.findChildMenu(idRoot);
	}
	
}
