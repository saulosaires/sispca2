package avaliacao.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import arquitetura.dao.AbstractDAO;
import avaliacao.model.Analise;
import avaliacao.model.AvaliacaoFisicoFinanceira;
 

public class AvaliacaoFisicoFinanceiraDAO extends AbstractDAO< AvaliacaoFisicoFinanceira >  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1358843609602731648L;


	public AvaliacaoFisicoFinanceiraDAO() {
		setClazz(AvaliacaoFisicoFinanceira.class );
	 
	}
 
 
 
	public List<AvaliacaoFisicoFinanceira> findByProgramaAndExercicio(Long programaId, Long exercicioId){
 		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<AvaliacaoFisicoFinanceira> query = cb.createQuery(AvaliacaoFisicoFinanceira.class);
		Root<AvaliacaoFisicoFinanceira> m = query.from(AvaliacaoFisicoFinanceira.class);
		query.select(m);
 
		
		Join<Object, Object> joinExercicio = m.join("exercicio", JoinType.INNER);
		joinExercicio.on(cb.equal(joinExercicio.get("id"), exercicioId));
		
		
		
		Join<Object, Object> joinPrograma = m.join("programa", JoinType.INNER);
		joinPrograma.on(cb.equal(joinPrograma.get("id"), programaId));
		
		
		return entityManager.createQuery(query).getResultList();	
		
		
	}
 
}
