package administrativo.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import administrativo.model.Action;
import administrativo.model.Calendario;
import administrativo.model.Usuario;
import arquitetura.dao.AbstractDAO;
 

public class CalendarioDAO extends AbstractDAO< Calendario >  {

	
	public CalendarioDAO() {
		setClazz(Calendario.class );
	 
	}
 
 
	public Optional<Calendario> findCalendarioByExercicio(Long exercicioId){
 		
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Calendario> query = cb.createQuery(Calendario.class);
		Root<Calendario> m = query.from(Calendario.class);
		query.select(m);
	 
		query.where(cb.equal(m.get("exercicio"), exercicioId));
 
		
		List<Calendario> list = getEntityManager().createQuery(query).setMaxResults(1).getResultList();
		
		return list.stream().findFirst();
		
		
	}
 
}
