package avaliacao.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import arquitetura.dao.AbstractDAO;
import avaliacao.model.DiretrizAssociada;
import avaliacao.model.IndicadorDesempenhoIntermediario;
 

public class IndicadorDesempenhoIntermediarioDAO extends AbstractDAO< IndicadorDesempenhoIntermediario >  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1358843609602731648L;


	public IndicadorDesempenhoIntermediarioDAO() {
		setClazz(IndicadorDesempenhoIntermediario.class );
	 
	}
 
 
 
	public List<IndicadorDesempenhoIntermediario> findByProgramaAndExercicio(Long programaId, Long exercicioId){
 		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<IndicadorDesempenhoIntermediario> query = cb.createQuery(IndicadorDesempenhoIntermediario.class);
		Root<IndicadorDesempenhoIntermediario> m = query.from(IndicadorDesempenhoIntermediario.class);
		query.select(m);
 
		
		Join<Object, Object> joinExercicio = m.join("exercicio", JoinType.INNER);
		joinExercicio.on(cb.equal(joinExercicio.get("id"), exercicioId));
		
		
		
		Join<Object, Object> joinPrograma = m.join("programa", JoinType.INNER);
		joinPrograma.on(cb.equal(joinPrograma.get("id"), programaId));
		
		
		return entityManager.createQuery(query).getResultList();	
		
		
	}
 
}
