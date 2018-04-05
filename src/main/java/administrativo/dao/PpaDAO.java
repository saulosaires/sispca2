package administrativo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import administrativo.model.Ppa;
import arquitetura.dao.AbstractDAO;
import arquitetura.utils.Utils;

public class PpaDAO extends AbstractDAO<Ppa> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3992569647283846129L;

	public PpaDAO() {
		setClazz(Ppa.class);

	}

	public List<Ppa> queryPpa(String sigla, String descricao, Integer anoInicio, Integer anoFim) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Ppa> query = cb.createQuery(Ppa.class);
		Root<Ppa> m = query.from(Ppa.class);

		query.select(m);

		List<Predicate> predicate = new ArrayList<>();

		if (!Utils.emptyParam(sigla)) {

			Expression<String> upperSigla = cb.upper(m.get("sigla"));

			predicate.add(cb.like(upperSigla, "%" + sigla.toUpperCase() + "%"));
		}

		if (!Utils.emptyParam(descricao)) {

			Expression<String> upperDes = cb.upper(m.get("descricao"));

			predicate.add(cb.like(upperDes, "%" + descricao.toUpperCase() + "%"));
		}

		if (!Utils.invalidYear((anoInicio))) {

			predicate.add(cb.gt(m.get("anoInicio"), anoInicio));
		}

		if (!Utils.invalidYear((anoFim))) {

			predicate.add(cb.le(m.get("anoFim"), anoFim));
		}

		query.where(predicate.toArray(new Predicate[predicate.size()]));

		return entityManager.createQuery(query).getResultList();

	}

}
