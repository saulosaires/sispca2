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
import qualitativo.model.UnidadeOrcamentaria;
import qualitativo.service.UnidadeOrcamentariaService;
import quantitativo.model.FisicoFinanceiro;
import quantitativo.service.FisicoFinanceiroService;

@Named
@ViewScoped
public class RelatorioFisicoFinanceiroMBean extends RelatorioMBean {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Long unidadeOrcamentaria;
	private List<UnidadeOrcamentaria> listUnidadeOrcamentaria;
	
	private FisicoFinanceiroService fisicoFinanceiroService;
	
	@Inject
	public RelatorioFisicoFinanceiroMBean(ExercicioService exercicioService,
										  FisicoFinanceiroService fisicoFinanceiroService,
			 							  UnidadeOrcamentariaService orcamentariaService) {
		super(exercicioService);
		
		Usuario user = (Usuario) SessionUtils.get(SessionUtils.USER);
		
		this.fisicoFinanceiroService = fisicoFinanceiroService;
		
		listUnidadeOrcamentaria = orcamentariaService.findAllOrderByDescricao(user.getId());
	}


	public String gerarRelatorio() {
				
		   try {
			    List<FisicoFinanceiro> listFisicoFinanceiro = fisicoFinanceiroService.relatorioFisicoFinanceiro(unidadeOrcamentaria, getExercicio().getPpa().getId());
		
				if (listFisicoFinanceiro == null || listFisicoFinanceiro.isEmpty()) {
					Messages.addMessageWarn(NO_DATA);
					return "";
				}
 				
				Map<String, Object> parameters = new HashMap<>();
		
				String brasaoMa = FileUtil.getRealPath("/resources/images/brasao_ma.png");
 				
				parameters.put("param_imagem", brasaoMa);
				parameters.put("ppa", getExercicio().getPpa().getDescricao());
 				
				String report = FileUtil.getRealPath("/relatorios/quantitativo/relatorio_fisico_financeiro.jasper");
				 
			 
				JasperPrint jasperRelatorio = JasperFillManager.fillReport(report, parameters,new JRBeanCollectionDataSource(listFisicoFinanceiro));
		
				if (jasperRelatorio == null || jasperRelatorio.getPages().isEmpty()) {
					Messages.addMessageWarn(REPORT_EMPTY);
					return "";
				}
		
				byte[] bytes = null;
				String fileName = null;
				String contentType = null;
		 
		
				if("PDF".equals(tipoArquivo)) {
					 
					 bytes = RelatorioUtil.exportReportToPdf(jasperRelatorio);
					 fileName="RelatorioFisicoFinanceiroPlanejado.pdf";
					 contentType = TipoArquivo.PDF.getId();

				 }else {
  
		             bytes =RelatorioUtil.exportReportToXLS(jasperRelatorio);
		             fileName="RelatorioFisicoFinanceiroPlanejado.xls";
					 contentType = TipoArquivo.XLS.getId();
		         
				 }	

		
				FileUtil.sendFileOnResponseAttached(bytes, fileName, contentType);
		
			} catch (Exception e) {
				SispcaLogger.logError(e);
		
				Messages.addMessageError(FAIL_REPORT);
			}
		
			return "";


	}


	public Long getUnidadeOrcamentaria() {
		return unidadeOrcamentaria;
	}


	public void setUnidadeOrcamentaria(Long unidadeOrcamentaria) {
		this.unidadeOrcamentaria = unidadeOrcamentaria;
	}


	public List<UnidadeOrcamentaria> getListUnidadeOrcamentaria() {
		return listUnidadeOrcamentaria;
	}


	public void setListUnidadeOrcamentaria(List<UnidadeOrcamentaria> listUnidadeOrcamentaria) {
		this.listUnidadeOrcamentaria = listUnidadeOrcamentaria;
	}
	
	
	
	
}
