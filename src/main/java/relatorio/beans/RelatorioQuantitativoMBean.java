package relatorio.beans;

import java.util.ArrayList;
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
import arquitetura.utils.Utils;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import qualitativo.model.Acao;
import qualitativo.model.UnidadeOrcamentaria;
import qualitativo.service.AcaoService;
import qualitativo.service.UnidadeOrcamentariaService;
import quantitativo.model.FisicoFinanceiro;
import quantitativo.service.FisicoFinanceiroService;
import relatorio.model.RelatorioQuantitativoAnual;

@Named
@ViewScoped
public class RelatorioQuantitativoMBean extends RelatorioMBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long unidadeOrcamentaria;
	private List<UnidadeOrcamentaria> listUnidadeOrcamentaria;
	
	private Long acao;
	private List<Acao> listAcoes;
	
	private FisicoFinanceiroService fisicoFinanceiroService;
	private UnidadeOrcamentariaService orcamentariaService;
	private AcaoService acaoService;
	
	@Inject
	public RelatorioQuantitativoMBean(ExercicioService exercicioService,
									  UnidadeOrcamentariaService orcamentariaService,
									  FisicoFinanceiroService fisicoFinanceiroService,
									  AcaoService acaoService) {
		super(exercicioService);
		
		Usuario user = (Usuario) SessionUtils.get(SessionUtils.USER);
		
		listUnidadeOrcamentaria = orcamentariaService.findAllOrderByDescricao(user.getId());
		
		this.acaoService = acaoService;
		this.fisicoFinanceiroService = fisicoFinanceiroService;
		this.orcamentariaService = orcamentariaService;
		
	}
	
	public void changeUnidade() {
		
		listAcoes = acaoService.buscarByUnidadeOrcamentaria(unidadeOrcamentaria);
		acao=null;
	}

	public String gerarRelatorio() {
		
		try {
			List<Acao> acoes;
			
			if(Utils.invalidId(acao)) {
				acoes = listAcoes;
			}else {
				acoes = new ArrayList<>();
				
				acoes.add(acaoService.findById(acao));
			}
			
			 
			
			List<RelatorioQuantitativoAnual> listRelatorioQuantitativoAnual = fisicoFinanceiroService.relatorioQuantitativoAnual(acoes,getExercicio().getPpa().getId());
	
			if (listRelatorioQuantitativoAnual == null || listRelatorioQuantitativoAnual.isEmpty()) {
				Messages.addMessageWarn(NO_DATA);
				return "";
			}
	
			List<FisicoFinanceiro> listTotal = fisicoFinanceiroService.totalPorUnidadeOrcamentaria(unidadeOrcamentaria, getExercicio().getPpa().getId());
			
			Map<String, Object> parameters = new HashMap<>();
	
			String brasaoMa = FileUtil.getRealPath("/resources/images/brasao_ma.png");
	
			UnidadeOrcamentaria unidade  = orcamentariaService.findById(unidadeOrcamentaria);
			
			parameters.put("MARANHAO_BRASAO", brasaoMa);
			parameters.put("totalUnidadeOrcamentaria", listTotal);
			parameters.put("unidadeOrcamentaria", unidade.getCodigo() + " - " + unidade.getDescricao());
			
			String report = FileUtil.getRealPath("/relatorios/quantitativo/relatorio_quantitativo.jasper");
			 
		 
			JasperPrint jasperRelatorio = JasperFillManager.fillReport(report, parameters,new JRBeanCollectionDataSource(listRelatorioQuantitativoAnual));
	
			if (jasperRelatorio == null || jasperRelatorio.getPages().isEmpty()) {
				Messages.addMessageWarn(REPORT_EMPTY);
				return "";
			}
	
			byte[] bytes = null;
			String fileName = null;
			String contentType = null;
	 
	
			bytes = RelatorioUtil.exportReportToPdf(jasperRelatorio);
			fileName = "relatorioQuantitativoAnual.pdf";
			contentType = TipoArquivo.PDF.getId();

	
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

	public Long getAcao() {
		return acao;
	}

	public void setAcao(Long acao) {
		this.acao = acao;
	}

	public List<Acao> getListAcoes() {
		return listAcoes;
	}

	public void setListAcoes(List<Acao> listAcoes) {
		this.listAcoes = listAcoes;
	}
	
	
	

}
