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
import qualitativo.model.UnidadeGestora;
import qualitativo.model.UnidadeOrcamentaria;
import qualitativo.service.AcaoService;
import qualitativo.service.UnidadeGestoraService;
import qualitativo.service.UnidadeOrcamentariaService;
import siafem.enums.NaturezaDespeza;
import siafem.model.FisicoFinanceiroMensalSiafem;
import siafem.service.FisicoFinanceiroMensalSiafemService;

@Named
@ViewScoped
public class RelatorioFinanceiroNaturezaDespesaMBean extends RelatorioMBean {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long unidadeGestora;
	private List<UnidadeGestora> listUnidadeGestora;
	
	private Long unidadeOrcamentaria;
	private List<UnidadeOrcamentaria> listUnidadeOrcamentaria;
	
	private Long acao;
	private List<Acao> listAcao;
	
	private NaturezaDespeza naturezaDespeza;
	private NaturezaDespeza[] naturezaDespezaValues;
	
	private AcaoService acaoService; 
	private FisicoFinanceiroMensalSiafemService fisicoFinanceiroMensalSiafemService;
	 
	@Inject
	public RelatorioFinanceiroNaturezaDespesaMBean(ExercicioService exercicioService,
												   AcaoService acaoService,
												   FisicoFinanceiroMensalSiafemService fisicoFinanceiroMensalSiafemService,
												   UnidadeGestoraService unidadeGestoraService, 
												   UnidadeOrcamentariaService unidadeOrcamentariaService) {
		super(exercicioService);

		naturezaDespezaValues = NaturezaDespeza.values();
		 
		Usuario user = (Usuario) SessionUtils.get(SessionUtils.USER);
		
		listUnidadeGestora = unidadeGestoraService.findAllOrderDescricao();
		listUnidadeOrcamentaria =  unidadeOrcamentariaService.findAllOrderByDescricao(user.getId());
		
		this.acaoService = acaoService;
		this.fisicoFinanceiroMensalSiafemService = fisicoFinanceiroMensalSiafemService;
		
	}
	
	
	
	public void buscaAcoesByUnidade() {
		
		listAcao = acaoService.buscarByUnidadeOrcamentaria(unidadeOrcamentaria,exercicioId);
		
	}

	public void gerarRelatorio() {
		
		try {
		 
		
			   List<FisicoFinanceiroMensalSiafem> listFisicoFinanceiro = fisicoFinanceiroMensalSiafemService.relatorioFinanceiroNaturezaDespesa(
																																				 unidadeGestora, 
																																				 unidadeOrcamentaria, 
																																				 acao, 
																																				 naturezaDespeza, 
																																				 getExercicio().getAno()
																																				 );
			   
		
			   List<FisicoFinanceiroMensalSiafem> listTotalNatureza = fisicoFinanceiroMensalSiafemService.totalPorNaturezaDespesa(
																																  unidadeGestora, 
																																  unidadeOrcamentaria, 
																																  acao, 
																																  naturezaDespeza, 
																																  getExercicio().getAno()
																																  );
			   
			   
				if (listFisicoFinanceiro == null || listFisicoFinanceiro.isEmpty()) {
					Messages.addMessageWarn(NO_DATA);
					 
					return ;
				}
		
				Map<String, Object> parameters = new HashMap<>();
		
				String brasaoMa = FileUtil.getRealPath("/resources/images/brasao_ma.png");
		
				parameters.put("param_imagem", brasaoMa);
				parameters.put("totalNatureza", listTotalNatureza);
				
				if(getExercicio()!=null)
					parameters.put("exercicio", getExercicio().getAno().toString());
				
				String report = FileUtil.getRealPath("/relatorios/financeiro/relatorio_financeiro_natureza_despesa.jasper");
					
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
					fileName = "RelatorioFisicoFinanceiroPlanejadoGeral.pdf";
					contentType = TipoArquivo.PDF.getId();
		
				} else {
		
					bytes = RelatorioUtil.exportReportToXLS(jasperRelatorio);
					fileName = "RelatorioFisicoFinanceiroPlanejadoGeral.xls";
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



	public NaturezaDespeza getNaturezaDespeza() {
		return naturezaDespeza;
	}



	public void setNaturezaDespeza(NaturezaDespeza naturezaDespeza) {
		this.naturezaDespeza = naturezaDespeza;
	}



	public NaturezaDespeza[] getNaturezaDespezaValues() {
		return naturezaDespezaValues;
	}



	public void setNaturezaDespezaValues(NaturezaDespeza[] naturezaDespezaValues) {
		this.naturezaDespezaValues = naturezaDespezaValues;
	}



	 

	
	
	
}
