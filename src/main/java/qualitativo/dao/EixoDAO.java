package qualitativo.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import arquitetura.dao.AbstractDAO;
import qualitativo.model.Eixo;

public class EixoDAO extends AbstractDAO<Eixo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2103883892152462274L;

	public EixoDAO() {
		setClazz(Eixo.class);

	}

	public List<Eixo> findAllOrderByDescricao() {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		CriteriaQuery<Eixo> q = cb.createQuery(Eixo.class);

		Root<Eixo> c = q.from(Eixo.class);

		q.select(c);
		q.orderBy(cb.asc(c.get("descricao")));

		return entityManager.createQuery(q).getResultList();

	}

}
