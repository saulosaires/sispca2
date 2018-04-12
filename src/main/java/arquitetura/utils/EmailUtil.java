package arquitetura.utils;

import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailConstants;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class EmailUtil {

	// CONSTANTES PRIVADAS
	/** O email utilizado como remetente da mensagem. */
	private static final String EMAIL_REMETENTE = "naoresponda@seati.ma.gov.br";
	/** A senha utilizada para autenticação com o servidor de email. */
	private static final String EMAIL_SENHA = "n@0r3sp0nd@"; 
	/** O host utilizado para autenticação do email. */
	private static final String EMAIL_HOST = "correio.ma.gov.br";
	/** A porta SSL-SMTP utilizada para autenticação do remetente. */
	private static final int EMAIL_SSL_SMTP_PORT = 587;
	
	
	private  EmailUtil() {
		 throw new IllegalStateException("Utility class");
	}
	
	public static HtmlEmail conectaEmail() {

		HtmlEmail email = new HtmlEmail();
		email.setHostName(EMAIL_HOST);
 
		email.setSmtpPort(EMAIL_SSL_SMTP_PORT);
		email.setSSLCheckServerIdentity(true);
		email.setStartTLSEnabled(true);
		email.setAuthenticator(new DefaultAuthenticator(EMAIL_REMETENTE, EMAIL_SENHA));
		email.setCharset(EmailConstants.ISO_8859_1);
		
		return email;
	}

	
	public static boolean enviaEmail(String assunto, String mensagem, List<String> destinatarios, List<EmailAttachment> anexos) throws EmailException {

		HtmlEmail email = conectaEmail();
		
		try {
			email.setFrom(EMAIL_REMETENTE);
			email.setSubject(assunto);

			email.setHtmlMsg(mensagem);

			// Adicionar todos os anexos ao email
			if ( anexos != null )
				for ( EmailAttachment curAnexo : anexos )
					email.attach(curAnexo);

			// Adicionar destinatários
			for ( String curDestinatario : destinatarios )
				email.addTo( curDestinatario );

			// Enviar o email!
			email.send();
			return true;
		} catch (EmailException e) {
			SispcaLogger.logError(e.getCause().getMessage()); 
			 
		}
		
		return false;
	}
	
}
