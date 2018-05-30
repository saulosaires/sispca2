 
package quantitativo.beans.fisicofinanceiro.anual;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.StreamedContent;

import arquitetura.enums.TipoArquivo;
import arquitetura.utils.FileUtil;
import arquitetura.utils.Messages;
import arquitetura.utils.PrimeFacesUtils;
import arquitetura.utils.SispcaLogger;
import arquitetura.utils.Utils;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import qualitativo.model.Acao;
import qualitativo.service.AcaoService;
import quantitativo.model.FisicoFinanceiro;
import quantitativo.service.FisicoFinanceiroService;

@Named
@SessionScoped
public class FisicoFinanceiroAnualReportMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5940179508090218836L;

	public static final String FAIL_REPORT = "Falha inesperada ao tentar Imprimir Relatório";
	public static final String REPORT_EMPTY = "Não há dados financeiros para a ação selecionada";

	private Long id;

	private Acao acao;

	private StreamedContent relatorio;
	
	private AcaoService acaoService;
	private FisicoFinanceiroService fisicoFinanceiroService;

	@Inject
	public FisicoFinanceiroAnualReportMBean(AcaoService acaoService, FisicoFinanceiroService fisicoFinanceiroService) {

		this.acaoService = acaoService;
		this.fisicoFinanceiroService = fisicoFinanceiroService;

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

			parameters.put("param_id_acao", acao.getId());
			parameters.put("param_imagem", brasaoMa);
			parameters.put("param_cod_acao", acao.getCodigo());
			parameters.put("param_acao", acao.getDenominacao());
			parameters.put("param_cod_orgao", acao.getUnidadeOrcamentaria().getOrgao().getCodigo());
			parameters.put("param_orgao", acao.getUnidadeOrcamentaria().getOrgao().getDescricao());
			parameters.put("param_cod_unid_orc", acao.getUnidadeOrcamentaria().getCodigo());
			parameters.put("param_unid_orc", acao.getUnidadeOrcamentaria().getDescricao());
			parameters.put("param_cod_programa", acao.getPrograma().getCodigo());
			parameters.put("param_programa", acao.getPrograma().getDenominacao());
			parameters.put("param_unid_medida", acao.getUnidadeMedida().getDescricao());
			parameters.put("param_prod_acao", acao.getProduto());

			List<FisicoFinanceiro> listFF = fisicoFinanceiroService.findByAcao(acao.getId());

			String report = FileUtil
					.getRealPath("/relatorios/quantitativo/relatorio_fisico_financeiro_anual_acao.jasper");

			JasperPrint jasperRelatorio = JasperFillManager.fillReport(report, parameters,new JRBeanCollectionDataSource(fromFisicoFinanceiroToReport(listFF)));

			if (jasperRelatorio.getPages().isEmpty()) {
				Messages.addMessageWarn(REPORT_EMPTY);
				return null;
			}

			byte[] bytes = JasperExportManager.exportReportToPdf(jasperRelatorio);

			return PrimeFacesUtils.converBytesToStreamedContent(bytes, TipoArquivo.PDF.getId(),"relatorio_fisico_financeiro_anual_acao.pdf");

		} catch (Exception e) {

			SispcaLogger.logError(e);

			Messages.addMessageError(FAIL_REPORT);
		}

		return null;
	}

	public List<RelatorioFisicoFinanceiroAnualReport> fromFisicoFinanceiroToReport(List<FisicoFinanceiro> listFF) {

		List<RelatorioFisicoFinanceiroAnualReport> list = new ArrayList<>();

		for (FisicoFinanceiro ff : listFF) {

			list.add(new RelatorioFisicoFinanceiroAnualReport(ff.getRegiaoMunicipio().getLabel(),ff.getExercicio().getAno(), ff.getQuantidade(), ff.getValor()));
		}

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
	
	public StreamedContent getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(StreamedContent relatorio) {
		this.relatorio = relatorio;
	}



	public class RelatorioFisicoFinanceiroAnualReport {

		private String regiaoMunicipioDescricao;
		private Integer ano;
		private BigDecimal quantidade;
		private BigDecimal valor;

		public RelatorioFisicoFinanceiroAnualReport(String regiaoMunicipioDescricao, Integer ano, BigDecimal quantidade, BigDecimal valor) {
			super();
			this.regiaoMunicipioDescricao = regiaoMunicipioDescricao;
			this.ano = ano;
			this.quantidade = quantidade;
			this.valor = valor;
		}

		public String getRegiaoMunicipioDescricao() {
			return regiaoMunicipioDescricao;
		}

		public void setRegiaoMunicipioDescricao(String regiaoMunicipioDescricao) {
			this.regiaoMunicipioDescricao = regiaoMunicipioDescricao;
		}

		public Integer getAno() {
			return ano;
		}

		public void setAno(Integer ano) {
			this.ano = ano;
		}

		public BigDecimal getQuantidade() {
			return quantidade;
		}

		public void setQuantidade(BigDecimal quantidade) {
			this.quantidade = quantidade;
		}

		public BigDecimal getValor() {
			return valor;
		}

		public void setValor(BigDecimal valor) {
			this.valor = valor;
		}

	}

}
 
