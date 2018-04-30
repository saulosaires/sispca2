package avaliacao.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.StreamedContent;

import administrativo.service.ExercicioService;
import arquitetura.enums.TipoArquivo;
import arquitetura.utils.FileUtil;
import arquitetura.utils.Messages;
import arquitetura.utils.PrimeFacesUtils;
import arquitetura.utils.SispcaLogger;
import avaliacao.service.AvaliacaoFisicoFinanceiraService;
import avaliacao.service.DiretrizAssociadaService;
import avaliacao.service.PainelAssociadoService;
import avaliacao.service.ResultadoService;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import qualitativo.service.ProgramaService;
import siafem.service.FisicoFinanceiroMensalSiafemService;

@Named
@SessionScoped
public class AvaliacaoProgramaRelatorioMBean extends AvaliacaoPrograma implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 
	public static final String FAIL_REPORT = "Falha inesperada ao tentar Imprimir Relatório";
	public static final String REPORT_EMPTY = "Não há dados para o programa Selecionado";

	
	private AvaliacaoFisicoFinanceiraService avaliacaoFisicoFinanceiraService; 
	private DiretrizAssociadaService diretrizAssociadaService;
	private PainelAssociadoService painelAssociadoService;

	@Inject
	public AvaliacaoProgramaRelatorioMBean(ProgramaService programaService,
										   ExercicioService exercicioService,
										   AvaliacaoFisicoFinanceiraService avaliacaoFisicoFinanceiraService,
										   DiretrizAssociadaService diretrizAssociadaService,
										   PainelAssociadoService painelAssociadoService,
										   FisicoFinanceiroMensalSiafemService fisicoFinanceiroMensalSiafemService,
										  	
										   ResultadoService resultadoService) {

		super(programaService,exercicioService,fisicoFinanceiroMensalSiafemService);
 
		this.avaliacaoFisicoFinanceiraService=avaliacaoFisicoFinanceiraService;
		this.diretrizAssociadaService=diretrizAssociadaService;
		this.painelAssociadoService=painelAssociadoService;
		
	}

	 
	public StreamedContent getFile() {
		
		try {
			
		//	List<FisicoFinanceiroMensalSiafem> listFisicoFinanceiroMensalSiafem = super.fisicoFinanceiroMensalSiafemService.analiseFisicoFinanceiro(getPrograma(), getExercicio());

	//		BigDecimal mediaEficaciaFisicoFinanceira   = fisicoFinanceiroMensalSiafemService.calculaMediaEficaciaAvaliacaoFisicoFinanceira(listFisicoFinanceiroMensalSiafem);
	//		BigDecimal mediaEficienciaFisicoFinanceira = fisicoFinanceiroMensalSiafemService.calculaEficienciaMediaAvaliacaoFisicoFinanceira(listFisicoFinanceiroMensalSiafem);

			BigDecimal empenhado = fisicoFinanceiroMensalSiafemService.calculaEmpenhadoByProgAndAno(getPrograma().getCodigo(), getExercicio().getAno());
			BigDecimal liquidado = fisicoFinanceiroMensalSiafemService.calculaLiquidadoByProgAndAno(getPrograma().getCodigo(), getExercicio().getAno());
			BigDecimal pago 	 = fisicoFinanceiroMensalSiafemService.calculaPagoByProgAndAno(getPrograma().getCodigo(), getExercicio().getAno());
			
			List<String> diretrizes = diretrizAssociadaService.findByProgramaAndExercicio(getPrograma().getId(), getExercicio().getId())
																		   				    .stream().map(p -> p.getDiretriz().getDescricao()).collect(Collectors.toList());

			
			List<String> painelAssociado = painelAssociadoService.findByProgramaAndExercicio(getPrograma().getId(), getExercicio().getId())
																.stream().map(p -> p.getIndicador().getIndicador()).collect(Collectors.toList());
			
			
			Map<String, Object> parameters = new HashMap<>();

			String brasaoMa = FileUtil.getRealPath("/resources/images/logo-gov-ma.png");
 
			parameters.put("param_imagem", brasaoMa);
			parameters.put("param_ano", getExercicio().getAno());
			 
			parameters.put("param_id_exerc",getExercicio().getId());
			parameters.put("param_ppa",getExercicio().getPpa().getDescricao());
			
			parameters.put("param_id_prog", 		  getPrograma().getId());
			parameters.put("param_cod_prog", 		  getPrograma().getCodigo());
			parameters.put("param_prog", 			  getPrograma().getDenominacao());
			parameters.put("param_unidade_executora", getPrograma().getUnidadeOrcamentaria().getDescricao());
			parameters.put("param_problema", 		  getPrograma().getProblema());
			parameters.put("param_objetivo", 		  getPrograma().getObjetivo());
			parameters.put("param_publico_alvo", 	  getPrograma().getPublicoAlvo());
			parameters.put("param_cod_uo",			  getPrograma().getUnidadeOrcamentaria().getCodigo());
  			
			parameters.put("param_dot_inicial", dotacaoInicial);
			parameters.put("param_dot_atual", dotacaoAtual);
			parameters.put("param_variacao", variacao);
			parameters.put("param_dot_empenhado", empenhado);
			parameters.put("param_dot_liquidado", liquidado);
			parameters.put("param_dot_pago", pago);
			
			parameters.put("diretrizes", diretrizes);
			
			parameters.put("painelAssociado", painelAssociado);
			
//			parameters.put("listFisicoFinanceiroMensalSiafem", listFisicoFinanceiroMensalSiafem);
//			parameters.put("param_media_eficacia_financ", mediaEficaciaFisicoFinanceira);
//			parameters.put("param_media_eficiencia_financ", mediaEficienciaFisicoFinanceira);
			
			
			String report = FileUtil.getRealPath("/relatorios/avaliacao/relatorio_avaliacao_programa.jasper");
 			
			
			JasperPrint jasperRelatorio = JasperFillManager.fillReport(report, parameters,new JREmptyDataSource());

			if (jasperRelatorio.getPages().isEmpty()) {
				Messages.addMessageWarn(REPORT_EMPTY);
				return null;
			}

			byte[] bytes = JasperExportManager.exportReportToPdf(jasperRelatorio);

			return PrimeFacesUtils.converBytesToStreamedContent(bytes, TipoArquivo.PDF.getId(),"Relatorio_Financeiro_Detalhamento.pdf");
			
			
		} catch (Exception e) {

			SispcaLogger.logError(e.getCause().getMessage());

			Messages.addMessageError(FAIL_REPORT);
		}

		return null;
			
	}
  

	 
	
	
	

}
