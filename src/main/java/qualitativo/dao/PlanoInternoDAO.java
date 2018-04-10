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
import qualitativo.model.PlanoInterno;

public class PlanoInternoDAO extends AbstractDAO<PlanoInterno> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlanoInternoDAO() {
		setClazz(PlanoInterno.class);

	}

	public List<PlanoInterno> buscar(String codigo) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<PlanoInterno> query = cb.createQuery(PlanoInterno.class);
		Root<PlanoInterno> m = query.from(PlanoInterno.class);

		query.select(m);

		List<Predicate> predicate = new ArrayList<>();

		if (!Utils.emptyParam(codigo)) {

			Expression<String> upperCodigo = cb.upper(m.get("sigla"));

			predicate.add(cb.like(upperCodigo, "%" + codigo.toUpperCase() + "%"));
		}

		query.where(predicate.toArray(new Predicate[predicate.size()]));

		query.orderBy(cb.asc(m.get("titulo")));

		return entityManager.createQuery(query).getResultList();
	}

}
