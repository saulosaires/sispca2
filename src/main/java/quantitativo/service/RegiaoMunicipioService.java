package quantitativo.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;
import quantitativo.controller.RegiaoMunicipioController;
import quantitativo.model.RegiaoMunicipio;

public class RegiaoMunicipioService extends AbstractService<RegiaoMunicipio> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public RegiaoMunicipioService(RegiaoMunicipioController controller) {
		super(controller);
	}

	 
	public List<RegiaoMunicipio> findAllByRegiao(Long regiaoId) {

		if(Utils.invalidId(regiaoId)) {
			return new ArrayList<>();
		}
		
		return ((RegiaoMunicipioController) getController()).findByRegiao(regiaoId);
	}
	 
	
	
	public List<RegiaoMunicipio> findAllByTipoRegiao(Long tipoRegiaoId) {

		if(Utils.invalidId(tipoRegiaoId)) {
			return new ArrayList<>();
		}
		
		return ((RegiaoMunicipioController) getController()).findByTipoRegiao(tipoRegiaoId);
	}

}
