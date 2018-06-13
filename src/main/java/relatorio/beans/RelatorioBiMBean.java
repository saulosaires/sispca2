package relatorio.beans;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import administrativo.model.Usuario;
import arquitetura.enums.TipoArquivo;
import arquitetura.utils.Messages;
import arquitetura.utils.SessionUtils;
import arquitetura.utils.SispcaLogger;
import arquitetura.utils.Utils;
import relatorio.model.RelatorioBi;
import relatorio.service.RelatorioBiService;

@Named
@ViewScoped
public class RelatorioBiMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String FAIL_REPORT = "Falha inesperada ao tentar Consultar Relatório";
	
	private StreamedContent file;
	
	private RelatorioBi bi;
	
	@Inject
	public RelatorioBiMBean(RelatorioBiService relatorioBiService){
		
		Usuario user= (Usuario) SessionUtils.get(SessionUtils.USER);
		
		if(user.getUnidadeOrcamentaria()!=null && !Utils.invalidId(user.getUnidadeOrcamentaria().getId())) {
			
			List<RelatorioBi> listBi = relatorioBiService.buscarByUnidadeOrcamentaria(user.getUnidadeOrcamentaria().getId());
			
			if(!Utils.emptyList(listBi)) {
				this.bi = listBi.get(0);
				
				try {
					file = new DefaultStreamedContent(new FileInputStream(this.bi.getCaminho()),TipoArquivo.PDF.getId());
				} catch (FileNotFoundException e) {
					SispcaLogger.logError(e);

					Messages.addMessageError(FAIL_REPORT);
				}	
			}
			 
		}
		
		
	}


	public StreamedContent getFile() {
		return file;
	}


	public void setFile(StreamedContent file) {
		this.file = file;
	}


	public RelatorioBi getBi() {
		return bi;
	}


	public void setBi(RelatorioBi bi) {
		this.bi = bi;
	}
	
	
	
	
	
}
