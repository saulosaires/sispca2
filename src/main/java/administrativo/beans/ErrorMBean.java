package administrativo.beans;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import arquitetura.utils.SessionUtils;

@Named
@ViewScoped
public class ErrorMBean implements Serializable{

 
	/**
	 * 
	 */
	private static final long serialVersionUID = -4837191377238836344L;
	private String code;
	private String type;
	private String message;
 
 
	public ErrorMBean() {
		
		  code    = (String) SessionUtils.get("code");
		  type    = (String) SessionUtils.get("type");
		  message = (String) SessionUtils.get("message");
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}

 
}
