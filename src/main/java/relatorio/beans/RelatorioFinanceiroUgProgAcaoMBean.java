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
import qualitativo.model.Programa;
import qualitativo.model.UnidadeGestora;
import qualitativo.model.UnidadeOrcamentaria;
import qualitativo.service.ProgramaService;
import qualitativo.service.UnidadeGestoraService;
import qualitativo.service.UnidadeOrcamentariaService;
import siafem.model.FisicoFinanceiroMensalSiafem;
import siafem.service.FisicoFinanceiroMensalSiafemService;

@Named
@ViewScoped
public class RelatorioFinanceiroUgProgAcaoMBean   extends RelatorioMBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3848197064024824235L;
	private Long unidadeGestora;
	private List<UnidadeGestora>listUnidadeGestora;

	private Long unidadeOrcamentaria;
	private List<UnidadeOrcamentaria>listUnidadeOrcamentaria;

	private Long programa;
	private List<Programa>listPrograma;

	private ProgramaService programaService;
	private FisicoFinanceiroMensalSiafemService fisicoFinanceiroMensalSiafemService;
	
	@Inject
	public RelatorioFinanceiroUgProgAcaoMBean(ExercicioService exercicioService,
											  UnidadeOrcamentariaService  unidadeOrcamentariaService,
											  UnidadeGestoraService unidadeGestoraService,
											  ProgramaService programaService,
											  FisicoFinanceiroMensalSiafemService fisicoFinanceiroMensalSiafemService) {
		super(exercicioService);
		
		this.programaService = programaService;
		this.fisicoFinanceiroMensalSiafemService = fisicoFinanceiroMensalSiafemService;

		Usuario user = (Usuario) SessionUtils.get(SessionUtils.USER);
		
		listUnidadeGestora = unidadeGestoraService.findAllOrderDescricao();
		listUnidadeOrcamentaria = unidadeOrcamentariaService.findAllOrderByDescricao(user.getId());
	}

	public void onChangeUnidade() {
		
		listPrograma = programaService.buscarPorUnidadeOrcamentaria(unidadeOrcamentaria);
	}
	
	public void gerarRelatorio() {
		
		

		try {
			
			List<FisicoFinanceiroMensalSiafem> listFisicoFinanceiro = fisicoFinanceiroMensalSiafemService.relatorioExecucaoUnidade(unidadeGestora, unidadeOrcamentaria, programa, exercicio);
			
			if (listFisicoFinanceiro == null || listFisicoFinanceiro.isEmpty()) {
				Messages.addMessageWarn(NO_DATA);
				 
				return ;
			}
			
			Map<String, Object> parameters = new HashMap<>();
			
			String brasaoMa = FileUtil.getRealPath("/resources/images/brasao_ma.png");
	
			parameters.put("param_imagem", brasaoMa);
				
			if(getExercicio()!=null)
				parameters.put("exercicio", getExercicio().getAno().toString());
			
			String report =  FileUtil.getRealPath("relatorios/financeiro/relatorio_financeiro_ug_prog_acao.jasper");
	 
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
				fileName = "RelatorioMetaFisicoFinanceiro.pdf";
				contentType = TipoArquivo.PDF.getId();
	
			} else {
	
				bytes = RelatorioUtil.exportReportToXLS(jasperRelatorio);
				fileName = "RelatorioMetaFisicoFinanceiro.xls";
				contentType = TipoArquivo.XLS.getId();
	
			}
	
			FileUtil.sendFileOnResponseAttached(bytes, fileName, contentType);

	} catch (Exception e) {
		SispcaLogger.logError(e);

		Messages.addMessageError(FAIL_REPORT);
	}	
	
	
	}

	public Long getUnidadeGestora() {
		return unidadeGestora;
	}

	public void setUnidadeGestora(Long unidadeGestora) {
		this.unidadeGestora = unidadeGestora;
	}

	public List<UnidadeGestora> getListUnidadeGestora() {
		return listUnidadeGestora;
	}

	public void setListUnidadeGestora(List<UnidadeGestora> listUnidadeGestora) {
		this.listUnidadeGestora = listUnidadeGestora;
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

	public Long getPrograma() {
		return programa;
	}

	public void setPrograma(Long programa) {
		this.programa = programa;
	}

	public List<Programa> getListPrograma() {
		return listPrograma;
	}

	public void setListPrograma(List<Programa> listPrograma) {
		this.listPrograma = listPrograma;
	}

	
	
	
	
}
