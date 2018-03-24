package arquitetura.utils;

import java.util.Random;

public class Utils {

	private Utils() {
	    throw new IllegalStateException("Utility class");
	  }

	
	public static int randomNumber() {
			
		return new Random().nextInt();
	}
	
}
