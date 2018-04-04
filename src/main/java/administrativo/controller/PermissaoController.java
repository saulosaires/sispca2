package administrativo.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import administrativo.dao.PermissaoDAO;
import administrativo.model.Permissao;
 

public class PermissaoController implements Serializable {

	 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1088277951634929132L;
	
	@Inject
	PermissaoDAO permissaoDAO;

	public List<Permissao> findAll() {

		return permissaoDAO.findAll();
	}

	public List<Permissao> buscaPermissao(String busca){

		return permissaoDAO.buscaPermissao(busca);
	}

	public void delete(Permissao permissao) {
		permissaoDAO.delete(permissao);

	}

	public Permissao findById(Long id) {
		return permissaoDAO.findOne(id);
		
	}

	public Permissao create(Permissao permissao) {
		return permissaoDAO.create(permissao);
		
	}

	public Permissao update(Permissao permissao) {
		return permissaoDAO.update(permissao);
		
	}

 
	public List<Permissao> findPermissaoAssociada(Long perfilId) {
		return permissaoDAO.findPermissaoAssociada(perfilId);
	}
	
	
}
