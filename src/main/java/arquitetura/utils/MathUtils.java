package arquitetura.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtils {

	private MathUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static BigDecimal divide(BigDecimal divider,BigDecimal divisor ) {

		return divider.divide(divisor ,2, RoundingMode.HALF_UP);
		 
	}

	 public static BigDecimal getZeroBigDecimal() {
		 return BigDecimal.valueOf(0.0).setScale(2);
	 }

}
