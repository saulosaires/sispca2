package qualitativo.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import arquitetura.dao.AbstractDAO;
import qualitativo.model.SubFuncao;

public class SubFuncaoDAO extends AbstractDAO<SubFuncao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2103883892152462274L;

	public SubFuncaoDAO() {
		setClazz(SubFuncao.class);

	}

	public List<SubFuncao> findAllOrderByCodigo() {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		CriteriaQuery<SubFuncao> q = cb.createQuery(SubFuncao.class);

		Root<SubFuncao> c = q.from(SubFuncao.class);

		q.select(c);
		q.orderBy(cb.asc(c.get("codigo")));

		return entityManager.createQuery(q).getResultList();

	}

}
