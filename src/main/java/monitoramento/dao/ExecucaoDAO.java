package monitoramento.dao;

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
	private static final  String NUMERO_MES = "numeroMes";
	
	public ExecucaoDAO() {
		setClazz(Execucao.class);

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

		Join<Object, Object> joinAcao = m.join("acao", JoinType.INNER);
		joinAcao.on(cb.equal(joinAcao.get("id"), acaoId));
		 	
		
		Join<Object, Object> joinExercicio = m.join("exercicio", JoinType.INNER);
		joinExercicio.on(cb.equal(joinExercicio.get("id"), exercicioId));

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

		 
		Join<Object, Object> joinRegiao = m.join("regiaoMunicipio", JoinType.INNER);
		joinRegiao.on(cb.equal(joinRegiao.get("id"), regiaoMunicipioId));

		Join<Object, Object> joinAcao = m.join("acao", JoinType.INNER);
		joinAcao.on(cb.equal(joinAcao.get("id"), acaoId));
		 	
		Join<Object, Object> joinMes = m.join("mes", JoinType.INNER);
		joinMes.on(cb.equal(joinMes.get("id"), mesId));

		Join<Object, Object> joinExercicio = m.join("exercicio", JoinType.INNER);
		joinExercicio.on(cb.equal(joinExercicio.get("id"), exercicioId));

		query.where(predicate.toArray(new Predicate[predicate.size()]));
 
		
		return entityManager.createQuery(query).setMaxResults(1).getResultList().stream().findFirst();

	}

	public Double findTotalValorExecutadoByAcao(Long unidadeGestoraId, Long unidadeOrcamentariaId, Long acaoId,Long exercicioVigenteId, Long mesId) {
		
		if(Utils.invalidId(mesId) || exercicioVigenteId==null)return 0d;
		
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Double> criteria = builder.createQuery(Double.class);
		
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
		
 
		Path<Double> valor = root.get(VALOR);
		Expression<Double> soma = builder.sum(valor);
		criteria.select(soma);
		 
	 
		criteria.groupBy(
						joinExercicio.get(ID),
						joinMes.get(NUMERO_MES)
					    );
		
		 Double value = entityManager.createQuery(criteria).getResultList().stream().findFirst().orElse(null);
			
			
		 return value!=null?value:0d;		
		
		 
	}
	
	
}
