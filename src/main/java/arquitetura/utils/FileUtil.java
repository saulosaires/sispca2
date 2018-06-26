package arquitetura.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

public class FileUtil {

	private FileUtil() {
		throw new IllegalStateException("File Utility class");
	}

	private static final String FOLDER = "sispca-documents";
	 
	
	public static String uploadArquivo( byte[] content,String mime, String filename,String extension) throws IOException {

	
 
		String realPath = getRealPath("/");
		
		StringBuilder sb = new StringBuilder("");
		StringBuilder filePath = new StringBuilder("");
		
		filePath.append(File.separator).append(FOLDER).append(File.separator).append(filename).append(".").append(extension);
		sb = sb.append(realPath).append(filePath.toString());

		File file = new File(sb.toString());
		boolean created= file.createNewFile();
 		
		if(!created)return "";
		
		Path path = Paths.get(file.getAbsolutePath());

		
		try (InputStream input = new ByteArrayInputStream(content)) {
			Files.copy(input, path, StandardCopyOption.REPLACE_EXISTING);
		}

		return getUrl()+filePath.toString() ;
		
		 

	}

	public static byte[] inputStreamToByte(InputStream input) throws IOException {
 
		return IOUtils.toByteArray(input);
		 
		
	}
	
	
	public static String getRealPath(String resource) {

		ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		return sc.getRealPath(resource);

	}
	
	public static String getUrl() {

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		
		String uri = request.getRequestURI();
		
		String url = request.getRequestURL().toString();
	
		String ctxPath = request.getContextPath();
	
		url = url.replaceFirst(uri, "");
	
		url = url + ctxPath;

		return  url;

	}

	public static File byteToFile(byte[] bytes, String prefix, String suffix) throws IOException {

		File file = File.createTempFile(prefix, suffix);

		Path path = Paths.get(file.getAbsolutePath());

		Files.write(path, bytes);

		return file;

	}
	
	public static void sendFileOnResponseAttached(byte[] bytes, String name, String contentType) throws IOException {
		
		if (bytes != null && bytes.length>0){
		
			FacesContext context = FacesContext.getCurrentInstance();
			
			HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();	    
			
			response.setContentType(contentType);				
			response.setHeader("Content-disposition", "attachment; filename="+name);
			response.setContentLength(bytes.length);
			ServletOutputStream outputStream = response.getOutputStream();				
			outputStream.write(bytes, 0, bytes.length);
			outputStream.flush();
			outputStream.close();
			
			context.responseComplete(); 	
	   }
		
	}

	

}
