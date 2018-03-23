package administrativo.beans.exercicio;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.controller.ActionCalendarioController;
import administrativo.controller.ActionController;
import administrativo.controller.CalendarioController;
import administrativo.controller.ExercicioController;
import administrativo.controller.PpaController;
import administrativo.model.Action;
import administrativo.model.ActionCalendario;
import administrativo.model.Calendario;
import administrativo.model.Exercicio;
 

@Named
@ViewScoped
public class ExercicioViewMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8609848522488523128L;
	private PpaController ppaController;
	private ExercicioController exercicioController;
	private CalendarioController calendarioController;
	private ActionCalendarioController actionCalendarioController;
	private ActionController actionController;
	
	private Date dataInicio;
	private Date dataFim;
	private List<Action> listAction;
	private Map<Action, List<ActionCalendario>> mapActionCalendario;
	
	private Long exercicioId;
	
	Exercicio exercicio;
	
	
	 @Inject
	public ExercicioViewMBean( PpaController ppaController,
			 				   ExercicioController exercicioController,
			 				   CalendarioController calendarioController,
			 				   ActionCalendarioController actionCalendarioController,
			 				   ActionController actionController){
		
		 this.ppaController=ppaController;
		 this.exercicioController=exercicioController;
		 this.calendarioController=calendarioController;
		 this.actionCalendarioController=actionCalendarioController;
		 this.actionController= actionController;
		 
	}
 

	public void init(){
		
		
		exercicio= exercicioController.findById(exercicioId);
		
		Optional<Calendario> ca = calendarioController.findById(exercicioId);
		
		listAction= actionController.buscaActionsComCalendario();
		
		if(ca.isPresent()) {
			Calendario calendario = ca.get();
			dataInicio = calendario.getDataInicio();
			dataFim = calendario.getDataFim();	
			
			mapActionCalendario= new HashMap<>();
			for (Action ac : listAction) {
				mapActionCalendario.put(ac,actionCalendarioController.buscaActionsComCalendario(calendario, ac));
			}
			
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


	public List<Action> getListAction() {
		return listAction;
	}


	public void setListAction(List<Action> listAction) {
		this.listAction = listAction;
	}


	public Map<Action, List<ActionCalendario>> getMapActionCalendario() {
		return mapActionCalendario;
	}


	public void setMapActionCalendario(Map<Action, List<ActionCalendario>> mapActionCalendario) {
		this.mapActionCalendario = mapActionCalendario;
	}
	
	
	
	
}
