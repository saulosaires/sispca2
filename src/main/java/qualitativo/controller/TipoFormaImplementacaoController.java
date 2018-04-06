package qualitativo.controller;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import qualitativo.dao.TipoFormaImplementacaoDAO;
import qualitativo.model.TipoFormaImplementacao;

public class TipoFormaImplementacaoController  extends AbstractController<TipoFormaImplementacao>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1344916015518730440L;
 
	@Inject
	public TipoFormaImplementacaoController(TipoFormaImplementacaoDAO dao) {

		super(dao);
	}

 
	 
	

}
