package administrativo.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import administrativo.controller.AcessoViewController;
import administrativo.model.AcessoView;

public class AcessoViewService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1892724163201113303L;
	AcessoViewController controller;

	@Inject
	public AcessoViewService(AcessoViewController controller) {
		this.controller = controller;
	}

	public List<AcessoView> findRoot() {

		return controller.findAll();
	}

}
