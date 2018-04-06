package qualitativo.controller;

import java.util.List;

import javax.inject.Inject;

import arquitetura.controller.AbstractController;
import qualitativo.dao.AcaoDAO;
import qualitativo.model.Acao;

public class AcaoController  extends AbstractController<Acao>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1344916015518730440L;
 
	@Inject
	public AcaoController(AcaoDAO acaoDAO) {

		super(acaoDAO);
	}

 
	
	public List<Acao> buscar(String codigo, String denominacao,Long unidadeOrcamentariaId,Long programaId){
		
		return ((AcaoDAO) getDao()).buscar(codigo, denominacao, unidadeOrcamentariaId, programaId);
	}
	

}
