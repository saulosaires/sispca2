package qualitativo.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import qualitativo.dao.FuncaoDAO;
import qualitativo.model.Funcao;

public class FuncaoService extends AbstractService<Funcao>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2854975367734660857L;

	@Inject
	public FuncaoService(FuncaoDAO dao) {
		super(dao);
	}

	public List<Funcao> findAllOrderByCodigo() {

		return ((FuncaoDAO) getDAO()).findAllOrderByCodigo();
	}

}
