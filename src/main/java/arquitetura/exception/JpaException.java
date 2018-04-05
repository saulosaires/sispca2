package arquitetura.exception;

public class JpaException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3476771874038529437L;

	public JpaException(String message, Throwable cause) {
        super(message, cause);
        
    }
}
