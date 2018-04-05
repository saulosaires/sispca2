package administrativo.beans.mensagem;

import java.io.Serializable;

import administrativo.model.Mensagem;
import arquitetura.interfaces.Validate;
import arquitetura.utils.Messages;
import arquitetura.utils.Utils;

public class MensagemValidate implements Validate<Mensagem>,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3251296339078735938L;
	public static final String MSG_REQUIRED_MSG = "Mensagem é um campo obrigatório";
	public static final String TITULO_REQUIRED_MSG = "Título é um campo obrigatório";
	public static final String FAIL_MESSAGE = "Falha inesperada ao tentar Salvar Mensagem";
	 
	
	@Override
	public boolean validar(Mensagem mensagem) {
		
		if (mensagem == null) {
			Messages.addMessageError(FAIL_MESSAGE);
			return false;

		}

		if (Utils.emptyParam(mensagem.getTitulo())) {
			Messages.addMessageError(TITULO_REQUIRED_MSG);
			return false;

		}

		if (Utils.emptyParam(mensagem.getTexto())) {
			Messages.addMessageError(MSG_REQUIRED_MSG);
			return false;

		}

		return true;
	}

}
