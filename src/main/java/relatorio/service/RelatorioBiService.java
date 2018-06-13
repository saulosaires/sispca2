package relatorio.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import relatorio.dao.RelatorioBIDAO;
import relatorio.model.RelatorioBi;

public class RelatorioBiService  extends AbstractService<RelatorioBi> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Inject
	public RelatorioBiService(RelatorioBIDAO dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}

	private RelatorioBIDAO dao() {
		return (RelatorioBIDAO) getDAO();
	}
	
	
	public List<RelatorioBi> buscarByUnidadeOrcamentaria(Integer ano,Long unidadeOrcamentariaId){
		
		return dao().buscarByUnidadeOrcamentaria(ano,unidadeOrcamentariaId);
		
	}
	
}
