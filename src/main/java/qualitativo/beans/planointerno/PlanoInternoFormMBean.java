package qualitativo.beans.planointerno;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import arquitetura.utils.Messages;
import arquitetura.utils.SispcaLogger;
import qualitativo.model.Acao;
import qualitativo.model.PlanoInterno;
import qualitativo.service.AcaoService;
import qualitativo.service.PlanoInternoService;

@Named
@ViewScoped
public class PlanoInternoFormMBean implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public static final String FAIL_SAVE = "Falha inesperada ao tentar Salvar Plano Interno";
	public static final String FAIL_INIT = "Falha inesperada ao tentar Iniciar Plano Interno";	 
  
	
	private PlanoInterno planoInterno = new PlanoInterno();
	
	private PlanoInternoService service;
	private AcaoService acaoService;
	private PlanoInternoValidate validate;
	
	private List<Acao> listAcao;
	
	@Inject
	public PlanoInternoFormMBean(PlanoInternoService service, AcaoService acaoService, PlanoInternoValidate validate) {
		
		this.service = service;
		this.acaoService = acaoService;
		
		this.validate =validate;
  
		listAcao = acaoService.findAll(); 
	}

	public String salvar() {

		try {

			if (!validate.validar(planoInterno)) {
				return "";
			}
			 
			Acao acao = acaoService.findById(planoInterno.getAcao().getId());
			planoInterno.setAcao(acao);
			
			service.create(planoInterno);

			return "planoInternoQualitativoList";

		} catch (Exception e) {
			SispcaLogger.logError(e);

			Messages.addMessageError(FAIL_SAVE);
		}

		return "";
	}

	public PlanoInterno getPlanoInterno() {
		return planoInterno;
	}

	public void setPlanoInterno(PlanoInterno planoInterno) {
		this.planoInterno = planoInterno;
	}

	public List<Acao> getListAcao() {
		return listAcao;
	}

	public void setListAcao(List<Acao> listAcao) {
		this.listAcao = listAcao;
	}
 

 
 
	
 
}
