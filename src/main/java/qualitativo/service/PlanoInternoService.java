package qualitativo.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;
import qualitativo.controller.PlanoInternoController;
import qualitativo.model.PlanoInterno;

public class PlanoInternoService extends AbstractService<PlanoInterno>  {
 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public PlanoInternoService(PlanoInternoController controller) {
		super(controller);
	}

	public List<PlanoInterno> relatorio(Long unidadeGestoraId,Long unidadeOrcamentariaId,Long acaoId,Long exercicioId) {
		 
		return ((PlanoInternoController)getController()).relatorio(unidadeGestoraId, unidadeOrcamentariaId, acaoId, exercicioId);
	}

	
	public List<PlanoInterno> buscar(String codigo) {

		if(Utils.emptyParam(codigo)) {
			return findAll();
		}else {
			return ((PlanoInternoController)getController()).buscar(codigo);
		}
		
		 
	}

 

}
