package avaliacao.service;

import javax.inject.Inject;

import arquitetura.exception.JpaException;
import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;
import avaliacao.controller.RecomendacaoController;
import avaliacao.model.Recomendacao;

public class RecomendacaoService extends AbstractService<Recomendacao> {

 
	/**
	 * 
	 */
	private static final long serialVersionUID = -156314605441094680L;
	
 
	@Inject
	public RecomendacaoService(RecomendacaoController controller) {
		super(controller);
	}
 

	public Recomendacao findByProgramaAndExercicio(Long programaId, Long exercicioId){
		return ((RecomendacaoController) getController()).findByProgramaAndExercicio(programaId, exercicioId);
	}


	public Recomendacao merge(Recomendacao recomendacao) throws JpaException {

		if(Utils.invalidId(recomendacao.getId())) {
			 return create(recomendacao);
		}else {
			return update(recomendacao);
		}
		
	}
	
	
}
