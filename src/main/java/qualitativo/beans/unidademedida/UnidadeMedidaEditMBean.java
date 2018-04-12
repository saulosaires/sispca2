package qualitativo.beans.unidademedida;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import arquitetura.utils.Messages;
import arquitetura.utils.SispcaLogger;
import arquitetura.utils.Utils;
import qualitativo.model.UnidadeMedida;
import qualitativo.service.UnidadeMedidaService;

@Named
@ViewScoped
public class UnidadeMedidaEditMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String SUCCESS = "Unidade Gestora Atualizada com sucesso";
	public static final String FAIL = "Falha inesperada ao tentar Atualizar Unidade Gestora";

	private Long id;
	
	private UnidadeMedida unidadeMedida = new UnidadeMedida();

	private UnidadeMedidaService service;

	private UnidadeMedidaValidate validate;

	@Inject
	public UnidadeMedidaEditMBean(UnidadeMedidaService service, UnidadeMedidaValidate validate) {

		this.service = service;
		this.validate = validate;

	}
	
	public void init() {

		if (!Utils.invalidId(id)) {

			unidadeMedida = service.findById(id);
			
 			

		}

	}

	

	public String atualizar() {

		try {

			if (!validate.validar(unidadeMedida)) {
				return "";
			}

			service.update(unidadeMedida);

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
}
