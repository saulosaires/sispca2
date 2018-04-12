package administrativo.beans;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import administrativo.model.Usuario;
import arquitetura.utils.SessionUtils;
import arquitetura.utils.SispcaLogger;

@Named
@SessionScoped
public class UserMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7880830417324625776L;

	private Usuario usuario;
	
	transient StreamedContent fotoPerfil;
	
	public UserMBean() {
		init();
	}
	
	public void init() {
		
		usuario = (Usuario) SessionUtils.get("user");
		
		initFotoPerfil();
	}

	public String logout() {
		
		SessionUtils.invalidate();
		
		return "login";
	}
	
	public StreamedContent initFotoPerfil() {

		DefaultStreamedContent content = null;
		try {
			if (usuario.getFoto() != null) {
				content = new DefaultStreamedContent(new ByteArrayInputStream(usuario.getFoto()), "image/jpeg");
			}
		} catch (Exception e) {
			SispcaLogger.logError(e.getCause().getMessage());
		}
		return content;
	}
	
	
	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public StreamedContent getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(StreamedContent fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}
	
	
	
	
}
