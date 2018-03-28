package administrativo.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import administrativo.model.Perfil;
import arquitetura.dao.AbstractDAO;

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

}
