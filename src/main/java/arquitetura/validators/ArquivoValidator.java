package arquitetura.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.primefaces.model.UploadedFile;

/*
 * Walter Júnior
 * 
 * Validador para saber se o arquivo está no formato em pdf. Caso não esteja,
 * é disparado uma mensagem de erro.
 */
@FacesValidator("arquivoValidator")
public class ArquivoValidator implements Validator {

	public void validate(FacesContext facesContext, UIComponent uIComponent,
			Object value) throws ValidatorException {

		UploadedFile arquivo = (UploadedFile) value;
		if (arquivo != null) {
			if (!arquivo.getFileName().endsWith(".pdf") && !arquivo.getFileName().endsWith(".PDF")) {
				FacesMessage msg = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Arquivo: o formato deve ser em pdf", "Arquivo: o formato deve ser em pdf");
				throw new ValidatorException(msg);
			}
		}
	}

}