package qualitativo.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import arquitetura.dao.AbstractDAO;
import qualitativo.model.Mes;

public class MesDAO extends AbstractDAO<Mes> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MesDAO() {
		setClazz(Mes.class);

	}

	public List<Mes> findallOrderById() {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Mes> query = cb.createQuery(Mes.class);
		Root<Mes> m = query.from(Mes.class);

		query.select(m);

		query.orderBy(cb.asc(m.get("id")));

		return entityManager.createQuery(query).getResultList();
	}

}
