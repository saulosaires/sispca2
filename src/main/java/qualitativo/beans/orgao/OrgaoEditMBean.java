package qualitativo.beans.orgao;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import arquitetura.utils.Messages;
import arquitetura.utils.SispcaLogger;
import arquitetura.utils.Utils;
import qualitativo.model.Orgao;
import qualitativo.service.OrgaoService;

@Named
@ViewScoped
public class OrgaoEditMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String FAIL_SAVE = "Falha inesperada ao tentar Salvar Orgão";

	private Long id;

	private Orgao orgao = new Orgao();

	private OrgaoService service;

	private OrgaoValidate validate;

	@Inject
	public OrgaoEditMBean(OrgaoService service, OrgaoValidate validate) {

		this.service = service;

		this.validate = validate;

	}

	public void init() {

		if (!Utils.invalidId(id)) {

			orgao = service.findById(id);

		}

	}

	public String atualizar() {

		try {

			if (!validate.validar(orgao)) {
				return "";
			}

			service.update(orgao);

			return "orgaoQualitativoList";

		} catch (Exception e) {
			SispcaLogger.logError(e.getCause().getMessage());

			Messages.addMessageError(FAIL_SAVE);
		}

		return "";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Orgao getOrgao() {
		return orgao;
	}

	public void setOrgao(Orgao orgao) {
		this.orgao = orgao;
	}

}
