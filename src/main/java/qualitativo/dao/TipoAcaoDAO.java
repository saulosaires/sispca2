package qualitativo.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import arquitetura.dao.AbstractDAO;
import qualitativo.model.TipoAcao;

public class TipoAcaoDAO extends AbstractDAO<TipoAcao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2103883892152462274L;

	public TipoAcaoDAO() {
		setClazz(TipoAcao.class);

	}

	public List<TipoAcao> findAllOrderByDescricao() {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		CriteriaQuery<TipoAcao> q = cb.createQuery(TipoAcao.class);

		Root<TipoAcao> c = q.from(TipoAcao.class);

		q.select(c);
		q.orderBy(cb.asc(c.get("descricao")));

		return entityManager.createQuery(q).getResultList();

	}

}
