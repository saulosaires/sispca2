package qualitativo.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;
import qualitativo.dao.ProgramaDAO;
import qualitativo.model.Programa;

public class ProgramaService extends AbstractService<Programa>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2854975367734660857L;
 
	@Inject
	public ProgramaService(ProgramaDAO dao) {
		super(dao);
	}

	private ProgramaDAO dao() {
		return (ProgramaDAO) getDAO();
	}

	
	public List<Programa> findAllOrderByDenominacao() {
		
		return dao().findAllOrderByDenominacao();
	}

	public List<Programa> buscar(String codigo, String denominacao,List<Long> listOrgao, Long tipoPrograma,Long exercicioId,Long eixoId) {
		
		if(Utils.emptyParam(codigo) && Utils.emptyParam(denominacao) &&  listOrgao==null && 
				Utils.invalidId(tipoPrograma) && Utils.invalidId(exercicioId)) {
			return findAll();
		}else {
			 return dao().buscar(codigo,denominacao,listOrgao,tipoPrograma,exercicioId,eixoId);	
		}
		
		
	}

	public List<Programa> buscarPorUnidadeOrcamentaria(String unidadeOrcamentariaCodigo) {

		return dao().buscarPorUnidadeOrcamentaria(unidadeOrcamentariaCodigo);

	}
 
	public List<Programa> buscarPorUnidadeOrcamentaria(Long unidadeOrcamentariaId) {

		return dao().buscarPorUnidadeOrcamentaria(unidadeOrcamentariaId);

	}

	 

	
}
