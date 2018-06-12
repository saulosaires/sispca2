package arquitetura.service;

import java.io.Serializable;
import java.util.List;

import arquitetura.dao.AbstractDAO;
import arquitetura.exception.JpaException;

public abstract class AbstractService<T extends Serializable> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AbstractDAO<T> dao;

	public AbstractService(AbstractDAO<T> dao) {

		this.dao = dao;
	}

	public T findById(Long id) {

		return dao.findOne(id);
	}

	public List<T> findAll() {

		return dao.findAll();
	}

	public T create(T t) throws JpaException {

		return dao.create(t);
	}

	public T update(T t) throws JpaException {

		return dao.update(t);
	}

	public T delete(T t) throws JpaException {

		return dao.delete(t);
	}

	protected AbstractDAO<T> getDAO() {
		return dao;
	}

	 
	
	
	
}
