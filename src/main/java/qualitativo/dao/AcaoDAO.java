package qualitativo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import arquitetura.dao.AbstractDAO;
import arquitetura.utils.Utils;
import qualitativo.model.Acao;

public class AcaoDAO extends AbstractDAO<Acao> {

 

	/**
	 * 
	 */
	private static final long serialVersionUID = -5222534690693067790L;

	public AcaoDAO() {
		setClazz(Acao.class);

	}

	public List<Acao> buscar(String codigo, String denominacao,Long unidadeOrcamentariaId,Long programaId){
		 
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Acao> query = cb.createQuery(Acao.class);
		Root<Acao> m = query.from(Acao.class);
		
		query.select(m);
		
		List<Predicate> predicate = new ArrayList<>();
		
		if(!Utils.emptyParam(codigo)) {
			
			Expression<String> upperCodigo = cb.upper(m.get("codigo"));
			
			predicate.add(cb.like(upperCodigo,"%"+codigo.toUpperCase()+"%"));
		}
		
		if(!Utils.emptyParam(denominacao)) {
			
			Expression<String> upperDenominacao = cb.upper(m.get("denominacao"));
			
			predicate.add(cb.like(upperDenominacao,"%"+denominacao.toUpperCase()+"%" ));		
		}
		
		if(!Utils.invalidId(unidadeOrcamentariaId)) {
 			
			Join<Object, Object> joinUO = m.join("unidadeOrcamentaria",JoinType.LEFT);
			
			Expression<String> uoId = joinUO.get("id");
			
			predicate.add(cb.equal(uoId,unidadeOrcamentariaId));
		}
		
		if(!Utils.invalidId(programaId)) {
 			
			Join<Object, Object> joinUO = m.join("programa",JoinType.LEFT);
			
			Expression<String> uoId = joinUO.get("id");
			
			predicate.add(cb.equal(uoId,programaId));
		}
		
		
			query.where(  predicate.toArray(new Predicate[predicate.size()]));
  
			query.orderBy(cb.asc(m.get("denominacao")));
		
		return entityManager.createQuery(query).getResultList();
	}

}
