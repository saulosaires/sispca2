package qualitativo.controller;

import java.util.List;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import qualitativo.dao.UnidadeOrcamentariaDAO;
import qualitativo.model.UnidadeOrcamentaria;

public class UnidadeOrcamentariaController  extends AbstractController<UnidadeOrcamentaria> {
 
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 3597150160739474071L;
 

	@Inject
	public UnidadeOrcamentariaController(UnidadeOrcamentariaDAO dao) {

		super(dao);
	}
 
	public List<UnidadeOrcamentaria> buscarByOrgao(Long orgaoId) {
		
		return ((UnidadeOrcamentariaDAO)getDao()).buscarByOrgao(orgaoId);
	}



	public List<UnidadeOrcamentaria> buscar(List<Long>unidadeOrcamentariaId,String codigo, String descricao, Long orgaoId) {
		
		return ((UnidadeOrcamentariaDAO)getDao()).buscar(unidadeOrcamentariaId, codigo, descricao, orgaoId);
	}

}
