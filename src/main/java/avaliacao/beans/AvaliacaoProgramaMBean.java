package avaliacao.beans;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

import administrativo.model.Exercicio;
import administrativo.service.ExercicioService;
import arquitetura.enums.TipoArquivo;
import arquitetura.utils.FileUtil;
import arquitetura.utils.MathUtils;
import arquitetura.utils.Messages;
import arquitetura.utils.SessionUtils;
import arquitetura.utils.SispcaLogger;
import avaliacao.model.Analise;
import avaliacao.model.AvaliacaoFisicoFinanceira;
import avaliacao.model.IndicadorDesempenhoIntermediario;
import avaliacao.model.Recomendacao;
import avaliacao.model.Resultado;
import avaliacao.service.AnaliseService;
import avaliacao.service.AvaliacaoFisicoFinanceiraService;
import avaliacao.service.DiretrizAssociadaService;
import avaliacao.service.IndicadorDesempenhoIntermediarioService;
import avaliacao.service.PainelAssociadoService;
import avaliacao.service.RecomendacaoService;
import avaliacao.service.ResultadoService;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import qualitativo.model.Eixo;
import qualitativo.model.Programa;
import qualitativo.service.EixoService;
import qualitativo.service.ProgramaService;
import siafem.model.FisicoFinanceiroMensalSiafem;
import siafem.service.FisicoFinanceiroMensalSiafemService;

@Named
@ViewScoped
public class AvaliacaoProgramaMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String FAIL_REPORT = "Falha inesperada ao tentar Imprimir Relatório";
	public static final String REPORT_EMPTY = "Não há dados para o programa Selecionado";
	
	private List<Programa> listPrograma;
	
	private Integer pages;
	
	private Long eixoId;
	private List<Eixo> listEixos;
	
	private boolean editarAvaliacao = false;
	
	private String codigoPrograma;
	private Exercicio exercicio ;
	
	private ProgramaService programaService; 
	private AvaliacaoFisicoFinanceiraService avaliacaoFisicoFinanceiraService; 
	private IndicadorDesempenhoIntermediarioService indicadorDesempenhoIntermediarioService;
	private DiretrizAssociadaService diretrizAssociadaService;
	private PainelAssociadoService painelAssociadoService;
	private AnaliseService analiseService; 
	private ResultadoService resultadoService;
	private RecomendacaoService recomendacaoService;
	private FisicoFinanceiroMensalSiafemService fisicoFinanceiroMensalSiafemService;

	private boolean avaliacaoSetorialProgramaAnalise;
	private boolean avaliacaoSetorialProgramaDiretrizAssociada;
	private boolean avaliacaoSetorialProgramaFisicoFinanceiro;
	private boolean avaliacaoSetorialProgramaIntermediario;
	private boolean avaliacaoSetorialProgramaPainelAssociado;
	private boolean avaliacaoSetorialProgramaRecomendacao;
	private boolean avaliacaoSetorialProgramaResultado;
	private boolean avaliacaoSetorialRelatorio;

	@Inject
	public AvaliacaoProgramaMBean( ProgramaService programaService,
								   ExercicioService exercicioService,
								   AvaliacaoFisicoFinanceiraService avaliacaoFisicoFinanceiraService,
								   DiretrizAssociadaService diretrizAssociadaService,
								   PainelAssociadoService painelAssociadoService,
								   AnaliseService analiseService, 
								   IndicadorDesempenhoIntermediarioService indicadorDesempenhoIntermediarioService,
								   FisicoFinanceiroMensalSiafemService fisicoFinanceiroMensalSiafemService,
								   RecomendacaoService recomendacaoService,
								   ResultadoService resultadoService,
								   EixoService eixoService) {

		this.programaService					    = programaService; 
		this.avaliacaoFisicoFinanceiraService		= avaliacaoFisicoFinanceiraService;
		this.diretrizAssociadaService				= diretrizAssociadaService;
		this.painelAssociadoService					= painelAssociadoService;
		this.analiseService							= analiseService;
		this.resultadoService					    = resultadoService;
		this.recomendacaoService				    = recomendacaoService;
		this.indicadorDesempenhoIntermediarioService= indicadorDesempenhoIntermediarioService;
		this.fisicoFinanceiroMensalSiafemService    = fisicoFinanceiroMensalSiafemService;
		
		listEixos = eixoService.findAllOrderByDecricao();
		
		Optional<Exercicio> exercicioAnterior = exercicioService.exercicioAnterior();
		
		if(exercicioAnterior.isPresent()) {
			
			   exercicio = exercicioAnterior.get();
		}
		
		buscarProgramas();
		
		
		avaliacaoSetorialProgramaDiretrizAssociada = SessionUtils.containsKey("avaliacaoSetorialProgramaDiretrizAssociada");
		avaliacaoSetorialProgramaPainelAssociado   = SessionUtils.containsKey("avaliacaoSetorialProgramaPainelAssociado");
		avaliacaoSetorialProgramaIntermediario     = SessionUtils.containsKey("avaliacaoSetorialProgramaIntermediario");
		avaliacaoSetorialProgramaAnalise 		   = SessionUtils.containsKey("avaliacaoSetorialProgramaAnalise");
		avaliacaoSetorialProgramaFisicoFinanceiro  = SessionUtils.containsKey("avaliacaoSetorialProgramaFisicoFinanceiro");
		avaliacaoSetorialProgramaRecomendacao      = SessionUtils.containsKey("avaliacaoSetorialProgramaRecomendacao");
		avaliacaoSetorialProgramaResultado         = SessionUtils.containsKey("avaliacaoSetorialProgramaResultado");
		avaliacaoSetorialRelatorio                 = SessionUtils.containsKey("avaliacaoSetorialRelatorio");
			
		
	}

	public void buscarProgramas() {
		
		this.listPrograma = programaService.buscar(codigoPrograma, null, null, null, exercicio.getId(),eixoId);
 	
	}
 	
	public void imprimirTodos() {
		
		try {
			
			if(pages==null) {
				pages=1;
			}
			
			List<JasperPrint> jasperPrints = new ArrayList<>();
			
			int page= pages;
			for(Programa programa: listPrograma) {
				
				JasperPrint jasperRelatorio = buildReport( programa,page) ;
				
				if(jasperRelatorio!=null)
					page+=jasperRelatorio.getPages().size();
				
				jasperPrints.add(jasperRelatorio);
			}
		
		 
			
			JRPdfExporter exporter = new JRPdfExporter();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrints)); 
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));  
			SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
			configuration.setCreatingBatchModeBookmarks(true);  
			exporter.setConfiguration(configuration);
			exporter.exportReport();
			byte[] bytes = out.toByteArray();
			

			FileUtil.sendFileOnResponse(bytes,"Relatorio_Financeiro_Detalhamento.pdf", TipoArquivo.PDF.getId(),"");
		} catch (Exception e) {
			
			SispcaLogger.logError(e.getCause().getMessage());
			 
			
			Messages.addMessageError(FAIL_REPORT);
		}
			
		
	}
	
	public void gerarRelatorio(Programa programa) {
		
		try {
			
			JasperPrint jasperRelatorio = buildReport( programa,1) ;
			
			 if (jasperRelatorio==null || jasperRelatorio.getPages().isEmpty()) {
					Messages.addMessageWarn(REPORT_EMPTY);
					return ;
				}

				byte[] bytes = JasperExportManager.exportReportToPdf(jasperRelatorio);

			 
				
				FileUtil.sendFileOnResponse(bytes,"Relatorio_Financeiro_Detalhamento.pdf", TipoArquivo.PDF.getId(),"");
				
			 
		} catch (Exception e) {

			SispcaLogger.logError(e.getCause().getMessage());

			Messages.addMessageError(FAIL_REPORT);
		}
		
		
	}
	
	private JasperPrint buildReport(Programa programa, int page) throws JRException {
	 

			BigDecimal dotacaoInicial = fisicoFinanceiroMensalSiafemService.calculaDotacaoInicialByProgAndAno(programa.getCodigo(), programa.getExercicio().getAno());
			BigDecimal dotacaoAtual   = fisicoFinanceiroMensalSiafemService.calculaDotacaoAtualByProgAndAno(programa.getCodigo(), programa.getExercicio().getAno());
			BigDecimal empenhado 	  = fisicoFinanceiroMensalSiafemService.calculaEmpenhadoByProgAndAno(programa.getCodigo(), programa.getExercicio().getAno());
			BigDecimal liquidado      = fisicoFinanceiroMensalSiafemService.calculaLiquidadoByProgAndAno(programa.getCodigo(), programa.getExercicio().getAno());
			BigDecimal pago 	      = fisicoFinanceiroMensalSiafemService.calculaPagoByProgAndAno(programa.getCodigo(), programa.getExercicio().getAno());

			if(dotacaoAtual==null || dotacaoInicial==null) return null;
			
			BigDecimal variacao = MathUtils.divide(dotacaoAtual, dotacaoInicial).subtract(new BigDecimal(1).setScale(2)).multiply(new BigDecimal(100).setScale(2), MathContext.UNLIMITED);
			 
			
			List<AvaliacaoFisicoFinanceira> listAvaliacaoFisicoFinanceira = avaliacaoFisicoFinanceiraService.findByProgramaAndExercicio(programa.getId(), programa.getExercicio().getId());
			
			List<FisicoFinanceiroMensalSiafem> listFisicoFinanceiroMensalSiafem = fisicoFinanceiroMensalSiafemService.analiseFisicoFinanceiro(programa, programa.getExercicio());

			BigDecimal mediaEficaciaFisicoFinanceira   = fisicoFinanceiroMensalSiafemService.calculaMediaEficaciaAvaliacaoFisicoFinanceira(listFisicoFinanceiroMensalSiafem);
			BigDecimal mediaEficienciaFisicoFinanceira = fisicoFinanceiroMensalSiafemService.calculaEficienciaMediaAvaliacaoFisicoFinanceira(listFisicoFinanceiroMensalSiafem);

			
			List<String> diretrizes = diretrizAssociadaService.findByProgramaAndExercicio(programa.getId(), programa.getExercicio().getId())
																		   				    .stream().map(p -> p.getDiretriz().getDescricao()).collect(Collectors.toList());

			
			List<String> painelAssociado = painelAssociadoService.findByProgramaAndExercicio(programa.getId(), programa.getExercicio().getId())
																.stream().map(p -> p.getIndicador().getIndicador()).collect(Collectors.toList());
			
			
			List<IndicadorDesempenhoIntermediario> listIntermediarioAssociado = indicadorDesempenhoIntermediarioService.findByProgramaAndExercicio(programa.getId(), programa.getExercicio().getId());
			
			List<Analise> listAnalise = analiseService.findByProgramaAndExercicio(programa.getId(), programa.getExercicio().getId());
			
			List<Resultado> listResultado = resultadoService.findByProgramaAndExercicio(programa.getId(), programa.getExercicio().getId());
			
			Recomendacao recomendacao = recomendacaoService.findByProgramaAndExercicio(programa.getId(), programa.getExercicio().getId());
			
			     
			BufferedImage chart=createChart(dotacaoInicial,dotacaoAtual,empenhado,liquidado,pago);
			
			Map<String, Object> parameters = new HashMap<>();

			String brasaoMa = FileUtil.getRealPath("/resources/images/logo-gov-ma.png");
			parameters.put("chart", chart);
			parameters.put("param_imagem", brasaoMa);
			parameters.put("param_ano", programa.getExercicio().getAno());
			 
			parameters.put("param_id_exerc",programa.getExercicio().getId());
			parameters.put("param_ppa",programa.getExercicio().getPpa().getDescricao());
			
			parameters.put("param_id_prog", 		  programa.getId());
			parameters.put("param_cod_prog", 		  programa.getCodigo());
			parameters.put("param_prog", 			  programa.getDenominacao());
			parameters.put("param_unidade_executora", programa.getUnidadeOrcamentaria().getDescricao());
			parameters.put("param_problema", 		  programa.getProblema());
			parameters.put("param_objetivo", 		  programa.getObjetivo());
			parameters.put("param_publico_alvo", 	  programa.getPublicoAlvo());
			parameters.put("param_cod_uo",			  programa.getUnidadeOrcamentaria().getCodigo());

			parameters.put("param_dot_inicial", dotacaoInicial);
			parameters.put("param_dot_atual", dotacaoAtual);
			parameters.put("param_variacao", variacao);
			parameters.put("param_dot_empenhado", empenhado);
			parameters.put("param_dot_liquidado", liquidado);
			parameters.put("param_dot_pago", pago);
			parameters.put("pageInit",page);
			
			parameters.put("diretrizes", diretrizes);
			
			parameters.put("painelAssociado", painelAssociado);
			
			parameters.put("intermediarioAssociado", listIntermediarioAssociado);
			
			parameters.put("analise", listAnalise);
			
			parameters.put("listFisicoFinanceiroMensalSiafem", listFisicoFinanceiroMensalSiafem);
			parameters.put("mediaEficaciaFisicoFinanceira", mediaEficaciaFisicoFinanceira);
			parameters.put("mediaEficienciaFisicoFinanceira", mediaEficienciaFisicoFinanceira);
			
			parameters.put("avaliacaoFisicoFinanceira", listAvaliacaoFisicoFinanceira);
			
			parameters.put("resultado", listResultado);
			
			parameters.put("recomendacao", recomendacao.getDescricao());
			
			String report = FileUtil.getRealPath("/relatorios/avaliacao/relatorio_avaliacao_programa2.jasper");
 	 
			return JasperFillManager.fillReport(report, parameters,new JREmptyDataSource());

		
	}
	
	public BufferedImage createChart(BigDecimal dotacaoInicial, BigDecimal dotacaoAtual, BigDecimal empenhado, BigDecimal liquidado, BigDecimal pago) {
		 
		  final String dotInicialLabel  = "Dotação Inicial";  
		  final String catDotInicialLabel  = "Dot.Inicial";   
		  
	      final String dotAtualLabel  = "Dotação Atual";    
	      final String catDotAtualLabel  = "Dot.Atual";    
	      final String empenhadoLabel = "Empenhado";        
	      final String liquidadoLabel = "Liquidado";        
	      final String pagoLabel = "Pago";        
	      
	      final DefaultCategoryDataset dataset =  new DefaultCategoryDataset( );  

	      dataset.addValue(dotacaoInicial,dotInicialLabel ,catDotInicialLabel );        
	      dataset.addValue(dotacaoAtual , dotAtualLabel ,  catDotAtualLabel );        
	      dataset.addValue(empenhado, 	  empenhadoLabel , empenhadoLabel ); 
	      dataset.addValue(liquidado, 	  liquidadoLabel , liquidadoLabel );           
	      dataset.addValue(pago, 		  pagoLabel, 	   pagoLabel );  
	      
	      
	      StandardChartTheme theme = new StandardChartTheme("sispca", false);
	      					 theme.setPlotBackgroundPaint(Color.WHITE); 
	      
	      
	      ChartFactory.setChartTheme(theme);
	      JFreeChart chart = ChartFactory.createBarChart3D("","","", dataset, PlotOrientation.VERTICAL, false, false, false);

	      
	      BarRenderer.setDefaultBarPainter(new StandardBarPainter());
	      CategoryPlot plot = chart.getCategoryPlot();
	      plot.setBackgroundAlpha(0);
	       
	      Font font = new Font("", Font.TRUETYPE_FONT, 10); 
	      CategoryAxis axis = plot.getDomainAxis();
	      axis.setTickLabelFont(font);
	       
	      NumberAxis e = (NumberAxis) plot.getRangeAxis();
	      e.setNumberFormatOverride(new DecimalFormat("#,###,##0.00"));
	      
	       BarRenderer br = (BarRenderer) plot.getRenderer();
	       br.setItemMargin(-2.5);
	   
	       
	       return chart.createBufferedImage(490, 150);
	      
	     
	} 
	
	
	
	
	public List<Programa> getListPrograma() {
		return listPrograma;
	}

	public void setListPrograma(List<Programa> listPrograma) {
		this.listPrograma = listPrograma;
	}

	public boolean isEditarAvaliacao() {
		return editarAvaliacao;
	}

	public void setEditarAvaliacao(boolean editarAvaliacao) {
		this.editarAvaliacao = editarAvaliacao;
	}

	public String getCodigoPrograma() {
		return codigoPrograma;
	}

	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}

	public Long getEixoId() {
		return eixoId;
	}

	public void setEixoId(Long eixoId) {
		this.eixoId = eixoId;
	}

	public Exercicio getExercicio() {
		return exercicio;
	}

	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}

	public List<Eixo> getListEixos() {
		return listEixos;
	}

	public void setListEixos(List<Eixo> listEixos) {
		this.listEixos = listEixos;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public boolean isAvaliacaoSetorialProgramaAnalise() {
		return avaliacaoSetorialProgramaAnalise;
	}

	public void setAvaliacaoSetorialProgramaAnalise(boolean avaliacaoSetorialProgramaAnalise) {
		this.avaliacaoSetorialProgramaAnalise = avaliacaoSetorialProgramaAnalise;
	}

	public boolean isAvaliacaoSetorialProgramaDiretrizAssociada() {
		return avaliacaoSetorialProgramaDiretrizAssociada;
	}

	public void setAvaliacaoSetorialProgramaDiretrizAssociada(boolean avaliacaoSetorialProgramaDiretrizAssociada) {
		this.avaliacaoSetorialProgramaDiretrizAssociada = avaliacaoSetorialProgramaDiretrizAssociada;
	}

	public boolean isAvaliacaoSetorialProgramaFisicoFinanceiro() {
		return avaliacaoSetorialProgramaFisicoFinanceiro;
	}

	public void setAvaliacaoSetorialProgramaFisicoFinanceiro(boolean avaliacaoSetorialProgramaFisicoFinanceiro) {
		this.avaliacaoSetorialProgramaFisicoFinanceiro = avaliacaoSetorialProgramaFisicoFinanceiro;
	}

	public boolean isAvaliacaoSetorialProgramaIntermediario() {
		return avaliacaoSetorialProgramaIntermediario;
	}

	public void setAvaliacaoSetorialProgramaIntermediario(boolean avaliacaoSetorialProgramaIntermediario) {
		this.avaliacaoSetorialProgramaIntermediario = avaliacaoSetorialProgramaIntermediario;
	}

	public boolean isAvaliacaoSetorialProgramaPainelAssociado() {
		return avaliacaoSetorialProgramaPainelAssociado;
	}

	public void setAvaliacaoSetorialProgramaPainelAssociado(boolean avaliacaoSetorialProgramaPainelAssociado) {
		this.avaliacaoSetorialProgramaPainelAssociado = avaliacaoSetorialProgramaPainelAssociado;
	}

	public boolean isAvaliacaoSetorialProgramaRecomendacao() {
		return avaliacaoSetorialProgramaRecomendacao;
	}

	public void setAvaliacaoSetorialProgramaRecomendacao(boolean avaliacaoSetorialProgramaRecomendacao) {
		this.avaliacaoSetorialProgramaRecomendacao = avaliacaoSetorialProgramaRecomendacao;
	}

	public boolean isAvaliacaoSetorialProgramaResultado() {
		return avaliacaoSetorialProgramaResultado;
	}

	public void setAvaliacaoSetorialProgramaResultado(boolean avaliacaoSetorialProgramaResultado) {
		this.avaliacaoSetorialProgramaResultado = avaliacaoSetorialProgramaResultado;
	}

	public boolean isAvaliacaoSetorialRelatorio() {
		return avaliacaoSetorialRelatorio;
	}

	public void setAvaliacaoSetorialRelatorio(boolean avaliacaoSetorialRelatorio) {
		this.avaliacaoSetorialRelatorio = avaliacaoSetorialRelatorio;
	}
	
	
	

}
