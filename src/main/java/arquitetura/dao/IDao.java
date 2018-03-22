package arquitetura.dao;

import java.io.Serializable;
import java.util.List;

public interface IDao<T> {
	T load(Serializable id) throws Exception;
	T get(Serializable id) throws Exception;
	void saveOrUpdate(T t) throws Exception;
	void delete(T t) throws Exception;
	
	List<T> findAll();
	List<T> findAll(int size);
	List<T> findAll(String orderBy);
	List<T> findAllDesc(String orderBy);
	T findById(Long id);
	
}
