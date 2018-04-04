package administrativo.beans.objeto;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.model.Perfil;
import administrativo.model.Permissao;
import administrativo.service.PerfilService;
import administrativo.service.PermissaoService;

import arquitetura.utils.Messages;
import arquitetura.utils.SispcaLogger;
import arquitetura.utils.Utils;

@Named
@ViewScoped
public class ObjetoFormMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4306070990599784210L;

	public static final String NOME_REQUIRED_MSG = " Nome é um campo obrigatório";
 
	public static final String FAIL_SAVE_MSG = "Falha inesperada ao tentar Salvar Permisão";
	public static final String FAIL_UPDATE_MSG = "Falha inesperada ao tentar Atualizar Permisão";

	public static final String SUCCESS_SAVE_MSG = "Permisão salva com Sucesso";
	public static final String SUCCESS_UPDATE_MSG = "Permisão atualizada com Sucesso";

 

	private Permissao permissao = new Permissao();

	private PermissaoService permissaoService;
	private PerfilService perfilService;

	@Inject
	public ObjetoFormMBean(PermissaoService permissaoService) {
		this.permissaoService = permissaoService;

	}

	

	public String salvar() {

		try {

			if (!validar()) {
				return "";
			}
		
			permissaoService.create(permissao);

			Messages.addMessageInfo(SUCCESS_SAVE_MSG);
			
			return "objetoList";
			
		} catch (Exception e) {
			SispcaLogger.logError(e.getLocalizedMessage());

			Messages.addMessageError(FAIL_SAVE_MSG);
		}

		return "";
		
	}

 

	private boolean validar() {

		if (permissao == null) {
			Messages.addMessageError(FAIL_SAVE_MSG);
			return false;

		}

		if (Utils.emptyParam(permissao.getAcao())) {
			Messages.addMessageError(NOME_REQUIRED_MSG);
			return false;

		}



		return true;
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}


	
}
