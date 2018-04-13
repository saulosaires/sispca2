package administrativo.controller;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import administrativo.dao.ExercicioDAO;
import administrativo.model.Exercicio;
import administrativo.model.Ppa;
import arquitetura.controller.AbstractController;
import arquitetura.exception.JpaException;

public class ExercicioController  extends AbstractController<Exercicio>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8543732848604788698L;
	 
	
	@Inject
	public ExercicioController(ExercicioDAO exercicioDAO) {
		super(exercicioDAO);
		 
	}
 
	
	public List<Exercicio> buscarPorPpa(Long ppaId){
		
		return ((ExercicioDAO)getDao()).buscarPorPpa(ppaId);
		
	}
	
	public Integer quantidadeVigente(){
	
		return ((ExercicioDAO)getDao()).quantidadeVigente();
		
	}
	
	public void trocarVigencia(Exercicio exercicio) throws JpaException {
		
		exercicio.setVigente(!exercicio.getVigente());
		
		((ExercicioDAO)getDao()).update(exercicio);
		
		
	}
	
	public Optional<Exercicio> exercicioVigente(){
		return ((ExercicioDAO)getDao()).exercicioVigente();
	}
	
 
	
	 
	
	
}
