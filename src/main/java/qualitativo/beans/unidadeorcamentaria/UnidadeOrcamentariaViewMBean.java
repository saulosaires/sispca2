package qualitativo.beans.unidadeorcamentaria;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import arquitetura.utils.Utils;
import qualitativo.model.UnidadeOrcamentaria;
import qualitativo.service.OrgaoService;
import qualitativo.service.UnidadeOrcamentariaService;

@Named
@ViewScoped
public class UnidadeOrcamentariaViewMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String SUCCESS = "Unidade Orçamentária Atualizar com sucesso";
	public static final String FAIL    = "Falha inesperada ao tentar Atualizar Unidade Orçamentária";

	private Long id;
	
	private UnidadeOrcamentaria unidadeOrcamentaria = new UnidadeOrcamentaria();
 	
	private UnidadeOrcamentariaService service; 
	private OrgaoService orgaoService;

	@Inject
	public UnidadeOrcamentariaViewMBean(UnidadeOrcamentariaService service,OrgaoService orgaoService) {

		this.service = service;
		this.orgaoService=orgaoService;
 
	}

	public void init() {

		if (!Utils.invalidId(id)) {

			unidadeOrcamentaria = service.findById(id);
  			 
			unidadeOrcamentaria.setOrgao(orgaoService.findById(unidadeOrcamentaria.getOrgao().getId()));
			
		}

	}
 
	 

	public UnidadeOrcamentaria getUnidadeOrcamentaria() {
		return unidadeOrcamentaria;
	}

	public void setUnidadeOrcamentaria(UnidadeOrcamentaria unidadeOrcamentaria) {
		this.unidadeOrcamentaria = unidadeOrcamentaria;
	}

	 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

 

	
	
}
