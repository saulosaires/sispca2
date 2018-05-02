package arquitetura.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class PrimeFacesUtils {

	private PrimeFacesUtils() {
	    throw new IllegalStateException("Utility class");
	  }

	
	public static void execute(String cmd) {
		 
		 PrimeFaces.current().executeScript(cmd);
	}
	
	public static StreamedContent converBytesToStreamedContent(byte[] bytes,String contentType, String titulo) { 
		StreamedContent file = null;
		InputStream inputStream = null; 
		inputStream = new ByteArrayInputStream(bytes);
		file = new DefaultStreamedContent(inputStream,contentType, titulo); 
		return file;
	} 
	
}
