package administrativo.beans.permissao;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import administrativo.model.Perfil;
import administrativo.model.Permissao;
import administrativo.service.PerfilService;
import administrativo.service.PermissaoService;
import arquitetura.utils.Messages;
import arquitetura.utils.SispcaLogger;
import arquitetura.utils.Utils;

@Named
@ViewScoped
public class PermissaoMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5940179508090218836L;

	public static final String FAIL_DELETE    = "Falha inesperada ao tentar Deletar Permisão";
	public static final String SUCCESS_DELETE = "Permisão deletado com Sucesso";
	
	public static final String FAIL_SAVE    = "Falha inesperada ao tentar Salvar Permisão";
	public static final String SUCCESS_SAVE = "Permisão Salva com Sucesso";
	
	
	public static final String FAIL_SEARCH    	   = "Falha ao pesquisar Permisão";
	public static final String NO_OBJECT_SELECTED  = "Selecione uma Permisão";
	public static final String NO_PROFILE_SELECTED = "Selecione um perfil!";	
 
	private Perfil perfil;
	private Long permissaoId;
	private List<Perfil> listPerfil;
	
	private List<Permissao> listPermissaoNaoAssociada;
 	
	private PermissaoService permissaoService;
	private PerfilService perfilService;
 	
	@Inject
	public PermissaoMBean(PermissaoService permissaoService,PerfilService perfilService) {
		this.permissaoService = permissaoService;
 		this.perfilService    = perfilService;
		
 		initPerfil();
	}

	public void onChangePerfil() {
 
 	 
		initNaoAssociada(perfil);
 		 
		Collections.sort(listPermissaoNaoAssociada, (p1, p2) -> p1.getAcao().compareTo(p2.getAcao()));
		Collections.sort(perfil.getPermissoes(), (p1, p2) -> p1.getAcao().compareTo(p2.getAcao()));
		
	}
	
	
	private void initNaoAssociada(Perfil perfil) {
		
		listPermissaoNaoAssociada = permissaoService.findAll();
		
		for(Permissao p : perfil.getPermissoes()) {
			listPermissaoNaoAssociada.remove(p);
		}

		
	}
	
	private void initPerfil() {
		listPerfil    = perfilService.findAll();
	}
	
	private boolean validar() {
		
		if (perfil==null || Utils.invalidId(perfil.getId())) {
			Messages.addMessageError(NO_PROFILE_SELECTED);
			return false;
		}
		
		if (Utils.invalidId(permissaoId)) {
			Messages.addMessageError(NO_OBJECT_SELECTED);
			return false;
		}

		return true;
	}
	
	@Transactional
	public String associar(){
		
		try {

			if(!validar())return "";
			
			Permissao permissao = permissaoService.findById(permissaoId);

			permissao.getPerfil().add(perfil);
			perfil.getPermissoes().add(permissao);

			permissaoService.update(permissao);
			perfilService.update(perfil);

			initPerfil();
			onChangePerfil();
			
			Messages.addMessageInfo(SUCCESS_SAVE);

		} catch (Exception e) {
			SispcaLogger.logError(e);

			Messages.addMessageError(FAIL_SAVE);

		}

		return "";

		
	}
  
	@Transactional
	public String deletarAssociacao(Permissao permissao) {

		try {

			 
			permissao.getPerfil().remove(perfil);
			perfil.getPermissoes().remove(permissao);
			
			permissaoService.update(permissao);
			perfilService.update(perfil);
			
			initPerfil();
			onChangePerfil();

			Messages.addMessageInfo(SUCCESS_DELETE);

		} catch (Exception e) {
			SispcaLogger.logError(e);

			Messages.addMessageError(FAIL_DELETE);

		}

		return "";

	}
 
	public List<Perfil> getListPerfil() {
		return listPerfil;
	}

	public void setListPerfil(List<Perfil> listPerfil) {
		this.listPerfil = listPerfil;
	}

	public List<Permissao> getListPermissaoNaoAssociada() {
		return listPermissaoNaoAssociada;
	}

	public void setListPermissaoNaoAssociada(List<Permissao> listPermissaoNaoAssociada) {
		this.listPermissaoNaoAssociada = listPermissaoNaoAssociada;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Long getPermissaoId() {
		return permissaoId;
	}

	public void setPermissaoId(Long permissaoId) {
		this.permissaoId = permissaoId;
	}

 
	
	
}
