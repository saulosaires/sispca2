package arquitetura.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/*
 * <h1>DAO Genérico baseado no Hibernate</h1>
 * 	Esta classe consiste em um DAO genérico direcionado para o Hibernate.
 *  Ela contém métodos que interagem com o Banco de Dados através de uma 
 *  Sessão do Hibernate. Esses métodos variam desde consultas pontuais e 
 *  consultas de coleções de dados até operações de persistência de dados
 *  tais como inserção, edição e remoção. 
 *  
 *  
 *  @author	Saul Raposo
 *  @see	Session
 *  @see	Criteria
 */
public class GenericHibernateDao<T> implements IDao<T> {

	private Session session;
	private Class<T> persistentClass;

	/*
	 * <h2>Construtor</h2> <p>O construtor desta classe inicializa o objeto com
	 * uma Sessão do Hibernate e uma Classe referente ao Model que sera
	 * persistido/consultado. Além disso, é passado na construção a classe que
	 * será utilizada nos métodos genéricos.</p> <p>Exemplo:
	 * GenericHibernateDao<Model> dao = new
	 * GenericHibernateDao<Model>(HibernateUtil.getSession(), Model.class)</p>
	 * 
	 * @param session Session - Sessão do Hibernate construída externamente
	 * utilizando a classe HibernateUtil ou outra similar
	 * 
	 * @param persistentClass Class - Classe referente ao Model que será
	 * persistido/consultado. É passado na forma Model.class
	 */
	public GenericHibernateDao(Session session, Class<T> persistentClass) {
		this.session = session;
		this.persistentClass = persistentClass;
	}

	/*
	 * Este método realiza uma consulta na Sessão Hibernate, ou seja, sem
	 * acessar o Banco de Dados, e retorna um objeto "proxy" do Hibernate.
	 * 
	 * @param id Serializable - Chave primária da Entidade a ser Localizada.
	 * 
	 * @return T - Proxy de uma Classe Genérica T definida na construção de um
	 * objeto desta classe.
	 */
	public T load(Serializable id) {

		T castObj = (T) getSession().load(persistentClass, id);
		return castObj;
	}

	/*
	 * Este método realiza uma consulta direta no Banco de Dados e retorna um
	 * objeto real que representa uma linha no bd, não um "proxy" do Hibernate.
	 * 
	 * @param id Serializable - Chave primária da Entidade a ser Localizada.
	 * 
	 * @return T - Proxy de uma Classe Genérica T definida na construção de um
	 * objeto desta classe.
	 */
	public T get(Serializable id) {

		T castObj = (T) getSession().get(persistentClass, id);
		return castObj;
	}

	/*
	 * Métodos de persistência
	 */

	public void saveOrUpdate(T t) throws Exception {
		getSession().saveOrUpdate(t);
	}

	/*
	 * Salva direto no banco ignorando transação
	 */
	public void save(T obj) throws Exception {
		getSession().save(obj);
	}

	/*
	 * Similar ao save. Insere no persistent context Somente dentro de
	 * transações
	 */
	public void persist(T obj) throws Exception {
		getSession().persist(obj);
	}

	public void update(T obj) throws Exception {
		getSession().update(obj);
	}

	public void merge(T obj) throws Exception {
		getSession().merge(obj);
	}

	public void evict(T obj) throws Exception {
		getSession().evict(obj);
	}

	public void delete(T obj) throws Exception {
		getSession().delete(obj);
	}

	public List<T> findAll() {
		Criteria c = createCriteria();

		@SuppressWarnings("unchecked")
		List<T> result = (List<T>) c.list();
		return result;
	}

	public List<T> findAll(int size) {
		Criteria c = createCriteria();
		c.setMaxResults(size);
		
		@SuppressWarnings("unchecked")
		List<T> result = (List<T>) c.list();
		return result;
	}

	public List<T> findAll(String orderBy) {
		Criteria c = createCriteria();
		c.addOrder(Order.asc(orderBy));
		
		@SuppressWarnings("unchecked")
		List<T> result = (List<T>) c.list();
		return result;
	}

	public List<T> findAllDesc(String orderBy) {
		Criteria c = createCriteria();
		c.addOrder(Order.desc(orderBy));
		
		@SuppressWarnings("unchecked")
		List<T> result = (List<T>) c.list();
		return result;
	}

	public T findById(Long id) {
		Criteria c = createCriteria();
		c.add(Restrictions.eq("id", id));
		
		@SuppressWarnings("unchecked")
		T castObj = (T) c.uniqueResult();
		return castObj;
	}

	protected Criteria createCriteria() {
		return getSession().createCriteria(persistentClass);
	}

	protected Query createQuery(String sql) {
		return getSession().createQuery(sql);
	}

	/*
	 * Método que inicia a transação da session
	 */
	protected Transaction begin() {
		return getSession().beginTransaction();
	}

	/*
	 * Método que realiza o commit da transação da session
	 */
	protected void commit() {
		getSession().getTransaction().commit();
	}

	/*
	 * Método que realiza o rollback da transação da session
	 */
	protected void rollback() {
		getSession().getTransaction().rollback();
	}

	public Session getSession() {
		return this.session;
	}

	public void close() {
		getSession().close();
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}
	
}
