package administrativo.beans;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.text.WordUtils;

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
	
	private String urlNavigation;
	
	private final int INDEX_FORM=5;
	String[][] arr= {
					 {"/sispca2/",""},
					 {"public/",""},
					 { "private/",""},
					 {".xhtml",""},
					 {"list"," Listar"},
					 {"form",""},
					 {"login",""}
	  				};
	
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

	//TODO Assim que possivel desfazer essa gambi
	public void proccessUrl(String url,boolean hasParameters) {
			
		arr[INDEX_FORM][1]= hasParameters?"Inserir":"Atualizar";
 	 
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
