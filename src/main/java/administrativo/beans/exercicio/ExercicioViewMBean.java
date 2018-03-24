package administrativo.beans.exercicio;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.controller.ActionCalendarioController;
import administrativo.controller.CalendarioController;
import administrativo.controller.ExercicioController;
import administrativo.model.Calendario;
import administrativo.model.Exercicio;
 

@Named
@ViewScoped
public class ExercicioViewMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8609848522488523128L;
	private ExercicioController exercicioController;
	private CalendarioController calendarioController;
	private ActionCalendarioController actionCalendarioController;
	 
	
	private Date dataInicio;
	private Date dataFim;
	 
	
	private Long exercicioId;
	
	Exercicio exercicio;
	
	
	 @Inject
	public ExercicioViewMBean( 
			 				   ExercicioController exercicioController,
			 				   CalendarioController calendarioController,
			 				   ActionCalendarioController actionCalendarioController){
		
		 this.exercicioController=exercicioController;
		 this.calendarioController=calendarioController;
		 this.actionCalendarioController=actionCalendarioController;
		 
		 
	}
 

	public void init(){
		
		
		exercicio= exercicioController.findById(exercicioId);
		
		Optional<Calendario> ca = calendarioController.findById(exercicioId);
		
		
		
		if(ca.isPresent()) {
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

	
	
	
	
}
