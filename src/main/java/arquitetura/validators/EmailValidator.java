package arquitetura.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator {

	public void validate(FacesContext facesContext, UIComponent uIComponent, Object object) throws ValidatorException {
		
		if (!validaEmail(String.valueOf(object))) {
			FacesMessage message = new FacesMessage();
			message.setDetail("Email inválido");
			message.setSummary("Email inválido");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
	
	public static boolean validaEmail(String enteredEmail){
		
		if(enteredEmail == null)
			return false;
		
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
		Matcher m = p.matcher(enteredEmail);

		return m.matches();
	}
	
}