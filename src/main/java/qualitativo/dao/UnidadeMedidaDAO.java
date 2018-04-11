package qualitativo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import arquitetura.dao.AbstractDAO;
import arquitetura.utils.Utils;
import qualitativo.model.UnidadeMedida;

public class UnidadeMedidaDAO extends AbstractDAO<UnidadeMedida> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2103883892152462274L;

	private static final String DESCRICAO = "descricao";
	private static final String SIGLA = "sigla";
	
	public UnidadeMedidaDAO() {
		setClazz(UnidadeMedida.class);

	}

	public List<UnidadeMedida> findAllOrderByDescricao() {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		CriteriaQuery<UnidadeMedida> q = cb.createQuery(UnidadeMedida.class);

		Root<UnidadeMedida> c = q.from(UnidadeMedida.class);

		q.select(c);
		q.orderBy(cb.asc(c.get(DESCRICAO)));

		return entityManager.createQuery(q).getResultList();

	}

	public  List<UnidadeMedida> buscar(String sigla, String descricao) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<UnidadeMedida> query = cb.createQuery(UnidadeMedida.class);
		Root<UnidadeMedida> m = query.from(UnidadeMedida.class);

		query.select(m);

		List<Predicate> predicate = new ArrayList<>();

		if (!Utils.emptyParam(sigla)) {
 
			predicate.add(cb.like(cb.upper(m.get(SIGLA)), "%" + sigla.toUpperCase() + "%"));
		}
		
		if (!Utils.emptyParam(descricao)) {
			 
			predicate.add(cb.like(cb.upper(m.get(DESCRICAO)), "%" + descricao.toUpperCase() + "%"));
		}
		
		
		query.orderBy(cb.asc(m.get(DESCRICAO)));
		
		query.where(predicate.toArray(new Predicate[predicate.size()]));

		return entityManager.createQuery(query).getResultList();
	}

}
