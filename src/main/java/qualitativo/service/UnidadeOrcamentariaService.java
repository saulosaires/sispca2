package qualitativo.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;
import qualitativo.controller.UnidadeOrcamentariaController;
import qualitativo.model.UnidadeOrcamentaria;

public class UnidadeOrcamentariaService extends AbstractService<UnidadeOrcamentaria>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3288174422611664897L;

	@Inject
	public UnidadeOrcamentariaService(UnidadeOrcamentariaController controller) {
		super(controller);
	}

	public List<UnidadeOrcamentaria> findAllOrderByDescricao() {

		return ((UnidadeOrcamentariaController) getController()).findAllOrderByDescricao();
	}

	public List<UnidadeOrcamentaria> buscar(String codigo, String descricao, Long orgaoId) {


		if(Utils.emptyParam(codigo) && Utils.emptyParam(descricao) && Utils.invalidId(orgaoId)) {
			return findAllOrderByDescricao();
		}else {
			return ((UnidadeOrcamentariaController) getController()).buscar(codigo, descricao, orgaoId);
		}
	}

}
