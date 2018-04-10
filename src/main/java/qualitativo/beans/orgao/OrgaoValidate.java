package qualitativo.beans.orgao;

import java.io.Serializable;

import arquitetura.interfaces.Validate;
import arquitetura.utils.Messages;
import arquitetura.utils.Utils;
import qualitativo.model.Orgao;

public class OrgaoValidate implements Validate<Orgao>,Serializable{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 8850827058229914224L;
	
	public static final String REQUIRED 	   	  = "Falha inesperada ao Salvar Orgão";
	public static final String CODIGO_REQUIRED 	  = "Campo Código é obrigatório";
	public static final String SIGLA_REQUIRED	  = "Campo Sigla é obrigatório";
	public static final String DESCRICAO_REQUIRED = "Campo Descrição é obrigatório";
	
	
 
	
	@Override
	public boolean validar(Orgao orgao) {

		
		if (orgao == null) {
			Messages.addMessageError(REQUIRED);
			return false;
		}

		boolean valido=true;
		
		if (Utils.emptyParam(orgao.getCodigo())) {
			Messages.addMessageError(CODIGO_REQUIRED);
			valido= false;
		}
 
		if (Utils.emptyParam(orgao.getCodigo())) {
			Messages.addMessageError(SIGLA_REQUIRED);
			valido= false;
		}

		if (Utils.emptyParam(orgao.getCodigo())) {
			Messages.addMessageError(DESCRICAO_REQUIRED);
			valido= false;
		}

		
		return valido;
	}


 

}
