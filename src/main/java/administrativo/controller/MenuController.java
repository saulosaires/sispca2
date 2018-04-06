package administrativo.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import administrativo.dao.MenuDAO;
import administrativo.model.Menu;
import arquitetura.controller.AbstractController;

public class MenuController  extends AbstractController<Menu> implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2753896240998617710L;
 	
	@Inject 
	MenuController(MenuDAO menuDAO){
		super(menuDAO);
	}
	
	public List<Menu> findRoot(){
		
		return ((MenuDAO) getDao()).findRoot();
	}
	
	public List<Menu> findChildMenu(Long idRoot){
		
		return ((MenuDAO) getDao()).findChildMenu(idRoot);
	}
	
}
