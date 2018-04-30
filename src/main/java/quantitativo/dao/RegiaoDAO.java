package quantitativo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import arquitetura.dao.AbstractDAO;
import arquitetura.utils.Utils;
import quantitativo.model.Regiao;

public class RegiaoDAO extends AbstractDAO<Regiao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RegiaoDAO() {
		setClazz(Regiao.class);

	}

	public List<Regiao> findAllOrderByDescricao() {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		CriteriaQuery<Regiao> q = cb.createQuery(Regiao.class);

		Root<Regiao> c = q.from(Regiao.class);

		q.select(c);
		q.orderBy(cb.asc(c.get("descricao")));

		return entityManager.createQuery(q).getResultList();

	}

	public List<Regiao> findAllByTipoRegiao(Long tipoRegiaoId) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Regiao> query = cb.createQuery(Regiao.class);
		Root<Regiao> m = query.from(Regiao.class);

		query.select(m);

		List<Predicate> predicate = new ArrayList<>();

		if (!Utils.invalidId((tipoRegiaoId))) {

			Join<Object, Object> joinOrgao = m.join("tipoRegiao", JoinType.INNER);
			joinOrgao.on(cb.equal(joinOrgao.get("id"), tipoRegiaoId));

		}

		query.where(predicate.toArray(new Predicate[predicate.size()]));

		query.orderBy(cb.asc(m.get("descricao")));

		return entityManager.createQuery(query).getResultList();

	}

}
