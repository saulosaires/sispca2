package administrativo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import administrativo.model.Usuario;
import arquitetura.dao.AbstractDAO;
import arquitetura.utils.Utils;
 

public class UsuarioDAO extends AbstractDAO< Usuario >{

 

	/**
	 * 
	 */
	private static final long serialVersionUID = -631918392833416067L;

	private static final String LOGIN = "login";
	private static final String EMAIL = "email";
	private static final String PWORD = "password";
	private static final String NAME  = "name";
	private static final String SIGLA = "sigla";
	
	public UsuarioDAO() {
		setClazz(Usuario.class );
	 
	}
	
	public Optional<Usuario> queryByUserName(String login){
		 
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Usuario> query = cb.createQuery(Usuario.class);
		Root<Usuario> m = query.from(Usuario.class);
		query.select(m);
		
		query.where(cb.equal(m.get(LOGIN),login ));
 
		List<Usuario> list = entityManager.createQuery(query).setMaxResults(1).getResultList();
		
		return list.stream().findFirst();
	}


	public Optional<Usuario> queryByUserNameAndPassword(String login,String password){
 
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Usuario> query = cb.createQuery(Usuario.class);
		Root<Usuario> m = query.from(Usuario.class);
		query.select(m);
		
		query.where(
			    cb.equal(m.get(LOGIN),login ),
			    cb.equal(m.get(PWORD),password )
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
			    cb.equal(m.get(EMAIL),email ),
			    cb.equal(m.get(PWORD),password )
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
			    cb.equal(m.get(EMAIL),email )
			    );
 
		
		List<Usuario> list = entityManager.createQuery(query).setMaxResults(1).getResultList();
		
		return list.stream().findFirst();

	}

	public List<Usuario> queryUser(String name, String email, String perfil, String unidadeOrcamentaria) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Usuario> query = cb.createQuery(Usuario.class);
		Root<Usuario> m = query.from(Usuario.class);

		query.select(m);

		List<Predicate> predicate = new ArrayList<>();

		if (!Utils.emptyParam(name)) {
 
			predicate.add(cb.like(cb.upper(m.get(NAME)), "%" + name.toUpperCase() + "%"));
		}

		if (!Utils.emptyParam(email)) {
 
			predicate.add(cb.like(cb.upper(m.get(EMAIL)), "%" + email.toUpperCase() + "%"));
		}

		if (!Utils.emptyParam((perfil))) {

			Join<Object, Object> joinPerfil = m.join("perfis",JoinType.LEFT);
			
			Expression<String> perfilUpper = cb.upper(joinPerfil.get(NAME));
			
			predicate.add(cb.like(perfilUpper, "%" + perfil.toUpperCase() + "%"));
			
		}

		if (!Utils.emptyParam((unidadeOrcamentaria))) {

			Join<Object, Object> joinUO = m.join("unidadeOrcamentaria",JoinType.LEFT);
			
			Expression<String> uoUpper = cb.upper(joinUO.get(SIGLA));
			
			predicate.add(cb.like(uoUpper, "%" + unidadeOrcamentaria.toUpperCase() + "%"));

		}

		
		
		query.where(predicate.toArray(new Predicate[predicate.size()]));

		return entityManager.createQuery(query).getResultList();
 
		
	}
	
 
}
