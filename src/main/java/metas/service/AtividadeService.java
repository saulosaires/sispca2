package metas.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.mail.EmailException;

import arquitetura.service.AbstractService;
import arquitetura.utils.EmailUtil;
import metas.dao.AtividadeDAO;
import metas.model.Atividade;

public class AtividadeService extends AbstractService<Atividade> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3288174422611664897L;
 

	@Inject
	public AtividadeService(AtividadeDAO dao) {
		super(dao);
	}
 
	public List<Atividade> findByNomeAndUnidadeOrcamentaria(String nome, Long unidadeOrcamentaria){
		
		return ((AtividadeDAO)getDAO()).findByNomeAndUnidadeOrcamentaria(nome, unidadeOrcamentaria);
	}

	
	public boolean enviarEmailAlteracaoMetas(Integer atividadeRealizadoAnterior,String  atividadeComentarioAnterior, Atividade atividade,List<String> destinatarios) throws EmailException {
		
		if(atividadeRealizadoAnterior.equals(atividade.getRealizado()) && atividadeComentarioAnterior.equals(atividade.getComentario()))
			return false;
	
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
			
			
	
			return EmailUtil.enviaEmail(assunto, mensagem, destinatarios, null);		
		
		
	}
	
}
