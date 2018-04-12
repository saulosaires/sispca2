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
import javax.transaction.Transactional;

import arquitetura.dao.AbstractDAO;
import arquitetura.utils.Utils;
import qualitativo.model.UnidadeOrcamentaria;
 
public class UnidadeOrcamentariaDAO extends AbstractDAO<UnidadeOrcamentaria> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8846447778420285798L;

	private static final String DESCRICAO = "descricao";
	private static final String CODIGO = "codigo";
	private static final String SIGLA = "sigla";
	private static final String ORGAO = "orgao";	
	
	
	public UnidadeOrcamentariaDAO() {
		setClazz(UnidadeOrcamentaria.class);

	}

	public List<UnidadeOrcamentaria> findAllOrderBySigla() {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<UnidadeOrcamentaria> q = cb.createQuery(UnidadeOrcamentaria.class);
		
		Root<UnidadeOrcamentaria> c = q.from(UnidadeOrcamentaria.class);
		
		q.select(c);
		q.orderBy(cb.asc(c.get(SIGLA)));
 
		return  entityManager.createQuery(q).getResultList();

	}
	
	public List<UnidadeOrcamentaria> findAllOrderByDescricao() {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<UnidadeOrcamentaria> q = cb.createQuery(UnidadeOrcamentaria.class);
		
		Root<UnidadeOrcamentaria> c = q.from(UnidadeOrcamentaria.class);
		
		q.select(c);
		q.orderBy(cb.asc(c.get(DESCRICAO)));
 
		return  entityManager.createQuery(q).getResultList();

	}

	public List<UnidadeOrcamentaria> buscar(String codigo, String descricao, Long orgaoId) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<UnidadeOrcamentaria> query = cb.createQuery(UnidadeOrcamentaria.class);
		Root<UnidadeOrcamentaria> m = query.from(UnidadeOrcamentaria.class);

		query.select(m);

		List<Predicate> predicate = new ArrayList<>();

		if (!Utils.emptyParam(codigo)) {

			Expression<String> upperCodigo = cb.upper(m.get(CODIGO));

			predicate.add(cb.like(upperCodigo, "%" + codigo.toUpperCase() + "%"));
		}

		if (!Utils.emptyParam(descricao)) {

			Expression<String> upperDes = cb.upper(m.get(DESCRICAO));

			predicate.add(cb.like(upperDes, "%" + descricao.toUpperCase() + "%"));
		}
		
	 
		
		if (!Utils.invalidId((orgaoId))) {

			Join<Object, Object> joinOrgao = m.join(ORGAO,JoinType.INNER);
			joinOrgao.on(cb.equal(joinOrgao.get("id"),orgaoId) );
		
		}
		
		query.where(predicate.toArray(new Predicate[predicate.size()]));

		query.orderBy(cb.asc(m.get(DESCRICAO)));

		return entityManager.createQuery(query).getResultList();
		
	}


}
