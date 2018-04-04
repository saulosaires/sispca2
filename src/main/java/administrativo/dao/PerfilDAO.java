package administrativo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import administrativo.model.Mensagem;
import administrativo.model.Perfil;
import administrativo.model.Usuario;
import arquitetura.dao.AbstractDAO;
import arquitetura.utils.Utils;

public class PerfilDAO extends AbstractDAO<Perfil> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6474504009545993672L;

	public PerfilDAO() {
		setClazz(Perfil.class);

	}

	public List<Perfil> findAllOrderByName() {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		CriteriaQuery<Perfil> q = cb.createQuery(Perfil.class);

		Root<Perfil> c = q.from(Perfil.class);

		q.select(c);
		q.orderBy(cb.asc(c.get("name")));

		return entityManager.createQuery(q).getResultList();

	}

	public Optional<Perfil> findByDescription(String name) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Perfil> query = cb.createQuery(Perfil.class);
		Root<Perfil> m = query.from(Perfil.class);
		
		query.select(m);
		
		List<Predicate> predicate = new ArrayList<>();
		 
		
		if(!Utils.emptyParam(name)) {
			
			Expression<String> upperUrl = cb.upper(m.get("name"));
			
			predicate.add(cb.like(upperUrl,"%"+name.toUpperCase()+"%" ));		
		}
		
		query.where(  predicate.toArray(new Predicate[predicate.size()]));
 
		List<Perfil> list = entityManager.createQuery(query).setMaxResults(1).getResultList();
		
		return list.stream().findFirst();


		
	}

}
