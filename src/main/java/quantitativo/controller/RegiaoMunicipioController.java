package quantitativo.controller;

import java.util.List;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import quantitativo.dao.RegiaoMunicipioDAO;
import quantitativo.model.RegiaoMunicipio;

public class RegiaoMunicipioController extends AbstractController<RegiaoMunicipio> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public RegiaoMunicipioController(RegiaoMunicipioDAO dao) {
		super(dao);

	}

	public List<RegiaoMunicipio> findByTipoRegiao(Long tipoRegiaoId) {

		return ((RegiaoMunicipioDAO) getDao()).findByTipoRegiao(tipoRegiaoId);
	}

	public List<RegiaoMunicipio> findByRegiao(Long regiaoId) {
	 
		return ((RegiaoMunicipioDAO) getDao()).findByRegiao(regiaoId);
	}

	public List<RegiaoMunicipio> findTodosTipoRegiao() {
		return ((RegiaoMunicipioDAO) getDao()).findTodosTipoRegiao();
	}

}
