package arquitetura.utils;

public class JPAUtil {

	public static boolean validId(Long id) {

		return (id != null && id > 0) ? true : false;
	}

}
