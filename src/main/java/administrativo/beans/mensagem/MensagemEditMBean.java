package administrativo.beans.mensagem;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.model.Mensagem;
import administrativo.service.MensagemService;
import arquitetura.utils.Messages;
import arquitetura.utils.SispcaLogger;
import arquitetura.utils.Utils;

@Named
@ViewScoped
public class MensagemEditMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -943431499633986156L;
 
 	public static final String FAIL_UPDATE_MESSAGE_MSG = "Falha inesperada ao tentar Atualizar Mensagem";

 	public static final String SUCCESS_UPDATE_MESSAGE_MSG = "Mensagem atualizada com Sucesso";

	private Long msgId;

	private Mensagem mensagem = new Mensagem();

	private MensagemService mensagemService;
	private MensagemValidate mensagemValidate;
	
	@Inject
	public MensagemEditMBean(MensagemService mensagemService, MensagemValidate mensagemValidate) {
		this.mensagemService = mensagemService;
		this.mensagemValidate=mensagemValidate;
	}

	public void init() {

		if (!Utils.invalidId(msgId)) {

			mensagem = mensagemService.findById(msgId);

		}

	}
 

	public String atualizar() {

		try {

			if (!mensagemValidate.validar(mensagem)) {
				return "";
			}

			mensagem = mensagemService.update(mensagem);

			Messages.addMessageInfo(SUCCESS_UPDATE_MESSAGE_MSG);

			return "mensagensList";
			
		} catch (Exception e) {
			SispcaLogger.logError(e);

			Messages.addMessageError(FAIL_UPDATE_MESSAGE_MSG);
		}

		return "";
	}

	 

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
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
