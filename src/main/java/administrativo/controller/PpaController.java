package administrativo.controller;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import administrativo.dao.PpaDAO;
import administrativo.model.Ppa;
import arquitetura.controller.AbstractController;

public class PpaController extends AbstractController<Ppa> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1370755486293182418L;

	@Inject
	public PpaController(PpaDAO ppaDAO) {
		super(ppaDAO);
	}

	public List<Ppa> queryPpa(String sigla, String descricao, Integer anoInicio, Integer anoFim) {

		return ((PpaDAO) getDao()).queryPpa(sigla, descricao, anoInicio, anoFim);
	}

	public Optional<Ppa> ppaVigente() {
		return ((PpaDAO)getDao()).ppaVigente();
	}

}
