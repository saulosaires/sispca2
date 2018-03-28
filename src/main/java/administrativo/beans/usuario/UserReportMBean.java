package administrativo.beans.usuario;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.primefaces.model.StreamedContent;

import administrativo.model.Exercicio;
import administrativo.model.Usuario;
import administrativo.service.UserService;
import arquitetura.utils.Messages;
import arquitetura.utils.SispcaLogger;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
 

@Named
@ViewScoped
public class UserReportMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3470732997866746354L;

	private static final String FAIL="Error ao gerar gelatorio";
	
	private StreamedContent file;

	private List<Usuario> listaUsuarios;

	private UserService service;

	@Inject
	public UserReportMBean(UserService service) {
		this.service = service;
	}

	public void imprimirUsuarios() {

	 
		 
		this.exercicioSelecionado = new ExercicioDao(HibernateUtil.getSession(), Exercicio.class).retornaExercicioVigente();
		try(Session hiSession = HibernateUtil.getSession()){
			Map<String, Object> parameters = new HashMap<String, Object>();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ServletContext sc = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();
			String imagem = sc.getRealPath("/images/brasao_ma.png");
			parameters.put("param_imagem", imagem);
			parameters.put("param_exercicio", this.exercicioSelecionado.getAno().toString());
			String caminho = null;
			caminho = sc.getRealPath("/relatorios/usuario/relatorio_usuario.jasper");
			JasperPrint preencher = JasperFillManager.fillReport(caminho, parameters, ((SessionImpl) hiSession).connection());
			if (preencher.getPages().size()==0){
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Não há usuários cadastrados", null));
				return null;
			}
			
			if (tipoArquivo.equals(TipoArquivoID.PDF)){
				byte[] bytes = JasperExportManager.exportReportToPdf(preencher);
				if (bytes != null && bytes.length > 0) {
					this.file = RelatorioUtil.converBytesToStreamedContent(bytes,"RelatorioUsuariosSISPCA.pdf");
					return "/views/private/relatorios/relatoriousuario/view.jsf?faces-redirect=true";
				}
			}else if (tipoArquivo.equals(TipoArquivoID.XLS)) {
				ByteArrayOutputStream output = new ByteArrayOutputStream();  
                JRXlsExporter xls = new JRXlsExporter();  
  
                xls.setExporterInput(new SimpleExporterInput(preencher));
                xls.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
                SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
                configuration.setIgnoreCellBorder(Boolean.FALSE);
                configuration.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
                configuration.setIgnoreCellBackground(Boolean.FALSE);
                xls.setConfiguration(configuration);
 
                xls.exportReport();  
                byte[] bytes = output.toByteArray();                 
                this.file = RelatorioUtil.converBytesToStreamedContent(bytes, "RelatorioUsuariosSISPCA.xls", tipoArquivo);                
     
			}
			facesContext.responseComplete();
		} catch (Exception e) {
			SispcaLogger.logError(e.getLocalizedMessage());

			Messages.addMessageError(FAIL);
		}
		 		
	
	}

}
