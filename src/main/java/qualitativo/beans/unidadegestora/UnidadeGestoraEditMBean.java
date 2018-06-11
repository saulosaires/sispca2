package qualitativo.beans.unidadegestora;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.model.Usuario;
import arquitetura.utils.Messages;
import arquitetura.utils.SessionUtils;
import arquitetura.utils.SispcaLogger;
import arquitetura.utils.Utils;
import qualitativo.model.UnidadeGestora;
import qualitativo.model.UnidadeOrcamentaria;
import qualitativo.service.UnidadeGestoraService;
import qualitativo.service.UnidadeOrcamentariaService;

@Named
@ViewScoped
public class UnidadeGestoraEditMBean implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public static final String  SUCCESS="Unidade Gestora atualizada com sucesso";
	public static final String FAIL = "Falha inesperada ao tentar Atualizar Unidade Gestora";
  
	private Long id;
	
	private UnidadeGestora unidadeGestora = new UnidadeGestora();
	
	private UnidadeGestoraService service;
	
	private UnidadeGestoraValidate validate;
 
	private List<UnidadeOrcamentaria> listUnidadeOrcamentaria; 
	
	@Inject
	public UnidadeGestoraEditMBean(UnidadeGestoraService service,
								   UnidadeGestoraValidate validate,   
								   UnidadeOrcamentariaService unidadeOrcamentariaService) {
		
		this.service = service;
		this.validate =validate; 
		
		Usuario user = (Usuario) SessionUtils.get(SessionUtils.USER);
		
		this.listUnidadeOrcamentaria   = unidadeOrcamentariaService.findAllOrderByDescricao(user.getId());

		
	}

	public void init() {

		if (!Utils.invalidId(id)) {

			unidadeGestora = service.findById(id);
			
			Long idUo = unidadeGestora.getUnidadeOrcamentaria().getId();
			unidadeGestora.setUnidadeOrcamentaria(new UnidadeOrcamentaria(idUo));
			

		}

	}
	
	public String atualizar() {

		try {

			if (!validate.validar(unidadeGestora)) {
				return "";
			}
			 
			validate.beforeMerge(unidadeGestora);
			
			service.update(unidadeGestora);
			
			Messages.addMessageInfo(SUCCESS);
			return "unidadeGestoraQualitativoList";

		} catch (Exception e) {
			SispcaLogger.logError(e);

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
 
 
}
