package arquitetura.utils;

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
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.model.UploadedFile;

public class FileUtil {

	private FileUtil() {
		throw new IllegalStateException("File Utility class");
	}

	private static final String FOLDER = "/sispca-documents";
	private static final String PATH = "/var" + FOLDER;

	public static String uploadArquivo(UploadedFile arquivo) throws IOException {

		String filename = FilenameUtils.getBaseName(arquivo.getFileName());
		String extension = FilenameUtils.getExtension(arquivo.getFileName());

		int number = Utils.randomNumber();

		StringBuilder sb = new StringBuilder("");

		sb = sb.append(PATH).append("/").append(filename).append("_").append(number).append(".").append(extension);

		File file = new File(sb.toString());
		boolean created= file.createNewFile();

		if(!created)return "";
		
		Path path = Paths.get(file.getAbsolutePath());

		try (InputStream input = arquivo.getInputstream()) {
			Files.copy(input, path, StandardCopyOption.REPLACE_EXISTING);
		}

		return file.toString();

	}

	public static String getRealPath(String resource) {

		ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		return sc.getRealPath(resource);

	}

	public static File byteToFile(byte[] bytes, String prefix, String suffix) throws IOException {

		File file = File.createTempFile(prefix, suffix);

		Path path = Paths.get(file.getAbsolutePath());

		Files.write(path, bytes);

		return file;

	}

	public static void sendFileOnResponse(byte[] bytes, String name, String contentType) throws IOException {
		
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
