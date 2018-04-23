package siafem.dao;

import java.math.BigDecimal;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import arquitetura.dao.AbstractDAO;
import arquitetura.utils.Utils;
import siafem.model.FisicoFinanceiroMensalSiafem;

public class FisicoFinanceiroMensalSiafemDAO extends AbstractDAO<FisicoFinanceiroMensalSiafem> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FisicoFinanceiroMensalSiafemDAO() {
		setClazz(FisicoFinanceiroMensalSiafem.class);

	}
  
	
	public BigDecimal calculaDotacaoInicialPorUoProg(Long programaId, Integer anoVigente){
 
		
		if(Utils.invalidId(programaId) || anoVigente==null)return null;
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<BigDecimal> criteria = builder.createQuery(BigDecimal.class);
		
		Root<FisicoFinanceiroMensalSiafem> root = criteria.from(FisicoFinanceiroMensalSiafem.class);
		
		Path<BigDecimal> dotacaoInicial = root.get("dotacaoInicial");
		Expression<BigDecimal> soma = builder.sum(dotacaoInicial);
		criteria.select(soma);
		
		criteria.where(
					 builder.equal(root.get("programa"),programaId.toString() ),
					 builder.equal(root.get("ano"),anoVigente )
			    );
		
		TypedQuery<BigDecimal> query = entityManager.createQuery(criteria);
 
		
	 return query.getSingleResult();
		
		
	}
	 
	public BigDecimal calculaDotacaoAtualPorUoProg(Long programaId, Integer anoVigente){
 
		
		if(Utils.invalidId(programaId) || anoVigente==null)return null;
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<BigDecimal> criteria = builder.createQuery(BigDecimal.class);
		
		Root<FisicoFinanceiroMensalSiafem> root = criteria.from(FisicoFinanceiroMensalSiafem.class);
		
		Path<BigDecimal> disponivel = root.get("disponivel");
		Expression<BigDecimal> soma = builder.sum(disponivel);
		criteria.select(soma);
		
		criteria.where(
					 builder.equal(root.get("programa"),programaId.toString() ),
					 builder.equal(root.get("ano"),anoVigente )
			    );
		
		TypedQuery<BigDecimal> query = entityManager.createQuery(criteria);
 
		
	 return query.getSingleResult();
		
		
	}
 

}
