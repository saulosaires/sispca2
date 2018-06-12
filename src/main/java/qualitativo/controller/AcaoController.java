package qualitativo.controller;

import java.util.List;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import qualitativo.dao.AcaoDAO;
import qualitativo.model.Acao;

public class AcaoController  extends AbstractController<Acao>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1344916015518730440L;
 
	@Inject
	public AcaoController(AcaoDAO acaoDAO) {

		super(acaoDAO);
	}

	public List<Acao> relatorioPlanoTrabalho(List<Long> listOrgaoId,List<Long> unidadeOrcamentaria,Long programaId,Long exercicioId,String orderBy){
		
		return ((AcaoDAO) getDao()).relatorioPlanoTrabalho(listOrgaoId, unidadeOrcamentaria, programaId, exercicioId,orderBy);
	} 
	
	
	public List<Acao> relatorioFinalidade(List<Long> listOrgaoId,List<Long> unidadeOrcamentaria,Long programaId,Long exercicioId){
		
		return ((AcaoDAO) getDao()).relatorioFinalidade(listOrgaoId, unidadeOrcamentaria, programaId, exercicioId);
	} 

	
	public List<Acao> buscar(String codigo, String codigoUnidadeOrcamentaria,String codigoPrograma,Long exercicioId){
		
		return ((AcaoDAO) getDao()).buscar(codigo,codigoUnidadeOrcamentaria,codigoPrograma,exercicioId);
	} 
	
	public List<Acao> buscar(String codigo, String denominacao,List<Long> unidadeOrcamentaria,Long programaId,Long exercicioId){
		
		return ((AcaoDAO) getDao()).buscar(codigo, denominacao,unidadeOrcamentaria, programaId,exercicioId);
	}
	
	public List<Acao> buscar(String codigo, String denominacao,List<Long> orgaoId,List<Long> unidadeOrcamentaria,Long programaId,Long exercicioId){
		
		return ((AcaoDAO) getDao()).buscar(codigo, denominacao, orgaoId, unidadeOrcamentaria, programaId, exercicioId);
	}
	
	public List<Acao> buscarByExercicio(Long exercicioId){
		
		return ((AcaoDAO) getDao()).buscarByExercicio(exercicioId);
	}

	public List<Acao> buscarByUnidadeOrcamentaria(Long unidadeOrcamentariaId){
		
		return ((AcaoDAO) getDao()).buscarByUnidadeOrcamentaria(unidadeOrcamentariaId);
	}

	
	
}
