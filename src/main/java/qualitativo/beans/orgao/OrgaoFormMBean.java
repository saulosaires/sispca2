package qualitativo.beans.orgao;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import arquitetura.utils.Messages;
import arquitetura.utils.SispcaLogger;
import qualitativo.model.Orgao;
import qualitativo.service.OrgaoService;

@Named
@ViewScoped
public class OrgaoFormMBean implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public static final String FAIL_SAVE = "Falha inesperada ao tentar Salvar Orgão";
	 
  
	
	private Orgao orgao = new Orgao();
	
	private OrgaoService service;
	
	private OrgaoValidate validate;
	
	@Inject
	public OrgaoFormMBean(OrgaoService service, OrgaoValidate validate) {
		
		this.service = service;
	
		this.validate =validate;
 
		
	}

	public String salvar() {

		try {

			if (!validate.validar(orgao)) {
				return "";
			}
			 
			
			service.create(orgao);

			return "orgaoQualitativoList";

		} catch (Exception e) {
			SispcaLogger.logError(e.getCause().getMessage());

			Messages.addMessageError(FAIL_SAVE);
		}

		return "";
	}

	public Orgao getOrgao() {
		return orgao;
	}

	public void setOrgao(Orgao orgao) {
		this.orgao = orgao;
	}

 
 
 
	
 
}
