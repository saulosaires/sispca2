package avaliacao.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.model.Exercicio;
import administrativo.service.ExercicioService;
import qualitativo.model.Programa;
import qualitativo.service.ProgramaService;

@Named
@ViewScoped
public class AvaliacaoProgramaMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Programa> listPrograma;

	private boolean editarAvaliacao = false;

	@Inject
	public AvaliacaoProgramaMBean(ProgramaService programaService, ExercicioService exercicioService) {

		Optional<Exercicio> exercicioAnterior = exercicioService.exercicioAnterior();
		
		if(exercicioAnterior.isPresent()) {
		
		   Exercicio exercicio = exercicioAnterior.get();
			
		   this.listPrograma = programaService.buscarPorExercicio(exercicio.getId());
		}
		
		
	}

	
	public String gerarRelatorio(Programa programa) {
		
		
		
		return "";
	}
	
	
	public List<Programa> getListPrograma() {
		return listPrograma;
	}

	public void setListPrograma(List<Programa> listPrograma) {
		this.listPrograma = listPrograma;
	}

	public boolean isEditarAvaliacao() {
		return editarAvaliacao;
	}

	public void setEditarAvaliacao(boolean editarAvaliacao) {
		this.editarAvaliacao = editarAvaliacao;
	}

}
