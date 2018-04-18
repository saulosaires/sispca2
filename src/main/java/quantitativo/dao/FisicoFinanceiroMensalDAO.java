package quantitativo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import arquitetura.dao.AbstractDAO;
import arquitetura.utils.Utils;
import quantitativo.model.FisicoFinanceiroMensal;

public class FisicoFinanceiroMensalDAO extends AbstractDAO<FisicoFinanceiroMensal> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FisicoFinanceiroMensalDAO() {
		setClazz(FisicoFinanceiroMensal.class);

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

		Join<Object, Object> joinRegiao = m.join("regiaoMunicipio", JoinType.INNER);

		joinRegiao.on(cb.equal(joinRegiao.get("id"), regiaoMunicipioId));

		
		
		Join<Object, Object> joinExercicio = m.join("exercicio", JoinType.INNER);

		joinExercicio.on(cb.equal(joinExercicio.get("id"), exercicioId));
		
		
		
		Join<Object, Object> joinAcao = m.join("acao", JoinType.INNER);

		joinAcao.on(cb.equal(joinAcao.get("id"), acaoId));
		
		
		
		Join<Object, Object> joinMes= m.join("mes", JoinType.INNER);

		joinMes.on(cb.equal(joinMes.get("id"), mesId));

		
		
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
 

}
