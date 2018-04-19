package monitoramento.beans.fisicofinanceiro.mensal;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import arquitetura.utils.Messages;
import arquitetura.utils.SessionUtils;
import arquitetura.utils.SispcaLogger;
import qualitativo.model.Acao;
import qualitativo.model.Programa;
import qualitativo.model.UnidadeOrcamentaria;
import qualitativo.service.AcaoService;
import qualitativo.service.ProgramaService;
import qualitativo.service.UnidadeOrcamentariaService;
import quantitativo.model.FisicoFinanceiro;

@Named
@ViewScoped
public class MonitoramentoFisicoFinanceiroMensalListMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5940179508090218836L;

 
	public static final String FAIL_SEARCH 	  = "Falha ao pesquisar Ações";
	public static final String  NO_RECORDS	  = "Nenhuma Ação Retornada";
	
	private String denominacao;
	private String codigo;
	private Long unidadeOrcamentariaId;
	private Long programaId;
	
	private List<UnidadeOrcamentaria> listUnidadeOrcamentaria;
	private List<Programa> listPrograma;
	
	private List<Acao>listAcoes;
 
    private AcaoService acaoService;
	
    private Boolean atualizar;
    private Boolean relatorio;
    
	@Inject
	public MonitoramentoFisicoFinanceiroMensalListMBean(AcaoService acaoService ,
										   UnidadeOrcamentariaService  unidadeOrcamentariaService, 
										   ProgramaService programaService) {
		
		this.acaoService   = acaoService;
		
		this.listUnidadeOrcamentaria = unidadeOrcamentariaService.findAllOrderByDescricao();
		this.listPrograma 		     = programaService.findAllOrderByDenominacao();
 		
		atualizar = SessionUtils.containsKey("monitoramentoFisicoFinanceiroMensalAtualizar");
		relatorio = SessionUtils.containsKey("monitoramentoFisicoFinanceiroMensalRelatorio");
		
		
		
	}

	
	public void buscar() {

		try {
			
			listAcoes = acaoService.buscar(codigo, denominacao, unidadeOrcamentariaId, programaId);

			if(listUnidadeOrcamentaria.isEmpty()) {
				Messages.addMessageWarn(NO_RECORDS);
			}
			
		} catch (Exception e) {
			SispcaLogger.logError(e.getCause().getMessage());

			Messages.addMessageError(FAIL_SEARCH);

		}
	}

	public String relatorio(FisicoFinanceiro fisicoFinanceiro) {
 
		return "";

	}


	public String getDenominacao() {
		return denominacao;
	}


	public void setDenominacao(String denominacao) {
		this.denominacao = denominacao;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public Long getUnidadeOrcamentariaId() {
		return unidadeOrcamentariaId;
	}


	public void setUnidadeOrcamentariaId(Long unidadeOrcamentariaId) {
		this.unidadeOrcamentariaId = unidadeOrcamentariaId;
	}


	public Long getProgramaId() {
		return programaId;
	}


	public void setProgramaId(Long programaId) {
		this.programaId = programaId;
	}


	public List<UnidadeOrcamentaria> getListUnidadeOrcamentaria() {
		return listUnidadeOrcamentaria;
	}


	public void setListUnidadeOrcamentaria(List<UnidadeOrcamentaria> listUnidadeOrcamentaria) {
		this.listUnidadeOrcamentaria = listUnidadeOrcamentaria;
	}


	public List<Programa> getListPrograma() {
		return listPrograma;
	}


	public void setListPrograma(List<Programa> listPrograma) {
		this.listPrograma = listPrograma;
	}


	public List<Acao> getListAcoes() {
		return listAcoes;
	}


	public void setListAcoes(List<Acao> listAcoes) {
		this.listAcoes = listAcoes;
	}


	public Boolean getAtualizar() {
		return atualizar;
	}


	public void setAtualizar(Boolean atualizar) {
		this.atualizar = atualizar;
	}


	public Boolean getRelatorio() {
		return relatorio;
	}


	public void setRelatorio(Boolean relatorio) {
		this.relatorio = relatorio;
	}

	
	 
	 
	
}
