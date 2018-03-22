package arquitetura.utils;

import org.primefaces.context.RequestContext;

public class PrimeFacesUtils {

	
	public static void execute(String cmd) {
		
		RequestContext.getCurrentInstance().execute(cmd);
	}
}
