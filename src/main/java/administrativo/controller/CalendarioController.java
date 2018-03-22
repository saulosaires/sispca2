package administrativo.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import administrativo.dao.CalendarioDAO;
import administrativo.model.Action;
import administrativo.model.Calendario;

public class CalendarioController implements Serializable{

	@Inject CalendarioDAO calendarioDAO;
 
	public Optional<Calendario> findById(Long exercicioId) {
		
		return calendarioDAO.findCalendarioByExercicio(exercicioId);
		
	}
	
	 
}
