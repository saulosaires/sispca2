package avaliacao.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import arquitetura.dao.AbstractDAO;
import arquitetura.utils.Utils;
import avaliacao.model.ExercicioTopicoResultado;

public class ExercicioTopicoResultadoDAO extends AbstractDAO< ExercicioTopicoResultado >  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public ExercicioTopicoResultadoDAO() {
		setClazz(ExercicioTopicoResultado.class );
	 
	}
 
	
	public List<ExercicioTopicoResultado> buscarPorExercicicio(Long exercicioId) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ExercicioTopicoResultado> query = cb.createQuery(ExercicioTopicoResultado.class);
		Root<ExercicioTopicoResultado> m = query.from(ExercicioTopicoResultado.class);

		query.select(m);
		
 
		if (!Utils.invalidId((exercicioId))) {
			Join<Object, Object> joinExercico = m.join("exercicio",JoinType.INNER);
			joinExercico.on(cb.equal(joinExercico.get("id"),exercicioId) );	
		}
		
 		
		
		return  entityManager.createQuery(query).getResultList();
	}
	
	
	
}
