package qualitativo.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import arquitetura.exception.JpaException;
import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;
import qualitativo.dao.AcaoDAO;
import qualitativo.model.Acao;
import quantitativo.service.FisicoFinanceiroMensalService;

public class AcaoService  extends AbstractService<Acao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2854975367734660857L;
 
	FisicoFinanceiroMensalService fisicoFinanceiroMensalService;
	
	@Inject
	public AcaoService(AcaoDAO dao, FisicoFinanceiroMensalService fisicoFinanceiroMensalService) {
		super(dao);
		
		this.fisicoFinanceiroMensalService= fisicoFinanceiroMensalService;
	}

	private AcaoDAO dao() {
		return (AcaoDAO) getDAO();
	}
	
	
	@Override
	public Acao delete(Acao a) throws JpaException {

		Acao acao = findById(a.getId());
	
		acao.setAtivo(false);
		
		update(acao);
			
		return acao;
	}
	
	
	public List<Acao> relatorioQualitativoProgramasAcoes(List<Long> listOrgaoId,List<Long> unidadeOrcamentaria,Long programaId,Long acaoId,Long exercicioId){
		
		
		if(!Utils.invalidId(acaoId)) {
			
			List<Acao> list =new ArrayList<>();
			list.add(findById(acaoId));
			return list;
			
		}else {
			return buscar(null, null, listOrgaoId,unidadeOrcamentaria, programaId, exercicioId);
		}
		
		
		
		
		 
	} 

	public List<Acao> relatorioPlanoTrabalho(List<Long> listOrgaoId,List<Long> unidadeOrcamentaria,Long programaId,Long exercicioId,String orderBy){
		
		return dao().relatorioPlanoTrabalho(listOrgaoId, unidadeOrcamentaria, programaId, exercicioId,orderBy);
	} 

	public List<Acao> relatorioFinalidade(List<Long> listOrgaoId,List<Long> unidadeOrcamentaria,Long programaId,Long exercicioId){
		
		return dao().relatorioFinalidade(listOrgaoId, unidadeOrcamentaria, programaId, exercicioId);
	} 

	
	public List<Acao> buscar(String codigo, String codigoUnidadeOrcamentaria,String codigoPrograma,Long exercicioId) {

		if (Utils.emptyParam(codigo) 		 && Utils.emptyParam(codigoUnidadeOrcamentaria) && 
			Utils.emptyParam(codigoPrograma)  && Utils.invalidId(exercicioId)) {
			return findAll();
		} else {
			return dao().buscar(codigo, codigoUnidadeOrcamentaria, codigoPrograma, exercicioId);
		}

	}

	
	public List<Acao> buscar(String codigo, String denominacao,List<Long> unidadeOrcamentaria,Long programaId,Long exercicioId) {

		if (Utils.emptyParam(codigo)    && Utils.emptyParam(denominacao) &&  unidadeOrcamentaria==null && 
			Utils.invalidId(programaId) && Utils.invalidId(exercicioId)) {
			return findAll();
		} else {
			return dao().buscar(codigo, denominacao, unidadeOrcamentaria, programaId,exercicioId);
		}

	}
	
	public List<Acao> buscar(String codigo, String denominacao,List<Long> orgaoId,List<Long> unidadeOrcamentaria,Long programaId,Long exercicioId){
		
		return dao().buscar(codigo, denominacao, orgaoId, unidadeOrcamentaria, programaId, exercicioId);
	}


	public List<Acao> buscarByExercicio(Long exercicioId) {

		if (Utils.invalidId(exercicioId)) {
			return new ArrayList<>();
		} else {
			return dao().buscarByExercicio( exercicioId);
		}

	}

	public List<Acao> buscarByUnidadeOrcamentaria(Long unidadeOrcamentariaId,Long exercicioId) {

		if (Utils.invalidId(unidadeOrcamentariaId)) {
			return new ArrayList<>();
		} else {
			
			List<Long> uo =new ArrayList<Long>();
			uo.add(unidadeOrcamentariaId);
			 
			return buscar(null,null,null,uo,null, exercicioId);
		 
		}

	}	
	
	
	public List<Acao> exportarBI(Long exercicioId){
		return dao().exportarBI(exercicioId);
	}

	public List<Acao> exportarBIMetas(Long exercicioId){
		
		List<Acao> listAcao = dao().exportarBIMetas(exercicioId);
		
		for(Acao acao :listAcao) {
			
			acao.setQuantidadePlanejado(fisicoFinanceiroMensalService.calculaPlanejamentoByExercicioAndAcao(exercicioId, acao.getId()));
			
		}
		
		
		return listAcao;
	}

	
	
}
