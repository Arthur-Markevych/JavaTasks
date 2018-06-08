package ua.khpi.markevich.Practice3.part4;

import static javax.xml.bind.DatatypeConverter.printHexBinary;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Part4 {

	/**
	 * Private constructor.
	 * 
	 * @throws IllegalAccessError
	 *             if constructor will be invoked
	 */
	private Part4() {
		throw new IllegalAccessError("Tha instance not allowed here");
	}

	/**
	 * Returns password hash.
	 * 
	 * @param password
	 *            specified password
	 * @param algorithm
	 *            algorithm type
	 * @return hash
	 * @throws NoSuchAlgorithmException
	 */
	public static String hash(String password, String algorithm) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance(algorithm);
		digest.update(password.getBytes());
		byte[] hash = digest.digest();
		return printHexBinary(hash);
	}

}
