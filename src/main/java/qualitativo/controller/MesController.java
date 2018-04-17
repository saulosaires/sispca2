package qualitativo.controller;

import java.util.List;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import qualitativo.dao.MesDAO;
import qualitativo.model.Mes;

public class MesController extends AbstractController<Mes> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public MesController(MesDAO dao) {
		super(dao);

	}

	 
	public List<Mes> findallOrderById(){
		
		return ((MesDAO) getDao()).findallOrderById();
	}
	

}
