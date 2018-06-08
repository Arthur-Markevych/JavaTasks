package ua.khpi.markevich.Practice4.Part4;

import java.io.FileNotFoundException;

/**
 * Prints to console separated sentences from file.
 * 
 * @author Arthur Markevich
 *
 */
public final class Part4 {

	/**
	 * Path to file for parsing.
	 */
	private static final String FILE_NAME = "part4.txt";

	/**
	 * File encoding.
	 */
	private static final String ENCODING = "Cp1251";

	/**
	 * Application start point.
	 * 
	 * @param args
	 *            ignored by program
	 * @throws FileNotFoundException
	 *             if file doesn't exists
	 */
	public static void main(final String[] args) throws FileNotFoundException {

		Parser parser = new Parser(FILE_NAME, ENCODING);
		for (String str : parser) {
			System.out.println(str);
		}
	}

	/**
	 * Private constructor.
	 * 
	 * @throws IllegalAccessError
	 *             if constructor will be invoked
	 */
	private Part4() {
		throw new IllegalAccessError();
	}

}
