package administrativo.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import administrativo.dao.AcessoViewDAO;
import administrativo.model.AcessoView;

public class AcessoViewController implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9133992896387359248L;
	private AcessoViewDAO dao;
	
	@Inject 
	AcessoViewController(AcessoViewDAO dao){
		this.dao=dao;
	}
	
	public List<AcessoView> findAll(){
		
		return dao.findAll();
	}
	
 
	
}
