package administrativo.service;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import administrativo.dao.ExercicioDAO;
import administrativo.model.Exercicio;
import arquitetura.exception.JpaException;
import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;

public class ExercicioService extends AbstractService<Exercicio>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2698541591873129072L;
 
	@Inject
	public ExercicioService(ExercicioDAO dao) {
		super(dao);
	}

    private ExercicioDAO dao() {
    	return ((ExercicioDAO)getDAO());
    }
	
	public Integer quantidadeVigente() {

		return dao().quantidadeVigente();

	}

	public Exercicio trocarVigencia(Exercicio exercicio) throws JpaException {

		exercicio.setVigente(!exercicio.getVigente());

		return dao().update(exercicio);

	}
	
	public Optional<Exercicio> exercicioVigente(){
		return dao().exercicioVigente();
	}
 
	public Optional<Exercicio> exercicioPorAno(Integer ano){
		return ((ExercicioDAO)getDAO()).exercicioPorAno(ano);
	}
	
	
	public Optional<Exercicio> exercicioAnterior(){

		Optional<Exercicio> exercicioVigente = exercicioVigente();
		
		if(exercicioVigente.isPresent()) {
			
			Exercicio exercicio = exercicioVigente.get();
			
			return exercicioPorAno(exercicio.getAno()-1);
			
		}else {
			
			return Optional.empty();
		}
		
		
	}
	

	public List<Exercicio> buscarPorPpa(Long ppaId) {

		return dao().buscarPorPpa(ppaId);

	}
	
	
	public List<Exercicio> buscar(Long ppaId) {

		if(Utils.invalidId(ppaId))
			return findAll();
		
		return dao().buscarPorPpa(ppaId);

	}
	
	
	 

}
