package quantitativo.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import arquitetura.dao.AbstractDAO;
import quantitativo.model.TipoRegiao;

public class TipoRegiaoDAO extends AbstractDAO<TipoRegiao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TipoRegiaoDAO() {
		setClazz(TipoRegiao.class);

	}
  

public List<TipoRegiao> findAllOrderByDescricao() {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<TipoRegiao> q = cb.createQuery(TipoRegiao.class);
		
		Root<TipoRegiao> c = q.from(TipoRegiao.class);
		
		q.select(c);
		q.orderBy(cb.asc(c.get("descricao")));
 
		return  entityManager.createQuery(q).getResultList();

	}

}
