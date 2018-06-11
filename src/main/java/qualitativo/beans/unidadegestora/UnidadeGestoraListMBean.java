package qualitativo.beans.unidadegestora;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.model.Usuario;
import arquitetura.utils.Messages;
import arquitetura.utils.SessionUtils;
import arquitetura.utils.SispcaLogger;
import qualitativo.model.UnidadeGestora;
import qualitativo.model.UnidadeOrcamentaria;
import qualitativo.service.UnidadeGestoraService;
import qualitativo.service.UnidadeOrcamentariaService;

@Named
@ViewScoped
public class UnidadeGestoraListMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5940179508090218836L;

	public static final String FAIL_DELETE 	  = "Falha inesperada ao tentar deletar Unidade Gestora";
	public static final String SUCCESS_DELETE = "Unidade Gestora deletada com Sucesso";
	public static final String FAIL_SEARCH 	  = "Falha ao pesquisar Unidade Gestora";
	public static final String  NO_RECORDS	  = "Nenhuma Unidade Gestora retornada";
	
	private String codigo;
	private String descricao;
	private String sigla;
	private Long unidadeOrcamentaria;
 
 
	 
	private List<UnidadeOrcamentaria> listUnidadeOrcamentaria;
	
	private List<UnidadeGestora> listUnidadeGestora;
 
	private UnidadeGestoraService unidadeGestoraService;
 	
	private boolean atualizar;
	private boolean deletar;
	private boolean salvar;
	private boolean view;
	
	@Inject
	public UnidadeGestoraListMBean(UnidadeGestoraService unidadeGestoraService, UnidadeOrcamentariaService unidadeOrcamentariaService) {
		
		this.unidadeGestoraService	 = unidadeGestoraService;

		Usuario user = (Usuario) SessionUtils.get(SessionUtils.USER);
		
		listUnidadeOrcamentaria = unidadeOrcamentariaService.findAllOrderByDescricao(user.getId());
		
 		
		atualizar = SessionUtils.containsKey("planejamentoQualitativoUnidadeGestoraAtualizar"); 
		deletar   = SessionUtils.containsKey("planejamentoQualitativoUnidadeGestoraDeletar");
		salvar    = SessionUtils.containsKey("planejamentoQualitativoUnidadeGestoraSalvar");
		view      = SessionUtils.containsKey("planejamentoQualitativoUnidadeGestoraVisualizar");
		
	}

	public void buscar() {

		try {
			
			listUnidadeGestora = unidadeGestoraService.buscar(codigo, descricao, sigla, unidadeOrcamentaria);

			if(listUnidadeGestora.isEmpty()) {
				Messages.addMessageWarn(NO_RECORDS);
			}
			
		} catch (Exception e) {
			SispcaLogger.logError(e);

			Messages.addMessageError(FAIL_SEARCH);

		}
	}

	public String deletar(UnidadeGestora unidadeGestora) {

		try {

			unidadeGestoraService.delete(unidadeGestora);

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Long getUnidadeOrcamentaria() {
		return unidadeOrcamentaria;
	}

	public void setUnidadeOrcamentaria(Long unidadeOrcamentaria) {
		this.unidadeOrcamentaria = unidadeOrcamentaria;
	}

	public List<UnidadeOrcamentaria> getListUnidadeOrcamentaria() {
		return listUnidadeOrcamentaria;
	}

	public void setListUnidadeOrcamentaria(List<UnidadeOrcamentaria> listUnidadeOrcamentaria) {
		this.listUnidadeOrcamentaria = listUnidadeOrcamentaria;
	}

	public List<UnidadeGestora> getListUnidadeGestora() {
		return listUnidadeGestora;
	}

	public void setListUnidadeGestora(List<UnidadeGestora> listUnidadeGestora) {
		this.listUnidadeGestora = listUnidadeGestora;
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
