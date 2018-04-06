package administrativo.beans.usuario;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.model.Perfil;
import administrativo.model.Usuario;
import administrativo.service.PerfilService;
import administrativo.service.UserService;
import arquitetura.utils.Messages;
import arquitetura.utils.SispcaLogger;
import arquitetura.utils.Utils;
import qualitativo.model.UnidadeOrcamentaria;
import qualitativo.service.UnidadeOrcamentariaService;

@Named
@ViewScoped
public class UsuarioEditMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -943431499633986156L;

	 
	
 	public static final String FAIL_UPDATE   = "Falha inesperada ao tentar Atualizar Usuário";
	public static final String SUCCESS_UPDATE = "Usuário atualizada com Sucesso";

	private Long id;

	private Usuario usuario = new Usuario();
	private Long perfilSelecionado;
	private Long uoSelecionada;
	private List<Perfil>listPerfil;
	private List<UnidadeOrcamentaria>listUnidadeOrcamentaria;
	
	private UserService userService;
	private PerfilService perfilService;
	private UnidadeOrcamentariaService unidadeOrcamentariaService;
	private UserValidate userValidate;

	@Inject
	public UsuarioEditMBean(UserService userService,PerfilService perfilService,UnidadeOrcamentariaService unidadeOrcamentariaService,UserValidate userValidate) {
		
		this.userService 				= userService;
		this.perfilService			    = perfilService;
		this.unidadeOrcamentariaService = unidadeOrcamentariaService;
		this.userValidate			    = userValidate;
		
		listPerfil 				= perfilService.findAll();
		listUnidadeOrcamentaria = unidadeOrcamentariaService.findAll();
	}

	public void init() {

		if (!Utils.invalidId(id)) {
			
			usuario = userService.findById(id);
			uoSelecionada = usuario.getUnidadeOrcamentaria().getId();
			
			if(!usuario.getPerfis().isEmpty())
				perfilSelecionado=usuario.getPerfis().get(0).getId();
			
			String[] names=usuario.getName().split(" ");
			
			StringBuilder login2 = new StringBuilder("");
			
			login2.append(names[names.length-1]).append(".").append(names[0]);
			
			Optional<Usuario> user2 = userService.queryByUserName(usuario.getLogin());
			
			if(user2.isPresent()) {
				login2.append(Utils.randomNumber());
			}
			 
			usuario.setLoginSegundaSugestao(login2.toString());
			
		}

	}

 

	public String atualizar() {

		try {

			if (!userValidate.validar(usuario)) {
				return "";
			}

			setDependency();
			usuario = userService.update(usuario);

			Messages.addMessageInfo(SUCCESS_UPDATE);

			return "usuarioList";
			
		} catch (Exception e) {
			SispcaLogger.logError(e.getLocalizedMessage());

			Messages.addMessageError(FAIL_UPDATE);
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
