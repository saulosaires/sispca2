package administrativo.beans.mensagem;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.model.Mensagem;
import administrativo.service.MensagemService;
import arquitetura.utils.Messages;
import arquitetura.utils.SispcaLogger;

@Named
@ViewScoped
public class MensagemListMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5940179508090218836L;

	public static final String FAIL_DELETE = "Falha inesperada ao tentar Deletar Mensagem";
	public static final String SUCCESS_DELETE = "Mensagem deletada com Sucesso";
	public static final String FAIL_SEARCH = "Falha ao pesquisar Mensagem";

	private String titulo;
	private String texto;
	private List<Mensagem> listMensagens;

	private MensagemService mensagemService;

	@Inject
	public MensagemListMBean(MensagemService mensagemService) {
		this.mensagemService = mensagemService;
	}

	public void pesquisar() {

		try {
			listMensagens = mensagemService.queryByTituloAndTexto(titulo, texto);

		} catch (Exception e) {
			SispcaLogger.logError(e.getLocalizedMessage());

			Messages.addMessageError(FAIL_SEARCH);

		}

	}

	public String delete(Mensagem msg) {

		try {

			mensagemService.delete(msg);

			pesquisar();

			Messages.addMessageInfo(SUCCESS_DELETE);

		} catch (Exception e) {
			SispcaLogger.logError(e.getLocalizedMessage());

			Messages.addMessageError(FAIL_DELETE);

		}

		return "";

	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public List<Mensagem> getListMensagens() {
		return listMensagens;
	}

	public void setListMensagens(List<Mensagem> listMensagens) {
		this.listMensagens = listMensagens;
	}

}