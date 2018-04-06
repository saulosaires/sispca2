package arquitetura.service;

import java.io.Serializable;
import java.util.List;

import arquitetura.controller.AbstractController;
import arquitetura.exception.JpaException;

public abstract class AbstractService<T extends Serializable> implements Serializable {

	private AbstractController<T> controller;

	public AbstractService(AbstractController<T> controller) {

		this.controller = controller;
	}

	public T findById(Long id) {

		return controller.findById(id);
	}

	public List<T> findAll() {

		return controller.findAll();
	}

	public void create(T t) throws JpaException {

		controller.create(t);
	}

	public T update(T t) throws JpaException {

		return controller.update(t);
	}

	public T delete(T t) throws JpaException {

		return controller.delete(t);
	}

	public AbstractController<T> getController() {
		return controller;
	}

	 
	
	
	
}
