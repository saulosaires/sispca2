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

	public List<Programa> buscar(String codigo, String denominacao, Long orgao, Long tipoPrograma,Long exercicioId,Long eixoId) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Programa> query = cb.createQuery(Programa.class);
		Root<Programa> m = query.from(Programa.class);

		query.select(m);
		
		List<Predicate> predicate = new ArrayList<>();

		if (!Utils.emptyParam(codigo)) {

			Expression<String> upperCodigo = cb.upper(m.get("codigo"));

			predicate.add(cb.like(upperCodigo, "%" + codigo.toUpperCase() + "%"));
		}

		if (!Utils.emptyParam(denominacao)) {

			Expression<String> upperDenominacao = cb.upper(m.get("denominacao"));

			predicate.add(cb.like(upperDenominacao, "%" + denominacao.toUpperCase() + "%"));
		}
		
		if (!Utils.invalidId((orgao))) {

			Join<Object, Object> joinOrgao = m.join("orgao",JoinType.INNER);
			joinOrgao.on(cb.equal(joinOrgao.get("id"),orgao) );
		
		}

		if (!Utils.invalidId((tipoPrograma))) {

			Join<Object, Object> joinTipoPrograma = m.join("tipoPrograma",JoinType.INNER);
			joinTipoPrograma.on(cb.equal(joinTipoPrograma.get("id"),tipoPrograma) );
		
		}
 
		if (!Utils.invalidId((exercicioId))) {

			Join<Object, Object> joinExercicio = m.join("exercicio",JoinType.INNER);
			joinExercicio.on(cb.equal(joinExercicio.get("id"),exercicioId) );	
		
		}
		
		if (!Utils.invalidId((eixoId))) {
			Join<Object, Object> joinEixo = m.join("eixos",JoinType.INNER);
			joinEixo.on(cb.equal(joinEixo.get("id"),eixoId) );	
		}
		
		
		query.where(predicate.toArray(new Predicate[predicate.size()]));

		query.orderBy(cb.asc(m.get("denominacao")));
		
		
		return  entityManager.createQuery(query).getResultList();
	}
	
	 
	public List<Programa> buscarPorUnidadeOrcamentaria(String unidadeOrcamentariaCodigo) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Programa> query = cb.createQuery(Programa.class);
		Root<Programa> m = query.from(Programa.class);

		query.select(m);
		
 
		if (!Utils.emptyParam((unidadeOrcamentariaCodigo))) {
			Join<Object, Object> joinUnidade = m.join("unidadeOrcamentaria",JoinType.INNER);
			joinUnidade.on(cb.equal(joinUnidade.get("codigo"),unidadeOrcamentariaCodigo) );	
		}
		
		 
		query.orderBy(cb.asc(m.get("denominacao")));
		
		
		return  entityManager.createQuery(query).getResultList();
	}
	
	public List<Programa> buscarPorUnidadeOrcamentaria(Long unidadeOrcamentariaId) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Programa> query = cb.createQuery(Programa.class);
		Root<Programa> m = query.from(Programa.class);

		query.select(m);
		
 
		if (!Utils.invalidId((unidadeOrcamentariaId))) {
			Join<Object, Object> joinUnidade = m.join("unidadeOrcamentaria",JoinType.INNER);
			joinUnidade.on(cb.equal(joinUnidade.get("id"),unidadeOrcamentariaId) );	
		}
		
		 
		query.orderBy(cb.asc(m.get("denominacao")));
		
		
		return  entityManager.createQuery(query).getResultList();
	}
	
	 
	
	

}
