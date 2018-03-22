package administrativo.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import administrativo.dao.ActionCalendarioDAO;
import administrativo.dao.CalendarioDAO;
import administrativo.model.Action;
import administrativo.model.ActionCalendario;
import administrativo.model.Calendario;

public class ActionCalendarioController implements Serializable{

	@Inject ActionCalendarioDAO actionCalendarioDAO;
 
	public List<ActionCalendario> buscaActionsComCalendario(Calendario calendario, Action action) {
		
		return actionCalendarioDAO.buscaActionsComCalendario(calendario,action);
		
	}
	
	 
	
}
