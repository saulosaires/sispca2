package qualitativo.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;
import qualitativo.dao.PlanoInternoDAO;
import qualitativo.model.PlanoInterno;

public class PlanoInternoService extends AbstractService<PlanoInterno>  {
 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public PlanoInternoService(PlanoInternoDAO dao) {
		super(dao);
	}

	public List<PlanoInterno> relatorio(Long unidadeGestoraId,Long unidadeOrcamentariaId,Long acaoId,Long exercicioId) {
		 
		return ((PlanoInternoDAO)getDAO()).relatorio(unidadeGestoraId, unidadeOrcamentariaId, acaoId, exercicioId);
	}

	
	public List<PlanoInterno> buscar(String codigo) {

		if(Utils.emptyParam(codigo)) {
			return findAll();
		}else {
			return ((PlanoInternoDAO)getDAO()).buscar(codigo);
		}
		
		 
	}

 

}
