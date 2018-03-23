package arquitetura.utils;

public class JPAUtil {
 
	private  JPAUtil() {
		 throw new IllegalStateException("Utility class");

	}
	
	public static boolean validId(Long id) {

		return (id != null && id > 0) ? true : false;
	}

}
