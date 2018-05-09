package qualitativo.beans.acao;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import arquitetura.enums.TipoArquivo;
import arquitetura.utils.FileUtil;
import arquitetura.utils.Messages;
import arquitetura.utils.SispcaLogger;
import arquitetura.utils.Utils;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import qualitativo.model.Acao;
import qualitativo.service.AcaoService;
import qualitativo.service.FuncaoService;
import qualitativo.service.ProgramaService;
import qualitativo.service.SubFuncaoService;
import qualitativo.service.TipoAcaoService;
import qualitativo.service.TipoCalculoMetaService;
import qualitativo.service.TipoFormaImplementacaoService;
import qualitativo.service.TipoHorizonteTemporalService;
import qualitativo.service.TipoOrcamentoService;
import qualitativo.service.UnidadeMedidaService;
import qualitativo.service.UnidadeOrcamentariaService;

@Named
@ViewScoped
public class AcaoViewMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5940179508090218836L;

	public static final String FAIL = "Falha inesperada ao tentar Imprimir Relatório";

	
	private Long id;

	private Acao acao = new Acao();

	private AcaoService acaoService;
	private UnidadeOrcamentariaService unidadeOrcamentariaService;
	private ProgramaService programaService;
	private FuncaoService funcaoService;
	private SubFuncaoService subFuncaoService;
	private UnidadeMedidaService unidadeMediaService;
	private TipoAcaoService tipoAcaoService;
	private TipoHorizonteTemporalService tipoHorizonteTemporalService;
	private TipoFormaImplementacaoService tipoFormaImplementacaoService;
	private TipoOrcamentoService tipoOrcamentoService;
	private TipoCalculoMetaService tipoCalculoMetaService;

	private AcaoValidate acaoValidate;

	@Inject
	public AcaoViewMBean(AcaoService acaoService, 
						 UnidadeOrcamentariaService unidadeOrcamentariaService,
						 ProgramaService programaService, 
						 FuncaoService funcaoService, 
						 SubFuncaoService subFuncaoService,
						 UnidadeMedidaService unidadeMediaService, 
						 TipoAcaoService tipoAcaoService,
						 TipoHorizonteTemporalService tipoHorizonteTemporalService,
						 TipoFormaImplementacaoService tipoFormaImplementacaoService, 
						 TipoOrcamentoService tipoOrcamentoService,
						 TipoCalculoMetaService tipoCalculoMetaService, 
						 AcaoValidate acaoValidate) {

		this.acaoService = acaoService;
		this.unidadeOrcamentariaService = unidadeOrcamentariaService;
		this.programaService = programaService;
		this.funcaoService = funcaoService;
		this.subFuncaoService = subFuncaoService;
		this.unidadeMediaService = unidadeMediaService;
		this.tipoAcaoService = tipoAcaoService;
		this.tipoHorizonteTemporalService = tipoHorizonteTemporalService;
		this.tipoFormaImplementacaoService = tipoFormaImplementacaoService;
		this.tipoOrcamentoService = tipoOrcamentoService;
		this.tipoCalculoMetaService = tipoCalculoMetaService;

		this.acaoValidate = acaoValidate;

	}

	public void init() {

		if (!Utils.invalidId(id)) {

			acao = acaoService.findById(id);

			acao = acaoValidate.init(acao);

			initFields(acao);

		}

	}

	private void initFields(Acao acao) {

		if (!Utils.invalidId(acao.getPrograma().getId())) {
			acao.setPrograma(programaService.findById(acao.getPrograma().getId()));
		}

		if (!Utils.invalidId(acao.getUnidadeOrcamentaria().getId())) {
			acao.setUnidadeOrcamentaria(unidadeOrcamentariaService.findById(acao.getUnidadeOrcamentaria().getId()));
		}

		if (!Utils.invalidId(acao.getFuncao().getId())) {
			acao.setFuncao(funcaoService.findById(acao.getFuncao().getId()));
		}

		if (!Utils.invalidId(acao.getSubfuncao().getId())) {
			acao.setSubfuncao(subFuncaoService.findById(acao.getSubfuncao().getId()));
		}

		if (!Utils.invalidId(acao.getTipoAcao().getId())) {
			acao.setTipoAcao(tipoAcaoService.findById(acao.getTipoAcao().getId()));
		}

		if (!Utils.invalidId(acao.getTipoCalculoMeta().getId())) {
			acao.setTipoCalculoMeta(tipoCalculoMetaService.findById(acao.getTipoCalculoMeta().getId()));
		}

		if (!Utils.invalidId(acao.getTipoFormaImplementacao().getId())) {
			acao.setTipoFormaImplementacao(tipoFormaImplementacaoService.findById(acao.getTipoFormaImplementacao().getId()));
		}

		if (!Utils.invalidId(acao.getTipoHorizonteTemporal().getId())) {
			acao.setTipoHorizonteTemporal(tipoHorizonteTemporalService.findById(acao.getTipoHorizonteTemporal().getId()));
		}

		if (!Utils.invalidId(acao.getTipoOrcamento().getId())) {
			acao.setTipoOrcamento(tipoOrcamentoService.findById(acao.getTipoOrcamento().getId()));
		}

		if (!Utils.invalidId(acao.getUnidadeMedida().getId())) {
			acao.setUnidadeMedida(unidadeMediaService.findById(acao.getUnidadeMedida().getId()));
		}

	}

	public String imprimir() {
		
		try {
		
			Map<String, Object> parameters = new HashMap<>();
			
			String brasaoMa=FileUtil.getRealPath("/resources/images/brasao_ma.png");
			
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			
			parameters.put("param_imagem", brasaoMa);
			parameters.put("codigo_acao",acao.getCodigo());
			parameters.put("acao",acao.getDenominacao());
			parameters.put("programa",acao.getPrograma().getDenominacao());
			parameters.put("funcao",acao.getFuncao().getDescricao());
			parameters.put("subfuncao",acao.getSubfuncao().getDescricao());
			parameters.put("unidade_orcamentaria",acao.getUnidadeOrcamentaria().getDescricao());
			parameters.put("medido_despesa",acao.getMedidoDespesa());
			parameters.put("produto",acao.getProduto());
			parameters.put("unidade_medida",acao.getUnidadeMedida().getDescricao());
			parameters.put("especificacao_produto",acao.getEspecificaoProduto());
			parameters.put("tipo_acao",acao.getTipoAcao().getDescricao());
			parameters.put("tipo_horizonte_temporal",acao.getTipoHorizonteTemporal().getDescricao());
			parameters.put("tipo_forma_implementacao",acao.getTipoFormaImplementacao().getDescricao());
			parameters.put("tipo_orcamento",acao.getTipoOrcamento().getDescricao());
			parameters.put("base_legal",acao.getBaseLegal());
			parameters.put("detalhamento_implementacao",acao.getDetalhamentoImplementacao());
			parameters.put("mes_inicio",acao.getMesInicio()!=null?df.format(acao.getMesInicio()):"");
			parameters.put("mes_termino",acao.getMesTermino()!=null?df.format(acao.getMesTermino()):"");
			parameters.put("finalidade",acao.getFinalidade());
			parameters.put("descricao_acao",acao.getDescricao());
			parameters.put("repercussao",acao.getRepercussao());
			parameters.put("tipo_calculo_meta",acao.getTipoCalculoMeta().getDescricao());
			
			String report=FileUtil.getRealPath("/relatorios/qualitativo/visualizacao_acao.jasper");
			
			JasperPrint relatorio = JasperFillManager.fillReport(report, parameters ,new JRBeanCollectionDataSource(null));
		
			byte[] bytes = JasperExportManager.exportReportToPdf(relatorio);
			
			FileUtil.sendFileOnResponseAttached(bytes, "relatorio_visualizacao_acao.pdf", TipoArquivo.PDF.getId());
			
		} catch (Exception e) {
			SispcaLogger.logError(e.getCause().getMessage());

			Messages.addMessageError(FAIL);
		}	  
		  
		return "";
	}
	
	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
