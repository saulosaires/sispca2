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
import qualitativo.model.Acao;
import qualitativo.model.Programa;
import qualitativo.model.UnidadeOrcamentaria;
import qualitativo.service.AcaoService;
import qualitativo.service.ProgramaService;
import qualitativo.service.UnidadeOrcamentariaService;
import siafem.model.FisicoFinanceiroMensalSiafem;
import siafem.service.FisicoFinanceiroMensalSiafemService;

@Named
@ViewScoped
public class RelatorioFisicoFinanceiroProgramaAcaoMBean  extends RelatorioMBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long programa;
	private List<Programa> listPrograma;
	
	private Long unidadeOrcamentaria;
	private List<UnidadeOrcamentaria> listUnidadeOrcamentaria;
	
	private Long acao;
	private List<Acao> listAcao;

	private AcaoService acaoService;
	private FisicoFinanceiroMensalSiafemService fisicoFinanceiroMensalSiafemService;
	
	@Inject
	public RelatorioFisicoFinanceiroProgramaAcaoMBean(ExercicioService exercicioService,
													  AcaoService acaoService,
													  UnidadeOrcamentariaService  unidadeOrcamentariaService,
													  ProgramaService programaService,
													  FisicoFinanceiroMensalSiafemService fisicoFinanceiroMensalSiafemService) {
		super(exercicioService);
		this.acaoService = acaoService;
		this.fisicoFinanceiroMensalSiafemService = fisicoFinanceiroMensalSiafemService;
		
		listUnidadeOrcamentaria = unidadeOrcamentariaService.findAllOrderByDescricao();
		listPrograma = programaService.findAllOrderByDenominacao();
	}

	public void onchangeUnidadeOrcamentaria() {
		
		listAcao = acaoService.buscarByUnidadeOrcamentaria(unidadeOrcamentaria);
	}

	public void gerarRelatorio(){
		

		try {
			
			List<FisicoFinanceiroMensalSiafem> listFisicoFinanceiro = fisicoFinanceiroMensalSiafemService.relatorioExecucaoProgramaAcao(programa, unidadeOrcamentaria, acao, exercicio);
			
			if (listFisicoFinanceiro == null || listFisicoFinanceiro.isEmpty()) {
				Messages.addMessageWarn(NO_DATA);
				 
				return ;
			}
			
			Map<String, Object> parameters = new HashMap<>();
			
			String brasaoMa = FileUtil.getRealPath("/resources/images/brasao_ma.png");
	
			parameters.put("param_imagem", brasaoMa);
				
			if(getExercicio()!=null)
				parameters.put("exercicio", getExercicio().getAno().toString());
			
			String report =  FileUtil.getRealPath("relatorios/financeiro/relatorio_financeiro_prog_uo_acao.jasper");
	 
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
				fileName = "RelatorioFinanceiroProgramaAcao.pdf";
				contentType = TipoArquivo.PDF.getId();
	
			} else {
	
				bytes = RelatorioUtil.exportReportToXLS(jasperRelatorio);
				fileName = "RelatorioFinanceiroProgramaAcao.xls";
				contentType = TipoArquivo.XLS.getId();
	
			}
	
			FileUtil.sendFileOnResponseAttached(bytes, fileName, contentType);

	} catch (Exception e) {
		SispcaLogger.logError(e);

		Messages.addMessageError(FAIL_REPORT);
	}	
	
	
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


	public Long getAcao() {
		return acao;
	}


	public void setAcao(Long acao) {
		this.acao = acao;
	}


	public List<Acao> getListAcao() {
		return listAcao;
	}


	public void setListAcao(List<Acao> listAcao) {
		this.listAcao = listAcao;
	}

	
	
}
