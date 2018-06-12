package qualitativo.service;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import qualitativo.dao.TipoOrcamentoDAO;
import qualitativo.model.TipoOrcamento;

public class TipoOrcamentoService  extends AbstractService<TipoOrcamento> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2854975367734660857L;
 

	@Inject
	public TipoOrcamentoService(TipoOrcamentoDAO dao) {
		super(dao);
	}
 

	 
}
