package administrativo.beans.exercicio;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.controller.PpaController;
import administrativo.model.Calendario;
import administrativo.model.Exercicio;
import administrativo.model.Ppa;
import administrativo.service.ExercicioService;

@Named
@ViewScoped
public class ExercicioFormMBean implements Serializable {

 
	/**
	 * 
	 */
	private static final long serialVersionUID = -8158918034092182434L;

	private ExercicioService exercicioService;

	private Date dataInicio;
	private Date dataFim;
	private Long exercicioId;
	private Exercicio exercicio;
	
	private List<Ppa> listPpa;

	@Inject
	public ExercicioFormMBean(ExercicioService exercicioService,PpaController  ppaController) {

		this.exercicioService = exercicioService;
		
		this.listPpa = ppaController.findAll();
	}

	public void init() {

		exercicio = exercicioService.findOne(exercicioId);

		Optional<Calendario> ca = exercicioService.findCalendario(exercicioId);

		if (ca.isPresent()) {
			Calendario calendario = ca.get();
			dataInicio = calendario.getDataInicio();
			dataFim = calendario.getDataFim();

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

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public List<Ppa> getListPpa() {
		return listPpa;
	}

	public void setListPpa(List<Ppa> listPpa) {
		this.listPpa = listPpa;
	}
	
	

}
