package administrativo.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import administrativo.dao.TipoLinkDAO;
import administrativo.model.TipoLink;

public class TipoLinkController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1344916015518730440L;

	private TipoLinkDAO tipoLinkDAO;

	@Inject
	public TipoLinkController(TipoLinkDAO tipoLinkDAO) {

		this.tipoLinkDAO = tipoLinkDAO;
	}
	
	public List<TipoLink> findAll() {

		return tipoLinkDAO.findAll();
	}
	
	
	public TipoLink findById(Long id) {

		return tipoLinkDAO.findOne(id);
	}
	

}
