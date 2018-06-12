package quantitativo.dao;

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
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

import arquitetura.dao.AbstractDAO;
import arquitetura.utils.MathUtils;
import arquitetura.utils.Utils;
import quantitativo.model.FisicoFinanceiroMensal;

public class FisicoFinanceiroMensalDAO extends AbstractDAO<FisicoFinanceiroMensal> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final  String ACAO="acao"; 
	private static final  String UNIDADE_ORCAMENTARIA="unidadeOrcamentaria"; 
	private static final  String ORGAO="orgao"; 
	private static final  String UNIDADE_GESTORA="unidadeGestoras";
	private static final  String REGIAO_MUNICIPIO="regiaoMunicipio";
	private static final  String REGIAO="regiao";
	private static final  String TIPO_REGIAO="tipoRegiao";
	private static final  String ATIVO="ativo";
	private static final  String CODIGO="codigo";
	private static final  String VALOR="valor";
	private static final  String MES="mes";
	private static final  String EXERCICIO="exercicio";
	private static final  String ID="id";
	private static final  String DESCRICAO="descricao";
	private static final  String NUMERO_MES = "numeroMes";
	private static final  String QUANTIDADE="quantidade";
	private static final  String PROGRAMA="programa";
	private static final  String DENOMINACAO="denominacao";
	
	public FisicoFinanceiroMensalDAO() {
		setClazz(FisicoFinanceiroMensal.class);

	}
  
	public List<FisicoFinanceiroMensal> relatorioPlanejamentoMensal(List<Long> listOrgaoId, 
																	Long unidadeOrcamentariaId,
																	Long programaId, 
																	Long acaoId, 
																	Long tipoRegiaoId, 
																	Long regiaoId,
																	Long regiaoMunicipioId,
																	Long exercicioId){
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<FisicoFinanceiroMensal> query = cb.createQuery(FisicoFinanceiroMensal.class);
		Root<FisicoFinanceiroMensal> m = query.from(FisicoFinanceiroMensal.class);

		query.select(m);
		
		Join<Object, Object> joinExercicio			 = m.join(EXERCICIO, JoinType.INNER);	
		Join<Object, Object> joinAcao 				 = m.join(ACAO, JoinType.INNER);
		Join<Object, Object> joinUnidadeOrcamentaria = joinAcao.join(UNIDADE_ORCAMENTARIA, JoinType.INNER);
		Join<Object, Object> joinOrgao 				 = joinUnidadeOrcamentaria.join(ORGAO, JoinType.INNER);
		Join<Object, Object> joinPrograma 			 = joinAcao.join(PROGRAMA, JoinType.INNER);
		
		Join<Object, Object> joinRegiaoMunicipio 	 = m.join(REGIAO_MUNICIPIO, JoinType.INNER);	
		Join<Object, Object> joinRegiao 			 = joinRegiaoMunicipio.join(REGIAO, JoinType.INNER);
		Join<Object, Object> joinTipoRegiao 		 = joinRegiao.join(TIPO_REGIAO, JoinType.INNER);
		
		
		if(!Utils.invalidId(exercicioId)) {
			joinExercicio.on(cb.equal(joinExercicio.get(ID), exercicioId));
		}	
		
		if(!Utils.emptyList(listOrgaoId)) {
			joinOrgao.on(cb.isTrue(joinOrgao.get(ID).in(listOrgaoId)));		
		}
		
		if(!Utils.invalidId(unidadeOrcamentariaId)) {
			joinUnidadeOrcamentaria.on(cb.equal(joinUnidadeOrcamentaria.get(ID), unidadeOrcamentariaId));		
		}
		
		if(!Utils.invalidId(programaId)) {
			joinPrograma.on(cb.equal(joinPrograma.get(ID), programaId));		
		}
		
		if(!Utils.invalidId(acaoId)) {
			joinAcao.on(cb.equal(joinAcao.get(ID), acaoId));		
		}
		
		if(!Utils.invalidId(tipoRegiaoId)) {
			joinTipoRegiao.on(cb.equal(joinTipoRegiao.get(ID), tipoRegiaoId));		
		}
	 
		if(!Utils.invalidId(regiaoId)) {
			joinRegiao.on(cb.equal(joinRegiao.get(ID), regiaoId));		
		}
		
		if(!Utils.invalidId(regiaoMunicipioId)) {
			joinRegiaoMunicipio.on(cb.equal(joinRegiaoMunicipio.get(ID), regiaoMunicipioId));		
		}

		query.where(
			    	cb.equal(m.get(ATIVO),true ),
			    	cb.or(cb.notEqual(m.get(VALOR),0 ),cb.notEqual(m.get(QUANTIDADE),0 ))
			    );
		
		  query.orderBy(
					cb.asc( joinOrgao.get(DESCRICAO)),
					cb.asc( joinUnidadeOrcamentaria.get(DESCRICAO)),
					cb.asc( joinPrograma.get(DENOMINACAO)),
					cb.asc( joinAcao.get(DENOMINACAO)) 
					
				);
		 
		
		return entityManager.createQuery(query).getResultList();	
				
	}

	public Optional<FisicoFinanceiroMensal> findByRegiaoMunicipioAndExercicioAndAcaoAndMes(Long regiaoMunicipioId,Long exercicioId,Long acaoId,Long mesId){
		
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<FisicoFinanceiroMensal> query = cb.createQuery(FisicoFinanceiroMensal.class);
		Root<FisicoFinanceiroMensal> m = query.from(FisicoFinanceiroMensal.class);

		query.select(m);

		if (Utils.invalidId((regiaoMunicipioId)) || Utils.invalidId((exercicioId)) || 
			Utils.invalidId((acaoId)) 			 || Utils.invalidId((mesId)) ) {

			return Optional.ofNullable(null);
		}

		Join<Object, Object> joinRegiao = m.join(REGIAO_MUNICIPIO, JoinType.INNER);
		joinRegiao.on(cb.equal(joinRegiao.get(ID), regiaoMunicipioId));

				
		Join<Object, Object> joinExercicio = m.join(EXERCICIO, JoinType.INNER);
		joinExercicio.on(cb.equal(joinExercicio.get(ID), exercicioId));
		
			
		Join<Object, Object> joinAcao = m.join(ACAO, JoinType.INNER);
		joinAcao.on(cb.equal(joinAcao.get(ID), acaoId));
		
			
		Join<Object, Object> joinMes= m.join(MES, JoinType.INNER);
		joinMes.on(cb.equal(joinMes.get(ID), mesId));

		
		
		return entityManager.createQuery(query).setMaxResults(1).getResultList().stream().findFirst();	
		
	}

	public List<FisicoFinanceiroMensal> findByAcao(Long acaoId) {


		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<FisicoFinanceiroMensal> query = cb.createQuery(FisicoFinanceiroMensal.class);
		Root<FisicoFinanceiroMensal> m = query.from(FisicoFinanceiroMensal.class);

		query.select(m);

		if (Utils.invalidId((acaoId))  ) {

			return new ArrayList<>();
		}
 	
		Join<Object, Object> joinAcao = m.join("acao", JoinType.INNER);

		joinAcao.on(cb.equal(joinAcao.get("id"), acaoId));
 
		
		return entityManager.createQuery(query).getResultList();
		
	}
 
	public BigDecimal findTotalValorFinanceiroPlanejadoByAcao(Long unidadeGestoraId, Long unidadeOrcamentariaId, Long acaoId,Long exercicioVigenteId, Long mesId) {
		
		if(Utils.invalidId(mesId) || exercicioVigenteId==null)return MathUtils.getZeroBigDecimal();
		
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<BigDecimal> criteria = builder.createQuery(BigDecimal.class);
		
		Root<FisicoFinanceiroMensal> root = criteria.from(FisicoFinanceiroMensal.class);
		
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
	
	
	public BigDecimal calculaPlanejamentoMensalByMesAndExercicioAndAcao(Long mes, Long exercicio,Long acao){


		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<BigDecimal> criteria = builder.createQuery(BigDecimal.class);
		
		Root<FisicoFinanceiroMensal> root = criteria.from(FisicoFinanceiroMensal.class);
		
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
