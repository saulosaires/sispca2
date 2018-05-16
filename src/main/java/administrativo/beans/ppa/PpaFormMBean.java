package administrativo.beans.ppa;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.model.Ppa;
import administrativo.service.PpaService;
import arquitetura.utils.Messages;
import arquitetura.utils.SispcaLogger;
import arquitetura.utils.Utils;

@Named
@ViewScoped
public class PpaFormMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8073902023587400765L;

	public static final String SIGLA_REQUIRED_MSG = " Sigla é um campo obrigatório";
	public static final String DESC_REQUIRED_MSG = " Descrição é um campo obrigatório";
	public static final String YEAR_REQUIRED_MSG = "Ano Fim deve ser maior que Ano Inicio";

	public static final String FAIL_SAVE_MESSAGE_MSG = "Falha inesperada ao tentar Salvar Ppa";
	public static final String FAIL_UPDATE_MESSAGE_MSG = "Falha inesperada ao tentar Atualizar Ppa";

	public static final String SUCCESS_SAVE_MESSAGE_MSG = "Ppa salva com Sucesso";
	public static final String SUCCESS_UPDATE_MESSAGE_MSG = "Ppa atualizada com Sucesso";

 
	private Ppa ppa = new Ppa();

	private PpaService service;

	@Inject
	public PpaFormMBean(PpaService service) {
		this.service = service;
	}

 

	public String salvar() {

		try {

			if (!validar()) {
				return "";
			}

			service.create(ppa);

			Messages.addMessageInfo(SUCCESS_SAVE_MESSAGE_MSG);

		} catch (Exception e) {
			SispcaLogger.logError(e);

			Messages.addMessageError(FAIL_SAVE_MESSAGE_MSG);
		}

		return "ppaList";
	}

	 

	private boolean validar() {

		if (ppa == null) {
			Messages.addMessageError(FAIL_SAVE_MESSAGE_MSG);
			return false;

		}

		if (Utils.emptyParam(ppa.getSigla())) {
			Messages.addMessageError(SIGLA_REQUIRED_MSG);
			return false;

		}

		if (Utils.emptyParam(ppa.getDescricao())) {
			Messages.addMessageError(DESC_REQUIRED_MSG);
			return false;

		}

		if ((!Utils.invalidYear(ppa.getAnoInicio()) && !Utils.invalidYear(ppa.getAnoFim()))
				&& ppa.getAnoFim() < ppa.getAnoInicio()) {
			
			Messages.addMessageError(YEAR_REQUIRED_MSG);
			return false;

		}

		return true;
	}



	public Ppa getPpa() {
		return ppa;
	}



	public void setPpa(Ppa ppa) {
		this.ppa = ppa;
	}

	
	
	
	
}
