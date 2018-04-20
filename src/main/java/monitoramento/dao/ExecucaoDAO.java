package monitoramento.dao;

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
import monitoramento.model.Execucao;

public class ExecucaoDAO extends AbstractDAO<Execucao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

}
