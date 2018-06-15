package avaliacao.beans;

import java.math.BigDecimal;
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
import avaliacao.model.AvaliacaoFisicoFinanceira;
import avaliacao.model.ExercicioTopicoAvaliacao;
import avaliacao.service.AnaliseService;
import avaliacao.service.AvaliacaoFisicoFinanceiraService;
import avaliacao.service.ExercicioTopicoAvaliacaoService;
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

	private static final  String SUCCESS_SAVE="Avalição Físico Financeira Salva com sucesso";
	private static final  String FAIL_SAVE="Falha ao Salvar Avalição Físico Financeira Salva";

	private List<FisicoFinanceiroMensalSiafem> listFisicoFinanceiroMensalSiafem;
	private List<AvaliacaoFisicoFinanceira> listAvaliacaoFisicoFinanceira;
	
	private FisicoFinanceiroMensalSiafemService fisicoFinanceiroMensalSiafemService;
	private AvaliacaoFisicoFinanceiraService avaliacaoFisicoFinanceiraService;
	private ExercicioTopicoAvaliacaoService exercicioTopicoAvaliacaoService;
	
	private BigDecimal mediaEficaciaFisicoFinanceira;
	private BigDecimal mediaEficienciaFisicoFinanceira;

	
	
	@Inject
	public AvaliacaoProgramaFisicoFinanceiroMBean(ProgramaService programaService,
											      ExercicioService exercicioService,
											      FisicoFinanceiroMensalSiafemService fisicoFinanceiroMensalSiafemService,
											      AvaliacaoFisicoFinanceiraService avaliacaoFisicoFinanceiraService,
											      ExercicioTopicoAvaliacaoService exercicioTopicoAvaliacaoService,
											      AnaliseService analiseService ) {

		super(programaService,exercicioService,fisicoFinanceiroMensalSiafemService);
	  
		this.fisicoFinanceiroMensalSiafemService=fisicoFinanceiroMensalSiafemService;
		this.avaliacaoFisicoFinanceiraService=avaliacaoFisicoFinanceiraService;
		this.exercicioTopicoAvaliacaoService=exercicioTopicoAvaliacaoService;
		
	}

	@Override
	public void init() {
		super.init();
		
		buscarAvaliacaoFisicoFinanceira();

		buscarFisicoFinanceiro();
		
	}
	
	
	private void buscarAvaliacaoFisicoFinanceira() {
		
		
		listAvaliacaoFisicoFinanceira = avaliacaoFisicoFinanceiraService.findByProgramaAndExercicio(getPrograma().getId(), getPrograma().getExercicio().getId());

		if (this.listAvaliacaoFisicoFinanceira .isEmpty()){
			
			List<ExercicioTopicoAvaliacao> listExercicioTopicoAvaliacao = exercicioTopicoAvaliacaoService.findByExercicio(getPrograma().getExercicio().getId());

			listAvaliacaoFisicoFinanceira = new ArrayList<>();
			
			for (ExercicioTopicoAvaliacao exercTopAval: listExercicioTopicoAvaliacao){
				
				AvaliacaoFisicoFinanceira avalFinanceira = new AvaliacaoFisicoFinanceira();
										  avalFinanceira.setData(new Date());
										  avalFinanceira.setExercicio(getPrograma().getExercicio());
										  avalFinanceira.setExercicioTopicoAvaliacao(exercTopAval);
										  avalFinanceira.setPrograma(getPrograma());
										  avalFinanceira.setResposta("");
				
				this.listAvaliacaoFisicoFinanceira.add(avalFinanceira);
				
			}
		}
		
	}
	
	private void buscarFisicoFinanceiro() {
		listFisicoFinanceiroMensalSiafem = fisicoFinanceiroMensalSiafemService.analiseFisicoFinanceiro(getPrograma(), getPrograma().getExercicio());
 	
		calculaMediaAvaliacaoFisicoFinanceira(listFisicoFinanceiroMensalSiafem);
	}
	
	private void calculaMediaAvaliacaoFisicoFinanceira(List<FisicoFinanceiroMensalSiafem> listFisicoFinanceiroMensalSiafem) {
		
		mediaEficaciaFisicoFinanceira   = fisicoFinanceiroMensalSiafemService.calculaMediaEficaciaAvaliacaoFisicoFinanceira(listFisicoFinanceiroMensalSiafem);
		mediaEficienciaFisicoFinanceira = fisicoFinanceiroMensalSiafemService.calculaEficienciaMediaAvaliacaoFisicoFinanceira(listFisicoFinanceiroMensalSiafem);
	 	
	}
 

	
	public void adicionarAvaliacaoFisicoFinanceira() {
		
		try {
			
			if(!validar()) {return ;}
		 
			for (AvaliacaoFisicoFinanceira avalFisFinanc: listAvaliacaoFisicoFinanceira){
				
				avaliacaoFisicoFinanceiraService.merge(avalFisFinanc);
			}
			
 			Messages.addMessageInfo(SUCCESS_SAVE);
		} catch (Exception e) {
			SispcaLogger.logError(e);
	
			Messages.addMessageError(FAIL_SAVE);
		}
		
		
	}

	public boolean validar() {
		
		for (AvaliacaoFisicoFinanceira avalFisFinanc: listAvaliacaoFisicoFinanceira){
			
			if(avalFisFinanc.getResposta()==null || Utils.emptyParam(avalFisFinanc.getResposta().trim())) {
				Messages.addMessageError("Você precisa responder ao tópico "+ avalFisFinanc.getExercicioTopicoAvaliacao().getTopicoAvaliacao().getDescricao());
				 return false;
			}
		}
		
		
		return true;
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

	public BigDecimal getMediaEficaciaFisicoFinanceira() {
		return mediaEficaciaFisicoFinanceira;
	}

	public void setMediaEficaciaFisicoFinanceira(BigDecimal mediaEficaciaFisicoFinanceira) {
		this.mediaEficaciaFisicoFinanceira = mediaEficaciaFisicoFinanceira;
	}

	public BigDecimal getMediaEficienciaFisicoFinanceira() {
		return mediaEficienciaFisicoFinanceira;
	}

	public void setMediaEficienciaFisicoFinanceira(BigDecimal mediaEficienciaFisicoFinanceira) {
		this.mediaEficienciaFisicoFinanceira = mediaEficienciaFisicoFinanceira;
	}

	 
	
	 
 
}
 
