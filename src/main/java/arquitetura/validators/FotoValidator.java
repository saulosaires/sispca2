package arquitetura.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.primefaces.model.UploadedFile;

@FacesValidator("FotoValidator")
public class FotoValidator implements Validator {

	private static final int MAX_SIZE = 2 * 1024 * 1024;

	public void validate(FacesContext facesContext, UIComponent uIComponent,Object value) throws ValidatorException {

		UploadedFile arquivo = (UploadedFile) value;
		if (arquivo != null) {
			
			if (arquivo.getSize() > MAX_SIZE) {
				FacesMessage msg = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"A foto deve ter o tamanho máximo de 2mb.",
						"O arquivo deve ter o tamanho máximo de 2mb.");
				throw new ValidatorException(msg);
			}

			if (!arquivo.getContentType().contains("image")) {
				FacesMessage msg = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"Tipo de arquivo inválido",
						"O arquivo deve ser do tipo texto.");
				throw new ValidatorException(msg);
			}
			
		}
	}

}