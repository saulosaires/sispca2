package qualitativo.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;
import qualitativo.controller.IndicadorController;
import qualitativo.model.Indicador;

public class IndicadorService extends AbstractService<Indicador>  {
 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public IndicadorService(IndicadorController controller) {
		super(controller);
	}

	public List<Indicador> buscar(String indicador) {

		if(Utils.emptyParam(indicador)) {
			return findAll();
		}else {
			return ((IndicadorController)getController()).buscar(indicador);
		}
		
		 
	}

 

}
