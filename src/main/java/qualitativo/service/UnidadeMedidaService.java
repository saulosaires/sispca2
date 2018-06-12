package qualitativo.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;
import qualitativo.dao.UnidadeMedidaDAO;
import qualitativo.model.UnidadeMedida;

public class UnidadeMedidaService extends AbstractService<UnidadeMedida> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2854975367734660857L;

	@Inject
	public UnidadeMedidaService(UnidadeMedidaDAO dao) {
		super(dao);
	}

	public List<UnidadeMedida> findAllOrderByDecricao() {

		return ((UnidadeMedidaDAO) getDAO()).findAllOrderByDescricao();
	}

	public List<UnidadeMedida> buscar(String sigla, String descricao) {

		if(Utils.emptyParam(sigla) && Utils.emptyParam(descricao)) {
			return findAllOrderByDecricao();
		}else {
			
			return ((UnidadeMedidaDAO) getDAO()).buscar(sigla,descricao);
		}
		
	}

}
