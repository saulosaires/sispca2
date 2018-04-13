package administrativo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import administrativo.model.Exercicio;
import administrativo.model.Ppa;
import arquitetura.dao.AbstractDAO;
import arquitetura.utils.Utils;
 

public class ExercicioDAO extends AbstractDAO< Exercicio >  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1756832461206322840L;

	public ExercicioDAO() {
		setClazz(Exercicio.class );
	 
	}

	public List<Exercicio> buscarPorPpa(Long ppaId){
 
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Exercicio> query = cb.createQuery(Exercicio.class);
		Root<Exercicio> m = query.from(Exercicio.class);
		query.select(m);
		
		List<Predicate> list = new ArrayList<>(1);
		
		if(!Utils.invalidId(ppaId)) {
			
			Join<Object, Object> joinPerfil = m.join("ppa",JoinType.LEFT); 
			
			list.add(   cb.equal(joinPerfil.get("id"),ppaId));
		}
		
 		
		query.where(list.toArray(new Predicate[list.size()]));
 
		query.orderBy(cb.asc(m.get("ano")));
		
		return entityManager.createQuery(query).getResultList();
	}

 
	public Integer quantidadeVigente(){
 		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Exercicio> query = cb.createQuery(Exercicio.class);
		Root<Exercicio> m = query.from(Exercicio.class);
		query.select(m);
	 
		query.where(cb.equal(m.get("vigente"), Boolean.TRUE));
 
		
		return entityManager.createQuery(query).getResultList().size();
		
		
	}

	public Optional<Exercicio> exercicioVigente() {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Exercicio> query = cb.createQuery(Exercicio.class);
		Root<Exercicio> m = query.from(Exercicio.class);
		query.select(m);
	 
		query.where(cb.equal(m.get("vigente"), Boolean.TRUE));
 
		
		return entityManager.createQuery(query).setMaxResults(1).getResultList().stream().findFirst();
		
	}
 
}
