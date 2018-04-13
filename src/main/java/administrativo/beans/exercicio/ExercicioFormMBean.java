package administrativo.beans.exercicio;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import administrativo.model.Exercicio;
import administrativo.model.Ppa;
import administrativo.service.ExercicioService;
import administrativo.service.PpaService;
import arquitetura.exception.JpaException;
import arquitetura.utils.Messages;
import arquitetura.utils.SispcaLogger;

@Named
@ViewScoped
public class ExercicioFormMBean implements Serializable {

 
	/**
	 * 
	 */
	private static final long serialVersionUID = -8158918034092182434L;

	public static final String FAIL = "Falha inesperada ao tentar Salvar Exercicio";
	 
	public static final String SUCCESS = "Exercicio salvo com Sucesso";

	private PpaService ppaService;
	private ExercicioService exercicioService;
	private ExercicioValidate validate;
 
	private Exercicio exercicio = new Exercicio();
	
	private List<Ppa> listPpa;

	@Inject
	public ExercicioFormMBean(ExercicioService exercicioService,
							  ExercicioValidate validate,
							  PpaService ppaService) {

		this.exercicioService = exercicioService;
		this.ppaService		  = ppaService;
		this.validate=validate;
		
		this.listPpa = ppaService.findAll();
	}

	@Transactional
	public String salvar() {

		try {

			if (!validate.validar(exercicio)) {
				return "";
			}

			Ppa ppa = ppaService.findById(exercicio.getPpa().getId());
			exercicio.setPpa(ppa);
			
			if(exercicio.getVigente()) {
				removerVigenciaDosOutrosExercicicios(ppa);
			}
			
		 
			exercicioService.create(exercicio);
 
			
			Messages.addMessageInfo(SUCCESS);
			
			return "exercicioList";
			
		} catch (Exception e) {
			SispcaLogger.logError(e.getCause().getMessage());

			Messages.addMessageError(FAIL);
		}

		return "";
	}

	private void removerVigenciaDosOutrosExercicicios(Ppa ppa) throws JpaException {
		
		List<Exercicio> listExercicios = exercicioService.buscarPorPpa(ppa.getId());
		
		for(int i=0;i<listExercicios.size();i++) {
			
			Exercicio exer = listExercicios.get(i);
			 
			if(exer.getVigente()) {
				exer.setVigente(false);
				exercicioService.update(exer);
			}
			
		}
		
	
		
	}
	
	
	public Exercicio getExercicio() {
		return exercicio;
	}


	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}


	public List<Ppa> getListPpa() {
		return listPpa;
	}


	public void setListPpa(List<Ppa> listPpa) {
		this.listPpa = listPpa;
	}
 
	
	 
}
