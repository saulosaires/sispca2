package administrativo.beans.objeto;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.model.Permissao;
import administrativo.service.PermissaoService;
import arquitetura.utils.Messages;
import arquitetura.utils.SispcaLogger;

@Named
@ViewScoped
public class ObjetoFormMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4306070990599784210L;


 
	public static final String FAIL_SAVE_MSG = "Falha inesperada ao tentar Salvar Permisão";
 
	public static final String SUCCESS_SAVE_MSG = "Permisão salva com Sucesso";
 
	private Permissao permissao = new Permissao();

	private PermissaoService permissaoService;
	private PermissaoValidate permissaoValidate;

	@Inject
	public ObjetoFormMBean(PermissaoService permissaoService, PermissaoValidate permissaoValidate) {
		this.permissaoService = permissaoService;
		this.permissaoValidate=permissaoValidate;

	}

	

	public String salvar() {

		try {

			if (!permissaoValidate.validar(permissao)) {
				return "";
			}
		
			permissaoService.create(permissao);

			Messages.addMessageInfo(SUCCESS_SAVE_MSG);
			
			return "objetoList";
			
		} catch (Exception e) {
			SispcaLogger.logError(e);

			Messages.addMessageError(FAIL_SAVE_MSG);
		}

		return "";
		
	}

  

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}


	
}
