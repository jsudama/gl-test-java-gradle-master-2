package flawedPackage; 
 
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Random; //comment
//comment
    
@SuppressWarnings("unused")
public class Flawed {


	public static final String PASSWORD = "pass";
	static byte[] dec;

	public static void main(String[] args) throws Exception {
		osCommandInjection_78(args[0]);
		improperResourceShutdown(args[0]);
		sqlInjection(args[0]);
		insufficientEntropy_311();
		greenlight_311();
	}

	public static void improperResourceShutdown(String taintedString) throws SQLException {
		java.sql.Connection conn = java.sql.DriverManager.getConnection(taintedString);
		java.sql.Statement statement = conn.createStatement(1004, 1007);
	}

	public static void sqlInjection(String taintedString) throws SQLException {
		try (java.sql.Connection conn = java.sql.DriverManager.getConnection(taintedString)) {
			java.sql.Statement statement = conn.createStatement(1004, 1007);
			statement.executeQuery(
					"SELECT * FROM user_system_data WHERE user_name = '" + taintedString + "'")
					.toString();
		}
	}

	public static void osCommandInjection_78(String taintedString) throws IOException {
		java.lang.Runtime.getRuntime().exec(taintedString);
	}

	public static String hardCodedPassword() {
		final String password = "pass";
		return password;
	}

	public static void insufficientEntropy_311() throws NoSuchAlgorithmException {
		System.out.println(new Random().nextInt());
		MessageDigest mdBadMD = MessageDigest.getInstance("MD5");
		MessageDigest mdBadSHA1 = MessageDigest.getInstance("SHA-1");
		MessageDigest mdGoodSHA256 = MessageDigest.getInstance("SHA-256");
		MessageDigest mdGoodSHA384 = MessageDigest.getInstance("SHA-384");
		MessageDigest mdGoodSHA512 = MessageDigest.getInstance("SHA-512");
	}

	public static void inadequateEncryptionStrength_326(byte[] salt) throws Exception {
		javax.crypto.spec.PBEParameterSpec ps = new javax.crypto.spec.PBEParameterSpec(salt, 20);
	}

	public static void greenlight_311() {
		SecureRandom sr = new SecureRandom();
		System.out.println(sr.nextInt());
	}
}
