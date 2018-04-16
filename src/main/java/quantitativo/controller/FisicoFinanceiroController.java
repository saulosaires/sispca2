package quantitativo.controller;

import java.util.Optional;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import quantitativo.dao.FisicoFinanceiroDAO;
import quantitativo.model.FisicoFinanceiro;

public class FisicoFinanceiroController extends AbstractController<FisicoFinanceiro> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public FisicoFinanceiroController(FisicoFinanceiroDAO dao) {
		super(dao);

	}

	
	public Optional<FisicoFinanceiro> findByRegiaoMunicipioAndExercicio(Long regiaoMunicipioId,Long exercicioId){
		
		return ((FisicoFinanceiroDAO)getDao()).findByRegiaoMunicipioAndExercicio(regiaoMunicipioId,exercicioId);
	}
	
	
}
