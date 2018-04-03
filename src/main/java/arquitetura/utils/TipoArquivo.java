package arquitetura.utils;

import java.util.HashMap;

public enum TipoArquivo {

	PDF("application/pdf"),

	XLS("application/pdf");

	 

	private final String contentType;

	private TipoArquivo(String contentType) {
		this.contentType = contentType;
	}

	public String getId() {
		return contentType;
	}
}
