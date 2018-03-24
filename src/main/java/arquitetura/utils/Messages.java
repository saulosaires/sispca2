package arquitetura.utils;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Messages {

	private Messages() {
	    throw new IllegalStateException("Utility class");
	  }

	
	public static void addMessageWarn(String message){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, null)); 
	}

	public static void addMessageInfo(String message){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null)); 
	}

	public static void addMessageFatal(String message){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, message, null)); 
	}
	
	public static void addMessageError(String message){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null)); 
	}
	
	public static String getMessage(String key) {
		return ResourceBundle.getBundle("messages").getString(key);
	}
	
	
}