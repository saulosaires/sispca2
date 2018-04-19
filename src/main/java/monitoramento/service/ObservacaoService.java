package monitoramento.service;

import javax.inject.Inject;

import arquitetura.exception.JpaException;
import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;
import monitoramento.controller.ObservacaoController;
import monitoramento.model.Observacao;

public class ObservacaoService extends AbstractService<Observacao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public ObservacaoService(ObservacaoController controller) {
		super(controller);
	}

	public Observacao merge(Observacao observacao) throws JpaException {
		
		if(observacao==null)return observacao;
		
		if(Utils.invalidId(observacao.getId())) {
			return create(observacao);
		}else {
			return update(observacao);
		}
		
	}

}
