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
import administrativo.service.PerfilService;
import administrativo.service.UserService;
import arquitetura.utils.Messages;
import arquitetura.utils.SessionUtils;
import arquitetura.utils.SispcaLogger;
import arquitetura.utils.Utils;
import qualitativo.model.UnidadeOrcamentaria;
import qualitativo.service.UnidadeOrcamentariaService;

@Named
@ViewScoped
public class UsuarioFormMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -943431499633986156L;


	public static final String SUCCESS_EMAIL_SENT = "Sua senha foi enviada por email";
	public static final String FAIL_EMAIL_SENT    = "Falha ao enviar email";

	public static final String FAIL_SAVE  = "Falha inesperada ao tentar Salvar Usuário";
	public static final String SUCCESS_SAVE = "Usuário Salvo com Sucesso";
 
	private Usuario usuario = new Usuario();
	private Long perfilSelecionado;
	private Long uoSelecionada;
	private List<Perfil>listPerfil;
	private List<UnidadeOrcamentaria>listUnidadeOrcamentaria;
	
	private UserValidate userValidate;
	private UserService userService;
	private PerfilService perfilService;
	private UnidadeOrcamentariaService unidadeOrcamentariaService;

	@Inject
	public UsuarioFormMBean(UserService userService,PerfilService perfilService,UnidadeOrcamentariaService unidadeOrcamentariaService,UserValidate userValidate) {
		
		this.userService 				= userService;
		this.perfilService			    = perfilService;
		this.unidadeOrcamentariaService = unidadeOrcamentariaService;
		this.userValidate			    =userValidate;
		
		listPerfil 				= perfilService.findAll();
		listUnidadeOrcamentaria = unidadeOrcamentariaService.findAll();
	}

 
	public String salvar() {

		try {

			if (!userValidate.validar(usuario)) {
				return "";
			}
			
			setDependency();
			createPassword();
			
			usuario=userService.create(usuario);

			boolean sent =  enviarEmailUsuarioCriado();
			
			if(sent) {
				Messages.addMessageInfo(SUCCESS_EMAIL_SENT);
			}else {
				Messages.addMessageError(FAIL_EMAIL_SENT);
			}
			
			Messages.addMessageInfo(SUCCESS_SAVE);

			return "usuarioList";
			
		} catch (Exception e) {
			SispcaLogger.logError(e.getCause().getMessage());

			Messages.addMessageError(FAIL_SAVE);
		}

		return "";
	}

 

	 

	private void setDependency() {
		
		setPerfil();
		setUO();
		
	}
	
	private void setPerfil() {
		
		usuario.getPerfis().clear();
		
		usuario.getPerfis().add(perfilService.findById(perfilSelecionado));
	}
	
	private void setUO() {
 
		usuario.setUnidadeOrcamentaria(unidadeOrcamentariaService.findById(uoSelecionada));
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
			 				
				Optional<Usuario> user1 = userService.queryByUserName(login1.toString());
				
				if(user1.isPresent()) {
					login1.append(Utils.randomNumber());
				}
				
				Optional<Usuario> user2 = userService.queryByUserName(login2.toString());
				
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
		
		return userService.enviarEmailUsuarioCriado(usuario, scheme, serveName, port, path);
		
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
