package avaliacao.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.service.ExercicioService;
import arquitetura.utils.Messages;
import arquitetura.utils.SispcaLogger;
import arquitetura.utils.Utils;
import avaliacao.model.Analise;
import avaliacao.model.ExercicioTopicoAnalise;
import avaliacao.service.AnaliseService;
import avaliacao.service.ExercicioTopicoAnaliseService;
import qualitativo.service.ProgramaService;
import siafem.service.FisicoFinanceiroMensalSiafemService;

@Named
@ViewScoped
public class AvaliacaoProgramaAnaliseMBean extends AvaliacaoPrograma{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final  String SUCCESS_SAVE="Análise Salvo com sucesso";
	private static final  String FAIL_SAVE="Falha ao Salvar Painel";

	private List<Analise> listAnalise;
	
	private ExercicioTopicoAnaliseService exercicioTopicoAnaliseService;
	private AnaliseService analiseService;
	
	@Inject
	public AvaliacaoProgramaAnaliseMBean(ProgramaService programaService,
									     ExercicioService exercicioService,
									     ExercicioTopicoAnaliseService exercicioTopicoAnaliseService,
									     FisicoFinanceiroMensalSiafemService fisicoFinanceiroMensalSiafemService,
									     AnaliseService analiseService
									    ) {

		super(programaService,exercicioService,fisicoFinanceiroMensalSiafemService);
	 
		this.analiseService = analiseService;
		this.exercicioTopicoAnaliseService =exercicioTopicoAnaliseService;
	}

	@Override
	public void init() {
		super.init();
		
		buscarAnalise();
		
	}
	
	private void buscarAnalise() {
	 	
		listAnalise = analiseService.findByProgramaAndExercicio(getPrograma().getId(), getPrograma().getExercicio().getId());
		
		if(Utils.emptyList(listAnalise)) {
			
			List<ExercicioTopicoAnalise> listExercicioTopicoAnalise = exercicioTopicoAnaliseService.buscarPorExercicicio(getPrograma().getExercicio().getId());
			
			listAnalise = new ArrayList<>();
			
			for(ExercicioTopicoAnalise exercicioTopicoAnalise :listExercicioTopicoAnalise) {
				
				Analise analise = new Analise();
				
				analise.setAtivo(true);
				analise.setExercicio(getPrograma().getExercicio());
				analise.setExercicioTopicoAnalise(exercicioTopicoAnalise);
				analise.setPrograma(getPrograma());
				
				listAnalise.add(analise);
				
			}
			
		}
		
		
	}
	
	public void adicionarAnalise() {
		
		try {
			
			for(Analise analise:listAnalise) {
				analise.setData(new Date());
				analiseService.update(analise);
			}
 
 			buscarAnalise();
 			Messages.addMessageInfo(SUCCESS_SAVE);
		} catch (Exception e) {
			SispcaLogger.logError(e);
	
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
