package quantitativo.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;
import quantitativo.dao.RegiaoMunicipioDAO;
import quantitativo.model.RegiaoMunicipio;

public class RegiaoMunicipioService extends AbstractService<RegiaoMunicipio> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public RegiaoMunicipioService(RegiaoMunicipioDAO dao) {
		super(dao);
	}

	private RegiaoMunicipioDAO dao() {
		
		return (RegiaoMunicipioDAO)getDAO();
	}

	
	public List<RegiaoMunicipio> findByRegiao(Long regiaoId) {

		if(Utils.invalidId(regiaoId)) {
			return new ArrayList<>();
		}
		
		return dao().findByRegiao(regiaoId);
	}
	 
	
	
	public List<RegiaoMunicipio> findAllByTipoRegiao(Long tipoRegiaoId) {

		if(Utils.invalidId(tipoRegiaoId)) {
			return new ArrayList<>();
		}
		
		List<RegiaoMunicipio> listTipoRegiao = dao().findByTipoRegiao(tipoRegiaoId);
		
		List<RegiaoMunicipio> listTodaRegiao = dao().findTodosTipoRegiao();
		
		listTodaRegiao.addAll(listTipoRegiao);
		
		return listTodaRegiao;
	}

}
