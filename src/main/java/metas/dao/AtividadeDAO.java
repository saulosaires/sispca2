package metas.dao;

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
import metas.model.Atividade;

public class AtividadeDAO extends AbstractDAO< Atividade >  {

	private static final String ID = "id";
	private static final String NOME = "nome";
	private static final String UNIDADE_ORCAMENTARIA = "unidadeOrcamentaria";

	/**
	 * 
	 */
	private static final long serialVersionUID = -6591651506694720804L;

	public AtividadeDAO() {
		setClazz(Atividade.class );
	 
	}
	
	public List<Atividade> findByNomeAndUnidadeOrcamentaria(String nome, Long unidadeOrcamentaria){
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Atividade> query = cb.createQuery(Atividade.class);
		Root<Atividade> m = query.from(Atividade.class);

		query.select(m);

		List<Predicate> predicate = new ArrayList<>();
		
		if (!Utils.emptyParam(nome)) {
			 
			predicate.add(cb.like(cb.upper(m.get(NOME)), "%" + nome.toUpperCase() + "%"));
		}

		if (!Utils.invalidId((unidadeOrcamentaria))) {

			Join<Object, Object> joinPerfil = m.join(UNIDADE_ORCAMENTARIA,JoinType.LEFT);
 
			predicate.add(cb.equal(joinPerfil.get(ID),unidadeOrcamentaria));
			
		}

		query.where(predicate.toArray(new Predicate[predicate.size()]));

		query.orderBy(cb.asc(m.get(NOME)));
		
		return entityManager.createQuery(query).getResultList();
		
	}
	
	
	

}
