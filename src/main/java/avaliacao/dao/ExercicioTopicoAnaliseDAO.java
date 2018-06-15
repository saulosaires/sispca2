package avaliacao.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import arquitetura.dao.AbstractDAO;
import arquitetura.utils.Utils;
import avaliacao.model.ExercicioTopicoAnalise;

public class ExercicioTopicoAnaliseDAO extends AbstractDAO< ExercicioTopicoAnalise >  {

	public ExercicioTopicoAnaliseDAO() {
		setClazz(ExercicioTopicoAnalise.class );
	 
	}
 
	
	public List<ExercicioTopicoAnalise> buscarPorExercicicio(Long exercicioId) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ExercicioTopicoAnalise> query = cb.createQuery(ExercicioTopicoAnalise.class);
		Root<ExercicioTopicoAnalise> m = query.from(ExercicioTopicoAnalise.class);

		query.select(m);
		
 
		if (!Utils.invalidId((exercicioId))) {
			Join<Object, Object> joinExercico = m.join("exercicio",JoinType.INNER);
			joinExercico.on(cb.equal(joinExercico.get("id"),exercicioId) );	
		}
		
 		
		
		return  entityManager.createQuery(query).getResultList();
	}
	
}
