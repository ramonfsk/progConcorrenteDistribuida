package exerc2;

public class Utils {
	public static void validaLong(long num) {
		if(num < 0)
			throw new IllegalArgumentException("Numero invalido!");
	}
}
