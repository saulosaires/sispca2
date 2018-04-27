package avaliacao.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import arquitetura.dao.AbstractDAO;
import avaliacao.model.ExercicioTopicoAvaliacao;
 

public class ExercicioTopicoAvaliacaoDAO extends AbstractDAO< ExercicioTopicoAvaliacao >  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1358843609602731648L;


	public ExercicioTopicoAvaliacaoDAO() {
		setClazz(ExercicioTopicoAvaliacao.class );
	 
	}
 
 
 
	public List<ExercicioTopicoAvaliacao> findByExercicio( Long exercicioId){
 		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ExercicioTopicoAvaliacao> query = cb.createQuery(ExercicioTopicoAvaliacao.class);
		Root<ExercicioTopicoAvaliacao> m = query.from(ExercicioTopicoAvaliacao.class);
		query.select(m);
 
		
		Join<Object, Object> joinExercicio = m.join("exercicio", JoinType.INNER);
		joinExercicio.on(cb.equal(joinExercicio.get("id"), exercicioId));
	 
		
		return entityManager.createQuery(query).getResultList();	
		
		
	}
 
}
