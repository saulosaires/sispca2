package qualitativo.beans.planointerno;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import arquitetura.utils.Messages;
import arquitetura.utils.SessionUtils;
import arquitetura.utils.SispcaLogger;
import qualitativo.model.PlanoInterno;
import qualitativo.service.PlanoInternoService;

@Named
@ViewScoped
public class PlanoInternoListMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5940179508090218836L;

	public static final String FAIL_DELETE = "Falha inesperada ao tentar deletar PlanoInterno";
	public static final String SUCCESS_DELETE = "PlanoInterno deletado com Sucesso";
	public static final String FAIL_SEARCH = "Falha ao pesquisar PlanoInterno";
	public static final String  NO_RECORDS="Nenhum PlanoInterno retornado";
	
	private String codigo;
 
	private List<PlanoInterno> listPlanoInterno;
 
	private PlanoInternoService service;
	
	private boolean atualizar;
	private boolean deletar;
	private boolean salvar;
	private boolean view;
	
	@Inject
	public PlanoInternoListMBean(PlanoInternoService service) {
		this.service = service;
 		
		atualizar = SessionUtils.containsKey("planejamentoQualitativoPlanoInternoAtualizar"); 
		deletar   = SessionUtils.containsKey("planejamentoQualitativoPlanoInternoDeletar");
		salvar    = SessionUtils.containsKey("planejamentoQualitativoPlanoInternoSalvar");
		view      = SessionUtils.containsKey("planejamentoQualitativoPlanoInternoVisualizar");
		
	}

	public void buscar() {

		try {
			
			listPlanoInterno = service.buscar(codigo);

			if(listPlanoInterno.isEmpty()) {
				Messages.addMessageWarn(NO_RECORDS);
			}
			
		} catch (Exception e) {
			SispcaLogger.logError(e.getCause().getMessage());

			Messages.addMessageError(FAIL_SEARCH);

		}
	}

	public String deletar(PlanoInterno planoInterno) {

		try {

			service.delete(planoInterno);

			Messages.addMessageInfo(SUCCESS_DELETE);

		} catch (Exception e) {
			SispcaLogger.logError(e.getCause().getMessage());

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

	public List<PlanoInterno> getListPlanoInterno() {
		return listPlanoInterno;
	}

	public void setListPlanoInterno(List<PlanoInterno> listPlanoInterno) {
		this.listPlanoInterno = listPlanoInterno;
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
