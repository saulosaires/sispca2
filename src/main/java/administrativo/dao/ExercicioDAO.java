package administrativo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import administrativo.model.Exercicio;
import administrativo.model.Ppa;
import administrativo.model.Usuario;
import arquitetura.dao.AbstractDAO;
 

public class ExercicioDAO extends AbstractDAO< Exercicio >  {

	
	public ExercicioDAO() {
		setClazz(Exercicio.class );
	 
	}

	public List<Exercicio> buscaExercicioPorPpaAno(Ppa buscaPpa, Integer ano){
 
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Exercicio> query = cb.createQuery(Exercicio.class);
		Root<Exercicio> m = query.from(Exercicio.class);
		query.select(m);
		
		List<Predicate> list = new ArrayList<>(2);
		
		if(buscaPpa!=null) {
			list.add(   cb.equal(m.get("ppa.id"),buscaPpa.getId() ));
		}
		
		if(ano!=null && ano>0) {
			list.add(cb.equal(m.get("ano"),ano));
		}
		
		query.where(list.toArray(new Predicate[list.size()]));
 
		
		return getEntityManager().createQuery(query).getResultList();
	}

 
	public Integer retornaQuantidadeVigente(){
 		
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Exercicio> query = cb.createQuery(Exercicio.class);
		Root<Exercicio> m = query.from(Exercicio.class);
		query.select(m);
	 
		query.where(cb.equal(m.get("vigente"), Boolean.TRUE));
 
		
		return getEntityManager().createQuery(query).getResultList().size();
		
		
	}
 
}
