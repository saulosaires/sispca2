package qualitativo.service;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import qualitativo.dao.TipoFormaImplementacaoDAO;
import qualitativo.model.TipoFormaImplementacao;

public class TipoFormaImplementacaoService  extends AbstractService<TipoFormaImplementacao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2854975367734660857L;
 

	@Inject
	public TipoFormaImplementacaoService(TipoFormaImplementacaoDAO dao) {
		super(dao);
	}
 

	 
}
