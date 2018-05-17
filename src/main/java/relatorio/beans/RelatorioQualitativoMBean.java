package relatorio.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.service.ExercicioService;
import arquitetura.enums.TipoArquivo;
import arquitetura.utils.FileUtil;
import arquitetura.utils.Messages;
import arquitetura.utils.RelatorioUtil;
import arquitetura.utils.SispcaLogger;
import arquitetura.utils.Utils;
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

@Named
@ViewScoped
public class RelatorioQualitativoMBean extends RelatorioMBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long orgao;
	private List<Orgao> listOrgao;

	
	private Long unidadeOrcamentaria;
	private List<UnidadeOrcamentaria> listUnidadeOrcamentaria;

	private Long programa;
	private List<Programa> listPrograma;	

	private Long acao;
	private List<Acao> listAcoes;	
	
	private boolean acoesUnidadeResponsavel;
	
	private AcaoService acaoService;
	private UnidadeOrcamentariaService unidadeOrcamentariaService;
	private ProgramaService programaService;
	
	
	@Inject
	public RelatorioQualitativoMBean(ExercicioService exercicioService,
									 OrgaoService orgaoService,
									 AcaoService acaoService,
									 UnidadeOrcamentariaService unidadeOrcamentariaService,
									 ProgramaService programaService) {
		super(exercicioService);

		listOrgao = orgaoService.findAllOrderByDescricao();
		
		this.acaoService = acaoService;
		this.unidadeOrcamentariaService = unidadeOrcamentariaService;
		this.programaService = programaService;
		
	}

	public void changeOrgao() {
		
		listUnidadeOrcamentaria = unidadeOrcamentariaService.buscar(null, null, orgao);
		
	}

	public void changeUnidadeOrcamentaria() {
		listPrograma = programaService.buscarPorUnidadeOrcamentaria(unidadeOrcamentaria);
	}
	
	public void changePrograma() {
		listAcoes = acaoService.buscar(null, null, null, programa, null);
	}
	
	public void todasAcoes() {
		
		listAcoes = new ArrayList<>();
		
		if(!Utils.invalidId(programa))
			listAcoes.addAll(acaoService.buscar(null, null, null, programa, null));
		
		if(!Utils.invalidId(unidadeOrcamentaria))
			listAcoes.addAll(acaoService.buscar(null, null, unidadeOrcamentaria, null, null));
	}
	
	
	public String gerarRelatorio(){
		
		try {

			List<Acao> listaAcao = acaoService.relatorioQualitativoProgramasAcoes(orgao, unidadeOrcamentaria, programa, acao, exercicioId);
			
			if (listaAcao == null || listaAcao.isEmpty()) {
				Messages.addMessageWarn(NO_DATA);
				return "";
			}

			Map<String, Object> parameters = new HashMap<>();

			String brasaoMa = FileUtil.getRealPath("/resources/images/maranhao_brasao.png");

			parameters.put("MARANHAO_BRASAO", brasaoMa);

			String report =   FileUtil.getRealPath("/relatorios/qualitativo/relatorio_qualitativo.jasper");
		 
			
			JasperPrint jasperRelatorio = JasperFillManager.fillReport(report, parameters,new JRBeanCollectionDataSource(listaAcao));

			if (jasperRelatorio == null || jasperRelatorio.getPages().isEmpty()) {
				Messages.addMessageWarn(REPORT_EMPTY);
				return "";
			}

			byte[] bytes = null;
			String fileName = null;
			String contentType = null;

			bytes = RelatorioUtil.exportReportToPdf(jasperRelatorio);
			fileName = "relatorioQualitativo.pdf";
			contentType = TipoArquivo.PDF.getId();
			
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

	public List<Acao> getListAcoes() {
		return listAcoes;
	}

	public void setListAcoes(List<Acao> listAcoes) {
		this.listAcoes = listAcoes;
	}

	public boolean isAcoesUnidadeResponsavel() {
		return acoesUnidadeResponsavel;
	}

	public void setAcoesUnidadeResponsavel(boolean acoesUnidadeResponsavel) {
		this.acoesUnidadeResponsavel = acoesUnidadeResponsavel;
	}



	
	
	
	
}
