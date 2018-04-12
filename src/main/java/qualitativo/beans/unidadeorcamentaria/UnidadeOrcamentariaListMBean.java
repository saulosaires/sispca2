package qualitativo.beans.unidadeorcamentaria;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import arquitetura.utils.Messages;
import arquitetura.utils.SessionUtils;
import arquitetura.utils.SispcaLogger;
import qualitativo.model.Orgao;
import qualitativo.model.UnidadeOrcamentaria;
import qualitativo.service.OrgaoService;
import qualitativo.service.UnidadeOrcamentariaService;

@Named
@ViewScoped
public class UnidadeOrcamentariaListMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5940179508090218836L;

	public static final String FAIL_DELETE 	  = "Falha inesperada ao tentar deletar Unidade Orcamentaria";
	public static final String SUCCESS_DELETE = "Unidade Orcamentaria deletada com Sucesso";
	public static final String FAIL_SEARCH 	  = "Falha ao pesquisar Unidade Orcamentaria";
	public static final String  NO_RECORDS	  = "Nenhuma Unidade Orcamentaria retornada";
	
	private String descricao;
	private String codigo;
	private Long orgaoId;
    private List<Orgao> listOrgao;
	
	private List<UnidadeOrcamentaria> listUnidadeOrcamentaria;

	
	private UnidadeOrcamentariaService service;
 	
	private boolean atualizar;
	private boolean deletar;
	private boolean salvar;
	private boolean view;
	
	@Inject
	public UnidadeOrcamentariaListMBean(UnidadeOrcamentariaService  service, OrgaoService orgaoService) {
		
		this.service   = service;
		this.listOrgao = orgaoService.findAllOrderByDescricao();
 		
		atualizar = SessionUtils.containsKey("planejamentoQualitativoUnidadeOrcamentariaAtualizar"); 
		deletar   = SessionUtils.containsKey("planejamentoQualitativoUnidadeOrcamentariaDeletar");
		salvar    = SessionUtils.containsKey("planejamentoQualitativoUnidadeOrcamentariaSalvar");
		view      = SessionUtils.containsKey("planejamentoQualitativoUnidadeOrcamentariaVisualizar");
		
	}

	
	public void buscar() {

		try {
			
			listUnidadeOrcamentaria = service.buscar(codigo, descricao, orgaoId);

			if(listUnidadeOrcamentaria.isEmpty()) {
				Messages.addMessageWarn(NO_RECORDS);
			}
			
		} catch (Exception e) {
			SispcaLogger.logError(e.getCause().getMessage());

			Messages.addMessageError(FAIL_SEARCH);

		}
	}

	public String deletar(UnidadeOrcamentaria unidadeOrcamentaria) {

		try {

			service.delete(unidadeOrcamentaria);

			Messages.addMessageInfo(SUCCESS_DELETE);

		} catch (Exception e) {
			SispcaLogger.logError(e.getCause().getMessage());

			Messages.addMessageError(FAIL_DELETE);

		}

		buscar();

		return "";

	}

	
	
	public List<Orgao> getListOrgao() {
		return listOrgao;
	}

	public void setListOrgao(List<Orgao> listOrgao) {
		this.listOrgao = listOrgao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Long getOrgaoId() {
		return orgaoId;
	}

	public void setOrgaoId(Long orgaoId) {
		this.orgaoId = orgaoId;
	}

	public List<UnidadeOrcamentaria> getListUnidadeOrcamentaria() {
		return listUnidadeOrcamentaria;
	}

	public void setListUnidadeOrcamentaria(List<UnidadeOrcamentaria> listUnidadeOrcamentaria) {
		this.listUnidadeOrcamentaria = listUnidadeOrcamentaria;
	}

	public boolean isAtualizar() {
		return atualizar;
	}

	public void setAtualizar(boolean atualizar) {
		this.atualizar = atualizar;
	}

	public boolean isDeletar() {
		return deletar;
	}

	public void setDeletar(boolean deletar) {
		this.deletar = deletar;
	}

	public boolean isSalvar() {
		return salvar;
	}

	public void setSalvar(boolean salvar) {
		this.salvar = salvar;
	}

	public boolean isView() {
		return view;
	}

	public void setView(boolean view) {
		this.view = view;
	}

	 
	 
	
}
