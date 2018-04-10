package administrativo.controller;

import java.util.List;

import javax.inject.Inject;

import administrativo.dao.PermissaoDAO;
import administrativo.model.Permissao;
import arquitetura.controller.AbstractController;
 

public class PermissaoController extends AbstractController<Permissao> {

	 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1088277951634929132L;
 
	@Inject
	PermissaoController(PermissaoDAO permissaoDAO){
		super(permissaoDAO);
	}

 

	public List<Permissao> buscaPermissao(String busca){

		return ((PermissaoDAO) getDao()).buscaPermissao(busca);
	}

	 
	public List<Permissao> buscaPermissaoEqAcao(String busca){

		return ((PermissaoDAO) getDao()).buscaPermissaoEqAcao(busca);
	} 

 
	public List<Permissao> findPermissaoAssociada(Long perfilId) {
		return ((PermissaoDAO) getDao()).findPermissaoAssociada(perfilId);
	}
	
	
}
