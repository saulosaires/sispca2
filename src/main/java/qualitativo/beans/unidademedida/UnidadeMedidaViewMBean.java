package qualitativo.beans.unidademedida;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import arquitetura.utils.Utils;
import qualitativo.model.UnidadeMedida;
import qualitativo.service.UnidadeMedidaService;

@Named
@ViewScoped
public class UnidadeMedidaViewMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private UnidadeMedida unidadeMedida = new UnidadeMedida();

	private UnidadeMedidaService service;

	@Inject
	public UnidadeMedidaViewMBean(UnidadeMedidaService service) {

		this.service = service;

	}

	public void init() {

		if (!Utils.invalidId(id)) {

			unidadeMedida = service.findById(id);

		}

	}

	public UnidadeMedida getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
