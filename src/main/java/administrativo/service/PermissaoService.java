package administrativo.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import administrativo.controller.PermissaoController;
import administrativo.model.Permissao;
import arquitetura.exception.JpaException;
import arquitetura.utils.Utils;

public class PermissaoService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3288174422611664897L;

	PermissaoController permissaoController;

	@Inject
	public PermissaoService(PermissaoController permissaoController) {
		this.permissaoController = permissaoController;
	}

	public List<Permissao> findAll() {
		return permissaoController.findAll();
	}

	public List<Permissao> buscaPermissao(String busca){

		if (Utils.emptyParam(busca) ) {
			return permissaoController.findAll();
		} else {
			return permissaoController.buscaPermissao(busca);
		}

	}

	public void delete(Permissao permissao) throws JpaException {

		permissaoController.delete(permissao);

	}

	public Permissao findById(Long id) {

		return permissaoController.findById(id);

	}

	public Permissao create(Permissao permissao) throws JpaException {
		return permissaoController.create(permissao);

	}

	public Permissao update(Permissao permissao) throws JpaException {
		return permissaoController.update(permissao);

	}
 

	public List<Permissao> findPermissaoAssociada(Long perfilId) {
		return permissaoController.findPermissaoAssociada(perfilId) ;
	}
	
	
}
