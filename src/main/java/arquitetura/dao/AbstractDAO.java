package arquitetura.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public abstract class AbstractDAO< T extends Serializable > {
	 
	   private Class< T > clazz;
	 
	  
	   public static final EntityManager entityManager;
	   
	   static {
		   entityManager=Persistence.createEntityManagerFactory("sispca2").createEntityManager();
	   }
	 
	   public final void setClazz( Class< T > clazzToSet ){
	      this.clazz = clazzToSet;
	   }
	 
	   public T findOne( long id ){
	      return entityManager.find( clazz, id );
	   }
	  @SuppressWarnings("unchecked")
	  public List< T > findAll(){
	      return entityManager.createQuery( "from " + clazz.getName() ).getResultList();
	   }
	 
	   public void create( T entity ){
	      entityManager.persist( entity );
	   }
	 
	   public T update( T entity ){
		   EntityTransaction t=null;
		   try {
			   t = entityManager.getTransaction();
			   t.begin();
		       entityManager.merge( entity );
		       entityManager.flush();
		       t.commit();
		       return entity;
		   }catch (Exception e) {
			   
			   if(t!=null)
	    	      t.rollback();
			   
	    	     throw new RuntimeException(e.getMessage());
	    	}
		 
	   }
	 
	   public void delete( T entity ){
	      entityManager.remove( entity );
	   }
	   public void deleteById( long entityId ){
	      T entity = findOne( entityId );
	      delete( entity );
	   }

 
	   
	   
	   
	}
