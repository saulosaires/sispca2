package arquitetura.dao;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

 
public class HibernateUtil {

	private static SessionFactory sessionFactory;
	private static ThreadLocal<Session> threadLocalSession = new ThreadLocal<Session>();
	private static ThreadLocal<StatelessSession> threadLocalStatelessSession = new ThreadLocal<StatelessSession>();

	public static SessionFactory getSessionFactory() {

		if (sessionFactory == null) {
			StandardServiceRegistryBuilder standardServRegBuilder = new StandardServiceRegistryBuilder();

			ServiceRegistry serviceRegistry = standardServRegBuilder.configure( "hibernate.cfg.xml" ).build();

			MetadataSources metadataSources = new MetadataSources(serviceRegistry);
			MetadataBuilder metadataBuilder = metadataSources.getMetadataBuilder();
			Metadata metadata = metadataBuilder.build();
			
			SessionFactoryBuilder sessionFactoryBuilder = metadata.getSessionFactoryBuilder();
		//LogInterceptor logInterceptor = new LogInterceptor();
		//	sessionFactoryBuilder.applyInterceptor(logInterceptor);
			
			sessionFactory = sessionFactoryBuilder.build();
			
		}

		return sessionFactory;
	}
	
	public static Session getSession() {
		
		Session session = threadLocalSession.get();
		
		if (session == null || !session.isOpen()) {
			session = getSessionFactory().openSession();
			threadLocalSession.set(session);
		}
		
		return session;
		
	}

	public static StatelessSession getStatelessSession() {

		StatelessSession session = threadLocalStatelessSession.get();

		if (session == null) {
			session = getSessionFactory().openStatelessSession();
			threadLocalStatelessSession.set(session);
		}

		return session;

	}

	public static void closeSession() {
		if (getSession() != null && getSession().isOpen()) {
			getSession().close();
			threadLocalSession.set(null);
		}
	}
	public static void closeStatelessSession() {
		if (getStatelessSession() != null ) {
			getStatelessSession().close();
			threadLocalStatelessSession.set(null);
		}
	}

	public static Transaction beginTransaction() {
		return getSession().beginTransaction();
	}

	public static void commitTransaction() {
		getSession().getTransaction().commit();
	}

	public static void rollBackTransaction() {
		getSession().getTransaction().rollback();
	}

	public static void debugEntityKeys() {
		Set<?> set = getSession().getStatistics().getEntityKeys();

		for (Object element : set) {
			System.out.println(element.toString());
		}
	}

}
