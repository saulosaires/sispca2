package avaliacao.beans;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.service.ExercicioService;
import arquitetura.utils.Messages;
import arquitetura.utils.SispcaLogger;
import arquitetura.utils.Utils;
import avaliacao.model.Recomendacao;
import avaliacao.service.RecomendacaoService;
import qualitativo.service.ProgramaService;
import siafem.service.FisicoFinanceiroMensalSiafemService;

@Named
@ViewScoped
public class AvaliacaoProgramaRecomendacaoMBean extends AvaliacaoPrograma{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final  String SUCCESS_SAVE="Recomendação Salva com sucesso";
	private static final  String FAIL_SAVE="Falha ao Salvar Recomendação";

 	private RecomendacaoService recomendacaoService;
	
 	private Recomendacao recomendacao;
	
	@Inject
	public AvaliacaoProgramaRecomendacaoMBean(ProgramaService programaService,
										   ExercicioService exercicioService,
										   FisicoFinanceiroMensalSiafemService fisicoFinanceiroMensalSiafemService, 			
										   RecomendacaoService recomendacaoService) {

		super(programaService,exercicioService,fisicoFinanceiroMensalSiafemService);
		
		this.recomendacaoService=recomendacaoService;
		 
		
	 
	
		
	}

	@Override
	public void init() {
		super.init();
		
		buscarRecomendacao();
		
	}
	
	private void buscarRecomendacao() {
		recomendacao = recomendacaoService.findByProgramaAndExercicio(getPrograma().getId(), getExercicio().getId());
	}
	
	public void adicionaRecomendacao() {
		
		try {
			
			if(!validar()) {return ;}
					
			recomendacao.setExercicio(getExercicio());
			recomendacao.setPrograma(getPrograma());
			
			recomendacaoService.merge(recomendacao);
	 
			Messages.addMessageInfo(SUCCESS_SAVE);
 
		} catch (Exception e) {
			SispcaLogger.logError(e);
	
			Messages.addMessageError(FAIL_SAVE);
		}
		
	}

	public boolean validar() {
 
		if(recomendacao.getDescricao()==null || Utils.emptyParam(recomendacao.getDescricao().trim())) {
			Messages.addMessageError("Recomendação: campo é obrigatório");
			 return false;
		 
		}
		
		return true;
	}

	public Recomendacao getRecomendacao() {
		return recomendacao;
	}

	public void setRecomendacao(Recomendacao recomendacao) {
		this.recomendacao = recomendacao;
	}
		
 
	       

 
}
