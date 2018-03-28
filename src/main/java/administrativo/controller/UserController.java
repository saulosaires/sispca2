package administrativo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import administrativo.dao.UsuarioDAO;
import administrativo.model.Usuario;
import arquitetura.utils.EmailUtil;
import arquitetura.utils.SispcaLogger;
 

public class UserController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1370755486293182418L;
 
	private UsuarioDAO usuarioDAO;
	
	@Inject
	public UserController(UsuarioDAO usuarioDAO){
		this.usuarioDAO=usuarioDAO;
	}
	
	public List<Usuario> findAll() {

		return usuarioDAO.findAll();
	}

	public List<Usuario> queryUser(String name, String email, String perfil, String unidadeOrcamentaria){

		return usuarioDAO.queryUser(name, email, perfil,unidadeOrcamentaria);
	}

	public void delete(Usuario user) {
		usuarioDAO.delete(user);

	}

	public Usuario findById(Long id) {
		return usuarioDAO.findOne(id);
		
	}

	public Usuario create(Usuario user) {
		return usuarioDAO.create(user);
		
	}

	public Usuario update(Usuario user) {
		return usuarioDAO.update(user);
		
	}

	public Optional<Usuario> queryByUserName(String login) {
		return usuarioDAO.queryByUserName(login);
		
	}
	
	public boolean enviarEmailUsuarioCriado(Usuario usuario, String scheme,String serveName,int port,String path) {
		
		try {
			
			String assunto = "SISPCA - Geração de Usuário";
			
			StringBuilder builder = new StringBuilder();
 
			String urlBaseSistema = String.format("%s://%s:%d%s",scheme,serveName, port,path);
			String paginaInicial = String.format("<a href=\"%s/views/public/login.jsf\">Acesso ao sistema</a>", urlBaseSistema);
	
			builder.append("<h1>SISPCA - Geração de Usuário</h1>");
			builder.append("<p><b>Login: </b>" + usuario.getLogin() + "</p>");
			builder.append("<p><b>Senha: </b>" + usuario.getSenhaDescriptografada() + "</p>");
			
			builder.append(paginaInicial);
			
			String mensagem = builder.toString(); 
			
			List<String> destinatarios = new ArrayList<>();
			
			destinatarios.add(usuario.getEmail());
	
			return EmailUtil.enviaEmail(assunto, mensagem, destinatarios, null);
		
		}catch(Exception e) {
			SispcaLogger.logError(e.getMessage());
		
       }
    	
    	return false;
		
	}
	
	
}
