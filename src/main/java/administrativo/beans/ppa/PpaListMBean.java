package administrativo.beans.ppa;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.model.Ppa;
import administrativo.service.PpaService;
import arquitetura.utils.Messages;
import arquitetura.utils.SessionUtils;
import arquitetura.utils.SispcaLogger;

@Named
@ViewScoped
public class PpaListMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5940179508090218836L;

	public static final String FAIL_DELETE = "Falha inesperada ao tentar Deletar Ppa";
	public static final String SUCCESS_DELETE = "Ppa deletado com Sucesso";
	public static final String FAIL_SEARCH    = "Falha ao pesquisar Ppa";

	public static final String FAIL_ANO_INICIO = "Ano inicio Inválido";
	public static final String FAIL_ANO_FIM = "Ano Fim Inválido";
	
	
	private String sigla;
	private String descricao;
	private Integer anoInicio;
	private Integer anoFim;
	
	private List<Ppa> listPpas;

	private PpaService ppaService;

	private boolean deletar;
	private boolean salvar;
	
	@Inject
	public PpaListMBean(PpaService ppaService) {
		this.ppaService = ppaService;
		 
		deletar   = SessionUtils.containsKey("ppaDeletar");
		salvar    = SessionUtils.containsKey("ppaSalvar");
	}

	public void pesquisar() {
 
		try {
			listPpas = ppaService.queryPpa(sigla, descricao, anoInicio,anoFim);

		} catch (Exception e) {
			SispcaLogger.logError(e.getCause().getMessage());

			Messages.addMessageError(FAIL_SEARCH);

		}
	}

	public String delete(Ppa ppa) {

		try {

			ppaService.delete(ppa);

			pesquisar();

			Messages.addMessageInfo(SUCCESS_DELETE);

		} catch (Exception e) {
			SispcaLogger.logError(e.getCause().getMessage());

			Messages.addMessageError(FAIL_DELETE);

		}

		return "";

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

	public Integer getAnoInicio() {
		return anoInicio;
	}

	public void setAnoInicio(Integer anoInicio) {
		this.anoInicio = anoInicio;
	}

	public Integer getAnoFim() {
		return anoFim;
	}

	public void setAnoFim(Integer anoFim) {
		this.anoFim = anoFim;
	}

	public List<Ppa> getListPpas() {
		return listPpas;
	}

	public void setListPpas(List<Ppa> listPpas) {
		this.listPpas = listPpas;
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

 
	
	
	
}
