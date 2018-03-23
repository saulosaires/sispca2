package administrativo.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import administrativo.model.Action;
import administrativo.model.ActionCalendario;
import administrativo.model.Calendario;
import arquitetura.dao.AbstractDAO;
 
public class ActionCalendarioDAO  extends AbstractDAO< ActionCalendario >  {

	
	public ActionCalendarioDAO() {
		setClazz(ActionCalendario.class );
	 
	}

	
	public List<ActionCalendario> buscaActionsComCalendario(Calendario calendario, Action action){
		 
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ActionCalendario> query = cb.createQuery(ActionCalendario.class);
		Root<ActionCalendario> m = query.from(ActionCalendario.class);
		query.select(m);
	 
		query.where(
				     cb.equal(m.get("calendario"), calendario.getId()),
				     cb.equal(m.get("action"), action.getId())
				    );
  
		return entityManager.createQuery(query).getResultList();
 
		
	}
	
}
