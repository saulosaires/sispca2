package administrativo.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import administrativo.model.Menu;
import arquitetura.dao.AbstractDAO;

public class MenuDAO extends AbstractDAO<Menu> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6194919124690666522L;

	public MenuDAO() {
		setClazz(Menu.class);

	}

	
	public List<Menu> findRoot(){
		

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Menu> query = cb.createQuery(Menu.class);
		Root<Menu> m = query.from(Menu.class);
		query.select(m);
		
		query.where(
			    	  cb.isNull(m.get("pai")),
			    	  cb.equal(m.get("ativo"),Boolean.TRUE)
			    	);
 
		
		return entityManager.createQuery(query).getResultList();
		
	}
	
	public List<Menu> findChildMenu(Long idRoot){
		

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Menu> query = cb.createQuery(Menu.class);
		Root<Menu> m = query.from(Menu.class);
		query.select(m);
		
		query.where(
			    	  cb.equal(m.get("pai"),  idRoot ),
			    	  cb.equal(m.get("ativo"),Boolean.TRUE)
			    	);
 
		
		return entityManager.createQuery(query).getResultList();
		
	}	
	
}
