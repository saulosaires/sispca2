package qualitativo.beans.indicador;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import arquitetura.utils.Messages;
import arquitetura.utils.SispcaLogger;
import arquitetura.utils.Utils;
import qualitativo.model.Indicador;
import qualitativo.service.IndicadorService;

@Named
@ViewScoped
public class IndicadorEditMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String FAIL_SAVE = "Falha inesperada ao tentar Atualizar Indicador";

	private Long id;

	private Indicador indicador = new Indicador();

	private IndicadorService service;

	private IndicadorValidate validate;

	@Inject
	public IndicadorEditMBean(IndicadorService service, IndicadorValidate validate) {

		this.service = service;

		this.validate = validate;

	}

	public void init() {

		if (!Utils.invalidId(id)) {

			indicador = service.findById(id);

		}

	}

	public String atualizar() {

		try {

			if (!validate.validar(indicador)) {
				return "";
			}

			service.update(indicador);

			return "indicadorQualitativoList";

		} catch (Exception e) {
			SispcaLogger.logError(e);

			Messages.addMessageError(FAIL_SAVE);
		}

		return "";
	}

	public Indicador getIndicador() {
		return indicador;
	}

	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
	}

	public IndicadorService getService() {
		return service;
	}

	public void setService(IndicadorService service) {
		this.service = service;
	}

	public IndicadorValidate getValidate() {
		return validate;
	}

	public void setValidate(IndicadorValidate validate) {
		this.validate = validate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
