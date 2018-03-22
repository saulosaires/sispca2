package administrativo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
 
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import administrativo.model.Usuario;
import arquitetura.dao.AbstractDAO;
import arquitetura.dao.GenericHibernateDao;
 

public class UsuarioDAO extends AbstractDAO< Usuario >  {

	
	public UsuarioDAO() {
		setClazz(Usuario.class );
	 
	}

	public Optional<Usuario> queryByUserNameAndPassword(String login,String password){
 
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Usuario> query = cb.createQuery(Usuario.class);
		Root<Usuario> m = query.from(Usuario.class);
		query.select(m);
		
		query.where(
			    cb.equal(m.get("login"),login ),
			    cb.equal(m.get("password"),password )
			    );
 
		List<Usuario> list = getEntityManager().createQuery(query).setMaxResults(1).getResultList();
		
		return list.stream().findFirst();
	}

	public Optional<Usuario> queryByEmailAndPassword(String email,String password){
		 
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Usuario> query = cb.createQuery(Usuario.class);
		Root<Usuario> m = query.from(Usuario.class);
		query.select(m);
		
		query.where(
			    cb.equal(m.get("email"),email ),
			    cb.equal(m.get("password"),password )
			    );
 
		
		List<Usuario> list = getEntityManager().createQuery(query).setMaxResults(1).getResultList();
		
		return list.stream().findFirst();

	}

	public Optional<Usuario> queryByEmail(String email){
		 
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Usuario> query = cb.createQuery(Usuario.class);
		Root<Usuario> m = query.from(Usuario.class);
		query.select(m);
		
		query.where(
			    cb.equal(m.get("email"),email )
			    );
 
		
		List<Usuario> list = getEntityManager().createQuery(query).setMaxResults(1).getResultList();
		
		return list.stream().findFirst();

	}
	
	
//	
//	@SuppressWarnings("unchecked")
//	public List<Usuario> buscaUsuariobyNameUOPerfil(String name, String email,
//			String perfil, String uo) {
//
//		Criteria c = this.getSession().createCriteria(Usuario.class, "usuario");
//
//		if (name!=null && !name.trim().equals("")){
//			c.add(Restrictions.ilike("usuario.name", name, MatchMode.ANYWHERE));
//		}
//		
//		if (email!=null && !email.trim().equals("")){
//			c.add(Restrictions.ilike("usuario.email", email, MatchMode.ANYWHERE));
//		}			
//		
//		if (perfil!=null && !perfil.trim().equals("")){
//			c.createAlias("usuario.perfis", "perfis");
//			c.add(Restrictions.ilike("perfis.name", perfil, MatchMode.ANYWHERE));
//		}
//		
//		if (uo!=null && !uo.trim().equals("")){
//			c.createAlias("usuario.unidadeOrcamentaria",
//					"usuario_unidadeOrcamentaria");
//			c.add(Restrictions.ilike("usuario_unidadeOrcamentaria.sigla", uo, MatchMode.ANYWHERE));
//		}		
//		
//		c.add(Restrictions.eq("ativo", Boolean.TRUE));
//		
//		List<Usuario> result = (List<Usuario>) c.list();
//		
//		for(Usuario u: result) {
//			u = this.getSession().get(Usuario.class, u.getId());
//			u.getPerfis().size();   // Acessa a coleção para previnir LazyInitializationException
//		}
//		
//		return result;
//	}
//
//	public Usuario buscaUsuarioByHash(String hash) {
//		
//		if(hash == null || hash.equals(""))
//			return null;
//		
//		Criteria c = this.getSession().createCriteria(Usuario.class, "usuario");
//		
//		//GregorianCalendar cal = new GregorianCalendar();
//		//Date validade = cal.getTime();
//		
//		c.add(Restrictions.eq("usuario.hash", hash));
//		//c.add(Restrictions.ge("usuario.validadeHash", validade));
//		
//		return (Usuario) c.uniqueResult();
//	}
//
//	public Usuario buscaUsuarioByEmail(String email) {
//
//		Criteria c = this.getSession().createCriteria(Usuario.class, "usuario");
//
//		c.add(Restrictions.eq("usuario.email", email));
//		c.add(Restrictions.eq("usuario.ativo", true));
//		
//		@SuppressWarnings("unchecked")
//		List<Usuario> listUsuario = c.list();
//		
//		return listUsuario.size() > 0 ? listUsuario.get(0) : null; 
//
//	}
//
//	public Usuario buscaUsuarioByLogin(String login) {
//
//		Criteria c = this.getSession().createCriteria(Usuario.class, "usuario");
//
//		c.add(Restrictions.eq("usuario.login", login));
//
//		return (Usuario) c.uniqueResult();
//
//	}
//	
//	public boolean saveOrUpdateSemTransaction(Object obj) throws Exception {
//
//		getSession().saveOrUpdate(obj);
//		return true;
//
//	}
//	
//	/** Procura um usuário pelo seu ID.
//	 * @param id O id do usuário que será obtido.
//	 * @return Retorna uma referência ao usuário encontrado. */
//	public Usuario buscaUsuarioPorId( Long id ) {
//		Criteria c = this.getSession().createCriteria(Usuario.class, "usuar");
//		c.add(Restrictions.eq("usuar.id", id));
//		return (Usuario) c.uniqueResult();
//	}
//	
//	/** lista de usuários.
//	 * @param campo de ordenação da lista.
//	 * @return Retorna uma lista de usuarios ativos. */
//	@SuppressWarnings("unchecked")
//	public List<Usuario> listaUsuario( String orderBy ) {
//		Criteria c = this.getSession().createCriteria(Usuario.class, "usuario");
//		c.add(Restrictions.eq("ativo", Boolean.TRUE));
//		
//		c.addOrder(Order.asc(orderBy));
//		
//		List<Usuario> result = (List<Usuario>) c.list();
//		
//		for(Usuario u: result) {
//			u = this.getSession().get(Usuario.class, u.getId());
//			u.getPerfis().size();   // Acessa a coleção para previnir LazyInitializationException
//		}
//		
//		return result;
//	}
//	
//	@SuppressWarnings("unchecked")
//	public List<Integer> permissaoAvaliacao(Long id) {
//		String sql = "select id_usuario from controle_acesso.view_usuario_avaliacao ";
//		Query q = this.getSession().createSQLQuery(sql);
//		return q.list();
//	}

}
