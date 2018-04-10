package administrativo.beans.objeto;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import administrativo.model.Permissao;
import administrativo.service.PermissaoService;
import arquitetura.interfaces.Validate;
import arquitetura.utils.Messages;
import arquitetura.utils.Utils;

public class PermissaoValidate implements Validate<Permissao>,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1539192572985371866L;
	public static final String NOME_REQUIRED_MSG = " Nome é um campo obrigatório";
	public static final String FAIL_PERMISSAO = "Falha inesperada ao tentar Salvar Permisão";
	public static final String SAME_ACAO_PERMISSAO = "Já existe uma Permisão com esse nome";
	
	private PermissaoService service;
	
	@Inject
	public PermissaoValidate(PermissaoService service){
		this.service=service;
	}
	
	
	@Override
	public boolean validar( Permissao permissao) {


		if (permissao == null) {
			Messages.addMessageError(FAIL_PERMISSAO);
			return false;

		}

		if (Utils.emptyParam(permissao.getAcao())) {
			Messages.addMessageError(NOME_REQUIRED_MSG);
			return false;

		}

		List<Permissao> list = service.buscaPermissaoEqAcao(permissao.getAcao());

		if(!list.isEmpty()) {
			Messages.addMessageError(SAME_ACAO_PERMISSAO);
			return false;
		}

		return true;
		
	}

}
