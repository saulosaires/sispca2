package qualitativo.service;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import qualitativo.dao.TipoProgramaDAO;
import qualitativo.model.TipoPrograma;

public class TipoProgramaService extends AbstractService<TipoPrograma>  {
 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public TipoProgramaService(TipoProgramaDAO dao) {
		super(dao);
	}

	 
 

}
