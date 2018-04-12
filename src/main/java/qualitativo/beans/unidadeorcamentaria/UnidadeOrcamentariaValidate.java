package qualitativo.beans.unidadeorcamentaria;

import java.io.Serializable;

import javax.inject.Inject;

import arquitetura.interfaces.Validate;
import arquitetura.utils.Messages;
import arquitetura.utils.Utils;
import qualitativo.model.UnidadeOrcamentaria;
import qualitativo.service.UnidadeOrcamentariaService;

public class UnidadeOrcamentariaValidate implements Validate<UnidadeOrcamentaria>,Serializable{
  
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String REQUIRED 		  = "Falha inesperada ao Salvar Unidade Gestora";
	public static final String SIGLA_REQUIRED 	  = "Campo Sigla é obrigatório";
	public static final String DESCRICAO_REQUIRED = "Campo Descrição é obrigatório";
	public static final String CODIGO_REQUIRED 	  = "Campo Descrição é obrigatório";
	public static final String ORGAO_REQUIRED 	  = "Campo Orgão é obrigatório";
	
	UnidadeOrcamentariaService orcamentariaService ;
	
	@Inject
	public UnidadeOrcamentariaValidate(UnidadeOrcamentariaService orcamentariaService){
		this.orcamentariaService=orcamentariaService;
	}
	
	@Override
	public boolean validar(UnidadeOrcamentaria unidadeOrcamentaria) {

		
		if (unidadeOrcamentaria == null) {
			Messages.addMessageError(REQUIRED);
			return false;
		}

		boolean valido=true;
		
		if (Utils.emptyParam(unidadeOrcamentaria.getCodigo())) {
			Messages.addMessageError(CODIGO_REQUIRED);
			valido= false;
		}
		
		if (Utils.emptyParam(unidadeOrcamentaria.getSigla())) {
			Messages.addMessageError(SIGLA_REQUIRED);
			valido= false;
		}
		
		if (Utils.emptyParam(unidadeOrcamentaria.getDescricao())) {
			Messages.addMessageError(DESCRICAO_REQUIRED);
			valido= false;
		}
 		
		if(unidadeOrcamentaria.getOrgao()==null && Utils.invalidId(unidadeOrcamentaria.getOrgao().getId())) {
			Messages.addMessageError(ORGAO_REQUIRED);
			valido= false;
			
		}
 
		return valido;
	}
 

}
