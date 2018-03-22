package arquitetura.utils;

import org.jboss.logging.Logger;

public class SispcaLogger {

	private final static Logger logger = Logger.getLogger(SispcaLogger.class.getName());
	
	
	public static void logError(String msg) {
		logger.error(msg);		
	}

	public static void logWarn(String msg) {
		logger.warn(msg);		
	}

	
}
