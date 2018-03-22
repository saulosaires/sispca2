package administrativo.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import administrativo.dao.ActionDAO;
import administrativo.dao.CalendarioDAO;
import administrativo.model.Action;
import administrativo.model.Calendario;

public class ActionController implements Serializable{

	@Inject ActionDAO actionDAO;
 
 
	public List<Action> buscaActionsComCalendario() {
		
		return actionDAO.buscaActionsComCalendario();
	}
	
}
