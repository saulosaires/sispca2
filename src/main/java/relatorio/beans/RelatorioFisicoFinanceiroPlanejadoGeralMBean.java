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
import qualitativo.model.Orgao;
import qualitativo.model.Programa;
import qualitativo.model.UnidadeOrcamentaria;
import qualitativo.service.AcaoService;
import qualitativo.service.OrgaoService;
import qualitativo.service.ProgramaService;
import qualitativo.service.UnidadeOrcamentariaService;
import quantitativo.model.Regiao;
import quantitativo.model.RegiaoMunicipio;
import quantitativo.model.RelatorioFisicoFinanceiro;
import quantitativo.model.TipoRegiao;
import quantitativo.service.FisicoFinanceiroMensalService;
import quantitativo.service.RegiaoMunicipioService;
import quantitativo.service.RegiaoService;
import quantitativo.service.TipoRegiaoService;

@Named
@ViewScoped
public class RelatorioFisicoFinanceiroPlanejadoGeralMBean  extends RelatorioMBean {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7006847763527258024L;
	private Long orgao;
	private List<Orgao> listOrgao;
	
	private Long unidadeOrcamentaria;
	private List<UnidadeOrcamentaria> listUnidadeOrcamentaria;
	
	
	private Long programa;
	private List<Programa>listPrograma;
	
	
	private Long acao;
	private List<Acao> listAcao;
	
	private Long tipoRegiao;
	private List<TipoRegiao> listTipoRegiao;
	
	private Long regiao;
	private List<Regiao> listRegiao;
	
	private Long regiaoMunicipio;
	private List<RegiaoMunicipio> listRegiaoMunicipio;
	
	private FisicoFinanceiroMensalService fisicoFinanceiroMensalService;
	private UnidadeOrcamentariaService unidadeOrcamentariaService;
	private RegiaoMunicipioService regiaoMunicipioService;
	private ProgramaService programaService;
	private RegiaoService regiaoService;
	private AcaoService acaoService;
	
	private Long userId;
	@Inject
	public RelatorioFisicoFinanceiroPlanejadoGeralMBean(ExercicioService exercicioService,
														UnidadeOrcamentariaService unidadeOrcamentariaService,
														FisicoFinanceiroMensalService fisicoFinanceiroMensalService,
														ProgramaService programaService,
														AcaoService acaoService,
														OrgaoService orgaoService,
														RegiaoService regiaoService,
														RegiaoMunicipioService regiaoMunicipioService,
														TipoRegiaoService tipoRegiaoService) {
			
		super(exercicioService);
		 
		this.fisicoFinanceiroMensalService = fisicoFinanceiroMensalService;
		this.regiaoMunicipioService = regiaoMunicipioService;
		this.unidadeOrcamentariaService = unidadeOrcamentariaService;
		this.programaService = programaService;
		this.acaoService = acaoService;
		this.regiaoService = regiaoService;
		
		Usuario user = (Usuario) SessionUtils.get(SessionUtils.USER);
		
		userId = user.getId();

		listTipoRegiao = tipoRegiaoService.findAllOrderByDescricao();
		listOrgao = orgaoService.findAllOrderByDescricao(user.getId());
		
		
	}
	
	public void changeOrgao() {
		
		listUnidadeOrcamentaria = unidadeOrcamentariaService.buscar(userId,null, null, orgao);
		unidadeOrcamentaria=null;
		programa=null;
		acao=null;
	}
	
	
	public void changeUnidade() {
		
		listPrograma = programaService.buscarPorUnidadeOrcamentaria(unidadeOrcamentaria);
		programa=null;
		acao=null;
	}

	public void changePrograma() {
		
		listAcao = acaoService.buscar(null, null, null, programa, exercicioId);
		acao=null;
	}
	
	public void changeTipoRegiao() {
		listRegiao = regiaoService.findAllByTipoRegiao(tipoRegiao);
	}

	public void changeRegiao() {
		listRegiaoMunicipio = regiaoMunicipioService.findByRegiao(regiao);
	}
	
	public String gerarRelatorio() {
		
		try {
			List<RelatorioFisicoFinanceiro> relatorio = fisicoFinanceiroMensalService.relatorioPlanejamentoMensal(orgao, unidadeOrcamentaria, programa, acao, tipoRegiao, regiao, regiaoMunicipio, exercicioId);
			
			if (relatorio == null || relatorio.isEmpty()) {
				Messages.addMessageWarn(NO_DATA);
				return "";
			}
	
			Map<String, Object> parameters = new HashMap<>();
	
			String brasaoMa = FileUtil.getRealPath("/resources/images/brasao_ma.png");
	
			parameters.put("param_imagem", brasaoMa);
			
			if(getExercicio()!=null)
				parameters.put("exercicio", getExercicio().getAno().toString());
			
			String report = FileUtil.getRealPath("/relatorios/financeiro/relatorio_planejado_mensal_geral.jasper");
				
			JasperPrint jasperRelatorio = JasperFillManager.fillReport(report, parameters,new JRBeanCollectionDataSource(relatorio));
	
			if (jasperRelatorio == null || jasperRelatorio.getPages().isEmpty()) {
				Messages.addMessageWarn(REPORT_EMPTY);
				return "";
			}
	
			byte[] bytes = null;
			String fileName = null;
			String contentType = null;
	
			if ("PDF".equals(tipoArquivo)) {
	
				bytes = RelatorioUtil.exportReportToPdf(jasperRelatorio);
				fileName = "RelatorioFinanceiroNaturezaDespesa.pdf";
				contentType = TipoArquivo.PDF.getId();
	
			} else {
	
				bytes = RelatorioUtil.exportReportToXLS(jasperRelatorio);
				fileName = "RelatorioFinanceiroNaturezaDespesa.xls";
				contentType = TipoArquivo.XLS.getId();
	
			}
	
			FileUtil.sendFileOnResponseAttached(bytes, fileName, contentType);
	
		} catch (Exception e) {
			SispcaLogger.logError(e);
	
			Messages.addMessageError(FAIL_REPORT);
		}

		return "";
	}
	
	public Long getOrgao() {
		return orgao;
	}

	public void setOrgao(Long orgao) {
		this.orgao = orgao;
	}

	public List<Orgao> getListOrgao() {
		return listOrgao;
	}

	public void setListOrgao(List<Orgao> listOrgao) {
		this.listOrgao = listOrgao;
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

	public Long getTipoRegiao() {
		return tipoRegiao;
	}

	public void setTipoRegiao(Long tipoRegiao) {
		this.tipoRegiao = tipoRegiao;
	}

	public List<TipoRegiao> getListTipoRegiao() {
		return listTipoRegiao;
	}

	public void setListTipoRegiao(List<TipoRegiao> listTipoRegiao) {
		this.listTipoRegiao = listTipoRegiao;
	}

	public Long getRegiao() {
		return regiao;
	}

	public void setRegiao(Long regiao) {
		this.regiao = regiao;
	}

	public List<Regiao> getListRegiao() {
		return listRegiao;
	}

	public void setListRegiao(List<Regiao> listRegiao) {
		this.listRegiao = listRegiao;
	}

	public Long getRegiaoMunicipio() {
		return regiaoMunicipio;
	}

	public void setRegiaoMunicipio(Long regiaoMunicipio) {
		this.regiaoMunicipio = regiaoMunicipio;
	}

	public List<RegiaoMunicipio> getListRegiaoMunicipio() {
		return listRegiaoMunicipio;
	}

	public void setListRegiaoMunicipio(List<RegiaoMunicipio> listRegiaoMunicipio) {
		this.listRegiaoMunicipio = listRegiaoMunicipio;
	}
	
	
	
	
}
