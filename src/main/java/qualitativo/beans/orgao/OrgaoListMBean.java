package qualitativo.beans.orgao;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.model.Usuario;
import arquitetura.utils.Messages;
import arquitetura.utils.SessionUtils;
import arquitetura.utils.SispcaLogger;
import qualitativo.model.Orgao;
import qualitativo.service.OrgaoService;

@Named
@ViewScoped
public class OrgaoListMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5940179508090218836L;

	public static final String FAIL_DELETE 	  = "Falha inesperada ao tentar Indicador Orgão";
	public static final String SUCCESS_DELETE = "Orgão deletado com Sucesso";
	public static final String FAIL_SEARCH 	  = "Falha ao pesquisar Orgão";
	public static final String NO_RECORDS	  = "Nenhum Orgão retornada";
	
	private String codigo;
	private String sigla;
	private String descricao;
 
	private List<Orgao> listOrgoes;
 
	private OrgaoService service;
	
	private boolean atualizar;
	private boolean deletar;
	private boolean salvar;
	private boolean view;
	
	@Inject
	public OrgaoListMBean(OrgaoService service) {
		this.service = service;
 		
		atualizar = SessionUtils.containsKey("planejamentoQualitativoOrgaoAtualizar"); 
		deletar   = SessionUtils.containsKey("planejamentoQualitativoOrgaoDeletar");
		salvar    = SessionUtils.containsKey("planejamentoQualitativoOrgaoSalvar");
		view      = SessionUtils.containsKey("planejamentoQualitativoOrgaoVisualizar");
		
	}

	public void buscar() {

		try {
			
			Usuario user = (Usuario) SessionUtils.get(SessionUtils.USER);
			
			listOrgoes = service.buscar(user.getId(),codigo, sigla, descricao);

			if(listOrgoes.isEmpty()) {
				Messages.addMessageWarn(NO_RECORDS);
			}
			
		} catch (Exception e) {
			SispcaLogger.logError(e);

			Messages.addMessageError(FAIL_SEARCH);

		}
	}

	public String deletar(Orgao orgao) {

		try {

			service.delete(orgao);

			Messages.addMessageInfo(SUCCESS_DELETE);

		} catch (Exception e) {
			SispcaLogger.logError(e);

			Messages.addMessageError(FAIL_DELETE);

		}

		buscar();

		return "";

	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Orgao> getListOrgoes() {
		return listOrgoes;
	}

	public void setListOrgoes(List<Orgao> listOrgoes) {
		this.listOrgoes = listOrgoes;
	}

	public OrgaoService getService() {
		return service;
	}

	public void setService(OrgaoService service) {
		this.service = service;
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
