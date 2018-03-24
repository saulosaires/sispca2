package arquitetura.utils;

import org.primefaces.context.RequestContext;

public class PrimeFacesUtils {

	private PrimeFacesUtils() {
	    throw new IllegalStateException("Utility class");
	  }

	
	public static void execute(String cmd) {
		
		RequestContext.getCurrentInstance().execute(cmd);
	}
}
