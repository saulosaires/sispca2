package administrativo.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import administrativo.dao.PpaDAO;
import administrativo.model.Ppa;

public class PpaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1370755486293182418L;

	@Inject
	PpaDAO ppaDAO;

	public List<Ppa> findAll() {

		return ppaDAO.findAll();
	}

	public List<Ppa> queryPpa(String sigla, String descricao, Integer anoInicio, Integer anoFim) {

		return ppaDAO.queryPpa(sigla, descricao, anoInicio, anoFim);
	}

	public void delete(Ppa ppa) {
		ppaDAO.delete(ppa);

	}

	public Ppa findById(Long id) {
		return ppaDAO.findOne(id);
		
	}

	public Ppa create(Ppa ppa) {
		return ppaDAO.create(ppa);
		
	}

	public Ppa update(Ppa ppa) {
		return ppaDAO.update(ppa);
		
	}

	
	
}
