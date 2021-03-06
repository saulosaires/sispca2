package administrativo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import administrativo.model.Mensagem;
import arquitetura.dao.AbstractDAO;
import arquitetura.utils.Utils;

public class MensagemDAO extends AbstractDAO<Mensagem> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6032713621553762618L;

	public MensagemDAO() {
		setClazz(Mensagem.class);

	}

	public Optional<Mensagem> queryByDate(Date dataExpiracao) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Mensagem> query = cb.createQuery(Mensagem.class);
		Root<Mensagem> m = query.from(Mensagem.class);
		
		query.select(m);
		
		List<Predicate> predicate = new ArrayList<>();
		
		if(dataExpiracao!=null) {

			predicate.add(cb.greaterThanOrEqualTo(m.get("dataExpiracao"),dataExpiracao));
		}
		

		query.where(  predicate.toArray(new Predicate[predicate.size()]));
 
		return entityManager.createQuery(query).setMaxResults(1).getResultList().stream().findFirst();
		
	}	
	
	
	public List<Mensagem> queryByTituloAndTexto(String titulo, String texto) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Mensagem> query = cb.createQuery(Mensagem.class);
		Root<Mensagem> m = query.from(Mensagem.class);
		
		query.select(m);
		
		List<Predicate> predicate = new ArrayList<>();
		
		if(!Utils.emptyParam(titulo)) {
			
			Expression<String> upperTitulo = cb.upper(m.get("titulo"));
			
			predicate.add(cb.like(upperTitulo,"%"+titulo.toUpperCase()+"%"));
		}
		
		if(!Utils.emptyParam(texto)) {
			
			Expression<String> upperUrl = cb.upper(m.get("texto"));
			
			predicate.add(cb.like(upperUrl,"%"+texto.toUpperCase()+"%" ));		
		}
		
		query.where(  predicate.toArray(new Predicate[predicate.size()]));
 
		return entityManager.createQuery(query).getResultList();
		
	}

	 
}
