package qualitativo.beans.unidadegestora;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import arquitetura.utils.Utils;
import qualitativo.model.UnidadeGestora;
import qualitativo.model.UnidadeOrcamentaria;
import qualitativo.service.UnidadeGestoraService;
import qualitativo.service.UnidadeOrcamentariaService;

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
	private UnidadeOrcamentariaService orcamentariaService;

	@Inject
	public UnidadeGestoraViewMBean(UnidadeGestoraService service,UnidadeOrcamentariaService orcamentariaService) {

		this.service = service;
		this.orcamentariaService=orcamentariaService;
	}

	public void init() {

		if (!Utils.invalidId(id)) {

			unidadeGestora = service.findById(id);

			Long idUo = unidadeGestora.getUnidadeOrcamentaria().getId();
			unidadeGestora.setUnidadeOrcamentaria(orcamentariaService.findById(idUo));
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
