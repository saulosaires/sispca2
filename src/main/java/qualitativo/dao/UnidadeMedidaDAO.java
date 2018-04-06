package qualitativo.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import arquitetura.dao.AbstractDAO;
import qualitativo.model.UnidadeMedida;

public class UnidadeMedidaDAO extends AbstractDAO<UnidadeMedida> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2103883892152462274L;

	public UnidadeMedidaDAO() {
		setClazz(UnidadeMedida.class);

	}

	public List<UnidadeMedida> findAllOrderByDescricao() {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		CriteriaQuery<UnidadeMedida> q = cb.createQuery(UnidadeMedida.class);

		Root<UnidadeMedida> c = q.from(UnidadeMedida.class);

		q.select(c);
		q.orderBy(cb.asc(c.get("descricao")));

		return entityManager.createQuery(q).getResultList();

	}

}
