package qualitativo.controller;

import java.io.Serializable;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import qualitativo.dao.UnidadeOrcamentariaDAO;
import qualitativo.model.UnidadeOrcamentaria;

public class UnidadeOrcamentariaController  extends AbstractController<UnidadeOrcamentaria> implements Serializable{
 
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 3597150160739474071L;
 

	@Inject
	public UnidadeOrcamentariaController(UnidadeOrcamentariaDAO dao) {

		super(dao);
	}
 
 

}
