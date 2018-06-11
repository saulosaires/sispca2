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

@Named
@ViewScoped
public class RelatorioPlanoTrabalhoMBean extends RelatorioMBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String ordemCampos="A";
	
	private Long orgao;
	private List<Orgao> listOrgao;

	private Long unidadeOrcamentaria;
	private List<UnidadeOrcamentaria> listUnidadeOrcamentaria;

	private Long programa;
	private List<Programa> listPrograma;	
	
	private UnidadeOrcamentariaService unidadeOrcamentariaService;
	private AcaoService acaoService;
	private ProgramaService programaService;
	
	private Long userId;
	
	@Inject
	public RelatorioPlanoTrabalhoMBean(ExercicioService exercicioService,
									   OrgaoService orgaoService,
									   AcaoService acaoService,
									   UnidadeOrcamentariaService unidadeOrcamentariaService,
									   ProgramaService programaService) {
		super(exercicioService);
		
		Usuario user = (Usuario) SessionUtils.get(SessionUtils.USER);
		
		userId= user.getId();
		listOrgao = orgaoService.findAllOrderByDescricao();
		
		this.unidadeOrcamentariaService = unidadeOrcamentariaService;
		this.programaService = programaService;
		this.acaoService = acaoService;
	}

	
	public void buscaUnidadeByOrgao(){
		
		listUnidadeOrcamentaria = unidadeOrcamentariaService.buscar(userId,null, null, orgao);
		
	}
	
	public void buscaProgramaByUnidade() {
		
		listPrograma = programaService.buscarPorUnidadeOrcamentaria(unidadeOrcamentaria);
		
	}
	
	public String gerarRelatorio() {
		
		try {

			List<Acao> listAcao = acaoService.relatorioPlanoTrabalho(orgao, unidadeOrcamentaria, programa, orgao,ordemCampos);

			if (listAcao == null || listAcao.isEmpty()) {
				Messages.addMessageWarn(NO_DATA);
				return "";
			}

			Map<String, Object> parameters = new HashMap<>();

			String brasaoMa = FileUtil.getRealPath("/resources/images/brasao_ma.png");

			parameters.put("param_imagem", brasaoMa);

			String report =  null;

			if ("A".equals(ordemCampos)){
				report =  FileUtil.getRealPath("/relatorios/relatorio_plano_trabalho.jasper");
			}else {
				report =  FileUtil.getRealPath("/relatorios/relatorio_plano_trabalho_programa.jasper");
			}	
			
			JasperPrint jasperRelatorio = JasperFillManager.fillReport(report, parameters,new JRBeanCollectionDataSource(listAcao));

			if (jasperRelatorio == null || jasperRelatorio.getPages().isEmpty()) {
				Messages.addMessageWarn(REPORT_EMPTY);
				return "";
			}

			byte[] bytes = null;
			String fileName = null;
			String contentType = null;

			if ("PDF".equals(tipoArquivo)) {

				bytes = RelatorioUtil.exportReportToPdf(jasperRelatorio);
				fileName = "RelatorioPlanoDeTrabalho.pdf";
				contentType = TipoArquivo.PDF.getId();

			} else {

				bytes = RelatorioUtil.exportReportToXLS(jasperRelatorio);
				fileName = "RelatorioPlanoDeTrabalho.xls";
				contentType = TipoArquivo.XLS.getId();

			}

			FileUtil.sendFileOnResponseAttached(bytes, fileName, contentType);

		} catch (Exception e) {
			SispcaLogger.logError(e);

			Messages.addMessageError(FAIL_REPORT);
		}

		return "";

	}


	public String getOrdemCampos() {
		return ordemCampos;
	}


	public void setOrdemCampos(String ordemCampos) {
		this.ordemCampos = ordemCampos;
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
	
	
	
	
	
}
