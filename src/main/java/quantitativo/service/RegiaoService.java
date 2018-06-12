package quantitativo.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;
import quantitativo.dao.RegiaoDAO;
import quantitativo.model.Regiao;

public class RegiaoService extends AbstractService<Regiao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public RegiaoService(RegiaoDAO dao) {
		super(dao);
	}

	private RegiaoDAO dao() {
		
		return (RegiaoDAO)getDAO();
	}

	
	public List<Regiao> findAllOrderByDescricao() {

		return dao().findAllOrderByDescricao();
	}
	 
	
	
	public List<Regiao> findAllByTipoRegiao(Long tipoRegiaoId) {

		if(Utils.invalidId(tipoRegiaoId)) {
			return findAllOrderByDescricao();
		}
		
		return dao().findAllByTipoRegiao(tipoRegiaoId);
	}

}
