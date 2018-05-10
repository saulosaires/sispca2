package metas.dao;

import arquitetura.dao.AbstractDAO;
import metas.model.Status;

public class StatusDAO extends AbstractDAO< Status >  {

	
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StatusDAO() {
		setClazz(Status.class );
	 
	}

}
