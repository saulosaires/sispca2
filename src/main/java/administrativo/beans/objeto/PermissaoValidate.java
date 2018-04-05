package administrativo.beans.objeto;

import java.io.Serializable;

import administrativo.model.Permissao;
import arquitetura.interfaces.Validate;
import arquitetura.utils.Messages;
import arquitetura.utils.Utils;

public class PermissaoValidate implements Validate<Permissao>,Serializable{

	public static final String NOME_REQUIRED_MSG = " Nome é um campo obrigatório";
	public static final String FAIL_PERMISSAO = "Falha inesperada ao tentar Salvar Permisão";
	@Override
	public boolean validar(Permissao permissao) {


		if (permissao == null) {
			Messages.addMessageError(FAIL_PERMISSAO);
			return false;

		}

		if (Utils.emptyParam(permissao.getAcao())) {
			Messages.addMessageError(NOME_REQUIRED_MSG);
			return false;

		}



		return true;
		
	}

}
