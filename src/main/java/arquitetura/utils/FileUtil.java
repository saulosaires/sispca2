package arquitetura.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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

		int number=Utils.randomNumber();
		
		StringBuilder sb = new StringBuilder("");
		
		sb=sb.append(PATH).append("/").append(filename).append("_").append(number).append(".").append(extension);
		
		File file= new File(sb.toString());
		     file.createNewFile();
 
		     Path path = Paths.get(file.getAbsolutePath());
		     
		try (InputStream input = arquivo.getInputstream()) {
			Files.copy(input, path, StandardCopyOption.REPLACE_EXISTING);
		}

		return file.toString();

	}

}
