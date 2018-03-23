package administrativo.controller;

import java.io.Serializable;
import java.util.Optional;

import javax.inject.Inject;

import administrativo.dao.CalendarioDAO;
import administrativo.model.Calendario;

public class CalendarioController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5660069627839389114L;
	@Inject CalendarioDAO calendarioDAO;
 
	public Optional<Calendario> findById(Long exercicioId) {
		
		return calendarioDAO.findCalendarioByExercicio(exercicioId);
		
	}
	
	 
}
