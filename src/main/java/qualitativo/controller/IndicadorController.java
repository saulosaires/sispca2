package qualitativo.controller;

import java.util.List;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import qualitativo.dao.IndicadorDAO;
import qualitativo.model.Indicador;

public class IndicadorController extends AbstractController<Indicador> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public IndicadorController(IndicadorDAO dao) {
		super(dao);

	}

	public List<Indicador> buscar(String indicador) {
		 
		return ((IndicadorDAO)getDao()).buscar(indicador);
	}

	public List<Indicador> findAllOrderByIndicador() {
		 
		return ((IndicadorDAO)getDao()).findAllOrderByIndicador();
	}

	
}
