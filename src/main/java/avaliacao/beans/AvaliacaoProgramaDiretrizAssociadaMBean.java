package avaliacao.beans;

import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.service.ExercicioService;
import arquitetura.utils.Messages;
import arquitetura.utils.SispcaLogger;
import avaliacao.model.DiretrizAssociada;
import avaliacao.service.DiretrizAssociadaService;
import qualitativo.model.Diretriz;
import qualitativo.service.DiretrizService;
import qualitativo.service.ProgramaService;
import siafem.service.FisicoFinanceiroMensalSiafemService;

@Named
@ViewScoped
public class AvaliacaoProgramaDiretrizAssociadaMBean extends AvaliacaoPrograma{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final  String SUCCESS_SAVE_DIRETRIZ="Diretriz Salva com sucesso";
	private static final  String FAIL_SAVE_DIRETRIZ="Falha ao Salvar Diretriz";

	private static final  String SUCCESS_DELETE_DIRETRIZ="Diretriz deletada com sucesso";
	private static final  String FAIL_DELETE_DIRETRIZ="Falha ao Deletar Diretriz";
	
	
	private Long diretrizId;
	private List<Diretriz>listDiretriz; 

	private List<DiretrizAssociada> listDiretrizAssociada;
	
	private DiretrizService diretrizService;
	private DiretrizAssociadaService diretrizAssociadaService;
	
	@Inject
	public AvaliacaoProgramaDiretrizAssociadaMBean(ProgramaService programaService,
												   ExercicioService exercicioService,
												   FisicoFinanceiroMensalSiafemService fisicoFinanceiroMensalSiafemService,
												   DiretrizService diretrizService,
												   DiretrizAssociadaService diretrizAssociadaService) {

		super(programaService,exercicioService,fisicoFinanceiroMensalSiafemService);
		
		this.diretrizService=diretrizService;
		this.diretrizAssociadaService=diretrizAssociadaService;
		
		listDiretriz = diretrizService.findAll();
	
		
	}

	@Override
	public void init() {
		super.init();
		
		buscarDiretrizAssociada();
		
	}
	
	private void buscarDiretrizAssociada() {
		listDiretrizAssociada = diretrizAssociadaService.findByProgramaAndExercicio(getPrograma().getId(), getExercicio().getId());
	}
	
	public void adicionaDiretrizAssociada() {
		
		try {
			
			DiretrizAssociada dir = new DiretrizAssociada();
			dir.setDiretriz(diretrizService.findById(diretrizId));
			dir.setData(new Date());
			dir.setExercicio(getExercicio());
			dir.setPrograma(getPrograma());
			 
			
		
			diretrizAssociadaService.create(dir);
			diretrizId=null;
			buscarDiretrizAssociada();
			Messages.addMessageInfo(SUCCESS_SAVE_DIRETRIZ);
		} catch (Exception e) {
			SispcaLogger.logError(e.getCause().getMessage());
	
			Messages.addMessageError(FAIL_SAVE_DIRETRIZ);
		}
		
	}
	
	public void deletaDiretrizAssociada(DiretrizAssociada diretrizAssociada){
	
		try {
			
			diretrizAssociadaService.delete(diretrizAssociada);
			buscarDiretrizAssociada();
			Messages.addMessageInfo(SUCCESS_DELETE_DIRETRIZ);
		
		} catch (Exception e) {
			SispcaLogger.logError(e.getCause().getMessage());
	
			Messages.addMessageError(FAIL_DELETE_DIRETRIZ);
		}
		
		
		
	}
	
	public List<Diretriz> getListDiretriz() {
		return listDiretriz;
	}

	public void setListDiretriz(List<Diretriz> listDiretriz) {
		this.listDiretriz = listDiretriz;
	}

	public Long getDiretrizId() {
		return diretrizId;
	}

	public void setDiretrizId(Long diretrizId) {
		this.diretrizId = diretrizId;
	}

	public List<DiretrizAssociada> getListDiretrizAssociada() {
		return listDiretrizAssociada;
	}

	public void setListDiretrizAssociada(List<DiretrizAssociada> listDiretrizAssociada) {
		this.listDiretrizAssociada = listDiretrizAssociada;
	}
 
}
