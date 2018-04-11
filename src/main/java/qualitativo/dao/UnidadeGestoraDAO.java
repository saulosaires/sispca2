package qualitativo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import arquitetura.dao.AbstractDAO;
import arquitetura.utils.Utils;
import qualitativo.model.UnidadeGestora;

public class UnidadeGestoraDAO extends AbstractDAO<UnidadeGestora> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String DESCRICAO = "descricao";
	private static final String CODIGO = "codigo";
	private static final String SIGLA = "sigla";
	
	public UnidadeGestoraDAO() {
		setClazz(UnidadeGestora.class);

	}

	public List<UnidadeGestora> buscar(String codigo,String descricao,String sigla,Long unidadeOrcamentariaId) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<UnidadeGestora> query = cb.createQuery(UnidadeGestora.class);
		Root<UnidadeGestora> m = query.from(UnidadeGestora.class);

		query.select(m);

		List<Predicate> predicate = new ArrayList<>();

		if (!Utils.emptyParam(codigo)) {

			Expression<String> upperCodigo = cb.upper(m.get(CODIGO));

			predicate.add(cb.like(upperCodigo, "%" + codigo.toUpperCase() + "%"));
		}

		if (!Utils.emptyParam(descricao)) {

			Expression<String> upperCodigo = cb.upper(m.get(DESCRICAO));

			predicate.add(cb.like(upperCodigo, "%" + descricao.toUpperCase() + "%"));
		}
		
		if (!Utils.emptyParam(sigla)) {

			Expression<String> upperCodigo = cb.upper(m.get(SIGLA));

			predicate.add(cb.like(upperCodigo, "%" + sigla.toUpperCase() + "%"));
		}
		
		if (!Utils.invalidId((unidadeOrcamentariaId))) {

			Join<Object, Object> joinTipoPrograma = m.join("unidadeOrcamentaria",JoinType.INNER);
			joinTipoPrograma.on(cb.equal(joinTipoPrograma.get("id"),unidadeOrcamentariaId) );
		
		}
		
		query.where(predicate.toArray(new Predicate[predicate.size()]));

		query.orderBy(cb.asc(m.get(DESCRICAO)));

		return entityManager.createQuery(query).getResultList();
	}

}
