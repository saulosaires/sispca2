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
public class MensagemFormMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -943431499633986156L;

	public static final String MSG_REQUIRED_MSG = "Mensagem é um campo obrigatório";
	public static final String TITULO_REQUIRED_MSG = "Título é um campo obrigatório";

	public static final String FAIL_SAVE_MESSAGE_MSG = "Falha inesperada ao tentar Salvar Mensagem";
	public static final String FAIL_UPDATE_MESSAGE_MSG = "Falha inesperada ao tentar Atualizar Mensagem";

	public static final String SUCCESS_SAVE_MESSAGE_MSG = "Mensagem salva com Sucesso";
	public static final String SUCCESS_UPDATE_MESSAGE_MSG = "Mensagem atualizada com Sucesso";

	private Long msgId;

	private Mensagem mensagem = new Mensagem();

	private MensagemService mensagemService;

	@Inject
	public MensagemFormMBean(MensagemService mensagemService) {
		this.mensagemService = mensagemService;
	}

	public void init() {

		if (!Utils.invalidId(msgId)) {

			mensagem = mensagemService.findById(msgId);

		}

	}

	public String salvar() {

		try {

			if (!validar()) {
				return "";
			}

			mensagemService.create(mensagem);

			Messages.addMessageInfo(SUCCESS_SAVE_MESSAGE_MSG);

		} catch (Exception e) {
			SispcaLogger.logError(e.getLocalizedMessage());

			Messages.addMessageError(FAIL_SAVE_MESSAGE_MSG);
		}

		return "";
	}

	public String atualizar() {

		try {

			if (!validar()) {
				return "";
			}

			mensagem = mensagemService.update(mensagem);

			Messages.addMessageInfo(SUCCESS_UPDATE_MESSAGE_MSG);

		} catch (Exception e) {
			SispcaLogger.logError(e.getLocalizedMessage());

			Messages.addMessageError(FAIL_UPDATE_MESSAGE_MSG);
		}

		return "";
	}

	private boolean validar() {

		if (mensagem == null) {
			Messages.addMessageError(FAIL_SAVE_MESSAGE_MSG);
			return false;

		}

		if (Utils.emptyParam(mensagem.getTitulo())) {
			Messages.addMessageError(TITULO_REQUIRED_MSG);
			return false;

		}

		if (Utils.emptyParam(mensagem.getTexto())) {
			Messages.addMessageError(MSG_REQUIRED_MSG);
			return false;

		}

		return true;
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
