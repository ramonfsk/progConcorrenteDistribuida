import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Utils {
	public static void validaInt(int num) {
		if(num < 0)
			throw new IllegalArgumentException("Numero invalido!");
	}
	
	public static int randInt(int min, int max) {
		Random rand = null;
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
	
	public static int randIntNew(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
}