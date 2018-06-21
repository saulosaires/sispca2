package administrativo.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.text.WordUtils;

import administrativo.model.Menu;
import administrativo.service.MenuService;
import arquitetura.utils.SessionUtils;

@Named
@SessionScoped
public class MenuMBean implements Serializable{
 

	/**
	 * 
	 */
	private static final long serialVersionUID = -6457168508738709185L;
 
	
	private  List<Menu> menu= new ArrayList<>();
	
	private String urlNavigation;
	
 
	String[][] arr= {
					 {"/sispca/",""},
					 {"public/",""},
					 { "private/",""},
					 {".xhtml",""},
					 {"list"," Listar"},
					 {"edit"," Editar"},
					 {"form"," Salvar"},
					 {"login",""}
	  				};
	
	private MenuService menuService;
	
	@Inject
	public MenuMBean(MenuService menuService) {
		
		this.menuService=menuService;
		
		initMenu() ;
		
	}

	public void initMenu() {
		
		menu.clear();
		
		List<Menu> listMenu = menuService.findRoot();
		
		Iterator<Menu> it = listMenu.iterator();
		
		while(it.hasNext()) {
			
			Menu m= it.next();
			m.getSubMenu().clear();
			if(SessionUtils.containsKey(m.getName())) {
 
				List<Menu> subMenu = menuService.findChildMenu(m.getId());
				
				for(Menu subM:subMenu) {
					
					if(SessionUtils.containsKey(subM.getName())) {
						m.getSubMenu().add(subM);
					}
					
				}
					
			 menu.add(m);
			}
			

		}
		
		
	}
	
	public List<Menu> getMenu() {
		return menu;
	}

	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}

	//TODO Assim que possivel desfazer essa gambi
	public void proccessUrl(String url) {
	 	 
		for(int i=0; i<arr.length;i++) {
			url = url.replace(arr[i][0],arr[i][1]);
		}
			 
		StringJoiner joiner = new StringJoiner(" > ");
		
		String[] fields = url.split("/");
		
		for(String f: fields) {
			joiner.add(WordUtils.capitalize(f));
		}
		
		StringBuilder stringBuilder = new StringBuilder("");
		
		if(joiner.length()>0)
			stringBuilder.append(" > ");
		
		stringBuilder.append(joiner.toString());
		
		urlNavigation=stringBuilder.toString(); 
		
	}
	
	public String getUrlNavigation() {
		return urlNavigation;
	}

	public void setUrlNavigation(String urlNavigation) {
		this.urlNavigation = urlNavigation;
	}
	
	
	
	
}
