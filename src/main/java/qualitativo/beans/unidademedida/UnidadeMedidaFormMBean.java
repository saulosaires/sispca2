package qualitativo.beans.unidademedida;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import arquitetura.utils.Messages;
import arquitetura.utils.SispcaLogger;
import qualitativo.model.UnidadeMedida;
import qualitativo.service.UnidadeMedidaService;

@Named
@ViewScoped
public class UnidadeMedidaFormMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String SUCCESS = "Unidade Gestora salva com sucesso";
	public static final String FAIL = "Falha inesperada ao tentar Salvar Unidade Gestora";

	private UnidadeMedida unidadeMedida = new UnidadeMedida();

	private UnidadeMedidaService service;

	private UnidadeMedidaValidate validate;

	@Inject
	public UnidadeMedidaFormMBean(UnidadeMedidaService service, UnidadeMedidaValidate validate) {

		this.service = service;
		this.validate = validate;

	}

	public String salvar() {

		try {

			if (!validate.validar(unidadeMedida)) {
				return "";
			}

			service.create(unidadeMedida);

			Messages.addMessageInfo(SUCCESS);

			return "unidadeMedidaQualitativoList";

		} catch (Exception e) {
			SispcaLogger.logError(e.getLocalizedMessage());

			Messages.addMessageError(FAIL);
		}

		return "";
	}

	public UnidadeMedida getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	
	
}
