package qualitativo.controller;

import java.util.List;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import qualitativo.dao.UnidadeGestoraDAO;
import qualitativo.model.UnidadeGestora;

public class UnidadeGestoraController extends AbstractController<UnidadeGestora> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public UnidadeGestoraController(UnidadeGestoraDAO dao) {
		super(dao);

	}

	public List<UnidadeGestora> buscar(String codigo,String descricao,String sigla,Long unidadeOrcamentariaId) {
		 
		return ((UnidadeGestoraDAO)getDao()).buscar(codigo, descricao, sigla, unidadeOrcamentariaId);
	}

}
