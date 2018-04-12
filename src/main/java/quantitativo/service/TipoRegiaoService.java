package quantitativo.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import quantitativo.controller.TipoRegiaoController;
import quantitativo.model.TipoRegiao;

public class TipoRegiaoService extends AbstractService<TipoRegiao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public TipoRegiaoService(TipoRegiaoController controller) {
		super(controller);
	}

	public List<TipoRegiao> findAllOrderByDescricao() {
	 
		return ((TipoRegiaoController)getController()).findAllOrderByDescricao();
	}

}
