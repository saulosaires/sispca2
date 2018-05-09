package grafico.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.model.Exercicio;
import administrativo.service.ExercicioService;
import arquitetura.enums.TipoArquivo;
import arquitetura.utils.FileUtil;
import arquitetura.utils.MathUtils;
import arquitetura.utils.Messages;
import arquitetura.utils.SispcaLogger;
import grafico.model.RelatorioLiquidadoAcumuladoFisicoFinanceiro;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import qualitativo.model.Mes;
import qualitativo.model.Programa;
import qualitativo.model.UnidadeOrcamentaria;
import qualitativo.service.MesService;
import qualitativo.service.ProgramaService;
import qualitativo.service.UnidadeOrcamentariaService;
import siafem.model.FisicoFinanceiroMensalSiafem;
import siafem.service.FisicoFinanceiroMensalSiafemService;
 
@Named
@ViewScoped
public class GraficoFisicoFinanceiro implements Serializable{
	
	public static final String FAIL_REPORT = "Falha inesperada ao tentar Imprimir Relatório";
	public static final String REPORT_EMPTY = "Não há dados para o programa Selecionado";
	private final static String NO_DATA="Não a dados a serem exibidos";
	
	private String tipoArquivo="PDF";
	private Integer exercicio;
	private String unidade;
	private String programa;
	
	private List<UnidadeOrcamentaria> listUnidade;
	private List<Exercicio> listExercicio;
	private List<Programa> listPrograma;
	private List<Mes> meses;
	
	private ProgramaService programaService;
	private FisicoFinanceiroMensalSiafemService fisicoFinanceiroMensalSiafemService;
	
	
	@Inject
	public GraficoFisicoFinanceiro(ExercicioService exercicioService,
								   ProgramaService programaService,
								   MesService mesService,
								   FisicoFinanceiroMensalSiafemService fisicoFinanceiroMensalSiafemService,
								   UnidadeOrcamentariaService unidadeOrcamentariaService) {
	
		this.programaService=programaService;
		this.fisicoFinanceiroMensalSiafemService=fisicoFinanceiroMensalSiafemService;
		
		meses = mesService.findAll();
		listExercicio = exercicioService.findAll();
		listUnidade   = unidadeOrcamentariaService.findAllOrderByDescricao();
		
	}

	public void buscaProgramaPorUnidadeOrcamentaria() {
		
		listPrograma = programaService.buscarPorUnidadeOrcamentaria(unidade);
		
	}
	
	public String gerarRelatorio() {
		
		try {
			
		
			List<FisicoFinanceiroMensalSiafem> listAnaliseFisicoFinanceiro = fisicoFinanceiroMensalSiafemService.analiseFisicoFinanceiro(unidade, programa, exercicio);
			
			
			if(listAnaliseFisicoFinanceiro==null || listAnaliseFisicoFinanceiro.isEmpty()) {
				Messages.addMessageWarn(NO_DATA);
				return "";
			}
			
			List<FisicoFinanceiroMensalSiafem> listAnaliseFisicoFinanceiroPorMes = fisicoFinanceiroMensalSiafemService.analiseFisicoFinanceiroPorMes(unidade, programa, exercicio);

			
			
			RelatorioLiquidadoAcumuladoFisicoFinanceiro liquidadoAcumuladoFisicoFinanceiro = fisicoFinanceiroMensalSiafemService.calculaLiquidadoAcumuladoByUnidadeAndProgAndMesAndAno(unidade, programa, meses, exercicio);
			
			
			FisicoFinanceiroMensalSiafem financeiroMensalSiafem = listAnaliseFisicoFinanceiro.get(0);
			
			String codigoUnidadeOrcamentaria    = financeiroMensalSiafem.getCodigoUnidadeOrcamentaria();
		    String descricaoUnidadeOrcamentaria = financeiroMensalSiafem.getDescricaoUnidadeOrcamentaria();
			String programaCodigo			    = financeiroMensalSiafem.getPrograma();
			String programaDenominacao 			= financeiroMensalSiafem.getProgramaDenominacao();
		    BigDecimal dotacaoInicial 			= financeiroMensalSiafem.getDotacaoInicial();
			BigDecimal disponivel 				= financeiroMensalSiafem.getDisponivel(); 
			BigDecimal empenhado  				= financeiroMensalSiafem.getEmpenhado();
			BigDecimal liquidado				= financeiroMensalSiafem.getLiquidado();
			BigDecimal pago						= financeiroMensalSiafem.getPago();
			
			BigDecimal saldo = null;
			
			if(disponivel!=null && empenhado!=null) {
				saldo = disponivel.subtract(empenhado);
			}else {
				saldo= MathUtils.getZeroBigDecimal();
			}
			
			BigDecimal number100= new BigDecimal(100);
			
			BigDecimal empenhadoPorDisponivel = MathUtils.multiply(MathUtils.divide(empenhado, disponivel),number100);
			BigDecimal liquidadoPorDisponivel = MathUtils.multiply(MathUtils.divide(liquidado, disponivel),number100);
			BigDecimal pagoPorDisponivel 	  = MathUtils.multiply(MathUtils.divide(pago, disponivel),number100);
			
			Map<String, Object> parameters = new HashMap<>();
	
			String brasaoMa = FileUtil.getRealPath("/resources/images/logo-gov-ma.png");
		
			parameters.put("param_imagem", brasaoMa);
			
			parameters.put("exercicio",    				    exercicio.toString());
			parameters.put("codigoUnidadeOrcamentaria",     codigoUnidadeOrcamentaria);
			parameters.put("descricaoUnidadeOrcamentaria",  descricaoUnidadeOrcamentaria);
			parameters.put("programa", 			 		    programaCodigo);
			parameters.put("programaDenominacao", 		    programaDenominacao);
			parameters.put("dotacaoInicial", 			    dotacaoInicial);
			parameters.put("disponivel", 				    disponivel);
			parameters.put("empenhado", 				    empenhado);
			parameters.put("liquidado", 				    liquidado);
			parameters.put("pago", 				    		pago);
			parameters.put("saldo", 					    saldo);
			parameters.put("empenhadoPorDisponivel", 	    empenhadoPorDisponivel);
			parameters.put("liquidadoPorDisponivel", 	    liquidadoPorDisponivel);
			parameters.put("pagoPorDisponivel", 	    	pagoPorDisponivel);
	
			parameters.put("analiseFisicoFinanceiroPorMes", listAnaliseFisicoFinanceiroPorMes);
			
			parameters.put("liquidadoAcumuladoFisicoFinanceiro", liquidadoAcumuladoFisicoFinanceiro);
			
			
			String report = FileUtil.getRealPath("/relatorios/grafico/grafico_acompanhamento_financeiro_orcamento.jasper");
		 	 
			JasperPrint jasperRelatorio = JasperFillManager.fillReport(report, parameters,new JREmptyDataSource());

			 if (jasperRelatorio==null || jasperRelatorio.getPages().isEmpty()) {
					Messages.addMessageWarn(REPORT_EMPTY);
					return "";
				}

				byte[] bytes = JasperExportManager.exportReportToPdf(jasperRelatorio);

			 
				
				FileUtil.sendFileOnResponseAttached(bytes,"  Acompanhamento Financeiro do Orçamento.pdf", TipoArquivo.PDF.getId());
				
			
			
		} catch (Exception e) {

			SispcaLogger.logError(e.getCause().getMessage());

			Messages.addMessageError(FAIL_REPORT);
		}
		
		
		return null;
	}

	public String getTipoArquivo() {
		return tipoArquivo;
	}

	public void setTipoArquivo(String tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}

	public Integer getExercicio() {
		return exercicio;
	}

	public void setExercicio(Integer exercicio) {
		this.exercicio = exercicio;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public List<UnidadeOrcamentaria> getListUnidade() {
		return listUnidade;
	}

	public void setListUnidade(List<UnidadeOrcamentaria> listUnidade) {
		this.listUnidade = listUnidade;
	}

	public List<Exercicio> getListExercicio() {
		return listExercicio;
	}

	public void setListExercicio(List<Exercicio> listExercicio) {
		this.listExercicio = listExercicio;
	}

	public List<Programa> getListPrograma() {
		return listPrograma;
	}

	public void setListPrograma(List<Programa> listPrograma) {
		this.listPrograma = listPrograma;
	}

 
	
	
}
