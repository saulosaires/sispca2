package qualitativo.service;

import java.io.Serializable;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import qualitativo.controller.UnidadeOrcamentariaController;
import qualitativo.model.UnidadeOrcamentaria;
 

public class UnidadeOrcamentariaService   extends AbstractService<UnidadeOrcamentaria> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3288174422611664897L;
 
	@Inject
	public UnidadeOrcamentariaService(UnidadeOrcamentariaController controller) {
		super(controller);
	}

	 
	 

	 
	
}
