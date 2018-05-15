package relatorio.beans;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.model.Exercicio;
import administrativo.service.ExercicioService;
import arquitetura.enums.TipoArquivo;
import arquitetura.utils.FileUtil;
import arquitetura.utils.Messages;
import arquitetura.utils.SispcaLogger;
import arquitetura.utils.Utils;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import qualitativo.model.Acao;
import qualitativo.model.Orgao;
import qualitativo.model.Programa;
import qualitativo.model.UnidadeOrcamentaria;
import qualitativo.service.AcaoService;
import qualitativo.service.OrgaoService;
import qualitativo.service.ProgramaService;
import qualitativo.service.UnidadeOrcamentariaService;

@Named
@ViewScoped
public class RelatorioFinalidadeAcaoMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String FAIL_REPORT = "Falha inesperada ao tentar Imprimir Relatório";
	public static final String REPORT_EMPTY = "Não há dados para o programa Selecionado";
	private final static String NO_DATA="Não a dados a serem exibidos";

	
	private Long id;

	private Exercicio exercicio;

	private AcaoService acaoService;
	private ExercicioService exercicioService;
	private UnidadeOrcamentariaService unidadeOrcamentariaService;
	private ProgramaService programaService;

	private String tipoArquivo="PDF";
	
	private Long orgaoId;
	private Long unidadeOrcamentariaId;
	private Long programaId;
	
	
	private List<Orgao> listOrgao;
	private List<UnidadeOrcamentaria> listUnidadeOrcamentaria;
	private List<Programa> listPrograma;
	
	
	@Inject
	public RelatorioFinalidadeAcaoMBean(ExercicioService exercicioService,
									    OrgaoService orgaoService,
									    AcaoService acaoService,
									    UnidadeOrcamentariaService unidadeOrcamentariaService,
									    ProgramaService programaService) {
		
		this.exercicioService = exercicioService;
		this.unidadeOrcamentariaService = unidadeOrcamentariaService;
		this.programaService = programaService;
		this.acaoService = acaoService;
		
		listOrgao = orgaoService.findAllOrderByDescricao();
		
	}

	public void init() {

		if (!Utils.invalidId(id)) {

			exercicio = exercicioService.findById(id);

		}

	}

	public void buscaUnidadeByOrgao() {
		
		listUnidadeOrcamentaria = unidadeOrcamentariaService.buscar(null, null, orgaoId);
	}
	
	public void buscaProgramaByUnidade() {
		
		listPrograma = programaService.buscarPorUnidadeOrcamentaria(unidadeOrcamentariaId);
		
	}
	
	public String gerarRelatorio() {
		
		try {
			
			List<Acao> listAcao = acaoService.relatorio(orgaoId, unidadeOrcamentariaId, programaId, id);
			
			if(listAcao==null || listAcao.isEmpty()) {
				Messages.addMessageWarn(NO_DATA);
				return "";
			}
	
			Map<String, Object> parameters = new HashMap<>();
			
			String brasaoMa = FileUtil.getRealPath("/resources/images/logo-gov-ma.png");
			
			parameters.put("param_imagem", brasaoMa);
			
			String report = FileUtil.getRealPath("/relatorios/relatorio_finalidade_descricao_acao.jasper");
		 	 
			JasperPrint jasperRelatorio = JasperFillManager.fillReport(report, parameters,new JRBeanCollectionDataSource(listAcao));
	
			 if (jasperRelatorio==null || jasperRelatorio.getPages().isEmpty()) {
					Messages.addMessageWarn(REPORT_EMPTY);
					return "";
			}
		
			 byte[] bytes =null;
			 String fileName=null;
			 String contentType=null;
			 
			 if("PDF".equals(tipoArquivo)) {
				 
					bytes = JasperExportManager.exportReportToPdf(jasperRelatorio);
					fileName="RelatorioFinalidadeDescricaoAcao.pdf";
					contentType = TipoArquivo.PDF.getId();
	
					
			 }else {
 
				 ByteArrayOutputStream output = new ByteArrayOutputStream();  
	             JRXlsExporter exporterXLS = new JRXlsExporter(); 
			
	             exporterXLS.setExporterInput(new SimpleExporterInput(jasperRelatorio));            
	             exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
	             
	             SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
	             configuration.setRemoveEmptySpaceBetweenRows(true);
	             configuration.setDetectCellType(true);
	             configuration.setWhitePageBackground(false);
	             exporterXLS.setConfiguration(configuration);
	             exporterXLS.exportReport();    
	             bytes = output.toByteArray();
	             output.close();
	             
	             fileName="RelatorioFinalidadeDescricaoAcao.xls";
				 contentType = TipoArquivo.XLS.getId();
             
			 }	
			
			 
 				
			  FileUtil.sendFileOnResponseAttached(bytes,fileName,contentType);			 
			 
			 
		
		} catch (Exception e) {
			SispcaLogger.logError(e.getCause().getMessage());
	
			Messages.addMessageError(FAIL_REPORT);
		}
	
	
		return "";

	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Exercicio getExercicio() {
		return exercicio;
	}

	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}

	public List<Orgao> getListOrgao() {
		return listOrgao;
	}

	public void setListOrgao(List<Orgao> listOrgao) {
		this.listOrgao = listOrgao;
	}

	public Long getOrgaoId() {
		return orgaoId;
	}

	public void setOrgaoId(Long orgaoId) {
		this.orgaoId = orgaoId;
	}

	public Long getUnidadeOrcamentariaId() {
		return unidadeOrcamentariaId;
	}

	public void setUnidadeOrcamentariaId(Long unidadeOrcamentariaId) {
		this.unidadeOrcamentariaId = unidadeOrcamentariaId;
	}

	public Long getProgramaId() {
		return programaId;
	}

	public void setProgramaId(Long programaId) {
		this.programaId = programaId;
	}

	public List<UnidadeOrcamentaria> getListUnidadeOrcamentaria() {
		return listUnidadeOrcamentaria;
	}

	public void setListUnidadeOrcamentaria(List<UnidadeOrcamentaria> listUnidadeOrcamentaria) {
		this.listUnidadeOrcamentaria = listUnidadeOrcamentaria;
	}

	public List<Programa> getListPrograma() {
		return listPrograma;
	}

	public void setListPrograma(List<Programa> listPrograma) {
		this.listPrograma = listPrograma;
	}

	public String getTipoArquivo() {
		return tipoArquivo;
	}

	public void setTipoArquivo(String tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}

	
	
	
}
