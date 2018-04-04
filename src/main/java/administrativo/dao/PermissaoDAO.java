package administrativo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import administrativo.model.Perfil;
import administrativo.model.Permissao;
import arquitetura.dao.AbstractDAO;
import arquitetura.utils.Utils;

public class PermissaoDAO extends AbstractDAO<Permissao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6552349274418558040L;

	public PermissaoDAO() {
		setClazz(Permissao.class);

	}

	public List<Permissao> buscaPermissao(String busca){

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Permissao> query = cb.createQuery(Permissao.class);
		Root<Permissao> m = query.from(Permissao.class);

		query.select(m);

		List<Predicate> predicate = new ArrayList<>();

		if (!Utils.emptyParam(busca)) {

			Expression<String> upperSigla = cb.upper(m.get("acao"));

			predicate.add(cb.like(upperSigla, "%" + busca.toUpperCase() + "%"));
		}

 
		query.where(predicate.toArray(new Predicate[predicate.size()]));

		return entityManager.createQuery(query).getResultList();

	}
 
	public List<Permissao> findPermissaoAssociada(Long perfilId) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Permissao> query = cb.createQuery(Permissao.class);
		Root<Permissao> root = query.from(Permissao.class);

		query.select(root);
		
		Join<Object, Object> joinPerfil = root.join("perfil",JoinType.INNER);
		joinPerfil.on(cb.equal(joinPerfil.get("id"),perfilId) );
		
		 
		
		return entityManager.createQuery(query).getResultList();

	}
	
	
	
}
