package administrativo.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import administrativo.controller.PpaController;
import administrativo.model.Ppa;

public class PpaService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3288174422611664897L;

	PpaController ppaController;

	@Inject
	public PpaService(PpaController ppaController) {
		this.ppaController = ppaController;
	}

	public List<Ppa> findAll() {
		return ppaController.findAll();
	}

}
