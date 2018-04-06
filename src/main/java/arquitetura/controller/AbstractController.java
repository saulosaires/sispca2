package arquitetura.controller;

import java.io.Serializable;
import java.util.List;

import arquitetura.dao.AbstractDAO;
import arquitetura.exception.JpaException;

public abstract class AbstractController<T extends Serializable> implements Serializable {

	private AbstractDAO<T> dao;

	public AbstractController(AbstractDAO<T> dao) {

		this.dao = dao;
	}

	public T findById(Long id) {

		return dao.findOne(id);
	}

	public List<T> findAll() {

		return dao.findAll();
	}

	public void create(T t) throws JpaException {

		dao.create(t);
	}

	public T update(T t) throws JpaException {

		return dao.update(t);
	}

	public T delete(T t) throws JpaException {

		return dao.delete(t);
	}

	public AbstractDAO<T> getDao() {
		return dao;
	}
	
	
	
}
