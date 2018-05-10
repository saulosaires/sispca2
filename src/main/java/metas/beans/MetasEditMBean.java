package metas.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.TreeMap;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.mail.EmailException;
import org.hibernate.Session;

import administrativo.model.Usuario;
import arquitetura.utils.EmailUtil;
import arquitetura.utils.Messages;
import arquitetura.utils.SessionUtils;
import arquitetura.utils.SispcaLogger;
import arquitetura.utils.Utils;
import metas.enuns.StatusAtividade;
import metas.model.Atividade;
import metas.model.Compromisso;
import metas.model.Status;
import metas.service.AtividadeService;
import metas.service.CompromissoService;
import metas.service.StatusService;

@Named
@ViewScoped
public class MetasEditMBean implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
 	public static final String FAIL_UPDATE = "Falha inesperada ao tentar Atualizar Metas";

 	public static final String SUCCESS_UPDATE= "Metas atualizada com Sucesso";

	
	private Long id;
 
	private Integer atividadeRealizadoAnterior=-1;
	private String  atividadeComentarioAnterior="";
	
	private Atividade atividade;
	 
	private AtividadeService atividadeService;
	private CompromissoService compromissoService;
	private StatusService statusService;
	
	@Inject
	public MetasEditMBean(AtividadeService atividadeService,CompromissoService compromissoService,StatusService statusService) {
		this.atividadeService=atividadeService;
		this.compromissoService=compromissoService;
		this.statusService=statusService;


 
	}
	
	public void init() {

		if (!Utils.invalidId(id)) {

			atividade = atividadeService.findById(id);

			if(atividade!=null && !Utils.invalidId(atividade.getId())) {
				
				atividadeRealizadoAnterior = atividade.getRealizado();
				atividadeComentarioAnterior= atividade.getComentario();
				
			   atividade.setCompromissos(compromissoService.findByAtividade(atividade.getId()));
			   
			   StringJoiner str = new StringJoiner(",");
			   for(Compromisso c : atividade.getCompromissos()) {
				   str.add(c.getCodigo());
			   }
			   atividade.setCodigosCompromissos(str.toString());
			  
			}
			
			
		}

	}
	 
	public String editar() {
		
		try {
		Usuario user = (Usuario) SessionUtils.get("user");
	
		atividade.setUltimaAlteracao(new Date());
		atividade.setUsuarioAlterador(user);

		atualizaStatus();
		enviarEmail();
		
		atividadeService.update(atividade);
		
		Messages.addMessageInfo(SUCCESS_UPDATE);
		
		return "metasAtividadeList";
		
	} catch (Exception e) {
		SispcaLogger.logError(e.getCause().getMessage());

		Messages.addMessageError(FAIL_UPDATE);
	}

	return "";
		
	}
	
	private void atualizaStatus() {
		
		Status status=null;
		
		if(atividade.getRealizado()==null  || atividade.getRealizado() == 0) {
			status= statusService.findById(StatusAtividade.NAO_CUMPRIU.getId());
			 
		}else if(atividade.getRealizado() >= atividade.getPrevisto()) {
			status= statusService.findById(StatusAtividade.CUMPRIU.getId());
		}else {
			status= statusService.findById(StatusAtividade.EM_ANDAMENTO.getId());
		}
		
		
		atividade.setStatus(status);
		
	}
	
	private void enviarEmail() throws EmailException {
 
			if(atividadeRealizadoAnterior.equals(atividade.getRealizado()) && atividadeComentarioAnterior.equals(atividade.getComentario()))
				return;
		
			String assunto = "Alteração em dados do sistema de Metas";
			
			String espaco = "&nbsp;";

			StringBuilder builder = new StringBuilder();

			builder.append("<html>");
			builder.append("<meta charset=\"utf-8\"/>");
			builder.append("<body bgcolor=white>");
			
			builder.append("<p><b>Unidade Orçamentária:</b>" + espaco + atividade.getUnidadeOrcamentaria().getDescricao() +"</p>");
			builder.append("<p><b>Atividade:</b>" + espaco + atividade.getNome()+"</p>");
			builder.append("<p><b>Compromisso:</b>" + espaco + atividade.getCodigosCompromissos() + "</p>");
			builder.append("<p><b>Previsto:</b>" + espaco + "<label>"+ atividade.getPrevisto() +"</label></p>");
			
			builder.append("<p><b>Anterior</p>");
			builder.append("<p><b>Realizado:</b>" + espaco + "<label style=\"color:#FF0000\">"+ atividadeRealizadoAnterior +"</label></p>");
			builder.append("<p><b>Comentário:</b>" + espaco + "<label style=\"color:#FF0000\">"+ atividadeComentarioAnterior +"</label></p>");
			
			builder.append("<p><b>Atual</p>");
			builder.append("<p><b>Realizado:</b>" + espaco + "<label style=\"color:blue\">"+ atividade.getRealizado() +"</label></p>");
			builder.append("<p><b>Comentário:</b>" + espaco + "<label style=\"color:blue\">"+ atividade.getComentario() +"</label></p>");
			
			builder.append("</body>");
			builder.append("</html>");
			
			String mensagem = builder.toString();
			
			List<String> destinatarios = new ArrayList<String>();
			
			destinatarios.add("promunicipios@segov.ma.gov.br");
		
			//destinatarios.add("ricardo.meirino@seati.ma.gov.br");
						
			boolean emailSent = false;
			 
				emailSent = EmailUtil.enviaEmail(assunto, mensagem, destinatarios, null);
			 

 
			if ( emailSent )
				Messages.addMessageInfo("Email enviado para: " + destinatarios);
			else
				Messages.addMessageError("Falha ao tentar enviar email para os seguintes destinatarios: " + destinatarios);
		}
		
	 
	
	
	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
