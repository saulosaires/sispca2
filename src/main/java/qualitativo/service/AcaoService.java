package qualitativo.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;
import qualitativo.controller.AcaoController;
import qualitativo.model.Acao;

public class AcaoService  extends AbstractService<Acao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2854975367734660857L;
 

	@Inject
	public AcaoService(AcaoController acaoController) {
		super(acaoController);
	}

	public List<Acao> relatorioQualitativoProgramasAcoes(Long orgaoId,List<Long> unidadeOrcamentaria,Long programaId,Long acaoId,Long exercicioId){
		
		
		if(!Utils.invalidId(acaoId)) {
			
			List<Acao> list =new ArrayList<>();
			list.add(findById(acaoId));
			return list;
			
		}else {
			return ((AcaoController) getController()).buscar(null, null, unidadeOrcamentaria, programaId, exercicioId);
		}
		
		
		
		
		 
	} 

	public List<Acao> relatorioPlanoTrabalho(Long orgaoId,List<Long> unidadeOrcamentaria,Long programaId,Long exercicioId,String orderBy){
		
		return ((AcaoController) getController()).relatorioPlanoTrabalho(orgaoId, unidadeOrcamentaria, programaId, exercicioId,orderBy);
	} 

	public List<Acao> relatorioFinalidade(Long orgaoId,List<Long> unidadeOrcamentaria,Long programaId,Long exercicioId){
		
		return ((AcaoController) getController()).relatorioFinalidade(orgaoId, unidadeOrcamentaria, programaId, exercicioId);
	} 

	
	public List<Acao> buscar(String codigo, String codigoUnidadeOrcamentaria,String codigoPrograma,Long exercicioId) {

		if (Utils.emptyParam(codigo) 		 && Utils.emptyParam(codigoUnidadeOrcamentaria) && 
			Utils.emptyParam(codigoPrograma)  && Utils.invalidId(exercicioId)) {
			return findAll();
		} else {
			return ((AcaoController) getController()).buscar(codigo, codigoUnidadeOrcamentaria, codigoPrograma, exercicioId);
		}

	}

	
	public List<Acao> buscar(String codigo, String denominacao,List<Long> unidadeOrcamentaria,Long programaId,Long exercicioId) {

		if (Utils.emptyParam(codigo)    && Utils.emptyParam(denominacao) &&  unidadeOrcamentaria==null && 
			Utils.invalidId(programaId) && Utils.invalidId(exercicioId)) {
			return findAll();
		} else {
			return ((AcaoController) getController()).buscar(codigo, denominacao, unidadeOrcamentaria, programaId,exercicioId);
		}

	}

	public List<Acao> buscarByExercicio(Long exercicioId) {

		if (Utils.invalidId(exercicioId)) {
			return new ArrayList<>();
		} else {
			return ((AcaoController) getController()).buscarByExercicio( exercicioId);
		}

	}

	public List<Acao> buscarByUnidadeOrcamentaria(Long unidadeOrcamentariaId) {

		if (Utils.invalidId(unidadeOrcamentariaId)) {
			return new ArrayList<>();
		} else {
			return ((AcaoController) getController()).buscarByUnidadeOrcamentaria( unidadeOrcamentariaId);
		}

	}	
	
}
