package administrativo.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import administrativo.controller.PpaController;
import administrativo.model.Ppa;
import arquitetura.exception.JpaException;
import arquitetura.utils.Utils;

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

	public List<Ppa> queryPpa(String sigla, String descricao, Integer anoInicio, Integer anoFim) {

		if (Utils.emptyParam(sigla) && Utils.emptyParam(descricao) && 
		    Utils.invalidYear(anoInicio) && Utils.invalidYear(anoFim)) {
			return ppaController.findAll();
		} else {
			return ppaController.queryPpa(sigla, descricao, anoInicio, anoFim);
		}

	}

	public void delete(Ppa ppa) throws JpaException {

		ppaController.delete(ppa);

	}

	public Ppa findById(Long id) {

		return ppaController.findById(id);

	}

	public Ppa create(Ppa ppa) throws JpaException {
		return ppaController.create(ppa);

	}

	public Ppa update(Ppa ppa) throws JpaException {
		return ppaController.update(ppa);

	}
	
}
