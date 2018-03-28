package administrativo.beans.usuario;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.model.Usuario;
import administrativo.service.UserService;
import arquitetura.utils.Messages;
import arquitetura.utils.SispcaLogger;

@Named
@ViewScoped
public class UsuarioListMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5940179508090218836L;

	public static final String FAIL_DELETE    = "Falha inesperada ao tentar Deletar Usuário";
	public static final String SUCCESS_DELETE = "Usuário deletado com Sucesso";
	public static final String FAIL_SEARCH    = "Falha ao pesquisar Usuarios";
	
	private String name;
	private String email;
	private String perfil;
	private String unidadeOrcamentaria;
	
	private List<Usuario> listaUsuarios;

	private UserService service;

	@Inject
	public UsuarioListMBean(UserService service) {
		this.service = service;
	}
 	
	public String pesquisar() {
 
		try {
			listaUsuarios = service.queryUser(name, email, perfil, unidadeOrcamentaria);

		} catch (Exception e) {
			SispcaLogger.logError(e.getLocalizedMessage());

			Messages.addMessageError(FAIL_SEARCH);

		}

		return "";
		

	}

	public String delete(Usuario user) {

		try {

			service.delete(user);

			pesquisar();

			Messages.addMessageInfo(SUCCESS_DELETE);

		} catch (Exception e) {
			SispcaLogger.logError(e.getLocalizedMessage());

			Messages.addMessageError(FAIL_DELETE);

		}

		return "";

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getUnidadeOrcamentaria() {
		return unidadeOrcamentaria;
	}

	public void setUnidadeOrcamentaria(String unidadeOrcamentaria) {
		this.unidadeOrcamentaria = unidadeOrcamentaria;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

 
 
	
	
	
}
