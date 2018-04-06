package qualitativo.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import arquitetura.dao.AbstractDAO;
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
		
//		List<Predicate> predicate = new ArrayList<>();
//		
//		if(!Utils.emptyParam(titulo)) {
//			
//			Expression<String> upperTitulo = cb.upper(m.get("titulo"));
//			
//			predicate.add(cb.like(upperTitulo,"%"+titulo.toUpperCase()+"%"));
//		}
//		
//		if(!Utils.emptyParam(url)) {
//			
//			Expression<String> upperUrl = cb.upper(m.get("url"));
//			
//			predicate.add(cb.like(upperUrl,"%"+url.toUpperCase()+"%" ));		
//		}
//		
//		query.where(  predicate.toArray(new Predicate[predicate.size()]));
// 
		return entityManager.createQuery(query).getResultList();
	}

}
