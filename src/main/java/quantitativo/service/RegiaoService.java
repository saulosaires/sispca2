package quantitativo.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;
import quantitativo.controller.RegiaoController;
import quantitativo.model.Regiao;

public class RegiaoService extends AbstractService<Regiao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public RegiaoService(RegiaoController controller) {
		super(controller);
	}

	public List<Regiao> findAllOrderByDescricao() {

		return ((RegiaoController) getController()).findAllOrderByDescricao();
	}
	 
	
	
	public List<Regiao> findAllByTipoRegiao(Long tipoRegiaoId) {

		if(Utils.invalidId(tipoRegiaoId)) {
			return findAllOrderByDescricao();
		}
		
		return ((RegiaoController) getController()).findAllByTipoRegiao(tipoRegiaoId);
	}

}
