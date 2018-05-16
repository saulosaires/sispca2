package qualitativo.controller;

import java.util.List;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import qualitativo.dao.PlanoInternoDAO;
import qualitativo.model.PlanoInterno;

public class PlanoInternoController extends AbstractController<PlanoInterno> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public PlanoInternoController(PlanoInternoDAO dao) {
		super(dao);

	}

	public List<PlanoInterno> relatorio(Long unidadeGestoraId,Long unidadeOrcamentariaId,Long acaoId,Long exercicioId) {
		 
		return ((PlanoInternoDAO)getDao()).relatorio(unidadeGestoraId, unidadeOrcamentariaId, acaoId, exercicioId);
	}
	
	
	
	public List<PlanoInterno> buscar(String codigo) {
		 
		return ((PlanoInternoDAO)getDao()).buscar(codigo);
	}

}
