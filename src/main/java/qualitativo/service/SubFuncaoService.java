package qualitativo.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import qualitativo.dao.SubFuncaoDAO;
import qualitativo.model.SubFuncao;

public class SubFuncaoService extends AbstractService<SubFuncao>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2854975367734660857L;

	@Inject
	public SubFuncaoService(SubFuncaoDAO dao) {
		super(dao);
	}

	public List<SubFuncao> findAllOrderByCodigo() {

		return ((SubFuncaoDAO) getDAO()).findAllOrderByCodigo();
	}

}
