package avaliacao.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import arquitetura.dao.AbstractDAO;
import avaliacao.model.Resultado;
 

public class ResultadoDAO extends AbstractDAO< Resultado >  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1358843609602731648L;

	private static final  String ID="id"; 
	private static final  String EXERCICIO="exercicio"; 
	private static final  String PROGRAMA="programa"; 

	public ResultadoDAO() {
		setClazz(Resultado.class );
	 
	}
 
 
 
	public List<Resultado> findByProgramaAndExercicio(Long programaId, Long exercicioId){
 		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Resultado> query = cb.createQuery(Resultado.class);
		Root<Resultado> m = query.from(Resultado.class);
		query.select(m);
 
		
		Join<Object, Object> joinExercicio = m.join(EXERCICIO, JoinType.INNER);
		joinExercicio.on(cb.equal(joinExercicio.get(ID), exercicioId));
		
		
		
		Join<Object, Object> joinPrograma = m.join(PROGRAMA, JoinType.INNER);
		joinPrograma.on(cb.equal(joinPrograma.get(ID), programaId));
		
		query.orderBy( cb.asc( m.get(ID)));
		 
		return entityManager.createQuery(query).getResultList();	
		
		
	}
 
}
