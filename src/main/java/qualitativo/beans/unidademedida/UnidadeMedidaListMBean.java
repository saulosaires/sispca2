package qualitativo.beans.unidademedida;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import arquitetura.utils.Messages;
import arquitetura.utils.SessionUtils;
import arquitetura.utils.SispcaLogger;
import qualitativo.model.UnidadeMedida;
import qualitativo.service.UnidadeMedidaService;

@Named
@ViewScoped
public class UnidadeMedidaListMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5940179508090218836L;

	public static final String FAIL_DELETE 	  = "Falha inesperada ao tentar deletar Unidade de Medida";
	public static final String SUCCESS_DELETE = "Unidade de Medida deletada com Sucesso";
	public static final String FAIL_SEARCH 	  = "Falha ao pesquisar Unidade de Medida";
	public static final String  NO_RECORDS	  = "Nenhuma Unidade de Medida retornada";
	
	private String descricao;
	private String sigla; 
  
	
	private List<UnidadeMedida> listUnidadeMedida;
	

 
	private UnidadeMedidaService unidadeMedidaService;
 	
	private boolean atualizar;
	private boolean deletar;
	private boolean salvar;
	private boolean view;
	
	@Inject
	public UnidadeMedidaListMBean(UnidadeMedidaService unidadeMedidaService) {
		
		this.unidadeMedidaService	 = unidadeMedidaService;
 		
 		
		atualizar = SessionUtils.containsKey("planejamentoQualitativoUnidadeMedidaAtualizar"); 
		deletar   = SessionUtils.containsKey("planejamentoQualitativoUnidadeMedidaDeletar");
		salvar    = SessionUtils.containsKey("planejamentoQualitativoUnidadeMedidaSalvar");
		view      = SessionUtils.containsKey("planejamentoQualitativoUnidadeMedidaVisualizar");
		
	}

	public void buscar() {

		try {
			
			listUnidadeMedida = unidadeMedidaService.buscar(sigla, descricao);

			if(listUnidadeMedida.isEmpty()) {
				Messages.addMessageWarn(NO_RECORDS);
			}
			
		} catch (Exception e) {
			SispcaLogger.logError(e.getCause().getMessage());

			Messages.addMessageError(FAIL_SEARCH);

		}
	}

	public String deletar(UnidadeMedida unidadeMedida) {

		try {

			unidadeMedidaService.delete(unidadeMedida);

			Messages.addMessageInfo(SUCCESS_DELETE);

		} catch (Exception e) {
			SispcaLogger.logError(e.getLocalizedMessage());

			Messages.addMessageError(FAIL_DELETE);

		}

		buscar();

		return "";

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

	public List<UnidadeMedida> getListUnidadeMedida() {
		return listUnidadeMedida;
	}

	public void setListUnidadeMedida(List<UnidadeMedida> listUnidadeMedida) {
		this.listUnidadeMedida = listUnidadeMedida;
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
