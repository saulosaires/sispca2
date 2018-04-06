package administrativo.beans.objeto;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.model.Permissao;
import administrativo.service.PermissaoService;
import arquitetura.utils.Messages;
import arquitetura.utils.SispcaLogger;
import arquitetura.utils.Utils;

@Named
@ViewScoped
public class ObjetoEditMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4306070990599784210L;


 
 	public static final String FAIL_UPDATE_MSG = "Falha inesperada ao tentar Atualizar Permisão";

 	public static final String SUCCESS_UPDATE_MSG = "Permisão atualizada com Sucesso";

    private Long id;

	private Permissao permissao = new Permissao();

	private PermissaoService permissaoService;
	private PermissaoValidate permissaoValidate;

	@Inject
	public ObjetoEditMBean(PermissaoService permissaoService, PermissaoValidate permissaoValidate) {
		this.permissaoService = permissaoService;
		this.permissaoValidate=permissaoValidate;

	}

	public void init() {

		if (!Utils.invalidId(id)) {

			permissao = permissaoService.findById(id);

		}

	}

	public String atualizar() {

		try {

			if (!permissaoValidate.validar(permissao)) {
				return "";
			}
		
			permissaoService.update(permissao);

			Messages.addMessageInfo(SUCCESS_UPDATE_MSG);
			
			return "objetoList";
			
		} catch (Exception e) {
			SispcaLogger.logError(e.getLocalizedMessage());

			Messages.addMessageError(FAIL_UPDATE_MSG);
		}

		return "";
		
	}

  

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	
}
