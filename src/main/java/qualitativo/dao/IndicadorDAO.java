package qualitativo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import arquitetura.dao.AbstractDAO;
import arquitetura.utils.Utils;
import qualitativo.model.Indicador;

public class IndicadorDAO extends AbstractDAO<Indicador> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IndicadorDAO() {
		setClazz(Indicador.class);

	}

	
	public List<Indicador> findAllOrderByIndicador() {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		CriteriaQuery<Indicador> q = cb.createQuery(Indicador.class);

		Root<Indicador> c = q.from(Indicador.class);

		q.select(c);
		q.orderBy(cb.asc(c.get("indicador")));

		return entityManager.createQuery(q).getResultList();

	}
	
	public List<Indicador> buscar(String indicador) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Indicador> query = cb.createQuery(Indicador.class);
		Root<Indicador> m = query.from(Indicador.class);

		query.select(m);

		List<Predicate> predicate = new ArrayList<>();

		if (!Utils.emptyParam(indicador)) {

			Expression<String> upperCodigo = cb.upper(m.get("indicador"));

			predicate.add(cb.like(upperCodigo, "%" + indicador.toUpperCase() + "%"));
		}

		query.where(predicate.toArray(new Predicate[predicate.size()]));

		query.orderBy(cb.asc(m.get("indicador")));

		return entityManager.createQuery(query).getResultList();
	}

}
