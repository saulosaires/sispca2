package administrativo.service;

import java.util.List;

import javax.inject.Inject;

import administrativo.dao.MenuDAO;
import administrativo.model.Menu;
import arquitetura.service.AbstractService;

public class MenuService extends AbstractService<Menu> {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4923079177213762511L;
 	
	@Inject
	public MenuService(MenuDAO dao){
		super(dao);
	}
	
	
	public List<Menu> findRoot(){
		
		return ((MenuDAO) getDAO()).findRoot();
	}
	
	public List<Menu> findChildMenu(Long idRoot){
		
		return ((MenuDAO) getDAO()).findChildMenu(idRoot);
	}
	
	
	
}
