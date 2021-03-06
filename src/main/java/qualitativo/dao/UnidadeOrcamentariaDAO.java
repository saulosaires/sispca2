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
	private static final String ID = "id";	
	
	public UnidadeOrcamentariaDAO() {
		setClazz(UnidadeOrcamentaria.class);

	}
 
	public List<UnidadeOrcamentaria> buscar(List<Long>unidadeOrcamentariaId, String codigo, String descricao, Long orgaoId) {

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
		
		if(unidadeOrcamentariaId!=null && !unidadeOrcamentariaId.isEmpty()) {

			predicate.add(cb.isTrue(m.get(ID).in(unidadeOrcamentariaId)) );
			 
		}
 
		if (!Utils.invalidId((orgaoId))) {

			Join<Object, Object> joinOrgao = m.join(ORGAO,JoinType.INNER);
			joinOrgao.on(cb.equal(joinOrgao.get("id"),orgaoId) );
		
		}
		
		query.where(predicate.toArray(new Predicate[predicate.size()]));

		query.orderBy(cb.asc(m.get(DESCRICAO)));

		return entityManager.createQuery(query).getResultList();
		
	}

	 
		public List<UnidadeOrcamentaria> buscarByOrgao(Long orgaoId) {

			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<UnidadeOrcamentaria> query = cb.createQuery(UnidadeOrcamentaria.class);
			Root<UnidadeOrcamentaria> m = query.from(UnidadeOrcamentaria.class);

			query.select(m);

			List<Predicate> predicate = new ArrayList<>();
 
	 
			if (!Utils.invalidId((orgaoId))) {

				Join<Object, Object> joinOrgao = m.join(ORGAO,JoinType.INNER);
				joinOrgao.on(cb.equal(joinOrgao.get("id"),orgaoId) );
			
			}
			
			query.where(predicate.toArray(new Predicate[predicate.size()]));

			query.orderBy(cb.asc(m.get(DESCRICAO)));

			return entityManager.createQuery(query).getResultList();
			
		}



}
