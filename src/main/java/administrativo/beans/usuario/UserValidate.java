package administrativo.beans.usuario;

import java.io.Serializable;
import java.util.Optional;

import javax.inject.Inject;

import administrativo.model.Usuario;
import administrativo.service.UserService;
import arquitetura.interfaces.Validate;
import arquitetura.utils.Messages;
import arquitetura.utils.Utils;

public class UserValidate implements Validate<Usuario>,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4224357976023849645L;
	
	public static final String NAME_REQUIRED_MSG  = "Nome é um campo obrigatório";
	public static final String CARGO_REQUIRED_MSG = "Cargo é um campo obrigatório";
	public static final String EMAIL_REQUIRED_MSG = "Email é um campo obrigatório";
	public static final String CPF_REQUIRED_MSG   = "Cpf é um campo obrigatório";
	public static final String DUPLICATED_CPF_REQUIRED_MSG   = "Já existe um usuário com esse Cpf";
	public static final String PERFIL_REQUIRED_MSG= "Perfil é um campo obrigatório";
	public static final String UO_REQUIRED_MSG    = "Unidade Orcamentaria é um campo obrigatório";
	
	public static final String FAIL_VALIDATE     = "Falha inesperada ao tentar Salvar/Atualizar Usuário";
  	
	private UserService userService;
	
	@Inject
	UserValidate(UserService userService){
		this.userService=userService;
	}
	
	
	@Override
	public boolean validar(Usuario usuario) {
	 
		if (usuario == null) {
			Messages.addMessageError(FAIL_VALIDATE);
			return false;

		}

		if (Utils.emptyParam(usuario.getName())) {
			Messages.addMessageError(NAME_REQUIRED_MSG);
			return false;

		}

		if (Utils.emptyParam(usuario.getCargo())) {
			Messages.addMessageError(CARGO_REQUIRED_MSG);
			return false;
		}

		if (Utils.emptyParam(usuario.getEmail())) {
			Messages.addMessageError(EMAIL_REQUIRED_MSG);
			return false;
		}

		if (Utils.emptyParam(usuario.getCpf())) {
			Messages.addMessageError(CPF_REQUIRED_MSG);
			return false;
		}

		if(usuario.isValidarSameCpf()) {
			
			Optional<Usuario> userOptinal = userService.queryByCPF(usuario.getCpf()); 
			
			if(userOptinal.isPresent()) {
				Messages.addMessageError(DUPLICATED_CPF_REQUIRED_MSG);
				return false;
			}
		}
		
		return true;

		
	}

}
