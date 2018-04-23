package avaliacao.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import arquitetura.dao.AbstractDAO;
import avaliacao.model.DiretrizAssociada;
 

public class DiretrizAssociadaDAO extends AbstractDAO< DiretrizAssociada >  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1358843609602731648L;


	public DiretrizAssociadaDAO() {
		setClazz(DiretrizAssociada.class );
	 
	}
 
 
	public List<DiretrizAssociada> findByProgramaAndExercicio(Long programaId, Long exercicioId){
 		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<DiretrizAssociada> query = cb.createQuery(DiretrizAssociada.class);
		Root<DiretrizAssociada> m = query.from(DiretrizAssociada.class);
		query.select(m);
 
		
		Join<Object, Object> joinExercicio = m.join("exercicio", JoinType.INNER);
		joinExercicio.on(cb.equal(joinExercicio.get("id"), exercicioId));
		
		
		
		Join<Object, Object> joinPrograma = m.join("programa", JoinType.INNER);
		joinPrograma.on(cb.equal(joinPrograma.get("id"), programaId));
		
		
		return entityManager.createQuery(query).getResultList();	
		
		
	}
 
}
