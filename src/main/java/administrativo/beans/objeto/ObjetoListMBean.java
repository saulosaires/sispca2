package administrativo.beans.objeto;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.model.Permissao;
import administrativo.service.PermissaoService;
import arquitetura.utils.Messages;
import arquitetura.utils.SispcaLogger;

@Named
@ViewScoped
public class ObjetoListMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5940179508090218836L;

	public static final String FAIL_DELETE = "Falha inesperada ao tentar Deletar Permisão";
	public static final String SUCCESS_DELETE = "Permisão deletado com Sucesso";
	public static final String FAIL_SEARCH    = "Falha ao pesquisar Permisão";
 
 
	private String busca;
 	
	private List<Permissao> listPermissao;

	private PermissaoService permissaoService;
	 
	
	@Inject
	public ObjetoListMBean(PermissaoService permissaoService) {
		this.permissaoService = permissaoService;
	 
	}

	public void pesquisar() {
 
		try {
			listPermissao = permissaoService.buscaPermissao(busca);

		} catch (Exception e) {
			SispcaLogger.logError(e.getLocalizedMessage());

			Messages.addMessageError(FAIL_SEARCH);

		}
	}

	public String deletar(Permissao permissao) {

		try {

			permissaoService.delete(permissao);

			pesquisar();

			Messages.addMessageInfo(SUCCESS_DELETE);

		} catch (Exception e) {
			SispcaLogger.logError(e.getLocalizedMessage());

			Messages.addMessageError(FAIL_DELETE);

		}

		return "";

	}
 

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

	public List<Permissao> getListPermissao() {
		return listPermissao;
	}

	public void setListPermissao(List<Permissao> listPermissao) {
		this.listPermissao = listPermissao;
	}

 
	
	
	
}
