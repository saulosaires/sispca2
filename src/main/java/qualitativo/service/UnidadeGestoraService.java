package qualitativo.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;
import qualitativo.dao.UnidadeGestoraDAO;
import qualitativo.model.UnidadeGestora;

public class UnidadeGestoraService extends AbstractService<UnidadeGestora>  {
 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public UnidadeGestoraService(UnidadeGestoraDAO dao) {
		super(dao);
	}

	public List<UnidadeGestora> findAllOrderDescricao() {
		 
		return ((UnidadeGestoraDAO)getDAO()).findAllOrderDescricao();
	}	

	
	public List<UnidadeGestora> buscar(String codigo,String descricao,String sigla,Long unidadeOrcamentariaId) {

		if(Utils.emptyParam(codigo) && Utils.emptyParam(descricao) && 
		   Utils.emptyParam(sigla)  && Utils.invalidId(unidadeOrcamentariaId)) {
			
			return findAll();
			
		}else {
			
			return ((UnidadeGestoraDAO)getDAO()).buscar(codigo, descricao, sigla, unidadeOrcamentariaId);
			
		}
		
		 
	}

 

}
