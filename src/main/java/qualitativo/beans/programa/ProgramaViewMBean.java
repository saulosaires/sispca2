package qualitativo.beans.programa;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import arquitetura.enums.TipoArquivo;
import arquitetura.utils.FileUtil;
import arquitetura.utils.Messages;
import arquitetura.utils.SispcaLogger;
import arquitetura.utils.Utils;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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
public class ProgramaViewMBean implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public static final String FAIL = "Falha inesperada ao tentar Imprimir Programa";
   
	 
	private Long id;
	
	private Programa programa = new Programa();
	
	private ProgramaService service;

	private OrgaoService orgaoService;
	private UnidadeOrcamentariaService unidadeOrcamentariaService; 
	private TipoProgramaService tipoProgramaService;
	private TipoHorizonteTemporalService tipoHorizonteTemporalService;
	
	private List<Orgao> listOrgoes;
	private List<UnidadeOrcamentaria> listUnidadeOrcamentaria;
	private List<TipoPrograma> listTipoPrograma;
	private List<TipoHorizonteTemporal> listTipoHorizonteTemporal;
	
	
	@Inject
	public ProgramaViewMBean(ProgramaService service,
							 OrgaoService orgaoService,
						     UnidadeOrcamentariaService unidadeOrcamentariaService, 
						     TipoProgramaService tipoProgramaService,
						     TipoHorizonteTemporalService tipoHorizonteTemporalService) {
		
		this.service = service; 
		this.orgaoService=orgaoService;
		this.unidadeOrcamentariaService=unidadeOrcamentariaService;
		this.tipoProgramaService=tipoProgramaService;
		this.tipoHorizonteTemporalService=tipoHorizonteTemporalService;
		
	}

	public void init() {

		if (!Utils.invalidId(id)) {

			programa = service.findById(id);
			
			if(programa.getOrgao()!=null && !Utils.invalidId(programa.getOrgao().getId())) {
			   programa.setOrgao(orgaoService.findById(programa.getOrgao().getId()));
			}
			
			if(programa.getUnidadeOrcamentaria()!=null && !Utils.invalidId(programa.getUnidadeOrcamentaria().getId())) {
			   programa.setUnidadeOrcamentaria(unidadeOrcamentariaService.findById(programa.getUnidadeOrcamentaria().getId()));
			}
			
			if(programa.getTipoPrograma()!=null && !Utils.invalidId(programa.getTipoPrograma().getId())) {
			   programa.setTipoPrograma(tipoProgramaService.findById(programa.getTipoPrograma().getId()));
			}

			if(programa.getTipoHorizonteTemporal()!=null && !Utils.invalidId(programa.getTipoHorizonteTemporal().getId())) {
			   programa.setTipoHorizonteTemporal(tipoHorizonteTemporalService.findById(programa.getTipoHorizonteTemporal().getId()));
			}


		}

	}
	
    public String imprimir() {
    	
		try {
			
			Map<String, Object> parameters = new HashMap<>();
			
			String brasaoMa=FileUtil.getRealPath("/resources/images/brasao_ma.png");
 			
			parameters.put("param_imagem", brasaoMa);
			
			parameters.put("codigo_programa",	     programa.getCodigo());
			parameters.put("programa",			     programa.getDenominacao());
			parameters.put("orgao",				     programa.getOrgao().getDescricao());
			parameters.put("unidade_orcamentaria",   programa.getUnidadeOrcamentaria().getDescricao());
			parameters.put("problema",			     programa.getProblema());
			parameters.put("objetivo",			     programa.getObjetivo());
			parameters.put("publico_alvo",		     programa.getPublicoAlvo());
			parameters.put("justificativa",		     programa.getJustificativa());
			parameters.put("tipo_programa",		     programa.getTipoPrograma().getDescricao());
			parameters.put("tipo_horizonte_temporal",programa.getTipoHorizonteTemporal().getDescricao());
			
			
 
			
			String report=FileUtil.getRealPath("/relatorios/qualitativo/visualizacao_programa.jasper");
			
			JasperPrint relatorio = JasperFillManager.fillReport(report, parameters ,new JRBeanCollectionDataSource(null));
		
			byte[] bytes = JasperExportManager.exportReportToPdf(relatorio);
			
			FileUtil.sendFileOnResponseAttached(bytes, "visualizacao_programa.pdf", TipoArquivo.PDF.getId());
			
		} catch (Exception e) {
			SispcaLogger.logError(e);

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
