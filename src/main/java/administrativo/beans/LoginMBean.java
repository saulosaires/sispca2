package administrativo.beans;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import administrativo.model.Perfil;
import administrativo.model.Permissao;
import administrativo.model.Usuario;
import administrativo.service.PermissaoService;
import administrativo.service.UserService;
import arquitetura.utils.JPAUtil;
import arquitetura.utils.Messages;
import arquitetura.utils.SessionUtils;
import arquitetura.utils.SispcaLogger;

@Named
@ViewScoped
public class LoginMBean implements Serializable{
	
	 
	private static final long serialVersionUID = 1L;
	
	private String login;
	private String password;
	private String emailUsuario;
	
	private UserService userService;
	private PermissaoService permissaoService;
	
	 @Inject
	public LoginMBean(UserService userService,PermissaoService permissaoService){
	
		 this.userService=userService;
		 this.permissaoService=permissaoService;
	}
 	
	public String login() {
		
		try {
 			
			Usuario usuario= userService.loginByUserNameAndPassword(login,password);
			
			if(!JPAUtil.validId(usuario.getId())) {
				Messages.addMessageError("Login ou senha Inválidos");
				return "";
			}
 
			SessionUtils.put("user", usuario);
			
			if(usuario.getPrimeiroAcesso()) {
				
				return "alterarsenha";
			}else {
				
				initPermissao(usuario);
				initMenu();
				initTopMenu();
				return "home";
				
			}

		}catch(Exception e) {
			SispcaLogger.logError(e);
			
			Messages.addMessageError("Falha inesperada ao tentar efetuar login");
		}
		
		return "";
	}
	
	public String solicitaRecuperacaoSenha(){
		
		try {
			
			if("".equals(emailUsuario) || emailUsuario==null) {
				Messages.addMessageError("Email não pode ser vazio");
				return "";
			}
			
			HttpServletRequest request = SessionUtils.getRequest();
			
			String scheme    = request.getScheme();
			String serveName = request.getServerName();
			int port         = request.getServerPort();
			String path      = request.getContextPath();
			
			boolean sent = userService.solicitaRecuperacaoSenha(emailUsuario, scheme, serveName, port, path);
 
			if(sent) {
				Messages.addMessageError("Email não encontrado");
				return "";
			}else {
				Messages.addMessageInfo("Acesse o link enviado para seu email para instruções de recuperação de senha");
			}
	 			
			return "";
				
		}catch(Exception e) {
			SispcaLogger.logError(e);
			
			Messages.addMessageError("Falha inesperada ao tentar efetuar login");
		}
		
		return "";
	}

	private void initMenu() {
		
		MenuMBean menuMBean = (MenuMBean) SessionUtils.getBean("menuMBean");
		menuMBean.initMenu();
	}
	
	private void  initTopMenu(){
		
		UserMBean userMBean= (UserMBean) SessionUtils.getBean("userMBean");
				  userMBean.init();
	}
	
	private void initPermissao(Usuario usuario) {
	
		List<Perfil> perfis = usuario.getPerfis();
		
		Iterator<Perfil> it = perfis.iterator();
		
		while(it.hasNext()) {
			
			Perfil perfil = it.next();
			
			List<Permissao> permissoes = permissaoService.findPermissaoAssociada(perfil.getId());
			
			for(Permissao permissao:permissoes) {
				SessionUtils.put(permissao.getAcao(),permissao.getAcao());
			}
		}
		
		
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}
 
 
	
}
