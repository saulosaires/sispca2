package qualitativo.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import arquitetura.dao.AbstractDAO;
import qualitativo.model.Programa;

public class ProgramaDAO extends AbstractDAO<Programa> {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 2103883892152462274L;

	public ProgramaDAO() {
		setClazz(Programa.class);

	}

	public List<Programa> findAllOrderByDenominacao() {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Programa> q = cb.createQuery(Programa.class);
		
		Root<Programa> c = q.from(Programa.class);
		
		q.select(c);
		q.orderBy(cb.asc(c.get("denominacao")));
 
		return  entityManager.createQuery(q).getResultList();

	}

}
