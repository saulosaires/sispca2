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
 

public class ActionDAO extends AbstractDAO< Action >  {

	
	public ActionDAO() {
		setClazz(Action.class );
	 
	}
 
	public List<Action> buscaActionsComCalendario(){
 
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Action> query = cb.createQuery(Action.class);
		Root<Action> m = query.from(Action.class);
		query.select(m);
	 
		query.where(
				     cb.equal(m.get("ativo"), Boolean.TRUE),
				     cb.equal(m.get("possuiCalendario"), Boolean.TRUE)
				    );
  
		return getEntityManager().createQuery(query).getResultList();
 
		
	}
  
 
}
