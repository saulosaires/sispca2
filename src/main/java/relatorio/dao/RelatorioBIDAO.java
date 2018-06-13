package relatorio.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import arquitetura.dao.AbstractDAO;
import arquitetura.utils.Utils;
import relatorio.model.RelatorioBi;

public class RelatorioBIDAO extends AbstractDAO<RelatorioBi> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3384453382168304771L;

	private static final  String UNIDADE_ORCAMENTARIA="unidadeOrcamentaria"; 
	private static final  String ANO="ano";
	private static final  String ID="id";
	
	public RelatorioBIDAO() {
		setClazz(RelatorioBi.class);

	}

	public List<RelatorioBi> buscarByUnidadeOrcamentaria(Integer ano,Long unidadeOrcamentariaId){
		 
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<RelatorioBi> query = cb.createQuery(RelatorioBi.class);
		Root<RelatorioBi> m = query.from(RelatorioBi.class);
		
		query.select(m);
 
		if (!Utils.invalidYear((ano))) {

			query.where(cb.equal(m.get(ANO),ano) );	
		
		}
	
		
		if (!Utils.invalidId((unidadeOrcamentariaId))) {

			Join<Object, Object> joinUO = m.join(UNIDADE_ORCAMENTARIA,JoinType.INNER);
			joinUO.on(cb.equal(joinUO.get(ID),unidadeOrcamentariaId) );	
		
		}
		
		
		
 		
		return entityManager.createQuery(query).getResultList();
	}

}
