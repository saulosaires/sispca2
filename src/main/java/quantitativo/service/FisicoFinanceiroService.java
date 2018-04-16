package quantitativo.service;

import java.util.Optional;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import quantitativo.controller.FisicoFinanceiroController;
import quantitativo.model.FisicoFinanceiro;

public class FisicoFinanceiroService extends AbstractService<FisicoFinanceiro> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public FisicoFinanceiroService(FisicoFinanceiroController controller) {
		super(controller);
	}

	public Optional<FisicoFinanceiro> findByRegiaoMunicipioAndExercicio(Long regiaoMunicipioId,Long exercicioId){
		
		return ((FisicoFinanceiroController)getController()).findByRegiaoMunicipioAndExercicio(regiaoMunicipioId,exercicioId);
	}
	
	
}
