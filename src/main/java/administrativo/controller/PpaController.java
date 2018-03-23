package administrativo.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import administrativo.dao.PpaDAO;
import administrativo.model.Ppa;

public class PpaController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1370755486293182418L;
	@Inject PpaDAO ppaDAO;
	
	public List<Ppa> findAll() {
		 
		return ppaDAO.findAll();
	}

}
