package administrativo.beans.usuario;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.StreamedContent;

import administrativo.model.Exercicio;
import administrativo.model.Usuario;
import administrativo.service.ExercicioService;
import administrativo.service.UserService;
import arquitetura.utils.FileUtil;
import arquitetura.utils.Messages;
import arquitetura.utils.PrimeFacesUtils;
import arquitetura.utils.SispcaLogger;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
 

@Named
@SessionScoped
public class UserReportMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3470732997866746354L;

	private static final String FAIL="Error ao gerar gelatorio";
	private static final String NO_USERS="Não há usuários cadastrados";
  
	private UserService userService;
	private ExercicioService exercicioService;

	@Inject
	public UserReportMBean(UserService userService,ExercicioService exercicioService) {
		this.userService = userService;
		this.exercicioService=exercicioService;
		 
	}

	
	public StreamedContent getFile() {

		try{
			
			
			Exercicio exercicio=null;
			Optional<Exercicio> ex = exercicioService.exercicioVigente();
			
			if(ex.isPresent()) {
				exercicio = ex.get();
			}else {
				Messages.addMessageError(FAIL);
				return null;
			}
	 	 
	 
			Map<String, Object> parameters = new HashMap<>();
			
			String brasaoMa=FileUtil.getRealPath("/resources/images/brasao_ma.png");
			
			parameters.put("param_imagem", brasaoMa);
			parameters.put("param_exercicio",exercicio.getAno().toString());
					
			String report=FileUtil.getRealPath("/relatorios/usuario/relatorio_usuario.jasper");
	
			List<Usuario> listUsers = userService.findAll();
	 
		 
			if(listUsers==null || listUsers.isEmpty()) {
				Messages.addMessageWarn(NO_USERS);
				return null;
			}
			
			 
			  JasperPrint relatorio = JasperFillManager.fillReport(report, parameters,new JRBeanCollectionDataSource(listUsers) );
			 
			  byte[] bytes = JasperExportManager.exportReportToPdf(relatorio);
			  
			  
			  return  PrimeFacesUtils.converBytesToStreamedContent(bytes,"application/pdf" ,"Relatorio_Usuarios_SISPCA.pdf");
			   
			 
		} catch (Exception e) {
			SispcaLogger.logError(e.getCause().getMessage());

			Messages.addMessageError(FAIL);
		}
	
 
		return null;
	}

 
 

	
	
}
