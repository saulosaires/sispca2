package administrativo.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import administrativo.model.Usuario;
import arquitetura.dao.AbstractDAO;
 

public class UsuarioDAO extends AbstractDAO< Usuario >{

 

	public UsuarioDAO() {
		setClazz(Usuario.class );
	 
	}

	public Optional<Usuario> queryByUserNameAndPassword(String login,String password){
 
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Usuario> query = cb.createQuery(Usuario.class);
		Root<Usuario> m = query.from(Usuario.class);
		query.select(m);
		
		query.where(
			    cb.equal(m.get("login"),login ),
			    cb.equal(m.get("password"),password )
			    );
 
		List<Usuario> list = entityManager.createQuery(query).setMaxResults(1).getResultList();
		
		return list.stream().findFirst();
	}

	public Optional<Usuario> queryByEmailAndPassword(String email,String password){
		 
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Usuario> query = cb.createQuery(Usuario.class);
		Root<Usuario> m = query.from(Usuario.class);
		query.select(m);
		
		query.where(
			    cb.equal(m.get("email"),email ),
			    cb.equal(m.get("password"),password )
			    );
 
		
		List<Usuario> list = entityManager.createQuery(query).setMaxResults(1).getResultList();
		
		return list.stream().findFirst();

	}

	public Optional<Usuario> queryByEmail(String email){
		 
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Usuario> query = cb.createQuery(Usuario.class);
		Root<Usuario> m = query.from(Usuario.class);
		query.select(m);
		
		query.where(
			    cb.equal(m.get("email"),email )
			    );
 
		
		List<Usuario> list = entityManager.createQuery(query).setMaxResults(1).getResultList();
		
		return list.stream().findFirst();

	}
	
 
}
