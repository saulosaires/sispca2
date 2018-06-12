package avaliacao.service;

import java.util.List;

import javax.inject.Inject;

import arquitetura.exception.JpaException;
import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;
import avaliacao.dao.ResultadoDAO;
import avaliacao.model.Resultado;

public class ResultadoService extends AbstractService<Resultado> {

 
	/**
	 * 
	 */
	private static final long serialVersionUID = -156314605441094680L;
	
 
	@Inject
	public ResultadoService(ResultadoDAO dao) {
		super(dao);
	}
 

	public List<Resultado> findByProgramaAndExercicio(Long programaId, Long exercicioId){
		return ((ResultadoDAO) getDAO()).findByProgramaAndExercicio(programaId, exercicioId);
	}


	public Resultado merge(Resultado resultado) throws JpaException {

		if(Utils.invalidId(resultado.getId())) {
			 return create(resultado);
		}else {
			return update(resultado);
		}
		
	}
	
	
}
