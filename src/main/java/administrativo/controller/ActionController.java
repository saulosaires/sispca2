package administrativo.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import administrativo.dao.ActionDAO;
import administrativo.model.Action;

public class ActionController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7419949642524803649L;
	@Inject ActionDAO actionDAO;
 
 
	public List<Action> buscaActionsComCalendario() {
		
		return actionDAO.buscaActionsComCalendario();
	}
	
}
