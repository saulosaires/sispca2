package administrativo.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import administrativo.controller.CalendarioController;
import administrativo.controller.ExercicioController;
import administrativo.model.Calendario;
import administrativo.model.Exercicio;
import administrativo.model.Ppa;

public class ExercicioService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2698541591873129072L;

	private ExercicioController exercicioController;
	private CalendarioController calendarioController;

	@Inject
	public ExercicioService(ExercicioController exercicioController,CalendarioController calendarioController) {

		this.exercicioController = exercicioController;
		this.calendarioController=calendarioController;
	}

	public Integer quantidadeVigente() {

		return exercicioController.quantidadeVigente();

	}

	public void trocarVigencia(Exercicio exercicio) {

		exercicio.setVigente(!exercicio.getVigente());

		exercicioController.update(exercicio);

	}

	public Exercicio findOne(Long exercicioId) {
		return exercicioController.findOne(exercicioId);
	}

	public List<Exercicio> buscaExercicioPorPpaAno(Ppa buscaPpa, Integer ano) {

		return exercicioController.buscaExercicioPorPpaAno(buscaPpa, ano);

	}
	
	public 	Optional<Calendario> findCalendario(Long exercicioId) {
		return calendarioController.findById(exercicioId);
	}

}
