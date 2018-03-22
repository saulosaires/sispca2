package arquitetura.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FormatoUtils {
	
	private static Locale sm_currentLocale = new Locale("pt", "BR");

	public static String formataData(Date data) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", sm_currentLocale);
		return format.format(data);
	}

	public static String formataValorMonetario(BigDecimal value) {
		return NumberFormat.getCurrencyInstance(sm_currentLocale).format(value);
	}

	public static String formataQuantidade(BigInteger value) {
		return NumberFormat.getNumberInstance(sm_currentLocale).format(value);
	}

	public static String formataPorcentagem(BigDecimal value) {
		NumberFormat f = NumberFormat.getPercentInstance( sm_currentLocale );
		f.setMinimumFractionDigits(2);
		f.setMaximumFractionDigits(2);
		return f.format(value);
	}
	
	public static String formataValorDecimal( BigDecimal value ) {
		NumberFormat f = NumberFormat.getInstance(sm_currentLocale);
		f.setMinimumFractionDigits(2);
		f.setMaximumFractionDigits(2);
		f.setGroupingUsed(true);
		f.setMinimumIntegerDigits(1);
		return f.format(value);
	}

	/** Substitui todos os caracteres acentuados (incluindo vogais acentuadas e letras como 'ç') por suas versões
	 * sem acentuação.
	 * @param value A string cujos caracteres acentuados devem ser removidos.
	 * @return Retorna uma string representando o valor passado sem acentuação. */
	public static String removeAcentos( String value ) {
		return Normalizer.normalize(value, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
	
}
