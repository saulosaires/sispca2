package quantitativo.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import quantitativo.dao.TipoRegiaoDAO;
import quantitativo.model.TipoRegiao;

public class TipoRegiaoService extends AbstractService<TipoRegiao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public TipoRegiaoService(TipoRegiaoDAO dao) {
		super(dao);
	}

	private TipoRegiaoDAO dao() {
		
		return (TipoRegiaoDAO)getDAO();
	}

	
	public List<TipoRegiao> findAllOrderByDescricao() {
	 
		return dao().findAllOrderByDescricao();
	}

}
