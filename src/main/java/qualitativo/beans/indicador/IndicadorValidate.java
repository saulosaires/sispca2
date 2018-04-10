package qualitativo.beans.indicador;

import java.io.Serializable;

import arquitetura.interfaces.Validate;
import arquitetura.utils.Messages;
import arquitetura.utils.Utils;
import qualitativo.model.Indicador;

public class IndicadorValidate implements Validate<Indicador>,Serializable{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 8850827058229914224L;
	
	public static final String REQUIRED 	   	  = "Falha inesperada ao Salvar Indicador";
	public static final String INDICADOR_REQUIRED = "Campo indicador é obrigatório";
	
	
 
	
	@Override
	public boolean validar(Indicador indicador) {

		
		if (indicador == null) {
			Messages.addMessageError(REQUIRED);
			return false;
		}

		boolean valido=true;
		
		if (Utils.emptyParam(indicador.getIndicador())) {
			Messages.addMessageError(INDICADOR_REQUIRED);
			valido= false;
		}
 
		
		
		return valido;
	}


 

}
