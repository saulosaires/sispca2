package qualitativo.beans.unidadegestora;

import java.io.Serializable;

import javax.inject.Inject;

import arquitetura.interfaces.Validate;
import arquitetura.utils.Messages;
import arquitetura.utils.Utils;
import qualitativo.model.UnidadeGestora;
import qualitativo.service.UnidadeOrcamentariaService;

public class UnidadeGestoraValidate implements Validate<UnidadeGestora>,Serializable{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 8850827058229914224L;
	
	public static final String REQUIRED 	     			= "Falha inesperada ao Salvar Unidade Gestora";
	public static final String CODIGO_REQUIRED 	 			= "Campo Código é obrigatório";
	public static final String SIGLA_REQUIRED 	 		    = "Campo Sigla é obrigatório";
	public static final String DESCRICAO_REQUIRED 			= "Campo Descrição é obrigatório";
	public static final String UNIDADE_ORCAMENTARIA_REQUIRED= "Campo Unidade Orcamentária é obrigatório";
	
	
	UnidadeOrcamentariaService orcamentariaService ;
	
	@Inject
	public UnidadeGestoraValidate(UnidadeOrcamentariaService orcamentariaService){
		this.orcamentariaService=orcamentariaService;
	}
	
	@Override
	public boolean validar(UnidadeGestora unidadeGestora) {

		
		if (unidadeGestora == null) {
			Messages.addMessageError(REQUIRED);
			return false;
		}

		boolean valido=true;
		
		if (Utils.emptyParam(unidadeGestora.getCodigo())) {
			Messages.addMessageError(CODIGO_REQUIRED);
			valido= false;
		}
		
		if (Utils.emptyParam(unidadeGestora.getSigla())) {
			Messages.addMessageError(SIGLA_REQUIRED);
			valido= false;
		}
		
		if (Utils.emptyParam(unidadeGestora.getDescricao())) {
			Messages.addMessageError(DESCRICAO_REQUIRED);
			valido= false;
		}
		
		if (unidadeGestora.getUnidadeOrcamentaria()==null && Utils.invalidId(unidadeGestora.getUnidadeOrcamentaria().getId())) {
			Messages.addMessageError(UNIDADE_ORCAMENTARIA_REQUIRED);
			valido= false;
		}
		
		return valido;
	}


  public UnidadeGestora beforeMerge(UnidadeGestora unidadeGestora) {
	  
	  	Long idUo = unidadeGestora.getUnidadeOrcamentaria().getId();
		unidadeGestora.setUnidadeOrcamentaria(orcamentariaService.findById(idUo));
		
		return unidadeGestora;
	  
  }

}
