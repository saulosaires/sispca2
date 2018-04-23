package avaliacao.beans;

import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.service.ExercicioService;
import arquitetura.utils.Messages;
import arquitetura.utils.SispcaLogger;
import avaliacao.model.PainelAssociado;
import avaliacao.service.PainelAssociadoService;
import qualitativo.model.Indicador;
import qualitativo.service.IndicadorService;
import qualitativo.service.ProgramaService;
import siafem.service.FisicoFinanceiroMensalSiafemService;

@Named
@ViewScoped
public class AvaliacaoProgramaPainelAssociadaMBean extends AvaliacaoPrograma{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final  String SUCCESS_SAVE="Painel Salvo com sucesso";
	private static final  String FAIL_SAVE="Falha ao Salvar Painel";

	private static final  String SUCCESS_DELETE="Painel deletado com sucesso";
	private static final  String FAIL_DELETE="Falha ao Deletar Painel";
	
	
	private Long indicadorId;
	private List<Indicador>listIndicador; 

	private List<PainelAssociado> listPainelAssociado;
	
	private IndicadorService indicadorService;
	private PainelAssociadoService painelAssociadoService;
	
	@Inject
	public AvaliacaoProgramaPainelAssociadaMBean(ProgramaService programaService,
												   ExercicioService exercicioService,
												   FisicoFinanceiroMensalSiafemService fisicoFinanceiroMensalSiafemService,
												   IndicadorService indicadorService,
												   PainelAssociadoService painelAssociadoService) {

		super(programaService,exercicioService,fisicoFinanceiroMensalSiafemService);
		
		this.indicadorService=indicadorService;
		this.painelAssociadoService=painelAssociadoService;
		
		listIndicador = indicadorService.findAllOrderByIndicador();
	
		
	}

	@Override
	public void init() {
		super.init();
		
		buscarPainelAssociado();
		
	}
	
	private void buscarPainelAssociado() {
		listPainelAssociado = painelAssociadoService.findByProgramaAndExercicio(getPrograma().getId(), getExercicio().getId());
	}
	
	public void adicionaPainelAssociado() {
		
		try {
			
			PainelAssociado painelAssoc = new PainelAssociado();
			painelAssoc.setData(new Date());
			painelAssoc.setExercicio(getExercicio());
			painelAssoc.setIndicador(indicadorService.findById(indicadorId));
			painelAssoc.setPrograma(getPrograma());
			 
			 
			Messages.addMessageInfo(SUCCESS_SAVE);
		
			painelAssociadoService.create(painelAssoc);
			indicadorId=null;
			
			buscarPainelAssociado();
			
		} catch (Exception e) {
			SispcaLogger.logError(e.getCause().getMessage());
	
			Messages.addMessageError(FAIL_SAVE);
		}
		
	}
	
	public void deletaPainelAssociado(PainelAssociado painelAssoc){
	
		try {
			
			painelAssociadoService.delete(painelAssoc);
			buscarPainelAssociado();
			Messages.addMessageInfo(SUCCESS_DELETE);
		
		} catch (Exception e) {
			SispcaLogger.logError(e.getCause().getMessage());
	
			Messages.addMessageError(FAIL_DELETE);
		}
		
		
		
	}

	public Long getIndicadorId() {
		return indicadorId;
	}

	public void setIndicadorId(Long indicadorId) {
		this.indicadorId = indicadorId;
	}

	public List<Indicador> getListIndicador() {
		return listIndicador;
	}

	public void setListIndicador(List<Indicador> listIndicador) {
		this.listIndicador = listIndicador;
	}

	public List<PainelAssociado> getListPainelAssociado() {
		return listPainelAssociado;
	}

	public void setListPainelAssociado(List<PainelAssociado> listPainelAssociado) {
		this.listPainelAssociado = listPainelAssociado;
	}

	 
	
	
 
	
	
}
