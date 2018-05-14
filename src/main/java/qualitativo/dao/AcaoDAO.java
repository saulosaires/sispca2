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
import qualitativo.model.Acao;

public class AcaoDAO extends AbstractDAO<Acao> {

 

	/**
	 * 
	 */
	private static final long serialVersionUID = -5222534690693067790L;

	public AcaoDAO() {
		setClazz(Acao.class);

	}

	public List<Acao> buscar(String codigo, String codigoUnidadeOrcamentaria,String codigoPrograma,Long exercicioId){
		 
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Acao> query = cb.createQuery(Acao.class);
		Root<Acao> m = query.from(Acao.class);
		
		query.select(m);
		
		List<Predicate> predicate = new ArrayList<>();
		
		if(!Utils.emptyParam(codigo)) {
			
			Expression<String> upperCodigo = cb.upper(m.get("codigo"));
			
			predicate.add(cb.like(upperCodigo,codigo.toUpperCase()));
			
		}
		
		
		if(!Utils.emptyParam(codigoUnidadeOrcamentaria)) {
 			
			Join<Object, Object> joinUO = m.join("unidadeOrcamentaria",JoinType.LEFT);
	 
			Expression<String> upperCodigo = cb.upper(joinUO.get("codigo"));
			
			predicate.add(cb.like(upperCodigo,codigoUnidadeOrcamentaria.toUpperCase()));
			
		}
		
		if(!Utils.emptyParam(codigoPrograma)) {
 			
			Join<Object, Object> join = m.join("programa",JoinType.LEFT);
			
			Expression<String> upperCodigo = cb.upper(join.get("codigo"));
			
			predicate.add(cb.like(upperCodigo,codigoPrograma.toUpperCase()));

		}
		
		if(!Utils.invalidId(exercicioId)) {
 			
			Join<Object, Object> joinExercicio = m.join("exercicio",JoinType.LEFT);
						
			predicate.add(cb.equal(joinExercicio.get("id") ,exercicioId));
		}

		
		query.where(  predicate.toArray(new Predicate[predicate.size()]));
  
		query.orderBy(cb.asc(m.get("denominacao")));
		
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	public List<Acao> buscar(String codigo, String denominacao,Long unidadeOrcamentariaId,Long programaId,Long exercicioId){
		 
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Acao> query = cb.createQuery(Acao.class);
		Root<Acao> m = query.from(Acao.class);
		
		query.select(m);
		
		List<Predicate> predicate = new ArrayList<>();
		
		if(!Utils.emptyParam(codigo)) {
			
			Expression<String> upperCodigo = cb.upper(m.get("codigo"));
			
			predicate.add(cb.like(upperCodigo,"%"+codigo.toUpperCase()+"%"));
		}
		
		if(!Utils.emptyParam(denominacao)) {
			
			Expression<String> upperDenominacao = cb.upper(m.get("denominacao"));
			
			predicate.add(cb.like(upperDenominacao,"%"+denominacao.toUpperCase()+"%" ));		
		}
		
		if(!Utils.invalidId(unidadeOrcamentariaId)) {
 			
			Join<Object, Object> joinUO = m.join("unidadeOrcamentaria",JoinType.LEFT);
			
			Expression<String> uoId = joinUO.get("id");
			
			predicate.add(cb.equal(uoId,unidadeOrcamentariaId));
		}
		
		if(!Utils.invalidId(programaId)) {
 			
			Join<Object, Object> joinUO = m.join("programa",JoinType.LEFT);
			
			Expression<String> uoId = joinUO.get("id");
			
			predicate.add(cb.equal(uoId,programaId));
		}
		
		if(!Utils.invalidId(exercicioId)) {
 			
			Join<Object, Object> joinExercicio = m.join("exercicio",JoinType.LEFT);
						
			predicate.add(cb.equal(joinExercicio.get("id") ,exercicioId));
		}

		
		query.where(  predicate.toArray(new Predicate[predicate.size()]));
  
		query.orderBy(cb.asc(m.get("denominacao")));
		
		return entityManager.createQuery(query).getResultList();
	}

	public List<Acao> buscarByExercicio(Long exercicioId){
		 
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Acao> query = cb.createQuery(Acao.class);
		Root<Acao> m = query.from(Acao.class);
		
		query.select(m);
 
 			
		if (!Utils.invalidId((exercicioId))) {

			Join<Object, Object> joinExercicio = m.join("exercicio",JoinType.INNER);
			joinExercicio.on(cb.equal(joinExercicio.get("id"),exercicioId) );	
		
		}
		  
		query.orderBy(cb.asc(m.get("denominacao")));
		
		return entityManager.createQuery(query).getResultList();
	}
	
	public List<Acao> buscarByUnidadeOrcamentaria(Long unidadeOrcamentariaId){
		 
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Acao> query = cb.createQuery(Acao.class);
		Root<Acao> m = query.from(Acao.class);
		
		query.select(m);
 
 			
		if (!Utils.invalidId((unidadeOrcamentariaId))) {

			Join<Object, Object> joinExercicio = m.join("unidadeOrcamentaria",JoinType.INNER);
			joinExercicio.on(cb.equal(joinExercicio.get("id"),unidadeOrcamentariaId) );	
		
		}
		  
		query.orderBy(cb.asc(m.get("denominacao")));
		
		return entityManager.createQuery(query).getResultList();
	}
	
}
