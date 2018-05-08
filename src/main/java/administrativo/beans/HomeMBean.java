package administrativo.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

import administrativo.model.Exercicio;
import administrativo.model.Usuario;
import administrativo.service.ExercicioService;
import arquitetura.utils.MathUtils;
import arquitetura.utils.SessionUtils;
import arquitetura.utils.Utils;
import monitoramento.service.ExecucaoService;
import qualitativo.model.Acao;
import qualitativo.model.Mes;
import qualitativo.model.UnidadeGestora;
import qualitativo.model.UnidadeOrcamentaria;
import qualitativo.service.AcaoService;
import qualitativo.service.MesService;
import qualitativo.service.ProgramaService;
import qualitativo.service.UnidadeGestoraService;
import qualitativo.service.UnidadeOrcamentariaService;
import quantitativo.service.FisicoFinanceiroMensalService;
import siafem.model.FisicoFinanceiroMensalSiafem;
import siafem.service.FisicoFinanceiroMensalSiafemService;

@Named
@ViewScoped
public class HomeMBean implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
 
	private Integer qtdAcao;
	private Integer qtdPrograma;
	private Integer exercicioVigente;
	private Long exercicioVigenteId;
	
	private Long unidadeGestoraId;
	private List<UnidadeGestora>listUnidadeGestora;
	
	private Long unidadeOrcamentariaId;
	private List<UnidadeOrcamentaria> listUnidadeOrcamentaria;
	
	private Long acaoId;
	private List<Acao> listAcao;
	
	private LineChartModel lineChartModel;
	
	private BigDecimal percentualInicial   = MathUtils.getZeroBigDecimal();
	private BigDecimal valorInicial 	   = MathUtils.getZeroBigDecimal();
	private BigDecimal percentualAtual     = MathUtils.getZeroBigDecimal();
	private BigDecimal valorAtual 		   = MathUtils.getZeroBigDecimal();
	private BigDecimal percentualEmpenhado = MathUtils.getZeroBigDecimal();
	private BigDecimal valorEmpenhado 	   = MathUtils.getZeroBigDecimal();
	private BigDecimal percentualLiquidado = MathUtils.getZeroBigDecimal();
	private BigDecimal valorLiquidado 	   = MathUtils.getZeroBigDecimal();
	
	
	private AcaoService acaoService;
	private UnidadeGestoraService unidadeGestoraService;
	private UnidadeOrcamentariaService unidadeOrcamentariaService;
	private ExecucaoService execucaoService;
	private MesService mesService;
	 FisicoFinanceiroMensalService fisicoFinanceiroMensalService;
	private FisicoFinanceiroMensalSiafemService fisicoFinanceiroMensalSiafemService;
	
	@Inject
	public HomeMBean(AcaoService acaoService, 
					 ProgramaService programaService,
					 UnidadeGestoraService unidadeGestoraService,
					 UnidadeOrcamentariaService unidadeOrcamentariaService,
					 FisicoFinanceiroMensalSiafemService fisicoFinanceiroMensalSiafemService,
					 FisicoFinanceiroMensalService fisicoFinanceiroMensalService,
					 ExecucaoService execucaoService,
					 MesService mesService,
					 ExercicioService exercicioService) {
		 
 
		this.acaoService=acaoService;
		this.mesService=mesService;
		this.execucaoService=execucaoService;
		this.unidadeGestoraService=unidadeGestoraService;
		this.unidadeOrcamentariaService=unidadeOrcamentariaService;
		this.fisicoFinanceiroMensalService=fisicoFinanceiroMensalService;
		this.fisicoFinanceiroMensalSiafemService=fisicoFinanceiroMensalSiafemService;
		
		Optional<Exercicio> exercicio = exercicioService.exercicioVigente();
		
		if(exercicio.isPresent()) {
			exercicioVigente   = exercicio.get().getAno();
			exercicioVigenteId = exercicio.get().getId();
			
			qtdAcao = acaoService.buscarByExercicio(exercicio.get().getId()).size();
			qtdPrograma = programaService.buscar(null, null, null, null, exercicio.get().getId(), null).size();

		}
		
		listUnidadeGestora = unidadeGestoraService.findAll();
		
		listUnidadeOrcamentaria = unidadeOrcamentariaService.findAllOrderByDescricao();
		
		Usuario user = (Usuario) SessionUtils.get("user");
		
		if(user!=null && user.getUnidadeOrcamentaria()!=null && !Utils.invalidId(user.getUnidadeOrcamentaria().getId())) {
			unidadeOrcamentariaId = user.getUnidadeOrcamentaria().getId();
			montarGrafico();
		}
		
	}

	public void changeUnidadeGestora() {
		
		montarGrafico();
		
	}
	
	public void changeUnidadeOrcamentaria() {
		
		if(!Utils.invalidId(unidadeOrcamentariaId))
			listAcao = acaoService.buscarByUnidadeOrcamentaria(unidadeOrcamentariaId);
		
		montarGrafico();
	}
	
	public void changeAcao() {
		
		montarGrafico();
	}
	
	private void montarGrafico() {
		
		List<FisicoFinanceiroMensalSiafem> list = buscaValorFisicoFinanceiro();
		
		montarGraficoCircle(list);
		
		montarGraficoLine();		
	
		
	}
	
	private void montarGraficoLine() {
	
		lineChartModel = initChartModel();
		lineChartModel.setTitle("Comparativo Planejado X Executado");
		lineChartModel.setLegendPosition("e");
		lineChartModel.setShowPointLabels(true);
          
        lineChartModel.getAxes().put(AxisType.X, new CategoryAxis("Mês"));
        Axis yAxis = lineChartModel.getAxis(AxisType.Y);
        yAxis.setLabel("Valor");
        yAxis.setMin(0);
		
	}
	
	private LineChartModel initChartModel() {
		
		LineChartModel model = new LineChartModel();
		
		ChartSeries series1 = new ChartSeries();		 
        series1.setLabel("Executado");
        
        ChartSeries series2 = new ChartSeries();
        series2.setLabel("Planejado");
		
		List<Mes> listMes = mesService.findAll();
		
		for(Mes mes : listMes) {
			
			Double valorExecutado = execucaoService.findTotalValorExecutadoByAcao(unidadeGestoraId, unidadeOrcamentariaId, acaoId,exercicioVigenteId, mes.getId());
			
			series1.set(mes.getDescricao(),valorExecutado);
			
			Double valorPlanejado = fisicoFinanceiroMensalService.findTotalValorFinanceiroPlanejadoByAcao(unidadeGestoraId, unidadeOrcamentariaId, acaoId, exercicioVigenteId, mes.getId());
			
			series2.set(mes.getDescricao(),valorPlanejado);
			
		}

		model.addSeries(series1);
        model.addSeries(series2);   
        
        return model;
		
	}
	
	private List<FisicoFinanceiroMensalSiafem> buscaValorFisicoFinanceiro() {
	
		String unidadeGestoraCodigo=null;
		String unidadeOrcamentariaCodigo=null;
		
		if(!Utils.invalidId(unidadeGestoraId)) {
			unidadeGestoraCodigo = unidadeGestoraService.findById(unidadeGestoraId).getCodigo();
		}
		
		if(!Utils.invalidId(unidadeOrcamentariaId)) {
			unidadeOrcamentariaCodigo = unidadeOrcamentariaService.findById(unidadeOrcamentariaId).getCodigo();
		}
		
		return  fisicoFinanceiroMensalSiafemService.valorFisicoFinanceiro(unidadeGestoraCodigo, unidadeOrcamentariaCodigo, acaoId, exercicioVigente);
 
	}
	
	
	private void montarGraficoCircle(List<FisicoFinanceiroMensalSiafem> list) {
		
		if(list==null ||list.isEmpty()) return;
		
		try {
			FisicoFinanceiroMensalSiafem financeiroMensalSiafem= list.get(0);
			
			valorInicial   = financeiroMensalSiafem.getDotacaoInicial();
			valorAtual 	   = financeiroMensalSiafem.getDisponivel();
			valorEmpenhado = financeiroMensalSiafem.getEmpenhado();
			valorLiquidado = financeiroMensalSiafem.getLiquidado();
			
			if (!valorAtual.equals(BigDecimal.valueOf(0))) {
				
				BigDecimal decimal100 = new BigDecimal(100);
				
				percentualInicial   = MathUtils.multiply( MathUtils.divide(valorInicial,   valorAtual), decimal100);
				percentualEmpenhado = MathUtils.multiply( MathUtils.divide(valorEmpenhado, valorAtual), decimal100);
				percentualLiquidado = MathUtils.multiply( MathUtils.divide(valorLiquidado, valorAtual), decimal100); 
				percentualAtual = decimal100;	
	 		}
		
		} catch (Exception e) {
			  
			percentualAtual 	= MathUtils.getZeroBigDecimal();
			percentualInicial   = MathUtils.getZeroBigDecimal();
			percentualEmpenhado = MathUtils.getZeroBigDecimal();
			percentualLiquidado = MathUtils.getZeroBigDecimal();
			valorInicial 		= MathUtils.getZeroBigDecimal();
			valorAtual 		    = MathUtils.getZeroBigDecimal();
			valorEmpenhado 		= MathUtils.getZeroBigDecimal();
			valorLiquidado 		= MathUtils.getZeroBigDecimal();
		}
		
	}
	
	public Integer getQtdAcao() {
		return qtdAcao;
	}

	public void setQtdAcao(Integer qtdAcao) {
		this.qtdAcao = qtdAcao;
	}

	public Integer getQtdPrograma() {
		return qtdPrograma;
	}

	public void setQtdPrograma(Integer qtdPrograma) {
		this.qtdPrograma = qtdPrograma;
	}

	public Integer getExercicioVigente() {
		return exercicioVigente;
	}

	public void setExercicioVigente(Integer exercicioVigente) {
		this.exercicioVigente = exercicioVigente;
	}

	public Long getUnidadeGestoraId() {
		return unidadeGestoraId;
	}

	public void setUnidadeGestoraId(Long unidadeGestoraId) {
		this.unidadeGestoraId = unidadeGestoraId;
	}

	public List<UnidadeGestora> getListUnidadeGestora() {
		return listUnidadeGestora;
	}

	public void setListUnidadeGestora(List<UnidadeGestora> listUnidadeGestora) {
		this.listUnidadeGestora = listUnidadeGestora;
	}

	public Long getUnidadeOrcamentariaId() {
		return unidadeOrcamentariaId;
	}

	public void setUnidadeOrcamentariaId(Long unidadeOrcamentariaId) {
		this.unidadeOrcamentariaId = unidadeOrcamentariaId;
	}

	public List<UnidadeOrcamentaria> getListUnidadeOrcamentaria() {
		return listUnidadeOrcamentaria;
	}

	public void setListUnidadeOrcamentaria(List<UnidadeOrcamentaria> listUnidadeOrcamentaria) {
		this.listUnidadeOrcamentaria = listUnidadeOrcamentaria;
	}

	public Long getAcaoId() {
		return acaoId;
	}

	public void setAcaoId(Long acaoId) {
		this.acaoId = acaoId;
	}

	public List<Acao> getListAcao() {
		return listAcao;
	}

	public void setListAcao(List<Acao> listAcao) {
		this.listAcao = listAcao;
	}

	public BigDecimal getPercentualInicial() {
		return percentualInicial;
	}

	public void setPercentualInicial(BigDecimal percentualInicial) {
		this.percentualInicial = percentualInicial;
	}

	public BigDecimal getValorInicial() {
		return valorInicial;
	}

	public void setValorInicial(BigDecimal valorInicial) {
		this.valorInicial = valorInicial;
	}

	public BigDecimal getPercentualAtual() {
		return percentualAtual;
	}

	public void setPercentualAtual(BigDecimal percentualAtual) {
		this.percentualAtual = percentualAtual;
	}

	public BigDecimal getValorAtual() {
		return valorAtual;
	}

	public void setValorAtual(BigDecimal valorAtual) {
		this.valorAtual = valorAtual;
	}

	public BigDecimal getPercentualEmpenhado() {
		return percentualEmpenhado;
	}

	public void setPercentualEmpenhado(BigDecimal percentualEmpenhado) {
		this.percentualEmpenhado = percentualEmpenhado;
	}

	public BigDecimal getValorEmpenhado() {
		return valorEmpenhado;
	}

	public void setValorEmpenhado(BigDecimal valorEmpenhado) {
		this.valorEmpenhado = valorEmpenhado;
	}

	public BigDecimal getPercentualLiquidado() {
		return percentualLiquidado;
	}

	public void setPercentualLiquidado(BigDecimal percentualLiquidado) {
		this.percentualLiquidado = percentualLiquidado;
	}

	public BigDecimal getValorLiquidado() {
		return valorLiquidado;
	}

	public void setValorLiquidado(BigDecimal valorLiquidado) {
		this.valorLiquidado = valorLiquidado;
	}

	public LineChartModel getLineChartModel() {
		return lineChartModel;
	}

	public void setLineChartModel(LineChartModel lineChartModel) {
		this.lineChartModel = lineChartModel;
	}
	
	
	
}
