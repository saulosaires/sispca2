package avaliacao.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Optional;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import administrativo.model.Exercicio;
import administrativo.service.ExercicioService;
import arquitetura.utils.Utils;
import qualitativo.model.Programa;
import qualitativo.service.ProgramaService;
import siafem.service.FisicoFinanceiroMensalSiafemService;

@Named
@ViewScoped
public class AvaliacaoPrograma implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private Programa programa;
	private BigDecimal dotacaoInicial; 
	private BigDecimal dotacaoAtual;
	private BigDecimal variacao;
	
	private Exercicio exercicio;
	
	private ProgramaService programaService;
	private FisicoFinanceiroMensalSiafemService fisicoFinanceiroMensalSiafemService;

 
	public AvaliacaoPrograma(ProgramaService programaService,
												   ExercicioService exercicioService,
												   FisicoFinanceiroMensalSiafemService fisicoFinanceiroMensalSiafemService) {

		this.programaService = programaService;
		this.fisicoFinanceiroMensalSiafemService=fisicoFinanceiroMensalSiafemService;
 		
		Optional<Exercicio> exercicioOptional = exercicioService.exercicioVigente();
		if(exercicioOptional.isPresent()) {
			exercicio = exercicioOptional.get();
		}else {
			exercicio = new Exercicio();
		}
		
	
		
	}

	public void init() {

		if (!Utils.invalidId(id)) {

			programa = programaService.findById(id);
			processaCalculos();
		}

	}
	
	
	public void processaCalculos(){
		calculaDotacaoInicial();
		calculaDotacaoAtual();
		calculaVariacao();
	}	
	
	private void calculaDotacaoInicial() {
		
		dotacaoInicial = fisicoFinanceiroMensalSiafemService.calculaDotacaoInicialPorUoProg(programa.getId(), exercicio.getAno());
		
		if(dotacaoInicial==null)dotacaoInicial=new BigDecimal(0);
		
	}
	
	private void calculaDotacaoAtual() {
		dotacaoAtual= fisicoFinanceiroMensalSiafemService.calculaDotacaoAtualPorUoProg(programa.getId(), exercicio.getAno());
		
		if(dotacaoAtual==null)dotacaoAtual=new BigDecimal(0);
	}
	
	private void calculaVariacao() {
		
		try{
			this.variacao = this.dotacaoAtual.divide(dotacaoInicial, 2, RoundingMode.HALF_UP).
				subtract(new BigDecimal(1).setScale(2)).multiply(new BigDecimal(100).setScale(2), MathContext.UNLIMITED);
			
		}catch (Exception e){
			this.variacao = BigDecimal.valueOf(0.0);
		}
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public BigDecimal getDotacaoInicial() {
		return dotacaoInicial;
	}

	public void setDotacaoInicial(BigDecimal dotacaoInicial) {
		this.dotacaoInicial = dotacaoInicial;
	}

	public Exercicio getExercicio() {
		return exercicio;
	}

	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}

	public BigDecimal getDotacaoAtual() {
		return dotacaoAtual;
	}

	public void setDotacaoAtual(BigDecimal dotacaoAtual) {
		this.dotacaoAtual = dotacaoAtual;
	}

	public BigDecimal getVariacao() {
		return variacao;
	}

	public void setVariacao(BigDecimal variacao) {
		this.variacao = variacao;
	}

	
	
	
	
}
