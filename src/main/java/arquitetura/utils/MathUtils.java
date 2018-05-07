package arquitetura.utils;

import java.math.BigDecimal;
import java.math.MathContext;
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

	 
	 
	 public static BigDecimal multiply(BigDecimal divider,BigDecimal divisor ) {
		 
		 return divider.multiply(divisor, new MathContext(16,RoundingMode.HALF_EVEN));
		 
	 }
	 
}
