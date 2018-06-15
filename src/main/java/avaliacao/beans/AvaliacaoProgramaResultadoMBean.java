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
import avaliacao.model.ExercicioTopicoResultado;
import avaliacao.model.Resultado;
import avaliacao.service.ExercicioTopicoResultadoService;
import avaliacao.service.ResultadoService;
import qualitativo.service.ProgramaService;
import siafem.service.FisicoFinanceiroMensalSiafemService;

@Named
@ViewScoped
public class AvaliacaoProgramaResultadoMBean extends AvaliacaoPrograma{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final  String SUCCESS_SAVE="Resultado Salvo com sucesso";
	private static final  String FAIL_SAVE="Falha ao Salvar Resultado";

	private ExercicioTopicoResultadoService exercicioTopicoResultadoService;
 	private ResultadoService resultadoService;
	
	private List<Resultado> listResultado;
	
	@Inject
	public AvaliacaoProgramaResultadoMBean(ProgramaService programaService,
										   ExercicioService exercicioService,
										   ExercicioTopicoResultadoService exercicioTopicoResultadoService,
										   FisicoFinanceiroMensalSiafemService fisicoFinanceiroMensalSiafemService, 			
										   ResultadoService resultadoService) {

		super(programaService,exercicioService,fisicoFinanceiroMensalSiafemService);
		
		this.exercicioTopicoResultadoService = exercicioTopicoResultadoService;
		this.resultadoService=resultadoService;
		 
		
	 
	
		
	}

	@Override
	public void init() {
		super.init();
		
		buscarResultado();
		
	}
	
	private void buscarResultado() {
		listResultado = resultadoService.findByProgramaAndExercicio(getPrograma().getId(), getPrograma().getExercicio().getId());
		
		
		if(Utils.emptyList(listResultado)) {
			
			
			List<ExercicioTopicoResultado> listExercicioTopicoResultado = exercicioTopicoResultadoService.buscarPorExercicicio(getPrograma().getExercicio().getId());
			
			listResultado = new ArrayList<>();
			
			for(ExercicioTopicoResultado exercicioTopicoResultado:listExercicioTopicoResultado) {
				
				Resultado resultado = new Resultado();
				
				resultado.setAtivo(true);
				resultado.setExercicio(getPrograma().getExercicio());
				resultado.setExercicioTopicoResultado(exercicioTopicoResultado);
				resultado.setPrograma(getPrograma());
				
				listResultado.add(resultado);
				
			}
			
			
			
		}
	}
	
	public void adicionaResultado() {
		
		try {
			
			if(!validar()) {return ;}
			
			for(Resultado resultado: listResultado) {
				resultado.setData(new Date());
				resultadoService.merge(resultado);
				
			}
			
			 
			Messages.addMessageInfo(SUCCESS_SAVE);
 
		} catch (Exception e) {
			SispcaLogger.logError(e);
	
			Messages.addMessageError(FAIL_SAVE);
		}
		
	}

	public boolean validar() {
		
		for (Resultado resultado: listResultado){
			
			if(resultado.getResposta()==null || Utils.emptyParam(resultado.getResposta().trim())) {
				Messages.addMessageError("Você precisa responder ao tópico "+ resultado.getExercicioTopicoResultado().getTopicoResultado().getDescricao());
				 return false;
			}
		}
		
		
		return true;
	}
		
	
	
	public List<Resultado> getListResultado() {
		return listResultado;
	}

	public void setListResultado(List<Resultado> listResultado) {
		this.listResultado = listResultado;
	}
	
	       

 
}
