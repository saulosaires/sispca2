package qualitativo.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import qualitativo.dao.TipoAcaoDAO;
import qualitativo.model.TipoAcao;

public class TipoAcaoService extends AbstractService<TipoAcao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2854975367734660857L;

	@Inject
	public TipoAcaoService(TipoAcaoDAO dao) {
		super(dao);
	}

	public List<TipoAcao> findAllOrderByDecricao() {

		return ((TipoAcaoDAO) getDAO()).findAllOrderByDescricao();
	}

}
