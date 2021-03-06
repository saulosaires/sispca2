package administrativo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import administrativo.model.Link;
import arquitetura.dao.AbstractDAO;
import arquitetura.utils.Utils;

public class LinkDAO extends AbstractDAO<Link> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2591235993911028241L;

	public LinkDAO() {
		setClazz(Link.class);

	}

	@Transactional
	public List<Link> queryLinkByDescricaoAndURL(String titulo, String url){
		 
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Link> query = cb.createQuery(Link.class);
		Root<Link> m = query.from(Link.class);
		
		query.select(m);
		
		List<Predicate> predicate = new ArrayList<>();
		
		if(!Utils.emptyParam(titulo)) {
			
			Expression<String> upperTitulo = cb.upper(m.get("titulo"));
			
			predicate.add(cb.like(upperTitulo,"%"+titulo.toUpperCase()+"%"));
		}
		
		if(!Utils.emptyParam(url)) {
			
			Expression<String> upperUrl = cb.upper(m.get("url"));
			
			predicate.add(cb.like(upperUrl,"%"+url.toUpperCase()+"%" ));		
		}
		
		query.where(  predicate.toArray(new Predicate[predicate.size()]));
 
		return entityManager.createQuery(query).getResultList();
	}

}
