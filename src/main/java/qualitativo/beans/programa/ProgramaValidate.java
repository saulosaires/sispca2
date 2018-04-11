package qualitativo.beans.programa;

import java.io.Serializable;

import arquitetura.interfaces.Validate;
import arquitetura.utils.Messages;
import arquitetura.utils.Utils;
import qualitativo.model.Programa;

public class ProgramaValidate implements Validate<Programa>,Serializable{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 8850827058229914224L;
	
	public static final String REQUIRED 	     				= "Falha inesperada ao Salvar Programa";
	public static final String CODIGO_REQUIRED 	 				= "Campo Código é obrigatório";
	public static final String DENOMINACAO_REQUIRED 	 		= "Campo Denominação é obrigatório";
	public static final String ORGAO_REQUIRED 				    = "Campo Orgão é obrigatório";
	public static final String UNIDADE_ORCAMENTARIA_REQUIRED 	= "Campo Unidade Orcamentária é obrigatório";
	public static final String TIPO_PROGRAMA_REQUIRED 	 		= "Campo Tipo Programa é obrigatório";
	public static final String TIPO_HORIZONTE_TEMPORAL_REQUIRED = "Campo Tipo Horizonte Temporal é obrigatório";
	
	
 
	
	@Override
	public boolean validar(Programa programa) {

		
		if (programa == null) {
			Messages.addMessageError(REQUIRED);
			return false;
		}

		boolean valido=true;
		
		if (Utils.emptyParam(programa.getCodigo())) {
			Messages.addMessageError(CODIGO_REQUIRED);
			valido= false;
		}
		
		if (Utils.emptyParam(programa.getDenominacao())) {
			Messages.addMessageError(DENOMINACAO_REQUIRED);
			valido= false;
		}
		
		if (programa.getOrgao()==null && Utils.invalidId(programa.getOrgao().getId())) {
			Messages.addMessageError(ORGAO_REQUIRED);
			valido= false;
		}

		if (programa.getUnidadeOrcamentaria()==null && Utils.invalidId(programa.getUnidadeOrcamentaria().getId())) {
			Messages.addMessageError(UNIDADE_ORCAMENTARIA_REQUIRED);
			valido= false;
		}

		if (programa.getTipoPrograma()==null  && Utils.invalidId(programa.getTipoPrograma().getId())) {
			Messages.addMessageError(TIPO_PROGRAMA_REQUIRED);
			valido= false;
		}

		if (programa.getTipoHorizonteTemporal()==null  && Utils.invalidId(programa.getTipoHorizonteTemporal().getId())) {
			Messages.addMessageError(TIPO_HORIZONTE_TEMPORAL_REQUIRED);
			valido= false;
		}
		
		return valido;
	}


 

}
