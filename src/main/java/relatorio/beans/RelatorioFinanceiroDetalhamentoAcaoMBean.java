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
import monitoramento.model.RelatorioDetalhamentoAcaoExecucaoMensal;
import monitoramento.service.ExecucaoService;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import qualitativo.model.Acao;
import qualitativo.model.Mes;
import qualitativo.model.UnidadeOrcamentaria;
import qualitativo.service.AcaoService;
import qualitativo.service.MesService;
import qualitativo.service.UnidadeOrcamentariaService;
import quantitativo.model.RelatorioDetalhamentoAcaoFinanceiroMensal;
import quantitativo.service.FisicoFinanceiroMensalService;
import siafem.model.RelatorioDetalhamentoAcaoSiafem;
import siafem.service.FisicoFinanceiroMensalSiafemService;

@Named
@ViewScoped
public class RelatorioFinanceiroDetalhamentoAcaoMBean  extends RelatorioMBean {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String unidadeOrcamentaria;
	private List<UnidadeOrcamentaria> listUnidadeOrcamentaria;
	
	private Long acao;
	private List<Acao> listAcao;
	
	private List<Mes> meses;
	
	private FisicoFinanceiroMensalSiafemService fisicoFinanceiroMensalSiafemService;
	FisicoFinanceiroMensalService fisicoFinanceiroMensalService;
	private  AcaoService acaoService;
	private ExecucaoService execucaoService;
	
	@Inject
	public RelatorioFinanceiroDetalhamentoAcaoMBean(ExercicioService exercicioService,
												    FisicoFinanceiroMensalSiafemService fisicoFinanceiroMensalSiafemService,
												    UnidadeOrcamentariaService unidadeOrcamentariaService,
												    FisicoFinanceiroMensalService fisicoFinanceiroMensalService,
												    MesService mesService,
												    ExecucaoService execucaoService,
												    AcaoService acaoService) {
		super(exercicioService);

		Usuario user = (Usuario) SessionUtils.get(SessionUtils.USER);
		
		listUnidadeOrcamentaria = unidadeOrcamentariaService.findAllOrderByDescricao(user.getId());
		
		meses =mesService.findAll();
		
		this.fisicoFinanceiroMensalSiafemService = fisicoFinanceiroMensalSiafemService;
		this.fisicoFinanceiroMensalService = fisicoFinanceiroMensalService;
		this.acaoService = acaoService;
		this.execucaoService = execucaoService;
		
	}
	
	
	public void buscaAcoesByUnidade() {
		
		listAcao = acaoService.buscar(null, unidadeOrcamentaria, null, null);
		
	}

    public String gerarRelatorio() {
    	

    	try {
    		
    		Acao ac = acaoService.findById(acao);
    		
        	RelatorioDetalhamentoAcaoSiafem relatorioDetalhamentoAcaoSiafem = fisicoFinanceiroMensalSiafemService.relatorioDetalhamentoAcao(meses,unidadeOrcamentaria, acao, getExercicio().getAno());
				
        	RelatorioDetalhamentoAcaoFinanceiroMensal planejado  = fisicoFinanceiroMensalService.calculaPlanejamentoMensalByMesAndExercicioAndAcao(meses, exercicio.getId(), acao);
        	
        	RelatorioDetalhamentoAcaoExecucaoMensal executado = execucaoService.calculaPlanejamentoMensalByMesAndExercicioAndAcao(meses, exercicio.getId(), acao);
        	
        	
			Map<String, Object> parameters = new HashMap<>();
	
			String brasaoMa = FileUtil.getRealPath("/resources/images/brasao_ma.png");
	
			parameters.put("param_imagem", brasaoMa);
			parameters.put("exercicio", getExercicio().getAno().toString());
			
			parameters.put("acao",ac);
					
			parameters.put("dotacalInicial", relatorioDetalhamentoAcaoSiafem.getDotacalInicial());
			parameters.put("empenhado", relatorioDetalhamentoAcaoSiafem.getEmpenhado());
			parameters.put("liquidado", relatorioDetalhamentoAcaoSiafem.getLiquidado());
			parameters.put("disponivel", relatorioDetalhamentoAcaoSiafem.getDisponivel());

			parameters.put("planejado", planejado);
			parameters.put("executado", executado);

			
			String report = FileUtil.getRealPath("/relatorios/financeiro/relatorio_financeiro_detalhamento_acao.jasper");
				
			JasperPrint jasperRelatorio = JasperFillManager.fillReport(report, parameters,new JREmptyDataSource());
	
			if (jasperRelatorio == null || jasperRelatorio.getPages().isEmpty()) {
				Messages.addMessageWarn(REPORT_EMPTY);
				return "";
			}
	
			byte[] bytes = null;
			String fileName = null;
			String contentType = null;
	
			if ("PDF".equals(tipoArquivo)) {
	
				bytes = RelatorioUtil.exportReportToPdf(jasperRelatorio);
				fileName = "RelatorioFinanceiroDetalhamentoAcao.pdf";
				contentType = TipoArquivo.PDF.getId();
	
			} else {
	
				bytes = RelatorioUtil.exportReportToXLS(jasperRelatorio);
				fileName = "RelatorioFinanceiroDetalhamentoAcao.xls";
				contentType = TipoArquivo.XLS.getId();
	
			}
	
			FileUtil.sendFileOnResponseAttached(bytes, fileName, contentType);
	
		} catch (Exception e) {
			SispcaLogger.logError(e);
	
			Messages.addMessageError(FAIL_REPORT);
		}

		return "";
    }
	
	
	public String getUnidadeOrcamentaria() {
		return unidadeOrcamentaria;
	}


	public void setUnidadeOrcamentaria(String unidadeOrcamentaria) {
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
