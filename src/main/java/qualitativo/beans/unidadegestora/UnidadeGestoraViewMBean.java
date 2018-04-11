package qualitativo.beans.unidadegestora;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import arquitetura.utils.Utils;
import qualitativo.model.UnidadeGestora;
import qualitativo.service.UnidadeGestoraService;

@Named
@ViewScoped
public class UnidadeGestoraViewMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 
	private Long id;

	private UnidadeGestora unidadeGestora = new UnidadeGestora();

	private UnidadeGestoraService service;

	@Inject
	public UnidadeGestoraViewMBean(UnidadeGestoraService service) {

		this.service = service;

	}

	public void init() {

		if (!Utils.invalidId(id)) {

			unidadeGestora = service.findById(id);

		}

	}

	public UnidadeGestora getUnidadeGestora() {
		return unidadeGestora;
	}

	public void setUnidadeGestora(UnidadeGestora unidadeGestora) {
		this.unidadeGestora = unidadeGestora;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
