package qualitativo.beans.programa;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import arquitetura.utils.Messages;
import arquitetura.utils.SispcaLogger;
import arquitetura.utils.Utils;
import qualitativo.model.Orgao;
import qualitativo.model.Programa;
import qualitativo.model.TipoHorizonteTemporal;
import qualitativo.model.TipoPrograma;
import qualitativo.model.UnidadeOrcamentaria;
import qualitativo.service.OrgaoService;
import qualitativo.service.ProgramaService;
import qualitativo.service.TipoHorizonteTemporalService;
import qualitativo.service.TipoProgramaService;
import qualitativo.service.UnidadeOrcamentariaService;

@Named
@ViewScoped
public class ProgramaEditMBean implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public static final String  SUCCESS="Programa atualizado com sucesso";
	public static final String FAIL = "Falha inesperada ao tentar Atualizar Programa";
   
	 
	private Long id;
	
	private Programa programa = new Programa();
	
	private ProgramaService service;
	
	private ProgramaValidate validate;
	
	private List<Orgao> listOrgoes;
	private List<UnidadeOrcamentaria> listUnidadeOrcamentaria;
	private List<TipoPrograma> listTipoPrograma;
	private List<TipoHorizonteTemporal> listTipoHorizonteTemporal;
	
	
	@Inject
	public ProgramaEditMBean(ProgramaService service,ProgramaValidate validate, 	OrgaoService orgaoService,
						     UnidadeOrcamentariaService unidadeOrcamentariaService, TipoProgramaService tipoProgramaService,
						     TipoHorizonteTemporalService tipoHorizonteTemporalService) {
		
		this.service = service;
	
		this.validate =validate;
  
		listTipoPrograma	      = tipoProgramaService.findAll();
		listOrgoes				  = orgaoService.findAllOrderByDescricao(); 
		listUnidadeOrcamentaria   = unidadeOrcamentariaService.findAll();
		listTipoHorizonteTemporal = tipoHorizonteTemporalService.findAll();
		
		
	}

	public void init() {

		if (!Utils.invalidId(id)) {

			programa = service.findById(id);

		}

	}
	
	public String atualizar() {

		try {

			if (!validate.validar(programa)) {
				return "";
			}
			 
			
			service.update(programa);
			Messages.addMessageInfo(SUCCESS);
			return "programaQualitativoList";

		} catch (Exception e) {
			SispcaLogger.logError(e.getCause().getMessage());

			Messages.addMessageError(FAIL);
		}

		return "";
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public List<Orgao> getListOrgoes() {
		return listOrgoes;
	}

	public void setListOrgoes(List<Orgao> listOrgoes) {
		this.listOrgoes = listOrgoes;
	}

	public List<UnidadeOrcamentaria> getListUnidadeOrcamentaria() {
		return listUnidadeOrcamentaria;
	}

	public void setListUnidadeOrcamentaria(List<UnidadeOrcamentaria> listUnidadeOrcamentaria) {
		this.listUnidadeOrcamentaria = listUnidadeOrcamentaria;
	}

	public List<TipoPrograma> getListTipoPrograma() {
		return listTipoPrograma;
	}

	public void setListTipoPrograma(List<TipoPrograma> listTipoPrograma) {
		this.listTipoPrograma = listTipoPrograma;
	}

	public List<TipoHorizonteTemporal> getListTipoHorizonteTemporal() {
		return listTipoHorizonteTemporal;
	}

	public void setListTipoHorizonteTemporal(List<TipoHorizonteTemporal> listTipoHorizonteTemporal) {
		this.listTipoHorizonteTemporal = listTipoHorizonteTemporal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	 
 
}