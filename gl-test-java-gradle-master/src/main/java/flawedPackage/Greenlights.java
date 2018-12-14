package flawedPackage;
//comment
import java.security.SecureRandom;

public class Greenlights {

	public static void sufficientEntropy_GREENLIGHT() {
		SecureRandom sr = new SecureRandom();
		System.out.println(sr.nextInt());
	}
}
