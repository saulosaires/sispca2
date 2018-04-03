package arquitetura.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

 
public class RelatorioUtil {
	
	private RelatorioUtil() {
	    throw new IllegalStateException("Utility class");
	}

	
	public static StreamedContent converBytesToStreamedContent(byte[] bytes,String titulo) { 
		StreamedContent file = null;
		InputStream inputStream = null; 
		inputStream = new ByteArrayInputStream(bytes);
		file = new DefaultStreamedContent(inputStream, "application/pdf", titulo, "UTF-8"); 
		return file;
	} 
	
	public static StreamedContent converBytesToStreamedContent(byte[] bytes,String titulo,TipoArquivo tipoArquivo) { 
		StreamedContent file = null;
		InputStream inputStream = null; 
		inputStream = new ByteArrayInputStream(bytes);
		if(tipoArquivo.equals(TipoArquivo.PDF))
			file = new DefaultStreamedContent(inputStream, "application/pdf", titulo, "UTF-8"); 
		if(tipoArquivo.equals(TipoArquivo.XLS))
			file = new DefaultStreamedContent(inputStream, "application/xls", titulo, "UTF-8");
		return file;
	}   
	
	public static String DataHoraAtual() { 
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","PT"));

		Calendar data = Calendar.getInstance();
		int hora = data.get(Calendar.HOUR_OF_DAY);
		int min = data.get(Calendar.MINUTE);
		int seg = data.get(Calendar.SECOND);

		NumberFormat nf = new DecimalFormat("00");

		String HORA = String.valueOf(nf.format(hora)) + ":"
					+ String.valueOf(nf.format(min)) + ":"
					+ String.valueOf(nf.format(seg)) + "";
		String DIA = df.format(new Date());
		return DIA + " " + HORA;
	}
	
	public static void mostraNaPagina(byte[] bytes,String titulo,TipoArquivo tipoArquivo) throws IOException {

		HttpServletResponse response = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();

		if (bytes != null && bytes.length > 0) {

			if(tipoArquivo.equals(TipoArquivo.PDF))
				response.setContentType("application/pdf");
			if(tipoArquivo.equals(TipoArquivo.XLS))
				response.setContentType("application/xls");

			response.setHeader("Content-Disposition", 
					"inline; filename=\"" + titulo + "\"");

			response.setContentLength(bytes.length);

			ServletOutputStream outputStream = response.getOutputStream();

			outputStream.write(bytes, 0, bytes.length);

			outputStream.flush();

			outputStream.close();

		}
	}

}
