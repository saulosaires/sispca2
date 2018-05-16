package avaliacao.beans;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.service.ExercicioService;
import arquitetura.utils.Messages;
import arquitetura.utils.SispcaLogger;
import arquitetura.utils.Utils;
import avaliacao.model.IndicadorDesempenhoIntermediario;
import avaliacao.service.IndicadorDesempenhoIntermediarioService;
import qualitativo.model.UnidadeMedida;
import qualitativo.service.ProgramaService;
import qualitativo.service.UnidadeMedidaService;
import siafem.service.FisicoFinanceiroMensalSiafemService;

@Named
@ViewScoped
public class AvaliacaoProgramaIndicadorIntermediarioMBean extends AvaliacaoPrograma{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final  String SUCCESS_SAVE  ="Indicador de Desempenho Intermediário Salvo com sucesso";
	private static final  String FAIL_SAVE	   ="Falha ao Salvar Indicador de Desempenho Intermediário";
	private static final  String SUCCESS_DELETE="Indicador de Desempenho Intermediário deletado com sucesso";
	private static final  String FAIL_DELETE   ="Indicador de Desempenho Intermediário ao Deletar Painel";
	
	
	private IndicadorDesempenhoIntermediario intermediarioAssociado;
	
	private List<UnidadeMedida> listunidMedida;
	
	private List<IndicadorDesempenhoIntermediario> listIntermediarioAssociado;
	
	private UnidadeMedidaService unidadeMedidaService;
	private IndicadorDesempenhoIntermediarioService indicadorDesempenhoIntermediarioService;
	
	
	@Inject
	public AvaliacaoProgramaIndicadorIntermediarioMBean(ProgramaService programaService,
												   		ExercicioService exercicioService,
												   		FisicoFinanceiroMensalSiafemService fisicoFinanceiroMensalSiafemService,
												   		UnidadeMedidaService unidadeMedidaService,
												   		IndicadorDesempenhoIntermediarioService indicadorDesempenhoIntermediarioService) {

		super(programaService,exercicioService,fisicoFinanceiroMensalSiafemService);
 
		this.indicadorDesempenhoIntermediarioService=indicadorDesempenhoIntermediarioService;
		this.unidadeMedidaService=unidadeMedidaService;
		
		listunidMedida = unidadeMedidaService.findAllOrderByDecricao();
	
		
	}

	@Override
	public void init() {
		super.init();
		
		intermediarioAssociado = new IndicadorDesempenhoIntermediario();
		
		buscarPainelAssociado();
		
	}
	
	private void buscarPainelAssociado() {
		listIntermediarioAssociado = indicadorDesempenhoIntermediarioService.findByProgramaAndExercicio(getPrograma().getId(), getExercicio().getId());
	}
	
	public void adicionaIntermediarioAssociado() {
		
		try {
			
			if(!validar(intermediarioAssociado)) {
				return;
			} 
			
			intermediarioAssociado.setExercicio(getExercicio());
			intermediarioAssociado.setPrograma(getPrograma()); 
			
			if(!Utils.invalidId(intermediarioAssociado.getUnidadeMedida().getId())) {
				intermediarioAssociado.setUnidadeMedida(unidadeMedidaService.findById(intermediarioAssociado.getUnidadeMedida().getId()));
			}else {
				intermediarioAssociado.setUnidadeMedida(null);
			}
			 
			
		
			indicadorDesempenhoIntermediarioService.create(intermediarioAssociado);
		 
			intermediarioAssociado = new IndicadorDesempenhoIntermediario();
			
			buscarPainelAssociado();
			
			Messages.addMessageInfo(SUCCESS_SAVE);
		} catch (Exception e) {
			SispcaLogger.logError(e);
	
			Messages.addMessageError(FAIL_SAVE);
		}
		
	}
	
	public void deletaIntermediarioAssociado(IndicadorDesempenhoIntermediario indicadorDesempenhoIntermediario){
	
		try {
			
			indicadorDesempenhoIntermediarioService.delete(indicadorDesempenhoIntermediario);
			buscarPainelAssociado();
			Messages.addMessageInfo(SUCCESS_DELETE);
		
		} catch (Exception e) {
			SispcaLogger.logError(e);
	
			Messages.addMessageError(FAIL_DELETE);
		}
			
		
	}

	public boolean validar(IndicadorDesempenhoIntermediario intermediarioAssociado) {
		 
		
		boolean valido = true;
		if (intermediarioAssociado.getIndicador()==null || intermediarioAssociado.getIndicador().trim().equals("")){
			Messages.addMessageError( "Indicador: campo é obrigatório");
			valido = false;
		}
		if (intermediarioAssociado.getUnidadeMedida()==null){
			Messages.addMessageError("Unidade de Medida: campo é obrigatório");
			valido = false;
		}
		if (intermediarioAssociado.getRefDataApuracao()==null){			
			Messages.addMessageError("Ref Data de Apuração: campo é obrigatório");
			valido = false;
		}
		if (intermediarioAssociado.getRefValor()==null){
			Messages.addMessageError("Ref Valor: campo é obrigatório");
			valido = false;			
		}
		if (intermediarioAssociado.getResultadoAnoApurado()==null){
			Messages.addMessageError("Resultado Ano Apurado: campo é obrigatório");
			valido = false;			
		}
		if (intermediarioAssociado.getResultadoAnoEsperado()==null){
			Messages.addMessageError("Resultado Ano Esperado: campo é obrigatório");
			valido = false;			
		}	
		if (intermediarioAssociado.getFonte()==null || intermediarioAssociado.getFonte().trim().equals("")){
			Messages.addMessageError( "Fonte: campo é obrigatório");
			valido = false;			
		}		
		 
		return valido;		
		
	}

	public IndicadorDesempenhoIntermediario getIntermediarioAssociado() {
		return intermediarioAssociado;
	}

	public void setIntermediarioAssociado(IndicadorDesempenhoIntermediario intermediarioAssociado) {
		this.intermediarioAssociado = intermediarioAssociado;
	}

	public List<UnidadeMedida> getListunidMedida() {
		return listunidMedida;
	}

	public void setListunidMedida(List<UnidadeMedida> listunidMedida) {
		this.listunidMedida = listunidMedida;
	}

	public List<IndicadorDesempenhoIntermediario> getListIntermediarioAssociado() {
		return listIntermediarioAssociado;
	}

	public void setListIntermediarioAssociado(List<IndicadorDesempenhoIntermediario> listIntermediarioAssociado) {
		this.listIntermediarioAssociado = listIntermediarioAssociado;
	}

	
	

}
