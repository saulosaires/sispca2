package qualitativo.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;
import qualitativo.controller.OrgaoController;
import qualitativo.model.Orgao;

public class OrgaoService extends AbstractService<Orgao>  {
 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public OrgaoService(OrgaoController controller) {
		super(controller);
	}

	public List<Orgao> buscar(String codigo,String sigla,String descricao) {

		if(Utils.emptyParam(codigo) && Utils.emptyParam(sigla) && Utils.emptyParam(descricao)) {
			return findAll();
		}else {
			return ((OrgaoController)getController()).buscar(codigo,sigla,descricao);
		}
		
		 
	}

 

}
