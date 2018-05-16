package administrativo.beans.mensagem;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.model.Mensagem;
import administrativo.service.MensagemService;
import arquitetura.utils.Messages;
import arquitetura.utils.SispcaLogger;

@Named
@ViewScoped
public class MensagemFormMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -943431499633986156L;


	public static final String FAIL_SAVE_MESSAGE_MSG = "Falha inesperada ao tentar Salvar Mensagem";
 
	public static final String SUCCESS_SAVE_MESSAGE_MSG = "Mensagem salva com Sucesso";
 
 
	private Mensagem mensagem = new Mensagem();

	private MensagemService mensagemService;
	private MensagemValidate mensagemValidate;

	@Inject
	public MensagemFormMBean(MensagemService mensagemService,MensagemValidate mensagemValidate) {
		this.mensagemService = mensagemService;
		this.mensagemValidate=mensagemValidate;
	}
 
	public String salvar() {

		try {

			if (!mensagemValidate.validar(mensagem)) {
				return "";
			}

			mensagemService.create(mensagem);

			Messages.addMessageInfo(SUCCESS_SAVE_MESSAGE_MSG);
			
			return "mensagensList";
			
		} catch (Exception e) {
			SispcaLogger.logError(e);

			Messages.addMessageError(FAIL_SAVE_MESSAGE_MSG);
		}

		return "";
	}
 

	 
 

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

	public MensagemService getMensagemService() {
		return mensagemService;
	}

	public void setMensagemService(MensagemService mensagemService) {
		this.mensagemService = mensagemService;
	}

	
	
}
