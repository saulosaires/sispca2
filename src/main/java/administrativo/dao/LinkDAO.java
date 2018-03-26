package administrativo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import administrativo.model.Link;
import arquitetura.dao.AbstractDAO;
import arquitetura.utils.Utils;

public class LinkDAO extends AbstractDAO<Link> {

	public LinkDAO() {
		setClazz(Link.class);

	}

	public List<Link> queryLinkByDescricaoAndURL(String titulo, String url){
		 
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Link> query = cb.createQuery(Link.class);
		Root<Link> m = query.from(Link.class);
		
		query.select(m);
		
		List<Predicate> predicate = new ArrayList<>();
		
		if(!Utils.emptyParam(titulo)) {
			
			Expression<String> upperTitulo = cb.upper(m.get("titulo"));
			
			predicate.add(cb.equal(upperTitulo,titulo.toUpperCase()));
		}
		
		if(!Utils.emptyParam(url)) {
			
			Expression<String> upperUrl = cb.upper(m.get("url"));
			
			predicate.add(cb.equal(upperUrl,url ));		
		}
		
		query.where( (Expression<Boolean>) predicate );
 
		return entityManager.createQuery(query).getResultList();
	}

}
