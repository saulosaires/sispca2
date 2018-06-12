package qualitativo.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;
import qualitativo.dao.IndicadorDAO;
import qualitativo.model.Indicador;

public class IndicadorService extends AbstractService<Indicador>  {
 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public IndicadorService(IndicadorDAO dao) {
		super(dao);
	}

	public List<Indicador> buscar(String indicador) {

		if(Utils.emptyParam(indicador)) {
			return findAll();
		}else {
			return ((IndicadorDAO)getDAO()).buscar(indicador);
		}
		
		 
	}

	public List<Indicador> findAllOrderByIndicador() {

		return ((IndicadorDAO) getDAO()).findAllOrderByIndicador();

	}

}
