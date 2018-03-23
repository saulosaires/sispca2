package arquitetura.utils;

import org.jboss.logging.Logger;

public class SispcaLogger {

	private SispcaLogger() {
		 throw new IllegalStateException("Utility class");
	}
	
	private static final  Logger logger = Logger.getLogger(SispcaLogger.class.getName());
	
	
	public static void logError(String msg) {
		logger.error(msg);		
	}

	public static void logWarn(String msg) {
		logger.warn(msg);		
	}

	
}
