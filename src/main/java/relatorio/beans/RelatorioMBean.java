package relatorio.beans;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import administrativo.model.Exercicio;
import administrativo.service.ExercicioService;
import arquitetura.utils.Messages;
import arquitetura.utils.Utils;

 
public abstract class RelatorioMBean implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected static final String FAIL_REPORT = "Falha inesperada ao tentar Imprimir Relatório";
	protected static final String REPORT_EMPTY = "Não há dados para o programa Selecionado";
	protected static final String NO_DATA="Não a dados a serem exibidos";
	protected static final String NO_EXERCICIO="Selecione um exercicio na lista de Relatórios";

	
	protected Long exercicioId;

	protected Exercicio exercicio;
	
	protected String tipoArquivo="PDF";
	
	private ExercicioService exercicioService;
	
	public RelatorioMBean(ExercicioService exercicioService){
		
		this.exercicioService=exercicioService;
	}
	
	public void init() {

		if (!Utils.invalidId(exercicioId)) {

			exercicio = exercicioService.findById(exercicioId);

		}else {
			Messages.addMessageError(NO_EXERCICIO);
		}

	}

 

	public Long getExercicioId() {
		return exercicioId;
	}

	public void setExercicioId(Long exercicioId) {
		this.exercicioId = exercicioId;
	}

	public Exercicio getExercicio() {
		return exercicio;
	}

	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}

	public String getTipoArquivo() {
		return tipoArquivo;
	}

	public void setTipoArquivo(String tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}

	
	
	
	
	
}
