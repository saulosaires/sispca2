package administrativo.service;

import java.util.List;

import javax.inject.Inject;

import administrativo.controller.PermissaoController;
import administrativo.model.Permissao;
import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;

public class PermissaoService extends AbstractService<Permissao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3288174422611664897L;

	@Inject
	public PermissaoService(PermissaoController permissaoController) {
		super(permissaoController);
	}

	public List<Permissao> buscaPermissao(String busca) {

		if (Utils.emptyParam(busca)) {
			return findAll();
		} else {
			return ((PermissaoController) getController()).buscaPermissao(busca);
		}

	}

	public List<Permissao> buscaPermissaoEqAcao(String busca) {

		return ((PermissaoController) getController()).buscaPermissaoEqAcao(busca);

	}

	public List<Permissao> findPermissaoAssociada(Long perfilId) {
		return ((PermissaoController) getController()).findPermissaoAssociada(perfilId);
	}

}
