package qualitativo.beans.planointerno;

import java.io.Serializable;

import arquitetura.interfaces.Validate;
import arquitetura.utils.Messages;
import arquitetura.utils.Utils;
import qualitativo.model.PlanoInterno;

public class PlanoInternoValidate implements Validate<PlanoInterno>,Serializable{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 8850827058229914224L;
	
	public static final String REQUIRED 	     = "Falha inesperada ao Salvar Indicador";
	public static final String SIGLA_REQUIRED 	 = "Campo Sigla é obrigatório";
	public static final String TITULO_REQUIRED 	 = "Campo Título é obrigatório";
	public static final String OBJETIVO_REQUIRED = "Campo Objetivo é obrigatório";
	public static final String ACAO_REQUIRED 	 = "Campo ação é obrigatório";
	
	
 
	
	@Override
	public boolean validar(PlanoInterno planoInterno) {

		
		if (planoInterno == null) {
			Messages.addMessageError(REQUIRED);
			return false;
		}

		boolean valido=true;
		
		if (Utils.emptyParam(planoInterno.getSigla())) {
			Messages.addMessageError(SIGLA_REQUIRED);
			valido= false;
		}
 
		if (Utils.emptyParam(planoInterno.getTitulo())) {
			Messages.addMessageError(TITULO_REQUIRED);
			valido= false;
		}
		
		if (Utils.emptyParam(planoInterno.getObjetivo())) {
			Messages.addMessageError(OBJETIVO_REQUIRED);
			valido= false;
		}

		if (Utils.invalidId(planoInterno.getAcao().getId())) {
			Messages.addMessageError(ACAO_REQUIRED);
			valido= false;
		}

		
		return valido;
	}


 

}
