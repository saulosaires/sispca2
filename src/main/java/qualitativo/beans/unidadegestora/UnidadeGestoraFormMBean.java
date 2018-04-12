package qualitativo.beans.unidadegestora;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import arquitetura.utils.Messages;
import arquitetura.utils.SispcaLogger;
import qualitativo.model.UnidadeGestora;
import qualitativo.model.UnidadeOrcamentaria;
import qualitativo.service.UnidadeGestoraService;
import qualitativo.service.UnidadeOrcamentariaService;

@Named
@ViewScoped
public class UnidadeGestoraFormMBean implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public static final String SUCCESS = "Unidade Gestora salva com sucesso";
	public static final String FAIL = "Falha inesperada ao tentar Salvar Unidade Gestora";
  
	
	private UnidadeGestora unidadeGestora = new UnidadeGestora();
	
	private UnidadeGestoraService service;
	
	private UnidadeGestoraValidate validate;
 
	private List<UnidadeOrcamentaria> listUnidadeOrcamentaria;
 	
	
	@Inject
	public UnidadeGestoraFormMBean(UnidadeGestoraService service,
								   UnidadeGestoraValidate validate,   
								   UnidadeOrcamentariaService unidadeOrcamentariaService) {
		
		this.service = service;
		this.validate =validate;

		this.listUnidadeOrcamentaria   = unidadeOrcamentariaService.findAllOrderByDescricao();

		
	}

	public String salvar() {

		try {

			if (!validate.validar(unidadeGestora)) {
				return "";
			}
			 
			validate.beforeMerge(unidadeGestora);
			
			service.create(unidadeGestora);
			
			Messages.addMessageInfo(SUCCESS);
			
			return "unidadeGestoraQualitativoList";

		} catch (Exception e) {
			SispcaLogger.logError(e.getCause().getMessage());

			Messages.addMessageError(FAIL);
		}

		return "";
	}

	public UnidadeGestora getUnidadeGestora() {
		return unidadeGestora;
	}

	public void setUnidadeGestora(UnidadeGestora unidadeGestora) {
		this.unidadeGestora = unidadeGestora;
	}

	public List<UnidadeOrcamentaria> getListUnidadeOrcamentaria() {
		return listUnidadeOrcamentaria;
	}

	public void setListUnidadeOrcamentaria(List<UnidadeOrcamentaria> listUnidadeOrcamentaria) {
		this.listUnidadeOrcamentaria = listUnidadeOrcamentaria;
	}

	
	
 
 
}
