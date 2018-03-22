package administrativo.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import administrativo.dao.PpaDAO;
import administrativo.model.Ppa;

public class PpaController implements Serializable{

	@Inject PpaDAO ppaDAO;
	
	public List<Ppa> findAll() {
		 
		return ppaDAO.findAll();
	}

}
