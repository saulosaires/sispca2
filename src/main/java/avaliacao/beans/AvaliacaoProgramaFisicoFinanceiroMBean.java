package avaliacao.beans;

import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.service.ExercicioService;
import arquitetura.utils.Messages;
import arquitetura.utils.SispcaLogger;
import avaliacao.model.Analise;
import avaliacao.service.AnaliseService;
import qualitativo.service.ProgramaService;
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

	private List<Analise> listAnalise;
	
	private AnaliseService analiseService;
	
	@Inject
	public AvaliacaoProgramaFisicoFinanceiroMBean(ProgramaService programaService,
									     ExercicioService exercicioService,
									     FisicoFinanceiroMensalSiafemService fisicoFinanceiroMensalSiafemService,
									     AnaliseService analiseService
									    ) {

		super(programaService,exercicioService,fisicoFinanceiroMensalSiafemService);
	 
		this.analiseService = analiseService;
		
	}

	@Override
	public void init() {
		super.init();
		
		buscarAnalise();
		
	}
	
	private void buscarAnalise() {
		listAnalise = analiseService.findByProgramaAndExercicio(getPrograma().getId(), getExercicio().getId());
	}
	
	public void adicionaAvaliacaoFisicoFinanceira() {
		
		try {
		 
 			Messages.addMessageInfo(SUCCESS_SAVE);
		} catch (Exception e) {
			SispcaLogger.logError(e.getCause().getMessage());
	
			Messages.addMessageError(FAIL_SAVE);
		}
		
	}

	public List<Analise> getListAnalise() {
		return listAnalise;
	}

	public void setListAnalise(List<Analise> listAnalise) {
		this.listAnalise = listAnalise;
	}
	
	 
 
}
