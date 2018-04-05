package arquitetura.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

 
public class RelatorioUtil {
	
	public static final String ENCODING="UTF-8";
	public static final String FILE_TYPE_PDF="application/pdf";
	public static final String FILE_TYPE_XLS="application/xls";
	
	private RelatorioUtil() {
	    throw new IllegalStateException("Utility class");
	}

	
	public static StreamedContent converBytesToStreamedContent(byte[] bytes,String titulo) { 
		StreamedContent file = null;	
		InputStream inputStream = null; 
		inputStream = new ByteArrayInputStream(bytes);
		file = new DefaultStreamedContent(inputStream,FILE_TYPE_PDF, titulo, ENCODING); 
		return file;
	} 
	
	public static StreamedContent converBytesToStreamedContent(byte[] bytes,String titulo,TipoArquivo tipoArquivo) { 
		StreamedContent file = null;
		InputStream inputStream = null; 
		inputStream = new ByteArrayInputStream(bytes);
		if(tipoArquivo.equals(TipoArquivo.PDF))
			file = new DefaultStreamedContent(inputStream,FILE_TYPE_PDF, titulo,ENCODING); 
		if(tipoArquivo.equals(TipoArquivo.XLS))
			file = new DefaultStreamedContent(inputStream, FILE_TYPE_XLS, titulo, ENCODING);
		return file;
	}   
	
	public static String getDataHoraAtual() { 
		
		LocalDateTime now = LocalDateTime.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		
		return  now.format(formatter);
	}
	
	public static void mostraNaPagina(byte[] bytes,String titulo,TipoArquivo tipoArquivo) throws IOException {

		HttpServletResponse response = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();

		if (bytes != null && bytes.length > 0) {

			if(tipoArquivo.equals(TipoArquivo.PDF))
				response.setContentType(FILE_TYPE_PDF);
			if(tipoArquivo.equals(TipoArquivo.XLS))
				response.setContentType(FILE_TYPE_XLS);

			response.setHeader("Content-Disposition","inline; filename=\"" + titulo + "\"");

			response.setContentLength(bytes.length);

			ServletOutputStream outputStream = response.getOutputStream();

			outputStream.write(bytes, 0, bytes.length);

			outputStream.flush();

			outputStream.close();

		}
	}

}
