package quantitativo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import arquitetura.dao.AbstractDAO;
import arquitetura.utils.Utils;
import quantitativo.model.RegiaoMunicipio;

public class RegiaoMunicipioDAO extends AbstractDAO<RegiaoMunicipio> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RegiaoMunicipioDAO() {
		setClazz(RegiaoMunicipio.class);

	}

	public List<RegiaoMunicipio> findByRegiao(Long regiaoId) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<RegiaoMunicipio> query = cb.createQuery(RegiaoMunicipio.class);
		Root<RegiaoMunicipio> m = query.from(RegiaoMunicipio.class);

		query.select(m);

		List<Predicate> predicate = new ArrayList<>();

		if (!Utils.invalidId((regiaoId))) {

			Join<Object, Object> joinRegiao = m.join("regiao", JoinType.INNER);
	 
			joinRegiao.on(cb.equal(joinRegiao.get("id"), regiaoId));
 
		}
 
		query.where(predicate.toArray(new Predicate[predicate.size()]));
 
		Join<Object, Object> joinMunicipio = m.join("municipio", JoinType.INNER);
		
		query.orderBy(cb.asc(joinMunicipio.get("descricao")));
		
		
		return entityManager.createQuery(query).getResultList();

	}	 

	public List<RegiaoMunicipio> findByTipoRegiao(Long tipoRegiaoId) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<RegiaoMunicipio> query = cb.createQuery(RegiaoMunicipio.class);
		Root<RegiaoMunicipio> m = query.from(RegiaoMunicipio.class);

		query.select(m);

		List<Predicate> predicate = new ArrayList<>();

		if (!Utils.invalidId((tipoRegiaoId))) {

			Join<Object, Object> joinRegiao = m.join("regiao", JoinType.INNER);
			
			Join<Object, Object> joinTipoRegiao = joinRegiao.join("tipoRegiao", JoinType.INNER);
			
			joinTipoRegiao.on(cb.equal(joinTipoRegiao.get("id"), tipoRegiaoId));

			query.orderBy(cb.asc(joinRegiao.get("descricao")));
		}

		query.where(predicate.toArray(new Predicate[predicate.size()]));
 

		return entityManager.createQuery(query).getResultList();

	}

}
