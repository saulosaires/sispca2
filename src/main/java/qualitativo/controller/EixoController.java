package qualitativo.controller;

import java.util.List;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import qualitativo.dao.EixoDAO;
import qualitativo.model.Eixo;

public class EixoController extends AbstractController<Eixo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1344916015518730440L;

	@Inject
	public EixoController(EixoDAO dao) {
		super(dao);
		 
	}

	public List<Eixo> findAllOrderByDescricao() {
	
		return((EixoDAO)getDao()).findAllOrderByDescricao();
	}
 
	

}
