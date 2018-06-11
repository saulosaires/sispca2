package monitoramento.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

import arquitetura.dao.AbstractDAO;
import arquitetura.utils.MathUtils;
import arquitetura.utils.Utils;
import monitoramento.model.Execucao;

public class ExecucaoDAO extends AbstractDAO<Execucao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final  String ACAO="acao"; 
	private static final  String UNIDADE_ORCAMENTARIA="unidadeOrcamentaria"; 
	private static final  String UNIDADE_GESTORA="unidadeGestoras";
	private static final  String VALOR="valor";
	private static final  String MES="mes";
	private static final  String EXERCICIO="exercicio";
	private static final  String ID="id";
	private static final  String OBSERVACAO="observacao";
	private static final  String NUMERO_MES = "numeroMes";
	private static final  String REGIAO_MUNICIPIO= "regiaoMunicipio";
	private static final  String QUANTIDADE="quantidade";
	private static final  String UNIDADE_MEDIDA="unidadeMedida"; 
	private static final  String ORGAO= "orgao";
	private static final  String PROGRAMA="programa"; 
	private static final  String MUNICIPIO= "municipio";
	private static final  String REGIAO ="regiao";
	private static final  String CODIGO ="codigo";
	private static final  String DESCRICAO ="descricao";
	private static final  String DENOMINACAO = "denominacao";
	private static final  String PRODUTO="produto";
	private static final  String TIPO_REGIAO= "tipoRegiao";
	
	public ExecucaoDAO() {
		setClazz(Execucao.class);

	}

	public List<Execucao> relatorioMonitoramento(Long exercicio,Long orgao, Long unidadeOrcamentaria,Long programa,Long acao,Long tipoRegiao,Long regiao,Long regiaoMunicipio) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Execucao> query = cb.createQuery(Execucao.class);
		Root<Execucao> root = query.from(Execucao.class);

		Join<Object, Object> joinExercicio 			 = root.join(EXERCICIO, 			   JoinType.LEFT);
		Join<Object, Object> joinAcao 				 = root.join(ACAO, 					   JoinType.LEFT);
		Join<Object, Object> joinRegiaoMunicipio     = root.join(REGIAO_MUNICIPIO, 		   JoinType.LEFT);
		Join<Object, Object> joinMes 				 = root.join(MES, 					   JoinType.LEFT);
		Join<Object, Object> joinRegiao 			 = joinRegiaoMunicipio.join(REGIAO,    JoinType.LEFT);
		Join<Object, Object> joinMunicipio 			 = joinRegiaoMunicipio.join(MUNICIPIO, JoinType.LEFT);
		Join<Object, Object> joinTipoRegiao 		 = joinRegiao.join(TIPO_REGIAO,    	   JoinType.LEFT);
		Join<Object, Object> joinUnidadeOrcamentaria = joinAcao.join(UNIDADE_ORCAMENTARIA, JoinType.LEFT);
		Join<Object, Object> joinUnidadeMedida 		 = joinAcao.join(UNIDADE_MEDIDA, 	   JoinType.LEFT);
		Join<Object, Object> joinPrograma 			 = joinAcao.join(PROGRAMA, 			   JoinType.LEFT);
		Join<Object, Object> joinOrgao 			     = joinUnidadeOrcamentaria.join(ORGAO, JoinType.LEFT);
		
		
		query.multiselect(
							joinRegiaoMunicipio.get(ID),
							joinRegiao.get(DESCRICAO),
							joinMunicipio.get(DESCRICAO),
							joinMes.get(ID),
					
							root.get(QUANTIDADE),
							root.get(VALOR),
							root.get(OBSERVACAO),
			        
							joinOrgao.get(CODIGO),
							joinOrgao.get(DESCRICAO),
					
							joinUnidadeOrcamentaria.get(CODIGO),
							joinUnidadeOrcamentaria.get(DESCRICAO),
					
							joinPrograma.get(CODIGO),
							joinPrograma.get(DENOMINACAO),
					 
							joinAcao.get(CODIGO),
							joinAcao.get(DENOMINACAO),
							joinAcao.get(PRODUTO),
					
							joinUnidadeMedida.get(DESCRICAO)
				
				         );
		
		List<Predicate> predicate = new ArrayList<>();
 		
		if (!Utils.invalidId((exercicio))) {
			predicate.add(cb.equal(joinExercicio.get(ID),exercicio));
		}

		if (!Utils.invalidId((orgao))) {
			predicate.add(cb.equal(joinOrgao.get(ID),orgao));
		}

		
		if (!Utils.invalidId((unidadeOrcamentaria))) {
			predicate.add(cb.equal(joinUnidadeOrcamentaria.get(ID),unidadeOrcamentaria));
		}

		if (!Utils.invalidId((programa))) {
			predicate.add(cb.equal(joinPrograma.get(ID),programa));
		}
		
		if (!Utils.invalidId((acao))) {
			predicate.add(cb.equal(joinAcao.get(ID),acao));
		}
		
		if (!Utils.invalidId((tipoRegiao))) {
			predicate.add(cb.equal(joinTipoRegiao.get(ID),tipoRegiao));
		}
		
		if (!Utils.invalidId((regiao))) {
			predicate.add(cb.equal(joinRegiao.get(ID),regiao));
		}
		
		if (!Utils.invalidId((regiaoMunicipio))) {
			predicate.add(cb.equal(joinRegiaoMunicipio.get(ID),regiaoMunicipio));
		}
		

		query.where(predicate.toArray(new Predicate[predicate.size()]));

		query.orderBy(
					 cb.asc( joinOrgao.get(DESCRICAO)),
					 cb.asc( joinUnidadeOrcamentaria.get(DESCRICAO)),
					 cb.asc( joinPrograma.get(DENOMINACAO)),
					 cb.asc( joinAcao.get(DENOMINACAO)) 
					);		
		

		return entityManager.createQuery(query).getResultList();
	}
	
	public List<Execucao> findByAcaoAndExercicio(Long acaoId,Long exercicioId) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Execucao> query = cb.createQuery(Execucao.class);
		Root<Execucao> m = query.from(Execucao.class);

		query.select(m);

		if (Utils.invalidId((acaoId))      || Utils.invalidId((exercicioId))  ) {
		   return new ArrayList<>();
		}
		
		
		List<Predicate> predicate = new ArrayList<>();

		Join<Object, Object> joinAcao = m.join(ACAO, JoinType.INNER);
		joinAcao.on(cb.equal(joinAcao.get(ID), acaoId));
		 	
		
		Join<Object, Object> joinExercicio = m.join(EXERCICIO, JoinType.INNER);
		joinExercicio.on(cb.equal(joinExercicio.get(ID), exercicioId));

		query.where(predicate.toArray(new Predicate[predicate.size()]));
 
		
		return entityManager.createQuery(query).getResultList();

	}

	public Optional<Execucao> findByAcaoAndRegiaoAndExercicioAndMes(Long acaoId,Long regiaoMunicipioId,Long exercicioId,Long mesId) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Execucao> query = cb.createQuery(Execucao.class);
		Root<Execucao> m = query.from(Execucao.class);

		query.select(m);

		if (Utils.invalidId((acaoId))      || Utils.invalidId((regiaoMunicipioId))  || 
			Utils.invalidId((exercicioId)) || Utils.invalidId((mesId)) ) {
		   return Optional.empty();
		}
		
		
		List<Predicate> predicate = new ArrayList<>();

		 
		Join<Object, Object> joinRegiao = m.join(REGIAO_MUNICIPIO, JoinType.INNER);
		joinRegiao.on(cb.equal(joinRegiao.get(ID), regiaoMunicipioId));

		Join<Object, Object> joinAcao = m.join(ACAO, JoinType.INNER);
		joinAcao.on(cb.equal(joinAcao.get(ID), acaoId));
		 	
		Join<Object, Object> joinMes = m.join(MES, JoinType.INNER);
		joinMes.on(cb.equal(joinMes.get(ID), mesId));

		Join<Object, Object> joinExercicio = m.join(EXERCICIO, JoinType.INNER);
		joinExercicio.on(cb.equal(joinExercicio.get(ID), exercicioId));

		query.where(predicate.toArray(new Predicate[predicate.size()]));
 
		
		return entityManager.createQuery(query).setMaxResults(1).getResultList().stream().findFirst();

	}

	public BigDecimal findTotalValorExecutadoByAcao(Long unidadeGestoraId, Long unidadeOrcamentariaId, Long acaoId,Long exercicioVigenteId, Long mesId) {
		
		if(Utils.invalidId(mesId) || exercicioVigenteId==null)return MathUtils.getZeroBigDecimal();
		
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<BigDecimal> criteria = builder.createQuery(BigDecimal.class);
		
		Root<Execucao> root = criteria.from(Execucao.class);
		
		Join<Object, Object> joinAcao = root.join(ACAO,JoinType.INNER);		
		
		Join<Object, Object> joinMes= root.join(MES,JoinType.INNER);
							 joinMes.on( builder.equal(joinMes.get(ID),mesId));
		
		Join<Object, Object> joinExercicio= root.join(EXERCICIO,JoinType.INNER);
							 joinExercicio.on( builder.equal(joinExercicio.get(ID),exercicioVigenteId));
		
		Join<Object, Object> joinUnidadeOrcamentaria = joinAcao.join(UNIDADE_ORCAMENTARIA,JoinType.INNER);
		
		SetJoin<Object, Object> joinUnidadeGestora = joinUnidadeOrcamentaria.joinSet(UNIDADE_GESTORA,JoinType.LEFT);
 
		
		if(!Utils.invalidId(unidadeGestoraId)){
			joinUnidadeGestora.on( builder.equal(joinUnidadeGestora.get(ID),unidadeGestoraId));	
		}

		if(!Utils.invalidId(unidadeOrcamentariaId) && Utils.invalidId(acaoId)){
			joinUnidadeOrcamentaria.on( builder.equal(joinUnidadeOrcamentaria.get(ID),unidadeOrcamentariaId));	
		}
		
		
		if(!Utils.invalidId(acaoId)){
			joinAcao.on( builder.equal(joinAcao.get(ID),acaoId));	
		}
		
 
		Path<BigDecimal> valor = root.get(VALOR);
		Expression<BigDecimal> soma = builder.sum(valor);
		criteria.select(soma);
		 
	 
		criteria.groupBy(
						joinExercicio.get(ID),
						joinMes.get(NUMERO_MES)
					    );
		
		BigDecimal value = entityManager.createQuery(criteria).getResultList().stream().findFirst().orElse(null);
			
			
		 return value!=null?value:MathUtils.getZeroBigDecimal();		
		
		 
	}
	
	public BigDecimal calculaExecutadoMensalByMesAndExercicioAndAcao(Long mes, Long exercicio,Long acao){


		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<BigDecimal> criteria = builder.createQuery(BigDecimal.class);
		
		Root<Execucao> root = criteria.from(Execucao.class);
		
		Join<Object, Object> joinMes= root.join(MES,JoinType.INNER);
		Join<Object, Object> joinExercicio= root.join(EXERCICIO,JoinType.INNER);
		Join<Object, Object> joinAcao = root.join(ACAO,JoinType.INNER);
		
		Path<BigDecimal> quantidade = root.get(QUANTIDADE);
		 
		criteria.select(builder.sum(quantidade));
		
		criteria.where(
					   builder.equal(joinMes.get(ID),mes ),
					   builder.equal(joinExercicio.get(ID),exercicio ),
					   builder.equal(joinAcao.get(ID),acao )
					   );
  
 
	    return  entityManager.createQuery(criteria).getSingleResult();
		
		
	}
	
	
}
