package relatorio.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.model.Usuario;
import administrativo.service.ExercicioService;
import arquitetura.enums.TipoArquivo;
import arquitetura.utils.FileUtil;
import arquitetura.utils.Messages;
import arquitetura.utils.RelatorioUtil;
import arquitetura.utils.SessionUtils;
import arquitetura.utils.SispcaLogger;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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
public class RelatorioFinalidadeAcaoMBean extends RelatorioMBean {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AcaoService acaoService;
 
	private UnidadeOrcamentariaService unidadeOrcamentariaService;
	private ProgramaService programaService;

	
	private Long orgaoId;
	private Long unidadeOrcamentariaId;
	private Long programaId;
	
	
	private List<Orgao> listOrgao;
	private List<UnidadeOrcamentaria> listUnidadeOrcamentaria;
	private List<Programa> listPrograma;
	
	private Long userId;
	
	@Inject
	public RelatorioFinalidadeAcaoMBean(ExercicioService exercicioService,
									    OrgaoService orgaoService,
									    AcaoService acaoService,
									    UnidadeOrcamentariaService unidadeOrcamentariaService,
									    ProgramaService programaService) {
		
		super(exercicioService);
		
	 
		this.unidadeOrcamentariaService = unidadeOrcamentariaService;
		this.programaService = programaService;
		this.acaoService = acaoService;
		
		listOrgao = orgaoService.findAllOrderByDescricao();
		
		Usuario user = (Usuario) SessionUtils.get(SessionUtils.USER);
		
		userId = user.getId();
		
	}

 

	public void buscaUnidadeByOrgao() {
		
		listUnidadeOrcamentaria = unidadeOrcamentariaService.buscar(userId,null, null, orgaoId);
	}
	
	public void buscaProgramaByUnidade() {
		
		listPrograma = programaService.buscarPorUnidadeOrcamentaria(unidadeOrcamentariaId);
		
	}
	
	public String gerarRelatorio() {
		
		try {
			
			List<Acao> listAcao = acaoService.relatorioFinalidade(orgaoId, unidadeOrcamentariaId, programaId, exercicioId);
			
			if(listAcao==null || listAcao.isEmpty()) {
				Messages.addMessageWarn(NO_DATA);
				return "";
			}
	
			Map<String, Object> parameters = new HashMap<>();
			
			String brasaoMa = FileUtil.getRealPath("/resources/images/brasao_ma.png");
			
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
				 
				  bytes = RelatorioUtil.exportReportToPdf(jasperRelatorio);
				  fileName="RelatorioFinalidadeDescricaoAcao.pdf";
				  contentType = TipoArquivo.PDF.getId();
					
			 }else {
 
				 bytes =RelatorioUtil.exportReportToXLS(jasperRelatorio);
	             fileName="RelatorioFinalidadeDescricaoAcao.xls";
				 contentType = TipoArquivo.XLS.getId();
             
			 }	
 				
			  FileUtil.sendFileOnResponseAttached(bytes,fileName,contentType);			 
			 		 
		
		} catch (Exception e) {
			SispcaLogger.logError(e);
	
			Messages.addMessageError(FAIL_REPORT);
		}
	
	
		return "";

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

	
	
	
	
}
