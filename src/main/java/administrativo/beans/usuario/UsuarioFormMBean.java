package administrativo.beans.usuario;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import administrativo.model.Perfil;
import administrativo.model.Usuario;
import administrativo.service.UserService;
import arquitetura.utils.Messages;
import arquitetura.utils.SessionUtils;
import arquitetura.utils.SispcaLogger;
import arquitetura.utils.Utils;
import qualitativo.model.UnidadeOrcamentaria;

@Named
@ViewScoped
public class UsuarioFormMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -943431499633986156L;

	public static final String NAME_REQUIRED_MSG  = "Nome é um campo obrigatório";
	public static final String CARGO_REQUIRED_MSG = "Cargo é um campo obrigatório";
	public static final String EMAIL_REQUIRED_MSG = "Email é um campo obrigatório";
	public static final String CPF_REQUIRED_MSG   = "Cpf é um campo obrigatório";
	public static final String PERFIL_REQUIRED_MSG= "Perfil é um campo obrigatório";
	public static final String UO_REQUIRED_MSG    = "Unidade Orcamentaria é um campo obrigatório";
	public static final String SUCCESS_EMAIL_SENT = "Sua senha foi enviada por email";
	public static final String FAIL_EMAIL_SENT    = "Falha ao enviar email";
	
	public static final String FAIL_SAVE     = "Falha inesperada ao tentar Salvar Usuário";
	public static final String FAIL_UPDATE   = "Falha inesperada ao tentar Atualizar Usuário";

	public static final String SUCCESS_SAVE   = "Usuário salva com Sucesso";
	public static final String SUCCESS_UPDATE = "Usuário atualizada com Sucesso";

	private Long id;

	private Usuario usuario = new Usuario();
	private Long perfilSelecionado;
	private Long uoSelecionada;
	private List<Perfil>listPerfil;
	private List<UnidadeOrcamentaria>listUnidadeOrcamentaria;
	
	private UserService service;

	@Inject
	public UsuarioFormMBean(UserService service) {
		this.service = service;
		
		listPerfil 				= service.perfilFindAll();
		listUnidadeOrcamentaria = service.uoFindAll();
	}

	public void init() {

		if (!Utils.invalidId(id)) {
			
			usuario = service.findById(id);
			uoSelecionada = usuario.getUnidadeOrcamentaria().getId();
			
			if(!usuario.getPerfis().isEmpty())
				perfilSelecionado=usuario.getPerfis().get(0).getId();
			
			String[] names=usuario.getName().split(" ");
			
			StringBuilder login2 = new StringBuilder("");
			
			login2.append(names[names.length-1]).append(".").append(names[0]);
			
			Optional<Usuario> user2 = service.queryByUserName(usuario.getLogin());
			
			if(user2.isPresent()) {
				login2.append(Utils.randomNumber());
			}
			 
			usuario.setLoginSegundaSugestao(login2.toString());
			
		}

	}

	public String salvar() {

		try {

			if (!validar()) {
				return "";
			}
			
			setDependency();
			createPassword();
			
			usuario=service.create(usuario);

			boolean sent =  enviarEmailUsuarioCriado();
			
			if(sent) {
				Messages.addMessageInfo(SUCCESS_EMAIL_SENT);
			}else {
				Messages.addMessageError(FAIL_EMAIL_SENT);
			}
			
			Messages.addMessageInfo(SUCCESS_SAVE);

		} catch (Exception e) {
			SispcaLogger.logError(e.getLocalizedMessage());

			Messages.addMessageError(FAIL_SAVE);
		}

		return "";
	}

	public String atualizar() {

		try {

			if (!validar()) {
				return "";
			}

			setDependency();
			usuario = service.update(usuario);

			Messages.addMessageInfo(SUCCESS_UPDATE);

		} catch (Exception e) {
			SispcaLogger.logError(e.getLocalizedMessage());

			Messages.addMessageError(FAIL_UPDATE);
		}

		return "";
	}

	private boolean validar() {

		if (usuario == null) {
			Messages.addMessageError(FAIL_SAVE);
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

		if(Utils.invalidId(perfilSelecionado)) {
			Messages.addMessageError(PERFIL_REQUIRED_MSG);
			return false;
		}
		
		if(Utils.invalidId(uoSelecionada)) {
			Messages.addMessageError(UO_REQUIRED_MSG);
			return false;
		}
		
		return true;
	}

	private void setDependency() {
		
		setPerfil();
		setUO();
		
	}
	
	private void setPerfil() {
		
		usuario.getPerfis().clear();
		
		usuario.getPerfis().add(service.perfilFindById(perfilSelecionado));
	}
	
	private void setUO() {
		
		
		
		usuario.setUnidadeOrcamentaria(service.uoFindById(uoSelecionada));
	}
	
	private void createPassword() {
		
		usuario.setPassword(Utils.randomNumber()+"");
		usuario.setSenhaDescriptografada(usuario.getPassword());
	}
	
	public void sugestoesLogin() {
		
		if(!Utils.emptyParam(usuario.getName())) {
			String[] names=usuario.getName().split(" ");
			
			if(names!=null && names.length>1) {
				
				StringBuilder login1 = new StringBuilder("");
				StringBuilder login2 = new StringBuilder("");
				
				int lastPos=names.length-1;
				
				login1.append(names[0]).append(".").append(names[lastPos]);
				login2.append(names[lastPos]).append(".").append(names[0]);
			 				
				Optional<Usuario> user1 = service.queryByUserName(login1.toString());
				
				if(user1.isPresent()) {
					login1.append(Utils.randomNumber());
				}
				
				Optional<Usuario> user2 = service.queryByUserName(login2.toString());
				
				if(user2.isPresent()) {
					login2.append(Utils.randomNumber());
				}
				
				usuario.setLogin(login1.toString());
				usuario.setLoginSegundaSugestao(login2.toString());
			}
		}
		
	}
	
	private boolean enviarEmailUsuarioCriado() {
		
		HttpServletRequest request = SessionUtils.getRequest();
		
		String scheme    = request.getScheme();
		String serveName = request.getServerName();
		int port         = request.getServerPort();
		String path      = request.getContextPath();
		
		return service.enviarEmailUsuarioCriado(usuario, scheme, serveName, port, path);
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getPerfilSelecionado() {
		return perfilSelecionado;
	}

	public void setPerfilSelecionado(Long perfilSelecionado) {
		this.perfilSelecionado = perfilSelecionado;
	}

	public Long getUoSelecionada() {
		return uoSelecionada;
	}

	public void setUoSelecionada(Long uoSelecionada) {
		this.uoSelecionada = uoSelecionada;
	}

	public List<Perfil> getListPerfil() {
		return listPerfil;
	}

	public void setListPerfil(List<Perfil> listPerfil) {
		this.listPerfil = listPerfil;
	}

	public List<UnidadeOrcamentaria> getListUnidadeOrcamentaria() {
		return listUnidadeOrcamentaria;
	}

	public void setListUnidadeOrcamentaria(List<UnidadeOrcamentaria> listUnidadeOrcamentaria) {
		this.listUnidadeOrcamentaria = listUnidadeOrcamentaria;
	}
	
	
	

	 
}
