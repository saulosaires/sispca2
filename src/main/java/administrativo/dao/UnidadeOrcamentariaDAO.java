package administrativo.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import arquitetura.dao.AbstractDAO;
import qualitativo.model.UnidadeOrcamentaria;

public class UnidadeOrcamentariaDAO extends AbstractDAO<UnidadeOrcamentaria> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8846447778420285798L;

	public UnidadeOrcamentariaDAO() {
		setClazz(UnidadeOrcamentaria.class);

	}

	public List<UnidadeOrcamentaria> findAllOrderBySigla() {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<UnidadeOrcamentaria> q = cb.createQuery(UnidadeOrcamentaria.class);
		
		Root<UnidadeOrcamentaria> c = q.from(UnidadeOrcamentaria.class);
		
		q.select(c);
		q.orderBy(cb.asc(c.get("sigla")));
 
		return  entityManager.createQuery(q).getResultList();

	}

}
