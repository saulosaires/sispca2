package qualitativo.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import qualitativo.dao.MesDAO;
import qualitativo.model.Mes;

public class MesService extends AbstractService<Mes> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public MesService(MesDAO dao) {
		super(dao);
	}

	public List<Mes> findallOrderById() {

		return ((MesDAO) getDAO()).findallOrderById();
	}

}
