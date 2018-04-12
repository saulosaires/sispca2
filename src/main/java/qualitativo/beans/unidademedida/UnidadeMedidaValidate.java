package qualitativo.beans.unidademedida;

import java.io.Serializable;

import javax.inject.Inject;

import arquitetura.interfaces.Validate;
import arquitetura.utils.Messages;
import arquitetura.utils.Utils;
import qualitativo.model.UnidadeMedida;
import qualitativo.service.UnidadeOrcamentariaService;

public class UnidadeMedidaValidate implements Validate<UnidadeMedida>,Serializable{
  
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String REQUIRED 	     			= "Falha inesperada ao Salvar Unidade Gestora";
	public static final String SIGLA_REQUIRED 	 		    = "Campo Sigla é obrigatório";
	public static final String DESCRICAO_REQUIRED 			= "Campo Descrição é obrigatório";
 	
	
	UnidadeOrcamentariaService orcamentariaService ;
	
	@Inject
	public UnidadeMedidaValidate(UnidadeOrcamentariaService orcamentariaService){
		this.orcamentariaService=orcamentariaService;
	}
	
	@Override
	public boolean validar(UnidadeMedida unidadeMedida) {

		
		if (unidadeMedida == null) {
			Messages.addMessageError(REQUIRED);
			return false;
		}

		boolean valido=true;
		
 
		
		if (Utils.emptyParam(unidadeMedida.getSigla())) {
			Messages.addMessageError(SIGLA_REQUIRED);
			valido= false;
		}
		
		if (Utils.emptyParam(unidadeMedida.getDescricao())) {
			Messages.addMessageError(DESCRICAO_REQUIRED);
			valido= false;
		}
 		
		return valido;
	}
 

}
