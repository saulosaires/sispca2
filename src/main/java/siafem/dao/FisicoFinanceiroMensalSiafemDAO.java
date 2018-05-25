package siafem.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import administrativo.model.Exercicio;
import arquitetura.dao.AbstractDAO;
import arquitetura.enums.TipoCalculoMeta;
import arquitetura.exception.JpaException;
import arquitetura.utils.MathUtils;
import arquitetura.utils.Utils;
import monitoramento.model.Execucao;
import qualitativo.model.Programa;
import quantitativo.model.FisicoFinanceiroMensal;
import siafem.model.FisicoFinanceiroMensalSiafem;

public class FisicoFinanceiroMensalSiafemDAO extends AbstractDAO<FisicoFinanceiroMensalSiafem> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final  String ID="id"; 
	private static final  String ANO="ano";
	private static final  String QUANTIDADE="quantidade";
	private static final  String DENOMINACAO="denominacao";
	private static final  String ACAO="acao"; 
	private static final  String DESCRICAO="descricao"; 
	private static final  String PRODUTO="produto"; 
	private static final  String PROGRAMA="programa"; 
	private static final  String UNIDADE_ORCAMENTARIA="unidadeOrcamentaria"; 
	private static final  String UNIDADE_MEDIDA="unidadeMedida"; 
	private static final  String UNIDADE_GESTORA="unidadeGestora";
	private static final  String TIPO_CALCULO_META="tipoCalculoMeta"; 
	private static final  String DOTACAO_INICIAL="dotacaoInicial"; 
	private static final  String DISPONIVEL="disponivel";
	private static final  String EMPENHADO="empenhado";
	private static final  String LIQUIDADO="liquidado";
	private static final  String PAGO="pago";
	private static final  String CODIGO="codigo";
	private static final  String EXERCICIO="exercicio";
	private static final  String MES="mes";
	
	public FisicoFinanceiroMensalSiafemDAO() {
		setClazz(FisicoFinanceiroMensalSiafem.class);

	}

	public void create(List<FisicoFinanceiroMensalSiafem> listSiafem) throws JpaException {

		EntityTransaction entityTransaction = entityManager.getTransaction();
		try {
			
			 entityTransaction.begin();

		 
			 for(FisicoFinanceiroMensalSiafem siafem: listSiafem) {
				 
				 
				 
				 entityManager.persist(siafem);
				 
			 }
			
			 entityTransaction.commit();
		 
		} catch (Exception e) {

			entityTransaction.rollback();

			throw new JpaException("Erro ao Salvar FisicoFinanceiroMensalSiafem em lote", e);
			 
		}

	
	}
	
	public int deleteByYear(Integer exercicio) {
	
		if(Utils.invalidYear(exercicio)) { 
			return -1;
		}else {
		
			entityManager.getTransaction().begin();
			
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaDelete<FisicoFinanceiroMensalSiafem> delete = builder.createCriteriaDelete(FisicoFinanceiroMensalSiafem.class);
			Root<FisicoFinanceiroMensalSiafem> root = delete.from(FisicoFinanceiroMensalSiafem.class);
			
			delete.where(builder.equal(root.get(ANO), exercicio));
			
			int i=  entityManager.createQuery(delete).executeUpdate();
 
			entityManager.getTransaction().commit();
		
			return i;
		} 
		
	}
	
	public List<FisicoFinanceiroMensalSiafem> analiseFisicoFinanceiro(Programa programa, Exercicio exercicio){
		
		if(programa==null  || Utils.invalidId(programa.getId()) || 
		   exercicio==null || Utils.invalidId(exercicio.getId()))return new ArrayList<>();
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<FisicoFinanceiroMensalSiafem> criteria = builder.createQuery(FisicoFinanceiroMensalSiafem.class);
		
		Root<FisicoFinanceiroMensalSiafem> root = criteria.from(FisicoFinanceiroMensalSiafem.class);
		
 		
		Join<Object, Object> joinAcao		 		 = root.join(ACAO, 				       JoinType.LEFT);		
		Join<Object, Object> joinUnidadeMedida 		 = joinAcao.join(UNIDADE_MEDIDA, 	   JoinType.LEFT);
		Join<Object, Object> joinPrograma 			 = joinAcao.join(PROGRAMA, 			   JoinType.LEFT);		
		Join<Object, Object> joinUnidadeOrcamentaria = joinAcao.join(UNIDADE_ORCAMENTARIA, JoinType.LEFT);
		Join<Object, Object> joinTipoCalculoMeta 	 = joinAcao.join(TIPO_CALCULO_META,    JoinType.LEFT);
		
		
 	 	criteria.multiselect(
 	 						  joinPrograma.get(CODIGO),
 	 						  joinUnidadeOrcamentaria.get(CODIGO),
 	 						  joinUnidadeOrcamentaria.get(DESCRICAO),
 	 						  joinAcao.get(CODIGO),
 	 						  joinAcao.get(ID),
 	 						  joinAcao.get(DENOMINACAO),
 	 						  joinAcao.get(PRODUTO),
 	 						  joinTipoCalculoMeta.get(ID),
 	 						  joinUnidadeMedida.get(DESCRICAO),
 	 						  
							  builder.sum(root.get(DOTACAO_INICIAL)),
							  builder.sum(root.get(DISPONIVEL)),
							  builder.sum(root.get(EMPENHADO)),
							  builder.sum(root.get(LIQUIDADO))
							 );


		criteria.where(						 
						builder.equal(root.get(PROGRAMA),programa.getCodigo()),
						builder.equal(root.get(ANO),	 exercicio.getAno()) 
				      );
		
		criteria.groupBy(
						  joinPrograma.get(CODIGO),
						  joinUnidadeOrcamentaria.get(CODIGO),
						  joinUnidadeOrcamentaria.get(DESCRICAO),
						  joinAcao.get(CODIGO),
						  joinAcao.get(ID),
						  joinAcao.get(DENOMINACAO),
						  joinAcao.get(PRODUTO),
						  joinTipoCalculoMeta.get(ID),
						  joinUnidadeMedida.get(DESCRICAO) 
						  );
 		
		criteria.orderBy(
						 builder.asc( joinPrograma.get(CODIGO)),
						 builder.asc( joinUnidadeOrcamentaria.get(CODIGO)),
						 builder.asc( joinAcao.get(CODIGO)) 
						);
		
		 return entityManager.createQuery(criteria).getResultList();
	}
 
	public List<FisicoFinanceiroMensalSiafem> analiseFisicoFinanceiro(String unidadeOrcamentaria,String programa, Integer exercicio){
		
		if(unidadeOrcamentaria==null || programa==null || Utils.invalidYear(exercicio))return new ArrayList<>();
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<FisicoFinanceiroMensalSiafem> criteria = builder.createQuery(FisicoFinanceiroMensalSiafem.class);
		
		Root<FisicoFinanceiroMensalSiafem> root = criteria.from(FisicoFinanceiroMensalSiafem.class);
		
 		
		Join<Object, Object> joinAcao		 		 = root.join(ACAO, 				       JoinType.LEFT);		
		Join<Object, Object> joinPrograma 			 = joinAcao.join(PROGRAMA, 			   JoinType.LEFT);		
		Join<Object, Object> joinUnidadeOrcamentaria = joinAcao.join(UNIDADE_ORCAMENTARIA, JoinType.LEFT);
		
		
 	 	criteria.multiselect(
 	 						  joinUnidadeOrcamentaria.get(CODIGO),
 	 						  joinUnidadeOrcamentaria.get(DESCRICAO),
 	 						  joinPrograma.get(CODIGO),
 	 						  joinPrograma.get(DENOMINACAO),
							  builder.sum(root.get(DOTACAO_INICIAL)),
							  builder.sum(root.get(DISPONIVEL)),
							  builder.sum(root.get(EMPENHADO)),
							  builder.sum(root.get(LIQUIDADO)),
							  builder.sum(root.get(PAGO))
							 );


		criteria.where(	
						builder.like(root.get(UNIDADE_ORCAMENTARIA),unidadeOrcamentaria),
						builder.like(root.get(PROGRAMA),programa),
						builder.equal(root.get(ANO),	 exercicio) 
				      );
		
		criteria.groupBy(
						  joinUnidadeOrcamentaria.get(CODIGO),
						  joinUnidadeOrcamentaria.get(DESCRICAO),
						  joinPrograma.get(CODIGO),
						  joinPrograma.get(DENOMINACAO)
						  );
 		
		criteria.orderBy(
						 builder.asc( joinUnidadeOrcamentaria.get(CODIGO)),
						 builder.asc( joinPrograma.get(CODIGO))
						);
		
		 return entityManager.createQuery(criteria).getResultList();
	}
	
	public List<FisicoFinanceiroMensalSiafem> analiseFisicoFinanceiroPorMes(String unidadeOrcamentaria,String programa, Integer exercicio){
		
		if(unidadeOrcamentaria==null || programa==null || Utils.invalidYear(exercicio))return new ArrayList<>();
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<FisicoFinanceiroMensalSiafem> criteria = builder.createQuery(FisicoFinanceiroMensalSiafem.class);
		
		Root<FisicoFinanceiroMensalSiafem> root = criteria.from(FisicoFinanceiroMensalSiafem.class);
		
 		
		Join<Object, Object> joinAcao		 		 = root.join(ACAO, 				       JoinType.LEFT);		
		Join<Object, Object> joinPrograma 			 = joinAcao.join(PROGRAMA, 			   JoinType.LEFT);		
		Join<Object, Object> joinUnidadeOrcamentaria = joinAcao.join(UNIDADE_ORCAMENTARIA, JoinType.LEFT);
		
		
 	 	criteria.multiselect(
 	 						  joinUnidadeOrcamentaria.get(CODIGO),
 	 						  joinUnidadeOrcamentaria.get(DESCRICAO),
 	 						  joinPrograma.get(CODIGO),
 	 						  joinPrograma.get(DENOMINACAO),
 	 						  root.get(MES),
							  builder.sum(root.get(DOTACAO_INICIAL)),
							  builder.sum(root.get(DISPONIVEL)),
							  builder.sum(root.get(EMPENHADO)),
							  builder.sum(root.get(LIQUIDADO)),
							  builder.sum(root.get(PAGO))
							 );


		criteria.where(	
						builder.like(root.get(UNIDADE_ORCAMENTARIA),unidadeOrcamentaria),
						builder.like(root.get(PROGRAMA),programa),
						builder.equal(root.get(ANO),	 exercicio) 
				      );
		
		criteria.groupBy(
						  joinUnidadeOrcamentaria.get(CODIGO),
						  joinUnidadeOrcamentaria.get(DESCRICAO),
						  joinPrograma.get(CODIGO),
						  joinPrograma.get(DENOMINACAO),
						  root.get(MES)
						  );
 		
		criteria.orderBy(
						 builder.asc( joinUnidadeOrcamentaria.get(CODIGO)),
						 builder.asc( joinPrograma.get(CODIGO)),
						 builder.asc( root.get(MES))
						);
		
		 return entityManager.createQuery(criteria).getResultList();
	}	
	
	public BigDecimal calculaLiquidadoByUnidadeAndProgAndMesAndAno(String unidadeOrcamentaria,String programa, Integer mes, Integer ano){
 
		
		if(Utils.emptyParam(unidadeOrcamentaria) || Utils.emptyParam(programa) || mes==null || ano==null)return new BigDecimal(0);
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<BigDecimal> criteria = builder.createQuery(BigDecimal.class);
		
		Root<FisicoFinanceiroMensalSiafem> root = criteria.from(FisicoFinanceiroMensalSiafem.class);
		
		Path<BigDecimal> liquidado = root.get(LIQUIDADO);
		Expression<BigDecimal> soma = builder.sum(liquidado);
		criteria.select(soma);
		
		criteria.where(
						 builder.equal(root.get(UNIDADE_ORCAMENTARIA),unidadeOrcamentaria ),
						 builder.equal(root.get(PROGRAMA),programa ),
						 builder.equal(root.get(MES),mes ),
						 builder.equal(root.get(ANO),ano )
			    	  );
		
		
		
		TypedQuery<BigDecimal> query = entityManager.createQuery(criteria);
 
		BigDecimal value = query.getSingleResult();
		
		
		return value!=null?value: MathUtils.getZeroBigDecimal();
		
		
	}
		
	public List<FisicoFinanceiroMensalSiafem> valorFisicoFinanceiro(String unidadeGestora, String unidadeOrcamentaria, Long acao, Integer ano){
		
		if(Utils.invalidYear(ano) )return new ArrayList<>();
		
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<FisicoFinanceiroMensalSiafem> criteria = builder.createQuery(FisicoFinanceiroMensalSiafem.class);
		
		Root<FisicoFinanceiroMensalSiafem> root = criteria.from(FisicoFinanceiroMensalSiafem.class);
		
 		
 	 	criteria.multiselect(
							  builder.sum(root.get(DOTACAO_INICIAL)),
							  builder.sum(root.get(DISPONIVEL)),
							  builder.sum(root.get(EMPENHADO)),
							  builder.sum(root.get(LIQUIDADO))
							 );

 		List<Predicate> predicate = new ArrayList<>();
 		
 		if(!Utils.emptyParam(unidadeGestora)) {
 			
 			predicate.add(builder.like(root.get(UNIDADE_GESTORA),unidadeGestora));
 			
 		}
 		
 		if(!Utils.emptyParam(unidadeOrcamentaria)) {
 			
 			predicate.add(builder.like(root.get(UNIDADE_ORCAMENTARIA),unidadeOrcamentaria));
 			
 		}
 		
 		if(!Utils.invalidId(acao)) {
 			
 			Join<Object, Object> joinAcao  = root.join(ACAO, JoinType.LEFT);	
 			
 			joinAcao.on(builder.equal(joinAcao.get(CODIGO), acao.toString()));
 			
 		}

 		predicate.add(builder.equal(root.get(ANO),ano));
 		
		criteria.where(predicate.toArray(new Predicate[predicate.size()]));
		
		
		if(!Utils.emptyParam(unidadeOrcamentaria) && !Utils.emptyParam(unidadeGestora)) {
			
			criteria.groupBy(
							root.get(UNIDADE_GESTORA),
							root.get(UNIDADE_ORCAMENTARIA)
						    );
 
		}
		
		 return entityManager.createQuery(criteria).getResultList();		
		
		
	}
	
	public Double calculaQuantidadeAcumulativoPlanejada(Long acaoId, Long exercicioId){
 
		
		if(Utils.invalidId(acaoId) || exercicioId==null)return 0d;
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Double> criteria = builder.createQuery(Double.class);
		
		Root<FisicoFinanceiroMensal> root = criteria.from(FisicoFinanceiroMensal.class);
		
		Join<Object, Object> joinAcao = root.join(ACAO,JoinType.LEFT);		
		
		Join<Object, Object> joinTipoCalculoMeta = joinAcao.join(TIPO_CALCULO_META,JoinType.LEFT);
		
		joinTipoCalculoMeta.on( builder.equal(joinTipoCalculoMeta.get(ID),TipoCalculoMeta.ACUMULATIVA.getId()));
		 
		
		Path<Double> dotacaoInicial = root.get(QUANTIDADE);
		Expression<Double> soma = builder.sum(dotacaoInicial);
		criteria.select(soma);
		 
		
		criteria.where(
					   builder.equal(root.get(ACAO).get(ID),acaoId),
					   builder.equal(root.get(EXERCICIO).get(ID),exercicioId )
						);
 
		
		 Double value = entityManager.createQuery(criteria).getSingleResult();
			
			
		 return value!=null?value:0d;

		
	}
	 
	public Double calculaQuantidadeNaoAcumulativoPlanejada(Long acaoId, Long exercicioId){
 
		
		if(Utils.invalidId(acaoId) || exercicioId==null)return 0d;
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Double> criteria = builder.createQuery(Double.class);
		
		Root<FisicoFinanceiroMensal> root = criteria.from(FisicoFinanceiroMensal.class);
		
		Join<Object, Object> joinAcao = root.join(ACAO,JoinType.LEFT);		
		
		Join<Object, Object> joinTipoCalculoMeta = joinAcao.join(TIPO_CALCULO_META,JoinType.LEFT);
		
		joinTipoCalculoMeta.on( builder.equal(joinTipoCalculoMeta.get(ID),TipoCalculoMeta.NAO_ACUMULATIVA.getId()));
		 
		
		Path<Double> dotacaoInicial = root.get(QUANTIDADE);
		Expression<Double> soma = builder.sum(dotacaoInicial);
		Expression<Long> count = builder.count(root);
		
		criteria.multiselect(builder.quot(soma, count));
		
		
		criteria.where(
					   builder.equal(root.get(ACAO).get(ID),acaoId),
					   builder.equal(root.get(EXERCICIO).get(ID),exercicioId ),
					   builder.notEqual(root.get(QUANTIDADE),0 )
					  );
 
		
	 Double value = entityManager.createQuery(criteria).getSingleResult();
		
		
	 return value!=null?value:0d;
	 
	}
	 		
	public Double calculaQuantidadeAcumulativoExecutada(Long acaoId, Long exercicioId){
		
		if(Utils.invalidId(acaoId) || exercicioId==null)return 0d;
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Double> criteria = builder.createQuery(Double.class);
		
		Root<Execucao> root = criteria.from(Execucao.class);
		
		Join<Object, Object> joinAcao = root.join(ACAO,JoinType.LEFT);		
		
		Join<Object, Object> joinTipoCalculoMeta = joinAcao.join(TIPO_CALCULO_META,JoinType.LEFT);
		
		joinTipoCalculoMeta.on( builder.equal(joinTipoCalculoMeta.get(ID),TipoCalculoMeta.ACUMULATIVA.getId()));
		 
		
		Path<Double> dotacaoInicial = root.get(QUANTIDADE);
		Expression<Double> soma = builder.sum(dotacaoInicial);
		criteria.select(soma);
		 
		
		criteria.where(
					   builder.equal(root.get(ACAO).get(ID),acaoId),
					   builder.equal(root.get(EXERCICIO).get(ID),exercicioId )
						);
 
		
		 Double value = entityManager.createQuery(criteria).getSingleResult();
			
			
		 return value!=null?value:0d;

		
	}
	
	public Double calculaQuantidadeNaoAcumulativoExecutada(Long acaoId, Long exercicioId){
 
		
		if(Utils.invalidId(acaoId) || exercicioId==null)return 0d;
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Double> criteria = builder.createQuery(Double.class);
		
		Root<Execucao> root = criteria.from(Execucao.class);
		
		Join<Object, Object> joinAcao = root.join(ACAO,JoinType.LEFT);		
		
		Join<Object, Object> joinTipoCalculoMeta = joinAcao.join(TIPO_CALCULO_META,JoinType.LEFT);
		
		joinTipoCalculoMeta.on( builder.equal(joinTipoCalculoMeta.get(ID),TipoCalculoMeta.NAO_ACUMULATIVA.getId()));
		 
		
		Path<Double> dotacaoInicial = root.get(QUANTIDADE);
		Expression<Double> soma = builder.sum(dotacaoInicial);
		Expression<Long> count = builder.count(root);
		
		criteria.multiselect(builder.quot(soma, count));
		
		
		criteria.where(
					   builder.equal(root.get(ACAO).get(ID),acaoId),
					   builder.equal(root.get(EXERCICIO).get(ID),exercicioId ),
					   builder.notEqual(root.get(QUANTIDADE),0 )
					  );
 
		
	 Double value = entityManager.createQuery(criteria).getSingleResult();
		
		
	 return value!=null?value:0d;
	 
	}
 	
	public BigDecimal calculaDotacaoInicialByProgAndAno(String programaCodigo, Integer anoVigente){
 
		
		if(Utils.emptyParam(programaCodigo) || anoVigente==null)return new BigDecimal(0);
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<BigDecimal> criteria = builder.createQuery(BigDecimal.class);
		
		Root<FisicoFinanceiroMensalSiafem> root = criteria.from(FisicoFinanceiroMensalSiafem.class);
		
		Path<BigDecimal> dotacaoInicial = root.get(DOTACAO_INICIAL);
		Expression<BigDecimal> soma = builder.sum(dotacaoInicial);
		criteria.select(soma);
		
		criteria.where(
					   builder.equal(root.get(PROGRAMA),programaCodigo ),
					   builder.equal(root.get(ANO),anoVigente )
					   );
 	
		TypedQuery<BigDecimal> query = entityManager.createQuery(criteria);
 
	    return query.getSingleResult();
		
		
	}
	 
	public FisicoFinanceiroMensalSiafem calculaDetalhamentoMensalByMesAndAnoAndProgramaAndUnidadeAndAcao(Long mes, Integer ano,String programaCodigo,String unidadeOrcamentariaCodigo,String acaoCodigo){
 
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<FisicoFinanceiroMensalSiafem> criteria = builder.createQuery(FisicoFinanceiroMensalSiafem.class);
		
		Root<FisicoFinanceiroMensalSiafem> root = criteria.from(FisicoFinanceiroMensalSiafem.class);
		Join<Object, Object> joinMes 		    = root.join(MES,JoinType.INNER);
		Join<Object, Object> joinAcao 		    = root.join(ACAO,JoinType.INNER);
		
	 
		criteria.multiselect(
								builder.sum(root.get(DOTACAO_INICIAL)),
								builder.sum(root.get(DISPONIVEL)),
								builder.sum(root.get(EMPENHADO)),
								builder.sum(root.get(LIQUIDADO))
								
							 );
		
		criteria.where(
					   builder.equal(joinMes.get(ID),mes),
					   builder.equal(root.get(ANO),ano),
					   builder.equal(root.get(PROGRAMA),programaCodigo),
					   builder.equal(root.get(UNIDADE_ORCAMENTARIA),unidadeOrcamentariaCodigo),
					   builder.equal(joinAcao.get(CODIGO),acaoCodigo)
					   );
 	
		TypedQuery<FisicoFinanceiroMensalSiafem> query = entityManager.createQuery(criteria);
  		
	    return query.getSingleResult();
	}

	
	public BigDecimal calculaDotacaoAtualByProgAndAno(String programaCodigo, Integer anoVigente){
 
		
		if(Utils.emptyParam(programaCodigo) || anoVigente==null)return null;
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<BigDecimal> criteria = builder.createQuery(BigDecimal.class);
		
		Root<FisicoFinanceiroMensalSiafem> root = criteria.from(FisicoFinanceiroMensalSiafem.class);
		
		Path<BigDecimal> disponivel = root.get(DISPONIVEL);
		Expression<BigDecimal> soma = builder.sum(disponivel);
		criteria.select(soma);
		
		criteria.where(
					 builder.equal(root.get(PROGRAMA),programaCodigo ),
					 builder.equal(root.get(ANO),anoVigente )
			    );
		
		TypedQuery<BigDecimal> query = entityManager.createQuery(criteria);
 
		
	 return query.getSingleResult();
		
		
	}
 	
	public BigDecimal calculaEmpenhadoByProgAndAno(String programaCodigo, Integer anoVigente){
 
		
		if(Utils.emptyParam(programaCodigo) || anoVigente==null)return null;
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<BigDecimal> criteria = builder.createQuery(BigDecimal.class);
		
		Root<FisicoFinanceiroMensalSiafem> root = criteria.from(FisicoFinanceiroMensalSiafem.class);
		
		Path<BigDecimal> disponivel = root.get(EMPENHADO);
		Expression<BigDecimal> soma = builder.sum(disponivel);
		criteria.select(soma);
		
		criteria.where(
					 builder.equal(root.get(PROGRAMA),programaCodigo ),
					 builder.equal(root.get(ANO),anoVigente )
			    );
		
		TypedQuery<BigDecimal> query = entityManager.createQuery(criteria);
 
		
		return query.getSingleResult();
		
		
	}

	public BigDecimal calculaLiquidadoByProgAndAno(String programaCodigo, Integer anoVigente) {

		if(Utils.emptyParam(programaCodigo) || anoVigente==null)return null;
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<BigDecimal> criteria = builder.createQuery(BigDecimal.class);
		
		Root<FisicoFinanceiroMensalSiafem> root = criteria.from(FisicoFinanceiroMensalSiafem.class);
		
		Path<BigDecimal> disponivel = root.get(LIQUIDADO);
		Expression<BigDecimal> soma = builder.sum(disponivel);
		criteria.select(soma);
		
		criteria.where(
					 builder.equal(root.get(PROGRAMA),programaCodigo ),
					 builder.equal(root.get(ANO),anoVigente )
			    );
		
		TypedQuery<BigDecimal> query = entityManager.createQuery(criteria);
 
		
		return query.getSingleResult();
		
	}
 	
	public BigDecimal calculaPagoByProgAndAno(String programaCodigo, Integer anoVigente) {

		if(Utils.emptyParam(programaCodigo) || anoVigente==null)return null;
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<BigDecimal> criteria = builder.createQuery(BigDecimal.class);
		
		Root<FisicoFinanceiroMensalSiafem> root = criteria.from(FisicoFinanceiroMensalSiafem.class);
		
		Path<BigDecimal> disponivel = root.get(PAGO);
		Expression<BigDecimal> soma = builder.sum(disponivel);
		criteria.select(soma);
		
		criteria.where(
					 builder.equal(root.get(PROGRAMA),programaCodigo ),
					 builder.equal(root.get(ANO),anoVigente )
			    );
		
		TypedQuery<BigDecimal> query = entityManager.createQuery(criteria);
 
		
		return query.getSingleResult();
		
	}
	
	public List<FisicoFinanceiroMensalSiafem>  relatorioDetalhamentoAcao(String unidadeOrcamentaria, Long acao, Integer ano){
		
		if(Utils.invalidYear(ano) )return new ArrayList<>();
		
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<FisicoFinanceiroMensalSiafem> criteria = builder.createQuery(FisicoFinanceiroMensalSiafem.class);
		
		Root<FisicoFinanceiroMensalSiafem> root = criteria.from(FisicoFinanceiroMensalSiafem.class);
		Join<Object, Object> joinAcao		 		 = root.join(ACAO, 				       JoinType.INNER);		
		Join<Object, Object> joinUnidadeMedida 		 = joinAcao.join(UNIDADE_MEDIDA, 	   JoinType.INNER);
		Join<Object, Object> joinPrograma 			 = joinAcao.join(PROGRAMA, 			   JoinType.INNER);		
		Join<Object, Object> joinUnidadeOrcamentaria = joinAcao.join(UNIDADE_ORCAMENTARIA, JoinType.INNER);
 		
		criteria.multiselect(
							  joinUnidadeOrcamentaria.get(CODIGO),
							  joinUnidadeOrcamentaria.get(DESCRICAO),
							  joinPrograma.get(CODIGO),
							  joinAcao.get(CODIGO),
							  joinAcao.get(DENOMINACAO),
							  joinAcao.get(PRODUTO),
							  joinUnidadeMedida.get(DESCRICAO)
				  			 );

 		List<Predicate> predicate = new ArrayList<>();
  		
 		if(!Utils.emptyParam(unidadeOrcamentaria)) {	
 			predicate.add(builder.equal(root.get(UNIDADE_ORCAMENTARIA),unidadeOrcamentaria));
 		}
 		
 		if(!Utils.invalidId(acao)) {
 			predicate.add(builder.equal(joinAcao.get(ID), acao));	
 		}

 		if(!Utils.invalidYear(ano)) {
 			predicate.add(builder.equal(root.get(ANO),ano));
 		}
 		
 		
		criteria.where(predicate.toArray(new Predicate[predicate.size()]));

		criteria.groupBy(
						  joinUnidadeOrcamentaria.get(CODIGO),
						  joinUnidadeOrcamentaria.get(DESCRICAO),
						  joinPrograma.get(CODIGO),
						  joinAcao.get(CODIGO),
						  joinAcao.get(DENOMINACAO),
						  joinAcao.get(PRODUTO),
						  joinUnidadeMedida.get(DESCRICAO)
						);
 

		criteria.orderBy(
						builder.asc( joinUnidadeOrcamentaria.get(CODIGO)),
						builder.asc( joinUnidadeOrcamentaria.get(DESCRICAO))
					  );
		
		
		 return entityManager.createQuery(criteria).getResultList();		
		
		
	} }
