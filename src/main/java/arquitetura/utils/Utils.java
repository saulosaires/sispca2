package arquitetura.utils;

import java.util.Random;

public class Utils {

	private Utils() {
		throw new IllegalStateException("Utility class");
	}

	public static int randomNumber() {

		return new Random().nextInt(999999 - 100000) + 100000;
	}

	public static boolean emptyParam(String param) {

		return (param == null || "".equals(param));
	}

	public static boolean invalidId(Long id) {

		return (id == null || id <= 0);
	}

	public static boolean invalidYear(Integer year) {

		return (year == null || year < 0);
	}

	public static boolean invalidYear(String year) {

		try {
			int y = Integer.parseInt(year);

			return invalidYear(y);

		} catch (Exception e) {
			return false;
		}

	}

}
