package administrativo.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import administrativo.model.Calendario;
import arquitetura.dao.AbstractDAO;
 

public class CalendarioDAO extends AbstractDAO< Calendario >  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1358843609602731648L;


	public CalendarioDAO() {
		setClazz(Calendario.class );
	 
	}
 
 
	public Optional<Calendario> findCalendarioByExercicio(Long exercicioId){
 		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Calendario> query = cb.createQuery(Calendario.class);
		Root<Calendario> m = query.from(Calendario.class);
		query.select(m);
	 
		query.where(cb.equal(m.get("exercicio"), exercicioId));
 
		
		List<Calendario> list = entityManager.createQuery(query).setMaxResults(1).getResultList();
		
		return list.stream().findFirst();
		
		
	}
 
}
