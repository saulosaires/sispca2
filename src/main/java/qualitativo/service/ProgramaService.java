package qualitativo.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;
import qualitativo.controller.ProgramaController;
import qualitativo.model.Programa;

public class ProgramaService extends AbstractService<Programa>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2854975367734660857L;
 
	@Inject
	public ProgramaService(ProgramaController controller) {
		super(controller);
	}

	public List<Programa> findAllOrderByDenominacao() {
		
		return((ProgramaController)getController()).findAllOrderByDenominacao();
	}

	public List<Programa> buscar(String codigo, String denominacao, Long orgao, Long tipoPrograma,Long exercicioId,Long eixoId) {
		
		if(Utils.emptyParam(codigo) && Utils.emptyParam(denominacao) && 
		   Utils.invalidId(orgao) && Utils.invalidId(tipoPrograma) && Utils.invalidId(exercicioId)) {
			return findAll();
		}else {
			 return((ProgramaController)getController()).buscar(codigo,denominacao,orgao,tipoPrograma,exercicioId,eixoId);	
		}
		
		
	}

 

	 

	
}
