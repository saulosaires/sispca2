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
	private static final  String FUNCAO="funcao";
	private static final  String SUBFUNCAO="subfuncao";
	
	private static final  String ANO= "ano";
	private static final  String CODIGO="codigo";
	private static final  String DESCRICAO="descricao";
	private static final  String DENOMINACAO="denominacao";
	private static final  String FINALIDADE="finalidade";
	private static final  String ID="id";

	private static final  String TIPO_HORIZONTE_TEMPORAL ="tipoHorizonteTemporal";
	private static final  String TIPO_FORMA_IMPLEMENTACAO ="tipoFormaImplementacao";
	private static final  String TIPO_ACAO= "tipoAcao";
	private static final  String TIPO_ORCAMENTO= "tipoOrcamento";
	private static final  String TIPO_CALCULO_META="tipoCalculoMeta";
	private static final  String UNIDADE_MEDIDA="unidadeMedida";
	private static final  String PRODUTO="produto";
	
	public AcaoDAO() {
		setClazz(Acao.class);
	}

	
	
	
	public List<Acao> relatorioPlanoTrabalho(List<Long> listOrgaoId,List<Long> unidadeOrcamentaria,Long programaId,Long exercicioId,String orderBy){
		 
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Acao> query = cb.createQuery(Acao.class);
		Root<Acao> root = query.from(Acao.class);
		
		Join<Object, Object> joinPrograma 			 = root.join(PROGRAMA, 			   	   JoinType.LEFT);
		Join<Object, Object> joinFuncao 			 = root.join(FUNCAO, 			   	   JoinType.LEFT);
		Join<Object, Object> joinSubFuncao 			 = root.join(SUBFUNCAO, 			   JoinType.LEFT);
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
			 	 			
			 	 			joinFuncao.get(CODIGO),
			 	 			joinFuncao.get(DESCRICAO),
			 	 			
			 	 			joinSubFuncao.get(CODIGO),
			 	 			joinSubFuncao.get(DESCRICAO),
			 	 			
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
		
		if(!Utils.emptyList(listOrgaoId)) {
			predicate.add(cb.isTrue(joinOrgao.get(ID).in(listOrgaoId)));
		}
 
		if(unidadeOrcamentaria!=null && !unidadeOrcamentaria.isEmpty()) {

			predicate.add(cb.isTrue(joinUnidadeOrcamentaria.get(ID).in(unidadeOrcamentaria)) );
			 
		}

		
		if(!Utils.invalidId(programaId)) {
			predicate.add(cb.equal(joinPrograma.get(ID),programaId));
		}

		if(!Utils.invalidId(exercicioId)) {
			predicate.add(cb.equal(joinAcaoExercicio.get(ID),exercicioId));
			predicate.add(cb.equal(joinProgExercicio.get(ID),exercicioId));
		}

		query.where( predicate.toArray(new Predicate[predicate.size()]));
 
		if("A".equals(orderBy)) {
			
			query.orderBy(
					       cb.asc(joinOrgao.get(CODIGO)),
					       cb.asc(joinUnidadeOrcamentaria.get(CODIGO)),
					       cb.asc(joinPrograma.get(CODIGO)),
					       cb.asc(joinFuncao.get(CODIGO)),
					       cb.asc(joinSubFuncao.get(CODIGO)),
					       cb.asc(root.get(CODIGO))
					     );
			
		}else {
			
			query.orderBy(
				          cb.asc(joinPrograma.get(CODIGO)),
				          cb.asc(joinUnidadeOrcamentaria.get(CODIGO)),
				          cb.asc(joinOrgao.get(CODIGO)),
				          cb.asc(joinFuncao.get(CODIGO)),
				          cb.asc(joinSubFuncao.get(CODIGO)),
				          cb.asc(root.get(CODIGO))
				     );

		}

		return entityManager.createQuery(query).getResultList();
		
	}	
	
	public List<Acao> relatorioFinalidade(List<Long> listOrgaoId,List<Long> unidadeOrcamentaria,Long programaId,Long exercicioId){
		 
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
		
		if(listOrgaoId!=null && !listOrgaoId.isEmpty()) {
		 
			predicate.add(cb.isTrue(joinOrgao.get(ID).in(listOrgaoId)) );
		}

		if(unidadeOrcamentaria!=null && !unidadeOrcamentaria.isEmpty()) {

			predicate.add(cb.isTrue(joinUnidadeOrcamentaria.get(ID).in(unidadeOrcamentaria)) );
			 
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
		
	//ESSE METODO E USADO APENAS NO SIAFEM, PARA BUSCAR PELO CODIGO
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

		predicate.add(cb.equal(m.get(ATIVO),true));
		
		query.where(  predicate.toArray(new Predicate[predicate.size()]));
  
		query.orderBy(cb.asc(m.get(DENOMINACAO)));
		
		return entityManager.createQuery(query).getResultList();
	}
		
	public List<Acao> buscar(String codigo, String denominacao,List<Long> unidadeOrcamentaria,Long programaId,Long exercicioId){
		 
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
		
		if(!Utils.emptyList(unidadeOrcamentaria)) {
 			
			Join<Object, Object> joinUO = m.join(UNIDADE_ORCAMENTARIA,JoinType.INNER);
 
			joinUO.on(cb.isTrue(joinUO.get(ID).in(unidadeOrcamentaria)) );
			 
		}
		
		if(!Utils.invalidId(programaId)) {
 			
			Join<Object, Object> joinPrograma = m.join(PROGRAMA,JoinType.INNER);
			
	 		
			joinPrograma.on(cb.equal(joinPrograma.get(ID),programaId) );
		 
		}
		
		if(!Utils.invalidId(exercicioId)) {
 			
			Join<Object, Object> joinExercicio = m.join(EXERCICIO,JoinType.INNER);
			
			joinExercicio.on(cb.equal(joinExercicio.get(ID),exercicioId) );
			 
		}

		predicate.add(cb.equal(m.get(ATIVO),true));
		
		query.where(  predicate.toArray(new Predicate[predicate.size()]));
  
		query.orderBy(cb.asc(m.get(DENOMINACAO)));
		
		return entityManager.createQuery(query).getResultList();
	}

	public List<Acao> buscar(String codigo, String denominacao,List<Long> orgaoId,List<Long> unidadeOrcamentaria,Long programaId,Long exercicioId){
		 
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Acao> query = cb.createQuery(Acao.class);
		Root<Acao> m = query.from(Acao.class);
		
		Join<Object, Object> joinUO = m.join(UNIDADE_ORCAMENTARIA,JoinType.INNER);
		
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
		
		if(!Utils.emptyList(unidadeOrcamentaria)) {
 			
			 
 
			joinUO.on(cb.isTrue(joinUO.get(ID).in(unidadeOrcamentaria)) );
			 
		}
		
		if(!Utils.emptyList(orgaoId)) {

			Join<Object, Object> joinOrgao = joinUO.join(ORGAO, JoinType.LEFT);
 
			joinOrgao.on(cb.isTrue(joinOrgao.get(ID).in(orgaoId)) );
 
			
		}		
		
		
		if(!Utils.invalidId(programaId)) {
 			
			Join<Object, Object> joinPrograma = m.join(PROGRAMA,JoinType.INNER);
			
	 		
			joinPrograma.on(cb.equal(joinPrograma.get(ID),programaId) );
		 
		}
		
		if(!Utils.invalidId(exercicioId)) {
 			
			Join<Object, Object> joinExercicio = m.join(EXERCICIO,JoinType.INNER);
			
			joinExercicio.on(cb.equal(joinExercicio.get(ID),exercicioId) );
			 
		}
		
		predicate.add(cb.equal(m.get(ATIVO),true));
		
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
	
 	
	public List<Acao> exportarBI(Long exercicioId){
		 
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Acao> query = cb.createQuery(Acao.class);
		Root<Acao> root = query.from(Acao.class);
		
		Join<Object, Object> joinExercicio 				= root.join(EXERCICIO,JoinType.INNER);
		Join<Object, Object> joinTipoFormaImplementacao = root.join(TIPO_FORMA_IMPLEMENTACAO,JoinType.INNER);
		Join<Object, Object> joinHorizonteTemporal  	= root.join(TIPO_HORIZONTE_TEMPORAL,JoinType.INNER);
		Join<Object, Object> joinTipoAcao 				= root.join(TIPO_ACAO,JoinType.INNER);
		Join<Object, Object> joinTipoOrcamento	    	= root.join(TIPO_ORCAMENTO,JoinType.INNER);
 
		
		query.multiselect(
						 joinExercicio.get(ANO),
						 root.get(CODIGO),
						 joinTipoOrcamento.get(DESCRICAO),
						 joinTipoAcao.get(DESCRICAO),
						 joinHorizonteTemporal.get(DESCRICAO),
						 joinTipoFormaImplementacao.get(DESCRICAO),
						 root.get(FINALIDADE)
				    );
 
 			
		query.where(
					cb.equal(root.get(ATIVO), true),
					cb.equal(joinExercicio.get(ID),exercicioId)
				   );

		query.groupBy(
					  joinExercicio.get(ANO),
					  root.get(CODIGO),
					  joinTipoOrcamento.get(DESCRICAO),
					  joinTipoAcao.get(DESCRICAO),
					  joinHorizonteTemporal.get(DESCRICAO),
					  joinTipoFormaImplementacao.get(DESCRICAO),
					  root.get(FINALIDADE)
				      );  
		
		query.orderBy(cb.asc(root.get(CODIGO)));
		
		return entityManager.createQuery(query).getResultList();
	}
	
	
	public List<Acao> exportarBIMetas(Long exercicioId){
		 
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Acao> query = cb.createQuery(Acao.class);
		Root<Acao> root = query.from(Acao.class);
		
		Join<Object, Object> joinPrograma				= root.join(PROGRAMA,JoinType.INNER);
		Join<Object, Object> joinUnidadeOrcamentaria  	= root.join(UNIDADE_ORCAMENTARIA,JoinType.INNER);
		Join<Object, Object> joinTipoCalculoMeta		= root.join(TIPO_CALCULO_META,JoinType.INNER);
		Join<Object, Object> joinUnidadeMedida	    	= root.join(UNIDADE_MEDIDA,JoinType.INNER);
		Join<Object, Object> joinExercicio 				= root.join(EXERCICIO,JoinType.INNER);


 		
		query.multiselect(
						 joinExercicio.get(ANO),
						 joinPrograma.get(CODIGO),
						 root.get(ID),
						 root.get(CODIGO),
						 joinUnidadeOrcamentaria.get(CODIGO),
						 root.get(PRODUTO),
						 joinTipoCalculoMeta.get(DESCRICAO),
						 joinUnidadeMedida.get(CODIGO),
						 joinUnidadeMedida.get(DESCRICAO)
				    );
 
 			
		query.where(
					cb.equal(root.get(ATIVO), true),
					cb.equal(joinExercicio.get(ID),exercicioId)
				   );

		query.groupBy(
						 joinExercicio.get(ANO),
						 joinPrograma.get(CODIGO),
						 root.get(ID),
						 root.get(CODIGO),
						 joinUnidadeOrcamentaria.get(CODIGO),
						 root.get(PRODUTO),
						 joinTipoCalculoMeta.get(DESCRICAO),
						 joinUnidadeMedida.get(CODIGO),
						 joinUnidadeMedida.get(DESCRICAO)
				      );  
		
		query.orderBy(cb.asc(joinPrograma.get(CODIGO)));
		
		return entityManager.createQuery(query).getResultList();
	}	
	
	
}
