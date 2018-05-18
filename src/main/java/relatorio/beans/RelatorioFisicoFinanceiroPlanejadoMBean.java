package relatorio.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.service.ExercicioService;
import arquitetura.enums.TipoArquivo;
import arquitetura.utils.FileUtil;
import arquitetura.utils.Messages;
import arquitetura.utils.RelatorioUtil;
import arquitetura.utils.SispcaLogger;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import qualitativo.model.UnidadeOrcamentaria;
import qualitativo.service.UnidadeOrcamentariaService;
import quantitativo.model.FisicoFinanceiro;
import quantitativo.model.Regiao;
import quantitativo.service.FisicoFinanceiroService;
import quantitativo.service.RegiaoService;

@Named
@ViewScoped
public class RelatorioFisicoFinanceiroPlanejadoMBean  extends RelatorioMBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5123217511875529450L;
	private Long unidadeOrcamentaria;
	private List<UnidadeOrcamentaria> listUnidadeOrcamentaria;
	
	private Long regiao;
	private List<Regiao> listRegiao;
	
	private FisicoFinanceiroService fisicoFinanceiroService;
	
	@Inject
	public RelatorioFisicoFinanceiroPlanejadoMBean(ExercicioService exercicioService,
												   RegiaoService regiaoService,
												   FisicoFinanceiroService fisicoFinanceiroService,
												   UnidadeOrcamentariaService unidadeOrcamentariaService) {
		super(exercicioService);
	 
		this.fisicoFinanceiroService = fisicoFinanceiroService;
		
		listUnidadeOrcamentaria = unidadeOrcamentariaService.findAllOrderByDescricao();
		
		listRegiao = regiaoService.findAllOrderByDescricao();
	}
	
	
	public String gerarRelatorio(){
		
		
		try {
			
		
				List<FisicoFinanceiro> listFisicoFinanceiro = fisicoFinanceiroService.relatorioPlanejadoPorRegiao(regiao,unidadeOrcamentaria, exercicioId);
				
				if (listFisicoFinanceiro == null || listFisicoFinanceiro.isEmpty()) {
					Messages.addMessageWarn(NO_DATA);
					return "";
				}
		
				Map<String, Object> parameters = new HashMap<>();
		
				String brasaoMa = FileUtil.getRealPath("/resources/images/brasao_ma.png");
		
				parameters.put("param_imagem", brasaoMa);
		
				String report = FileUtil.getRealPath("/relatorios/financeiro/relatorio_planejado_financeiro.jasper");
				 
				
				JasperPrint jasperRelatorio = JasperFillManager.fillReport(report, parameters,new JRBeanCollectionDataSource(listFisicoFinanceiro));
		
				if (jasperRelatorio == null || jasperRelatorio.getPages().isEmpty()) {
					Messages.addMessageWarn(REPORT_EMPTY);
					return "";
				}
		
				byte[] bytes = null;
				String fileName = null;
				String contentType = null;
		
				if ("PDF".equals(tipoArquivo)) {
		
					bytes = RelatorioUtil.exportReportToPdf(jasperRelatorio);
					fileName = "RelatorioPlanejadoFinanceiro.pdf";
					contentType = TipoArquivo.PDF.getId();
		
				} else {
		
					bytes = RelatorioUtil.exportReportToXLS(jasperRelatorio);
					fileName = "RelatorioPlanejadoFinanceiro.xls";
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


	public Long getRegiao() {
		return regiao;
	}


	public void setRegiao(Long regiao) {
		this.regiao = regiao;
	}


	public List<Regiao> getListRegiao() {
		return listRegiao;
	}


	public void setListRegiao(List<Regiao> listRegiao) {
		this.listRegiao = listRegiao;
	}
	
	
}
