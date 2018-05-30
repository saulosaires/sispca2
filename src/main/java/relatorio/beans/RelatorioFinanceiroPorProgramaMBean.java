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
import siafem.model.FisicoFinanceiroMensalSiafem;
import siafem.service.FisicoFinanceiroMensalSiafemService;

@Named
@ViewScoped
public class RelatorioFinanceiroPorProgramaMBean   extends RelatorioMBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FisicoFinanceiroMensalSiafemService fisicoFinanceiroMensalSiafemService;	
	
	@Inject
	public RelatorioFinanceiroPorProgramaMBean(ExercicioService exercicioService,FisicoFinanceiroMensalSiafemService fisicoFinanceiroMensalSiafemService) {
		super(exercicioService);
		
		this.fisicoFinanceiroMensalSiafemService = fisicoFinanceiroMensalSiafemService;
		 
	}

	public void gerarRelatorio() {
		
		try {
			List<FisicoFinanceiroMensalSiafem> listFisicoFinanceiro = fisicoFinanceiroMensalSiafemService.relatorioFinanceiroPorPrograma(getExercicio().getAno());
			
			
			if (listFisicoFinanceiro == null || listFisicoFinanceiro.isEmpty()) {
				Messages.addMessageWarn(NO_DATA);
				 
				return ;
			}
	
			Map<String, Object> parameters = new HashMap<>();
	
			String brasaoMa = FileUtil.getRealPath("/resources/images/brasao_ma.png");
	
			parameters.put("param_imagem", brasaoMa);
				
			if(getExercicio()!=null)
				parameters.put("exercicio", getExercicio().getAno().toString());
			
			String report = FileUtil.getRealPath("/relatorios/financeiro/relatorio_fisico_financeiro_por_programa.jasper");
	
			JasperPrint jasperRelatorio = JasperFillManager.fillReport(report, parameters,new JRBeanCollectionDataSource(listFisicoFinanceiro));
	
			if (jasperRelatorio == null || jasperRelatorio.getPages().isEmpty()) {
				Messages.addMessageWarn(REPORT_EMPTY);
				return ;
			}
	
			byte[] bytes = null;
			String fileName = null;
			String contentType = null;
	
			if ("PDF".equals(tipoArquivo)) {
	
				bytes = RelatorioUtil.exportReportToPdf(jasperRelatorio);
				fileName = "RelatorioFísicoFinanceiroPorPrograma.pdf";
				contentType = TipoArquivo.PDF.getId();
	
			} else {
	
				bytes = RelatorioUtil.exportReportToXLS(jasperRelatorio);
				fileName = "RelatorioFísicoFinanceiroPorPrograma.xls";
				contentType = TipoArquivo.XLS.getId();
	
			}
	
			FileUtil.sendFileOnResponseAttached(bytes, fileName, contentType);

	} catch (Exception e) {
		SispcaLogger.logError(e);

		Messages.addMessageError(FAIL_REPORT);
	}

		
		
		
		
		
	}
	
}
