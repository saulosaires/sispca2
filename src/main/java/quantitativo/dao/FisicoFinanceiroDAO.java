package quantitativo.dao;

import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import arquitetura.dao.AbstractDAO;
import arquitetura.utils.Utils;
import quantitativo.model.FisicoFinanceiro;

public class FisicoFinanceiroDAO extends AbstractDAO<FisicoFinanceiro> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FisicoFinanceiroDAO() {
		setClazz(FisicoFinanceiro.class);

	}
  

	public Optional<FisicoFinanceiro> findByRegiaoMunicipioAndExercicio(Long regiaoMunicipioId,Long exercicioId){
		
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<FisicoFinanceiro> query = cb.createQuery(FisicoFinanceiro.class);
		Root<FisicoFinanceiro> m = query.from(FisicoFinanceiro.class);

		query.select(m);

		if (Utils.invalidId((regiaoMunicipioId)) || Utils.invalidId((exercicioId))) {

			return Optional.ofNullable(null);
		}

		Join<Object, Object> joinRegiao = m.join("regiaoMunicipio", JoinType.INNER);

		joinRegiao.on(cb.equal(joinRegiao.get("id"), regiaoMunicipioId));

		
		
		Join<Object, Object> joinExercicio = m.join("exercicio", JoinType.INNER);

		joinExercicio.on(cb.equal(joinExercicio.get("id"), exercicioId));
		
		return entityManager.createQuery(query).setMaxResults(1).getResultList().stream().findFirst();	
		
	}
 

}
