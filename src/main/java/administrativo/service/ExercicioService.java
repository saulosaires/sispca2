package administrativo.service;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import administrativo.controller.ExercicioController;
import administrativo.model.Exercicio;
import administrativo.model.Ppa;
import arquitetura.exception.JpaException;
import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;

public class ExercicioService extends AbstractService<Exercicio>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2698541591873129072L;
 
	@Inject
	public ExercicioService(ExercicioController exercicioController) {
		super(exercicioController);
	}


	public Integer quantidadeVigente() {

		return ((ExercicioController)getController()).quantidadeVigente();

	}

	public void trocarVigencia(Exercicio exercicio) throws JpaException {

		exercicio.setVigente(!exercicio.getVigente());

		((ExercicioController)getController()).update(exercicio);

	}
	
	public Optional<Exercicio> exercicioVigente(){
		return ((ExercicioController)getController()).exercicioVigente();
	}
 

	public List<Exercicio> buscarPorPpa(Long ppaId) {

		return ((ExercicioController)getController()).buscarPorPpa(ppaId);

	}
	
	
	public List<Exercicio> buscar(Long ppaId) {

		if(Utils.invalidId(ppaId))
			return findAll();
		
		return ((ExercicioController)getController()).buscarPorPpa(ppaId);

	}
	
	
	 

}
