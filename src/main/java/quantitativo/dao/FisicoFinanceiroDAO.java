package quantitativo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import arquitetura.dao.AbstractDAO;
import arquitetura.utils.Utils;
import quantitativo.model.FisicoFinanceiro;

public class FisicoFinanceiroDAO extends AbstractDAO<FisicoFinanceiro> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String ID = "id";
	private static final String ACAO = "acao";
	private static final String ANO = "ano";
	private static final String CODIGO = "codigo";
	private static final String DESCRICAO = "descricao";
	private static final String EXERCICIO = "exercicio";
	private static final String QUANTIDADE = "quantidade";
	private static final String VALOR = "valor";
	private static final String REGIAO_MUNICIPIO = "regiaoMunicipio";
	private static final String REGIAO = "regiao";
	private static final String MUNICIPIO = "municipio";
	private static final String DENOMINACAO = "denominacao";
	private static final String UNIDADE_ORCAMENTARIA = "unidadeOrcamentaria";
	private static final String PPA="ppa";
	
	public FisicoFinanceiroDAO() {
		setClazz(FisicoFinanceiro.class);

	}
 
	public List<FisicoFinanceiro> totalPorUnidadeOrcamentaria(Long unidadeOrcamentariaId, Long ppaId){
		
		if (Utils.invalidId((unidadeOrcamentariaId)) || Utils.invalidId(ppaId)) {

			return new ArrayList<>();
		  	
		}
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<FisicoFinanceiro> query = cb.createQuery(FisicoFinanceiro.class);
		Root<FisicoFinanceiro> m = query.from(FisicoFinanceiro.class);

		Join<Object, Object> joinAcao 				 = m.join(ACAO, JoinType.INNER);
		Join<Object, Object> joinUnidadeOrcamentaria = joinAcao.join(UNIDADE_ORCAMENTARIA, JoinType.INNER);
		Join<Object, Object> joinExercicio 			 = joinAcao.join(EXERCICIO, JoinType.INNER);
		Join<Object, Object> joinPpa 				 = joinExercicio.join(PPA, JoinType.INNER);
		
		query.multiselect(
						   cb.sum(m.get(QUANTIDADE)),	
						   cb.sum(m.get(VALOR)),
						   joinExercicio.get(ANO)
					      );
		
		query.where(						 
				     cb.equal(joinUnidadeOrcamentaria.get(ID), unidadeOrcamentariaId),
				     cb.equal(joinPpa.get(ID), ppaId)
		            );

		query.groupBy(
					  joinExercicio.get(ANO) 
				      );
	
		query.orderBy(
						cb.asc( joinExercicio.get(ANO))
					  );

		
		return entityManager.createQuery(query).getResultList();
	
	}
	
	public List<FisicoFinanceiro> findByAcaoAndPpa(String acaoCodigo, Long ppaId){
		
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<FisicoFinanceiro> query = cb.createQuery(FisicoFinanceiro.class);
		Root<FisicoFinanceiro> m = query.from(FisicoFinanceiro.class);

		query.select(m);

		Join<Object, Object> joinAcao = m.join(ACAO, JoinType.INNER);
		
		if (!Utils.emptyParam((acaoCodigo))) {

			joinAcao.on(cb.equal(joinAcao.get(CODIGO), acaoCodigo));
		}
		
		if(!Utils.invalidId(ppaId)) {
			
			Join<Object, Object> joinExercicio = joinAcao.join(EXERCICIO, JoinType.INNER);
			Join<Object, Object> joinPpa =    joinExercicio.join(PPA,       JoinType.INNER);
			 
			
			joinPpa.on(cb.equal(joinPpa.get(ID), ppaId));
			
			query.orderBy(
							cb.asc( joinExercicio.get(ANO))
						  );
			
		}
		
		  
		
		return entityManager.createQuery(query).getResultList();
		
	}
	
	public List<FisicoFinanceiro> findByAcao(Long acaoId){
		
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<FisicoFinanceiro> query = cb.createQuery(FisicoFinanceiro.class);
		Root<FisicoFinanceiro> m = query.from(FisicoFinanceiro.class);

		query.select(m);

		if (Utils.invalidId((acaoId))) {

			return new ArrayList<>();
		}

		
		Join<Object, Object> joinAcao = m.join(ACAO, JoinType.INNER);

		joinAcao.on(cb.equal(joinAcao.get(ID), acaoId));
		
		
		return entityManager.createQuery(query).getResultList();
		
	}
		
	public Optional<FisicoFinanceiro> findByRegiaoMunicipioAndExercicioAndAcao(Long regiaoMunicipioId,Long exercicioId,Long acaoId){
		
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<FisicoFinanceiro> query = cb.createQuery(FisicoFinanceiro.class);
		Root<FisicoFinanceiro> m = query.from(FisicoFinanceiro.class);

		query.select(m);

		if (Utils.invalidId((regiaoMunicipioId)) || Utils.invalidId((exercicioId)) || Utils.invalidId((acaoId))) {

			return Optional.ofNullable(null);
		}

		Join<Object, Object> joinRegiao = m.join(REGIAO_MUNICIPIO, JoinType.INNER);

		joinRegiao.on(cb.equal(joinRegiao.get(ID), regiaoMunicipioId));

		
		
		Join<Object, Object> joinExercicio = m.join(EXERCICIO, JoinType.INNER);

		joinExercicio.on(cb.equal(joinExercicio.get(ID), exercicioId));
		
		
		
		Join<Object, Object> joinAcao = m.join(ACAO, JoinType.INNER);

		joinAcao.on(cb.equal(joinAcao.get(ID), acaoId));
		
		
		return entityManager.createQuery(query).setMaxResults(1).getResultList().stream().findFirst();	
		
	}
 
	public List<FisicoFinanceiro>  relatorioPlanejadoPorAno(Long regiaoId,Long municipioId,Long unidadeOrcamentariaId,Long exercicioId){
		
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<FisicoFinanceiro> query = cb.createQuery(FisicoFinanceiro.class);
		
		Root<FisicoFinanceiro> m = query.from(FisicoFinanceiro.class);
		
		Join<Object, Object> joinExercicio 			 = m.join(EXERCICIO, 				   JoinType.INNER);
		Join<Object, Object> joinAcao 				 = m.join(ACAO, 					   JoinType.INNER);
    	Join<Object, Object> joinUnidadeOrcamentaria = joinAcao.join(UNIDADE_ORCAMENTARIA, JoinType.INNER);
    	Join<Object, Object> joinRegiaoMunicipio 	 = m.join(REGIAO_MUNICIPIO, 		   JoinType.INNER);
    	Join<Object, Object>joinRegiao 				 = joinRegiaoMunicipio.join(REGIAO,    JoinType.LEFT);
    	Join<Object, Object>joinMunicipio 			 = joinRegiaoMunicipio.join(MUNICIPIO, JoinType.LEFT);
		query.select(m);

		
		List<Predicate> predicate = new ArrayList<>();
		if(!Utils.invalidId(exercicioId)) {
			
			predicate.add(cb.equal(joinExercicio.get(ID), exercicioId));
		}
		
	    if(!Utils.invalidId(unidadeOrcamentariaId)) {
	    	
	    	predicate.add(cb.equal(joinUnidadeOrcamentaria.get(ID), unidadeOrcamentariaId));
	    }
	
	   if(!Utils.invalidId(regiaoId)) {
		  
			predicate.add(cb.equal(joinRegiao.get(ID), regiaoId));
	   }
	
	   if(!Utils.invalidId(municipioId)) {
		 
			predicate.add(cb.equal(joinMunicipio.get(ID), municipioId));
	   }

		query.where(predicate.toArray(new Predicate[predicate.size()]));
		
	   query.orderBy(
					cb.asc( joinExercicio.get(ANO)),
					cb.asc( joinUnidadeOrcamentaria.get(DESCRICAO)),
					cb.asc( joinRegiao.get(DESCRICAO)),
					cb.asc( joinMunicipio.get(DESCRICAO)),
					cb.asc( joinAcao.get(DENOMINACAO)),
					cb.asc( m.get(QUANTIDADE)),
					cb.asc( m.get(VALOR))
				);
 		
		
		return entityManager.createQuery(query).getResultList();	
		
	}
		
	public List<FisicoFinanceiro>  relatorioPlanejadoPorRegiao(Long regiaoId,Long unidadeOrcamentariaId,Long exercicioId){
		
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<FisicoFinanceiro> query = cb.createQuery(FisicoFinanceiro.class);
		
		Root<FisicoFinanceiro> m = query.from(FisicoFinanceiro.class);
		
		Join<Object, Object> joinExercicio 			 = m.join(EXERCICIO, 				   JoinType.INNER);
		Join<Object, Object> joinAcao 				 = m.join(ACAO, 					   JoinType.INNER);
    	Join<Object, Object> joinUnidadeOrcamentaria = joinAcao.join(UNIDADE_ORCAMENTARIA, JoinType.INNER);
    	Join<Object, Object> joinRegiaoMunicipio 	 = m.join(REGIAO_MUNICIPIO, 		   JoinType.INNER);
    	Join<Object, Object>joinRegiao 				 = joinRegiaoMunicipio.join(REGIAO,    JoinType.LEFT);
   	
		query.multiselect(
						joinRegiao.get(DESCRICAO),
						joinUnidadeOrcamentaria.get(DESCRICAO),
						joinAcao.get(CODIGO),
						joinAcao.get(DENOMINACAO),
						joinExercicio.get(ANO),
						m.get(QUANTIDADE),
						m.get(VALOR)
					  );

		
		List<Predicate> predicate = new ArrayList<>();
		if(!Utils.invalidId(exercicioId)) {
			
			predicate.add(cb.equal(joinExercicio.get(ID), exercicioId));
		}
		
	    if(!Utils.invalidId(unidadeOrcamentariaId)) {
	    	
	    	predicate.add(cb.equal(joinUnidadeOrcamentaria.get(ID), unidadeOrcamentariaId));
	    }
	
	   if(!Utils.invalidId(regiaoId)) {
		  
			predicate.add(cb.equal(joinRegiao.get(ID), regiaoId));
	   }
	
	   query.where(predicate.toArray(new Predicate[predicate.size()]));
		
	  
	   query.groupBy(
			   		  joinRegiao.get(DESCRICAO),
			   		  joinUnidadeOrcamentaria.get(DESCRICAO),
	   				  joinExercicio.get(ANO),
					  joinAcao.get(CODIGO),
					  joinAcao.get(DENOMINACAO),
					  m.get(QUANTIDADE),
					  m.get(VALOR)
				  );

	   query.orderBy(
					 cb.asc(joinRegiao.get(DESCRICAO)), 
					 cb.asc(joinUnidadeOrcamentaria.get(DESCRICAO)),
					 cb.asc(joinExercicio.get(ANO)), 
					 cb.asc(joinAcao.get(DENOMINACAO)), 
					 cb.asc(m.get(QUANTIDADE)),
					 cb.asc(m.get(VALOR))
				  );
 		
		
		return entityManager.createQuery(query).getResultList();	
		
	}
	
	
	
	
	
}
