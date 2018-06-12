package administrativo.service;

import java.util.List;

import javax.inject.Inject;

import administrativo.dao.PermissaoDAO;
import administrativo.model.Permissao;
import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;

public class PermissaoService extends AbstractService<Permissao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3288174422611664897L;

	@Inject
	public PermissaoService(PermissaoDAO dao) {
		super(dao);
	}

	public List<Permissao> buscaPermissao(String busca) {

		if (Utils.emptyParam(busca)) {
			return findAll();
		} else {
			return ((PermissaoDAO) getDAO()).buscaPermissao(busca);
		}

	}

	public List<Permissao> buscaPermissaoEqAcao(String busca) {

		return ((PermissaoDAO) getDAO()).buscaPermissaoEqAcao(busca);

	}

	public List<Permissao> findPermissaoAssociada(Long perfilId) {
		return ((PermissaoDAO) getDAO()).findPermissaoAssociada(perfilId);
	}

}
