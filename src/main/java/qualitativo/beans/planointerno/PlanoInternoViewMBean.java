package qualitativo.beans.planointerno;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import arquitetura.utils.Utils;
import qualitativo.model.Acao;
import qualitativo.model.PlanoInterno;
import qualitativo.service.AcaoService;
import qualitativo.service.PlanoInternoService;

@Named
@ViewScoped
public class PlanoInternoViewMBean implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

   
	private Long id;
	
	private PlanoInterno planoInterno = new PlanoInterno();
	
	private PlanoInternoService service; 
	private AcaoService acaoService;
	
	private List<Acao> listAcao;
	
	@Inject
	public PlanoInternoViewMBean(PlanoInternoService service,AcaoService acaoService ) {
		
		this.service = service;
		this.acaoService=acaoService;
	}

	public void init() {

		if (!Utils.invalidId(id)) {

			planoInterno = service.findById(id);
			
			if(planoInterno.getAcao()!=null && !Utils.invalidId(planoInterno.getAcao().getId())) {
				planoInterno.setAcao(acaoService.findById(planoInterno.getAcao().getId()));
			}

		}

	}
 

	public PlanoInterno getPlanoInterno() {
		return planoInterno;
	}

	public void setPlanoInterno(PlanoInterno planoInterno) {
		this.planoInterno = planoInterno;
	}

	public List<Acao> getListAcao() {
		return listAcao;
	}

	public void setListAcao(List<Acao> listAcao) {
		this.listAcao = listAcao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
 

 
 
	
 
}
