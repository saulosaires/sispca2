package metas.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

import arquitetura.dao.AbstractDAO;
import arquitetura.utils.Utils;
import metas.model.Compromisso;

public class CompromissoDAO extends AbstractDAO< Compromisso >  {

	
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String ID = "id";
	private static final String ATIVIDADES = "atividades";
	
	public CompromissoDAO() {
		setClazz(Compromisso.class );
	 
	}

	public List<Compromisso> findByAtividade(Long atividadeId){
		
		if (Utils.invalidId(atividadeId))
			return new ArrayList<>();

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Compromisso> query = cb.createQuery(Compromisso.class);
		Root<Compromisso> root = query.from(Compromisso.class);

		query.select(root);

		SetJoin<Object, Object> joinAtividade = root.joinSet(ATIVIDADES, JoinType.INNER);

		joinAtividade.on(cb.equal(joinAtividade.get(ID), atividadeId));

		return entityManager.createQuery(query).getResultList();
		
	}
	
	
}
