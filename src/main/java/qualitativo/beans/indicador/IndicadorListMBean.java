package qualitativo.beans.indicador;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import arquitetura.utils.Messages;
import arquitetura.utils.SessionUtils;
import arquitetura.utils.SispcaLogger;
import qualitativo.model.Indicador;
import qualitativo.service.IndicadorService;

@Named
@ViewScoped
public class IndicadorListMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5940179508090218836L;

	public static final String FAIL_DELETE = "Falha inesperada ao tentar Indicador Ação";
	public static final String SUCCESS_DELETE = "Indicador deletado com Sucesso";
	public static final String FAIL_SEARCH = "Falha ao pesquisar Indicadores";
	public static final String  NO_RECORDS="Nenhum Indicador retornada";
	
	private String indicador;
 
	private List<Indicador> listIndicadores;
 
	private IndicadorService service;
	
	private boolean atualizar;
	private boolean deletar;
	private boolean salvar;
	private boolean view;
	
	@Inject
	public IndicadorListMBean(IndicadorService service) {
		this.service = service;
 		
		atualizar = SessionUtils.containsKey("planejamentoQualitativoIndicadorAtualizar"); 
		deletar   = SessionUtils.containsKey("planejamentoQualitativoIndicadorDeletar");
		salvar    = SessionUtils.containsKey("planejamentoQualitativoIndicadorSalvar");
		view      = SessionUtils.containsKey("planejamentoQualitativoIndicadorVizualizar");
		
	}

	public void buscar() {

		try {
			
			listIndicadores = service.buscar(indicador);

			if(listIndicadores.isEmpty()) {
				Messages.addMessageWarn(NO_RECORDS);
			}
			
		} catch (Exception e) {
			SispcaLogger.logError(e.getLocalizedMessage());

			Messages.addMessageError(FAIL_SEARCH);

		}
	}

	public String deletar(Indicador indicador) {

		try {

			service.delete(indicador);

			Messages.addMessageInfo(SUCCESS_DELETE);

		} catch (Exception e) {
			SispcaLogger.logError(e.getLocalizedMessage());

			Messages.addMessageError(FAIL_DELETE);

		}

		buscar();

		return "";

	}

	public String getIndicador() {
		return indicador;
	}

	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

	public List<Indicador> getListIndicadores() {
		return listIndicadores;
	}

	public void setListIndicadores(List<Indicador> listIndicadores) {
		this.listIndicadores = listIndicadores;
	}

	public IndicadorService getService() {
		return service;
	}

	public void setService(IndicadorService service) {
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
