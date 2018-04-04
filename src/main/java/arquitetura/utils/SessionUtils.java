package arquitetura.utils;

import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {

	private SessionUtils() {
	    throw new IllegalStateException("Utility class");
	}

	
	public static void invalidate() {
		
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.invalidate();
	}
	
	public static Object put(String key, Object value) {
		
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		
		return sessionMap.put(key, value);
		
	}
	
	public static Object getBean(String beanName) {
		
		 FacesContext context = FacesContext.getCurrentInstance();
		 return context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);

 
		
	}

	
	public static Object get(Object key) {
		
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		
		return sessionMap.get(key);
		
	}
	
	public static HttpServletRequest getRequest() {
		
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		
	}


}
