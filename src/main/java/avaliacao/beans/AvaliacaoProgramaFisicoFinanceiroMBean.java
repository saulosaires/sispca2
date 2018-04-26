package avaliacao.beans;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.service.ExercicioService;
import arquitetura.utils.Messages;
import arquitetura.utils.SispcaLogger;
import avaliacao.model.AvaliacaoFisicoFinanceira;
import avaliacao.service.AnaliseService;
import qualitativo.service.ProgramaService;
import siafem.model.FisicoFinanceiroMensalSiafem;
import siafem.service.FisicoFinanceiroMensalSiafemService;

@Named
@ViewScoped
public class AvaliacaoProgramaFisicoFinanceiroMBean extends AvaliacaoPrograma{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final  String SUCCESS_SAVE="Avalição Físico Financeira Salva Salvo com sucesso";
	private static final  String FAIL_SAVE="Falha ao Salvar Avalição Físico Financeira Salva";

	private List<FisicoFinanceiroMensalSiafem> listFisicoFinanceiroMensalSiafem;
	private List<AvaliacaoFisicoFinanceira> listAvaliacaoFisicoFinanceira;
	
	private FisicoFinanceiroMensalSiafemService fisicoFinanceiroMensalSiafemService;
	
	
	private Double mediaEficaciaFisicoFinanceira;
	private Double mediaEficienciaFisicoFinanceira;
	
	@Inject
	public AvaliacaoProgramaFisicoFinanceiroMBean(ProgramaService programaService,
									     ExercicioService exercicioService,
									     FisicoFinanceiroMensalSiafemService fisicoFinanceiroMensalSiafemService,
									     AnaliseService analiseService
									    ) {

		super(programaService,exercicioService,fisicoFinanceiroMensalSiafemService);
	  
		this.fisicoFinanceiroMensalSiafemService=fisicoFinanceiroMensalSiafemService;
	}

	@Override
	public void init() {
		super.init();
		
		buscarAnalise();
		
	}
	
	private void buscarAnalise() {
		listFisicoFinanceiroMensalSiafem = fisicoFinanceiroMensalSiafemService.analiseFisicoFinanceiro(getPrograma(), getExercicio());
 	}
	
	public String adicionarAvaliacaoFisicoFinanceira() {
		
		try {
		 
 			Messages.addMessageInfo(SUCCESS_SAVE);
		} catch (Exception e) {
			SispcaLogger.logError(e.getCause().getMessage());
	
			Messages.addMessageError(FAIL_SAVE);
		}
		
		return null;
		
	}

	public List<FisicoFinanceiroMensalSiafem> getListFisicoFinanceiroMensalSiafem() {
		return listFisicoFinanceiroMensalSiafem;
	}

	public void setListFisicoFinanceiroMensalSiafem(List<FisicoFinanceiroMensalSiafem> listFisicoFinanceiroMensalSiafem) {
		this.listFisicoFinanceiroMensalSiafem = listFisicoFinanceiroMensalSiafem;
	}

	public List<AvaliacaoFisicoFinanceira> getListAvaliacaoFisicoFinanceira() {
		return listAvaliacaoFisicoFinanceira;
	}

	public void setListAvaliacaoFisicoFinanceira(List<AvaliacaoFisicoFinanceira> listAvaliacaoFisicoFinanceira) {
		this.listAvaliacaoFisicoFinanceira = listAvaliacaoFisicoFinanceira;
	}

	public Double getMediaEficaciaFisicoFinanceira() {
		return mediaEficaciaFisicoFinanceira;
	}

	public void setMediaEficaciaFisicoFinanceira(Double mediaEficaciaFisicoFinanceira) {
		this.mediaEficaciaFisicoFinanceira = mediaEficaciaFisicoFinanceira;
	}

	public Double getMediaEficienciaFisicoFinanceira() {
		return mediaEficienciaFisicoFinanceira;
	}

	public void setMediaEficienciaFisicoFinanceira(Double mediaEficienciaFisicoFinanceira) {
		this.mediaEficienciaFisicoFinanceira = mediaEficienciaFisicoFinanceira;
	}

	 
	
	 
 
}
