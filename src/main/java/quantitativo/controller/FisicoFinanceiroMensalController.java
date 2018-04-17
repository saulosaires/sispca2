package quantitativo.controller;

import java.util.Optional;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import quantitativo.dao.FisicoFinanceiroMensalDAO;
import quantitativo.model.FisicoFinanceiroMensal;

public class FisicoFinanceiroMensalController extends AbstractController<FisicoFinanceiroMensal> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public FisicoFinanceiroMensalController(FisicoFinanceiroMensalDAO dao) {
		super(dao);

	}

	
	public Optional<FisicoFinanceiroMensal> findByRegiaoMunicipioAndExercicioAndAcaoAndMes(Long regiaoMunicipioId,Long exercicioId,Long acaoId,Long mesId){
		
		return ((FisicoFinanceiroMensalDAO)getDao()).findByRegiaoMunicipioAndExercicioAndAcaoAndMes(regiaoMunicipioId,exercicioId,acaoId,mesId);
	}
	
	
}
