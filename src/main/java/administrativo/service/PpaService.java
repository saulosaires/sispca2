package administrativo.service;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import administrativo.controller.PpaController;
import administrativo.model.Ppa;
import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;

public class PpaService extends AbstractService<Ppa>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3288174422611664897L;

	@Inject
	public PpaService(PpaController ppaController) {
		super(ppaController);
	}

	public List<Ppa> queryPpa(String sigla, String descricao, Integer anoInicio, Integer anoFim) {

		if (Utils.emptyParam(sigla) && Utils.emptyParam(descricao) && Utils.invalidYear(anoInicio)
				&& Utils.invalidYear(anoFim)) {
			return findAll();
		} else {
			return ((PpaController) getController()).queryPpa(sigla, descricao, anoInicio, anoFim);
		}

	}
	
	
	public Optional<Ppa> ppaVigente(){
		return ((PpaController)getController()).ppaVigente();
	}

}
