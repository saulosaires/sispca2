package administrativo.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import administrativo.dao.ExercicioDAO;
import administrativo.model.Exercicio;
import administrativo.model.Ppa;

public class ExercicioController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8543732848604788698L;
	@Inject ExercicioDAO exercicioDAO;
	
	public List<Exercicio> buscaExercicioPorPpaAno(Ppa buscaPpa, Integer ano){
		
		return exercicioDAO.buscaExercicioPorPpaAno(buscaPpa, ano);
		
	}
	
	public Integer quantidadeVigente(){
	
		return exercicioDAO.quantidadeVigente();
		
	}
	
	public void trocarVigencia(Exercicio exercicio) {
		
		exercicio.setVigente(!exercicio.getVigente());
		
		exercicioDAO.update(exercicio);
		
		
	}
	
	public Exercicio findOne(Long exercicioId) {
		
		return exercicioDAO.findOne(exercicioId);
		
	}
	
	public Exercicio update(Exercicio exercicio) {
		
		return exercicioDAO.update(exercicio);
		
	}
	
	
}
