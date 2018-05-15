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

	private static final  String PROGRAMA="programa"; 
	private static final  String UNIDADE_ORCAMENTARIA="unidadeOrcamentaria"; 
	private static final  String ORGAO="orgao";
	private static final  String EXERCICIO="exercicio";
	
	private static final  String CODIGO="codigo";
	private static final  String DESCRICAO="descricao";
	private static final  String DENOMINACAO="denominacao";
	private static final  String FINALIDADE="finalidade";
	private static final  String ATIVO="ativo";
	private static final  String ID="id";
	
	public AcaoDAO() {
		setClazz(Acao.class);
	}

	public List<Acao> relatorio(Long orgaoId,Long unidadeOrcamentariaId,Long programaId,Long exercicioId){
		 
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Acao> query = cb.createQuery(Acao.class);
		Root<Acao> root = query.from(Acao.class);
		
		Join<Object, Object> joinPrograma 			 = root.join(PROGRAMA, 			   	   JoinType.LEFT);		
		Join<Object, Object> joinUnidadeOrcamentaria = root.join(UNIDADE_ORCAMENTARIA,     JoinType.LEFT);
		Join<Object, Object> joinAcaoExercicio 		 = root.join(EXERCICIO, 			   JoinType.LEFT);
		Join<Object, Object> joinProgExercicio 		 = joinPrograma.join(EXERCICIO, 	   JoinType.LEFT);
		Join<Object, Object> joinOrgao			     = joinUnidadeOrcamentaria.join(ORGAO, JoinType.LEFT);
		
		
		query.multiselect(
			 	 			joinOrgao.get(CODIGO),
			 	 			joinOrgao.get(DESCRICAO),
			 	 			joinUnidadeOrcamentaria.get(CODIGO),
			 	 			joinUnidadeOrcamentaria.get(DESCRICAO),
			 	 			joinPrograma.get(CODIGO),
			 	 			joinPrograma.get(DENOMINACAO),
			 	 			root.get(CODIGO),
			 	 			root.get(DENOMINACAO),
			 	 			root.get(FINALIDADE),
			 	 			root.get(DESCRICAO)
				 		  );
		
		List<Predicate> predicate = new ArrayList<>();
		
		predicate.add(cb.equal(root.get(ATIVO),					  true));
		predicate.add(cb.equal(joinPrograma.get(ATIVO),			  true));
		predicate.add(cb.equal(joinUnidadeOrcamentaria.get(ATIVO),true));
		predicate.add(cb.equal(joinOrgao.get(ATIVO),			  true));
		
		if(!Utils.invalidId(orgaoId)) {
			predicate.add(cb.equal(joinOrgao.get(ID),orgaoId));
		}
		
		if(!Utils.invalidId(unidadeOrcamentariaId)) {
			predicate.add(cb.equal(joinUnidadeOrcamentaria.get(ID),unidadeOrcamentariaId));
		}

		if(!Utils.invalidId(programaId)) {
			predicate.add(cb.equal(joinPrograma.get(ID),programaId));
		}

		if(!Utils.invalidId(exercicioId)) {
			predicate.add(cb.equal(joinAcaoExercicio.get(ID),exercicioId));
			predicate.add(cb.equal(joinProgExercicio.get(ID),exercicioId));
		}

		query.where( predicate.toArray(new Predicate[predicate.size()]));
  
		query.groupBy(
		 	 		  joinOrgao.get(CODIGO),
		 	 		  joinOrgao.get(DESCRICAO),
		 	 		  joinUnidadeOrcamentaria.get(CODIGO),
		 	 		  joinUnidadeOrcamentaria.get(DESCRICAO),
		 	 		  joinPrograma.get(CODIGO),
		 	 		  joinPrograma.get(DENOMINACAO),
		 	 		  root.get(CODIGO),
		 	 		  root.get(DENOMINACAO),
		 	 		  root.get(FINALIDADE),
		 	 		  root.get(DESCRICAO)
				  );

		query.orderBy(
				       cb.asc(joinOrgao.get(CODIGO)),
				       cb.asc(joinUnidadeOrcamentaria.get(CODIGO)),
				       cb.asc(joinPrograma.get(CODIGO)),
				       cb.asc(root.get(CODIGO))
				     );

		return entityManager.createQuery(query).getResultList();
		
	}	
	
	
	public List<Acao> buscar(String codigo, String codigoUnidadeOrcamentaria,String codigoPrograma,Long exercicioId){
		 
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Acao> query = cb.createQuery(Acao.class);
		Root<Acao> m = query.from(Acao.class);
		
		query.select(m);
		
		List<Predicate> predicate = new ArrayList<>();
		
		if(!Utils.emptyParam(codigo)) {
			
			Expression<String> upperCodigo = cb.upper(m.get(CODIGO));
			
			predicate.add(cb.like(upperCodigo,codigo.toUpperCase()));
			
		}
		
		
		if(!Utils.emptyParam(codigoUnidadeOrcamentaria)) {
 			
			Join<Object, Object> joinUO = m.join(UNIDADE_ORCAMENTARIA,JoinType.LEFT);
	 
			Expression<String> upperCodigo = cb.upper(joinUO.get(CODIGO));
			
			predicate.add(cb.like(upperCodigo,codigoUnidadeOrcamentaria.toUpperCase()));
			
		}
		
		if(!Utils.emptyParam(codigoPrograma)) {
 			
			Join<Object, Object> join = m.join(PROGRAMA,JoinType.LEFT);
			
			Expression<String> upperCodigo = cb.upper(join.get(CODIGO));
			
			predicate.add(cb.like(upperCodigo,codigoPrograma.toUpperCase()));

		}
		
		if(!Utils.invalidId(exercicioId)) {
 			
			Join<Object, Object> joinExercicio = m.join(EXERCICIO,JoinType.LEFT);
						
			predicate.add(cb.equal(joinExercicio.get(ID) ,exercicioId));
		}

		
		query.where(  predicate.toArray(new Predicate[predicate.size()]));
  
		query.orderBy(cb.asc(m.get(DENOMINACAO)));
		
		return entityManager.createQuery(query).getResultList();
	}
	
	
	
	public List<Acao> buscar(String codigo, String denominacao,Long unidadeOrcamentariaId,Long programaId,Long exercicioId){
		 
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Acao> query = cb.createQuery(Acao.class);
		Root<Acao> m = query.from(Acao.class);
		
		query.select(m);
		
		List<Predicate> predicate = new ArrayList<>();
		
		if(!Utils.emptyParam(codigo)) {
			
			Expression<String> upperCodigo = cb.upper(m.get(CODIGO));
			
			predicate.add(cb.like(upperCodigo,"%"+codigo.toUpperCase()+"%"));
		}
		
		if(!Utils.emptyParam(denominacao)) {
			
			Expression<String> upperDenominacao = cb.upper(m.get(DENOMINACAO));
			
			predicate.add(cb.like(upperDenominacao,"%"+denominacao.toUpperCase()+"%" ));		
		}
		
		if(!Utils.invalidId(unidadeOrcamentariaId)) {
 			
			Join<Object, Object> joinUO = m.join(UNIDADE_ORCAMENTARIA,JoinType.LEFT);
			
			Expression<String> uoId = joinUO.get(ID);
			
			predicate.add(cb.equal(uoId,unidadeOrcamentariaId));
		}
		
		if(!Utils.invalidId(programaId)) {
 			
			Join<Object, Object> joinUO = m.join(PROGRAMA,JoinType.LEFT);
			
			Expression<String> uoId = joinUO.get(ID);
			
			predicate.add(cb.equal(uoId,programaId));
		}
		
		if(!Utils.invalidId(exercicioId)) {
 			
			Join<Object, Object> joinExercicio = m.join(EXERCICIO,JoinType.LEFT);
						
			predicate.add(cb.equal(joinExercicio.get(ID) ,exercicioId));
		}

		
		query.where(  predicate.toArray(new Predicate[predicate.size()]));
  
		query.orderBy(cb.asc(m.get(DENOMINACAO)));
		
		return entityManager.createQuery(query).getResultList();
	}

	public List<Acao> buscarByExercicio(Long exercicioId){
		 
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Acao> query = cb.createQuery(Acao.class);
		Root<Acao> m = query.from(Acao.class);
		
		query.select(m);
 
 			
		if (!Utils.invalidId((exercicioId))) {

			Join<Object, Object> joinExercicio = m.join(EXERCICIO,JoinType.INNER);
			joinExercicio.on(cb.equal(joinExercicio.get(ID),exercicioId) );	
		
		}
		  
		query.orderBy(cb.asc(m.get(DENOMINACAO)));
		
		return entityManager.createQuery(query).getResultList();
	}
	
	public List<Acao> buscarByUnidadeOrcamentaria(Long unidadeOrcamentariaId){
		 
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Acao> query = cb.createQuery(Acao.class);
		Root<Acao> m = query.from(Acao.class);
		
		query.select(m);
 
 			
		if (!Utils.invalidId((unidadeOrcamentariaId))) {

			Join<Object, Object> joinExercicio = m.join(UNIDADE_ORCAMENTARIA,JoinType.INNER);
			joinExercicio.on(cb.equal(joinExercicio.get(ID),unidadeOrcamentariaId) );	
		
		}
		  
		query.orderBy(cb.asc(m.get(DENOMINACAO)));
		
		return entityManager.createQuery(query).getResultList();
	}
	
}
