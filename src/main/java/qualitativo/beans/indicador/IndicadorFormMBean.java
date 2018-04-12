package qualitativo.beans.indicador;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import arquitetura.utils.Messages;
import arquitetura.utils.SispcaLogger;
import qualitativo.model.Indicador;
import qualitativo.service.IndicadorService;

@Named
@ViewScoped
public class IndicadorFormMBean implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public static final String FAIL_SAVE = "Falha inesperada ao tentar Salvar Indicador";
	 
  
	
	private Indicador indicador = new Indicador();
	
	private IndicadorService service;
	
	private IndicadorValidate validate;
	
	@Inject
	public IndicadorFormMBean(IndicadorService service, IndicadorValidate validate) {
		
		this.service = service;
	
		this.validate =validate;
 
		
		
		
	}

	public String salvar() {

		try {

			if (!validate.validar(indicador)) {
				return "";
			}
			 
			
			service.create(indicador);

			return "indicadorQualitativoList";

		} catch (Exception e) {
			SispcaLogger.logError(e.getCause().getMessage());

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

 
 
	
 
}
