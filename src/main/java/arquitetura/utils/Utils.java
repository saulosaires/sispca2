package arquitetura.utils;

import java.util.Random;

public class Utils {

	private Utils() {
	    throw new IllegalStateException("Utility class");
	  }

	
	public static int randomNumber() {
			
		return new Random().nextInt();
	}
	
	public static boolean emptyParam(String param) {
		
		return (param==null || "".equals(param));
	}
	
	public static boolean invalidId(Long id) {
		
		return (id==null ||  id<0);
	}
	
	
}
