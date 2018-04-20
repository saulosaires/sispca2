package monitoramento.beans.fisicofinanceiro.mensal;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.StreamedContent;

import administrativo.model.Exercicio;
import administrativo.service.ExercicioService;
import arquitetura.exception.JpaException;
import arquitetura.utils.FileUtil;
import arquitetura.utils.Messages;
import arquitetura.utils.PrimeFacesUtils;
import arquitetura.utils.SispcaLogger;
import arquitetura.utils.TipoArquivo;
import arquitetura.utils.Utils;
import monitoramento.model.Execucao;
import monitoramento.service.ExecucaoService;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import qualitativo.model.Acao;
import qualitativo.model.Mes;
import qualitativo.service.AcaoService;
import qualitativo.service.MesService;
import quantitativo.model.FisicoFinanceiro;
import quantitativo.model.FisicoFinanceiroMensal;
import quantitativo.model.Regiao;
import quantitativo.model.RegiaoMunicipio;
import quantitativo.model.TipoRegiao;
import quantitativo.service.FisicoFinanceiroService;
import quantitativo.service.RegiaoMunicipioService;
import quantitativo.service.TipoRegiaoService;

@Named
@SessionScoped
public class MonitoramentoFisicoFinanceiroMensalReportMBean implements Serializable {

	public static final String FAIL_REPORT = "Falha inesperada ao tentar Imprimir Relatório";
	public static final String REPORT_EMPTY = "Não há dados financeiros para a ação selecionada";

	
	private Long id;
	private boolean obs;
	
	private StreamedContent relatorio;
	
	private Acao acao;
   
 	
	private Exercicio exercicio;
	 
	private AcaoService acaoService; 
	 
	private ExecucaoService execucaoService; 

	@Inject
	public MonitoramentoFisicoFinanceiroMensalReportMBean(AcaoService acaoService,
														  ExecucaoService execucaoService,
														  ExercicioService exercicioService ) {

		this.acaoService = acaoService;
		 
 		this.execucaoService=execucaoService;
 		
 		Optional<Exercicio> exercicioOptinal = exercicioService.exercicioVigente();
		
		if(!exercicioOptinal.isPresent()) {
		  Messages.addMessageError("Nenhuma Exercicio Vigente encontrado ");
			return;
		}
		
		exercicio = exercicioOptinal.get();
	 
			
	}

	public void init() {

		if (!Utils.invalidId(id)) {

			acao = acaoService.findById(id);
 
			relatorio = gerarRelatorio();
		}

	}

	public StreamedContent gerarRelatorio() {

		try {

			Map<String, Object> parameters = new HashMap<>();

			String brasaoMa = FileUtil.getRealPath("/resources/images/brasao_ma.png");

	        parameters.put("param_id_acao",acao.getId());
	        parameters.put("param_imagem", brasaoMa);
	        parameters.put("param_cod_acao", acao.getCodigo());
	        parameters.put("param_acao", acao.getDenominacao());
	        parameters.put("param_cod_orgao", acao.getOrgao() != null ? acao.getOrgao().getCodigo() : "");
	        parameters.put("param_orgao", acao.getOrgao() != null ?  acao.getOrgao().getDescricao() : "");
	        parameters.put("param_cod_unid_orc", acao.getUnidadeOrcamentaria().getCodigo());
	        parameters.put("param_unid_orc", acao.getUnidadeOrcamentaria().getDescricao());
	        parameters.put("param_cod_programa", acao.getPrograma().getCodigo());
	        parameters.put("param_programa", acao.getPrograma().getDenominacao());
	        parameters.put("param_unid_medida", acao.getUnidadeMedida().getDescricao());
	        parameters.put("param_prod_acao", acao.getProduto());
			parameters.put("param_id_exercicio", exercicio.getId());
			
			String report="";
			 
			
			if(obs) {
				 report = FileUtil.getRealPath("/relatorios/monitoramento/relatorio_monitoramento_execucao_geral_com_observacao.jasper");
			}else {
				 report = FileUtil.getRealPath("/relatorios/monitoramento/relatorio_monitoramento_execucao_geral_sem_observacao.jasper");
			}
			
			List<RelatorioExecucao> list = buscarExecucao();
			 
			JasperPrint jasperRelatorio = JasperFillManager.fillReport(report, parameters,new JRBeanCollectionDataSource(list));

			if (jasperRelatorio.getPages().isEmpty()) {
				Messages.addMessageWarn(REPORT_EMPTY);
				return null;
			}

			byte[] bytes = JasperExportManager.exportReportToPdf(jasperRelatorio);

			return PrimeFacesUtils.converBytesToStreamedContent(bytes, TipoArquivo.PDF.getId(),"relatorio_monitoramento_execucao_geral.pdf"); 
			
			

		} catch (Exception e) {

			SispcaLogger.logError(e.getCause().getMessage());

			Messages.addMessageError(FAIL_REPORT);
		}

		return null;
	}
	
	public List<RelatorioExecucao> buscarExecucao(){
		
		List<RelatorioExecucao> list = new ArrayList<>();

		Map<Long,RelatorioExecucao> map = new HashMap<>();

			
		 List<Execucao> execucoes = execucaoService.findByAcaoAndExercicio(acao.getId(), exercicio.getId());
		 
		 for(Execucao e : execucoes) {	
			 
			 if(map.containsKey(e.getRegiaoMunicipio().getId())) {
				 map.get(e.getRegiaoMunicipio().getId()).setData(e);
				 
			 }else {
				 map.put(e.getRegiaoMunicipio().getId(), new RelatorioExecucao(e));
			 }
 
		 }
		 
		 
		 list.addAll(map.values());
		 
		 list.sort((RelatorioExecucao o1, RelatorioExecucao o2)-> o1.getRegiaoId().compareTo(o2.getRegiaoId()));
		  
		 
		return list;
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}
 

	public Exercicio getExercicio() {
		return exercicio;
	}

	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}

	public StreamedContent getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(StreamedContent relatorio) {
		this.relatorio = relatorio;
	}

	public boolean isObs() {
		return obs;
	}

	public void setObs(boolean obs) {
		this.obs = obs;
	}
 
	 
	
}
