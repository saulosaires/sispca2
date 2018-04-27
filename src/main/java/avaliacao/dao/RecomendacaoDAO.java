package avaliacao.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import arquitetura.dao.AbstractDAO;
import avaliacao.model.Recomendacao;
 

public class RecomendacaoDAO extends AbstractDAO< Recomendacao >  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1358843609602731648L;

	private static final  String ID="id"; 
	private static final  String EXERCICIO="exercicio"; 
	private static final  String PROGRAMA="programa"; 

	public RecomendacaoDAO() {
		setClazz(Recomendacao.class );
	 
	}
 
 
 
	public Recomendacao findByProgramaAndExercicio(Long programaId, Long exercicioId){
 		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Recomendacao> query = cb.createQuery(Recomendacao.class);
		Root<Recomendacao> m = query.from(Recomendacao.class);
		query.select(m);
 		
		Join<Object, Object> joinExercicio = m.join(EXERCICIO, JoinType.INNER);
		joinExercicio.on(cb.equal(joinExercicio.get(ID), exercicioId));
			
		Join<Object, Object> joinPrograma = m.join(PROGRAMA, JoinType.INNER);
		joinPrograma.on(cb.equal(joinPrograma.get(ID), programaId));
			 
		 
		return entityManager.createQuery(query).getResultList().stream().findFirst().orElse(new Recomendacao());	
		
		
	}
 
}
