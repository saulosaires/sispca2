package administrativo.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import administrativo.dao.ActionCalendarioDAO;
import administrativo.model.Action;
import administrativo.model.ActionCalendario;
import administrativo.model.Calendario;

public class ActionCalendarioController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6478996224578869634L;
	@Inject ActionCalendarioDAO actionCalendarioDAO;
 
	public List<ActionCalendario> buscaActionsComCalendario(Calendario calendario, Action action) {
		
		return actionCalendarioDAO.buscaActionsComCalendario(calendario,action);
		
	}
	
	 
	
}
