package qualitativo.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import arquitetura.dao.AbstractDAO;
import qualitativo.model.Funcao;

public class FuncaoDAO extends AbstractDAO<Funcao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2103883892152462274L;

	public FuncaoDAO() {
		setClazz(Funcao.class);

	}

	public List<Funcao> findAllOrderByCodigo() {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		CriteriaQuery<Funcao> q = cb.createQuery(Funcao.class);

		Root<Funcao> c = q.from(Funcao.class);

		q.select(c);
		q.orderBy(cb.asc(c.get("codigo")));

		return entityManager.createQuery(q).getResultList();

	}

}
