package qualitativo.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import arquitetura.dao.AbstractDAO;
import qualitativo.model.UnidadeGestora;

public class UnidadeGestoraDAO extends AbstractDAO<UnidadeGestora> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnidadeGestoraDAO() {
		setClazz(UnidadeGestora.class);

	}

	public List<UnidadeGestora> buscar(String codigo,String descricao,String sigla,Long unidadeOrcamentariaId) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<UnidadeGestora> query = cb.createQuery(UnidadeGestora.class);
		Root<UnidadeGestora> m = query.from(UnidadeGestora.class);

		query.select(m);

//		List<Predicate> predicate = new ArrayList<>();
//
//		if (!Utils.emptyParam(indicador)) {
//
//			Expression<String> upperCodigo = cb.upper(m.get("indicador"));
//
//			predicate.add(cb.like(upperCodigo, "%" + indicador.toUpperCase() + "%"));
//		}
//
//		query.where(predicate.toArray(new Predicate[predicate.size()]));

		query.orderBy(cb.asc(m.get("descricao")));

		return entityManager.createQuery(query).getResultList();
	}

}
