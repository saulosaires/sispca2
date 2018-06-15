package qualitativo.beans.programa;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.model.Exercicio;
import administrativo.model.Usuario;
import administrativo.service.ExercicioService;
import arquitetura.utils.Messages;
import arquitetura.utils.SessionUtils;
import arquitetura.utils.SispcaLogger;
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
public class ProgramaFormMBean implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public static final String FAIL_SAVE = "Falha inesperada ao tentar Salvar Programa";
  
	
	private Programa programa = new Programa();
	
	private ExercicioService exercicioService;
	private ProgramaService service;
	
	private OrgaoService orgaoService;
	private UnidadeOrcamentariaService unidadeOrcamentariaService;
	private TipoProgramaService tipoProgramaService;
	private TipoHorizonteTemporalService tipoHorizonteTemporalService;
	
	private ProgramaValidate validate;
	
	private List<Orgao> listOrgoes;
	private List<UnidadeOrcamentaria> listUnidadeOrcamentaria;
	private List<TipoPrograma> listTipoPrograma;
	private List<TipoHorizonteTemporal> listTipoHorizonteTemporal;
	
	
	@Inject
	public ProgramaFormMBean(ProgramaService service,ProgramaValidate validate, 	
							 OrgaoService orgaoService,
							 ExercicioService exercicioService,
						     UnidadeOrcamentariaService unidadeOrcamentariaService, 
						     TipoProgramaService tipoProgramaService,
						     TipoHorizonteTemporalService tipoHorizonteTemporalService) {
		
		
		this.service = service;
		this.exercicioService = exercicioService;
		this.unidadeOrcamentariaService = unidadeOrcamentariaService;
		this.orgaoService = orgaoService;
		this.tipoProgramaService= tipoProgramaService;
		this.tipoHorizonteTemporalService = tipoHorizonteTemporalService;
		
		this.validate =validate;
  
		Usuario user = (Usuario) SessionUtils.get(SessionUtils.USER);
		
		listTipoPrograma	      = tipoProgramaService.findAll();
		listOrgoes				  = orgaoService.findAllOrderByDescricao(user.getId()); 
		listUnidadeOrcamentaria   = unidadeOrcamentariaService.findAllOrderByDescricao(user.getId());
		listTipoHorizonteTemporal = tipoHorizonteTemporalService.findAll();
		
		
	}

	public String salvar() {

		try {

			if (!validate.validar(programa)) {
				return "";
			}
			 
			Optional<Exercicio> ex = exercicioService.exercicioVigente();
			if(ex.isPresent())
				programa.setExercicio(ex.get());

			
			programa.setOrgao(orgaoService.findById(programa.getOrgao().getId()));
			programa.setUnidadeOrcamentaria(unidadeOrcamentariaService.findById(programa.getUnidadeOrcamentaria().getId()));
			programa.setTipoPrograma(tipoProgramaService.findById(programa.getTipoPrograma().getId()));
			programa.setTipoHorizonteTemporal(tipoHorizonteTemporalService.findById(programa.getTipoHorizonteTemporal().getId()));
			
			service.create(programa);

			return "programaQualitativoList";

		} catch (Exception e) {
			SispcaLogger.logError(e);

			Messages.addMessageError(FAIL_SAVE);
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

 
}
