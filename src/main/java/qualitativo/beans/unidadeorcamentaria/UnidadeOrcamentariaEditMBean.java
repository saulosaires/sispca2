package qualitativo.beans.unidadeorcamentaria;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.model.Usuario;
import arquitetura.utils.Messages;
import arquitetura.utils.SessionUtils;
import arquitetura.utils.SispcaLogger;
import arquitetura.utils.Utils;
import qualitativo.model.Orgao;
import qualitativo.model.UnidadeOrcamentaria;
import qualitativo.service.OrgaoService;
import qualitativo.service.UnidadeOrcamentariaService;

@Named
@ViewScoped
public class UnidadeOrcamentariaEditMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String SUCCESS = "Unidade Orçamentária Atualizar com sucesso";
	public static final String FAIL    = "Falha inesperada ao tentar Atualizar Unidade Orçamentária";

	private Long id;
	
	private UnidadeOrcamentaria unidadeOrcamentaria = new UnidadeOrcamentaria();

	private List<Orgao> listOrgao;
	
	private UnidadeOrcamentariaService service;
    private OrgaoService orgaoService;
    
	private UnidadeOrcamentariaValidate validate;

	@Inject
	public UnidadeOrcamentariaEditMBean(UnidadeOrcamentariaService service, UnidadeOrcamentariaValidate validate,OrgaoService orgaoService) {

		this.service = service;
		this.orgaoService = orgaoService;
		this.validate = validate; 
		
		Usuario user = (Usuario) SessionUtils.get(SessionUtils.USER);
		
		this.listOrgao = orgaoService.findAllOrderByDescricao(user.getId());

	}

	public void init() {

		if (!Utils.invalidId(id)) {

			unidadeOrcamentaria = service.findById(id);
  			
			unidadeOrcamentaria.setOrgao(new Orgao(unidadeOrcamentaria.getOrgao().getId()));
		}

	}
 
	public String atualizar() {

		try {

			if (!validate.validar(unidadeOrcamentaria)) {
				return "";
			}
 			
			unidadeOrcamentaria.setOrgao(orgaoService.findById(unidadeOrcamentaria.getOrgao().getId()));
			
			unidadeOrcamentaria= service.update(unidadeOrcamentaria);

			Messages.addMessageInfo(SUCCESS);

			return "unidadeOrcamentarioQualitativoList";

		} catch (Exception e) {
			SispcaLogger.logError(e);

			Messages.addMessageError(FAIL);
		}

		return "";
	}

	public UnidadeOrcamentaria getUnidadeOrcamentaria() {
		return unidadeOrcamentaria;
	}

	public void setUnidadeOrcamentaria(UnidadeOrcamentaria unidadeOrcamentaria) {
		this.unidadeOrcamentaria = unidadeOrcamentaria;
	}

	public List<Orgao> getListOrgao() {
		return listOrgao;
	}

	public void setListOrgao(List<Orgao> listOrgao) {
		this.listOrgao = listOrgao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

 

	
	
}
