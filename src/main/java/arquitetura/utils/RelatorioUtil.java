package arquitetura.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import arquitetura.enums.TipoArquivo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

 
public class RelatorioUtil {
	
	public static final String ENCODING="UTF-8";
	public static final String FILE_TYPE_PDF="application/pdf";
	public static final String FILE_TYPE_XLS="application/xls";
	
	private RelatorioUtil() {
	    throw new IllegalStateException("Utility class");
	}

	
	public static byte[] exportReportToPdf(JasperPrint jasperRelatorio) throws JRException {
		
		return JasperExportManager.exportReportToPdf(jasperRelatorio);
		
	}
	
	public static byte[] exportReportToXLS(JasperPrint jasperRelatorio) throws JRException, IOException {
		
		 ByteArrayOutputStream output = new ByteArrayOutputStream();  
         JRXlsExporter exporterXLS = new JRXlsExporter(); 
	
         exporterXLS.setExporterInput(new SimpleExporterInput(jasperRelatorio));            
         exporterXLS.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
         
         SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
         configuration.setRemoveEmptySpaceBetweenRows(true);
         configuration.setDetectCellType(true);
         configuration.setWhitePageBackground(false);
         exporterXLS.setConfiguration(configuration);
         exporterXLS.exportReport();    
         byte[]  bytes = output.toByteArray();
         output.close();
		
		return bytes;
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
	
	public static void mostraNaPagina(byte[] bytes,String titulo,String tipoArquivo) throws IOException {

		HttpServletResponse response = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();

		if (bytes != null && bytes.length > 0) {

			response.setContentType(tipoArquivo);
			 
			response.setHeader("Content-Disposition","inline; filename=\"" + titulo + "\"");

			response.setContentLength(bytes.length);

			ServletOutputStream outputStream = response.getOutputStream();

			outputStream.write(bytes, 0, bytes.length);

			outputStream.flush();

			outputStream.close();

		}
	}

}
