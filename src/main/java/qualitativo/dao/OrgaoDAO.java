package qualitativo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import arquitetura.dao.AbstractDAO;
import arquitetura.utils.Utils;
import qualitativo.model.Orgao;

public class OrgaoDAO extends AbstractDAO<Orgao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrgaoDAO() {
		setClazz(Orgao.class);

	}

	public List<Orgao> findAllOrderByDescricao() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Orgao> query = cb.createQuery(Orgao.class);
		Root<Orgao> m = query.from(Orgao.class);

		query.select(m);
		
		query.orderBy(cb.asc(m.get("descricao")));

		return entityManager.createQuery(query).getResultList();
	}
	
	
	public List<Orgao> buscar(String codigo,String sigla,String descricao) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Orgao> query = cb.createQuery(Orgao.class);
		Root<Orgao> m = query.from(Orgao.class);

		query.select(m);

		List<Predicate> predicate = new ArrayList<>();

		if (!Utils.emptyParam(codigo)) {

			Expression<String> upperCodigo = cb.upper(m.get("codigo"));

			predicate.add(cb.like(upperCodigo, "%" + codigo.toUpperCase() + "%"));
		}

		if (!Utils.emptyParam(sigla)) {

			Expression<String> upperSigla = cb.upper(m.get("sigla"));

			predicate.add(cb.like(upperSigla, "%" + sigla.toUpperCase() + "%"));
		}
		
		if (!Utils.emptyParam(descricao)) {

			Expression<String> upperDescricao = cb.upper(m.get("descricao"));

			predicate.add(cb.like(upperDescricao, "%" + descricao.toUpperCase() + "%"));
		}	
		
		query.where(predicate.toArray(new Predicate[predicate.size()]));

		query.orderBy(cb.asc(m.get("descricao")));

		return entityManager.createQuery(query).getResultList();
	}

}
