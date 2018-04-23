package avaliacao.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import arquitetura.dao.AbstractDAO;
import avaliacao.model.PainelAssociado;
 

public class PainelAssociadoDAO extends AbstractDAO< PainelAssociado >  {
 

	/**
	 * 
	 */
	private static final long serialVersionUID = -1580652167507898456L;


	public PainelAssociadoDAO() {
		setClazz(PainelAssociado.class );
	 
	}
 
	
	
	public List<PainelAssociado> findByProgramaAndExercicio(Long programaId, Long exercicioId){
 		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<PainelAssociado> query = cb.createQuery(PainelAssociado.class);
		Root<PainelAssociado> m = query.from(PainelAssociado.class);
		query.select(m);
 
		
		Join<Object, Object> joinExercicio = m.join("exercicio", JoinType.INNER);
		joinExercicio.on(cb.equal(joinExercicio.get("id"), exercicioId));
		
		
		
		Join<Object, Object> joinPrograma = m.join("programa", JoinType.INNER);
		joinPrograma.on(cb.equal(joinPrograma.get("id"), programaId));
		
		
		return entityManager.createQuery(query).getResultList();	
		
		
	}
 
}
