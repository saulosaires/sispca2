package qualitativo.controller;

import java.util.List;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import qualitativo.dao.OrgaoDAO;
import qualitativo.model.Orgao;

public class OrgaoController extends AbstractController<Orgao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public OrgaoController(OrgaoDAO dao) {
		super(dao);

	}

	public List<Orgao> buscar(String codigo,String sigla,String descricao) {
		 
		return ((OrgaoDAO)getDao()).buscar(codigo,sigla,descricao);
	}

}
