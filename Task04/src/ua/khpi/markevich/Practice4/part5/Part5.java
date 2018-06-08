package ua.khpi.markevich.Practice4.part5;

import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Application read localization key from console and prints accorded value.
 * 
 * @author Arthur Markevich
 *
 */
public final class Part5 {

	/**
	 * Base name of resource files.
	 */
	private static final String BASE_NAME = "resources";

	/**
	 * File encoding.
	 */
	private static final String ENCODING = "Cp1251";

	/**
	 * Locale.
	 */
	private static Locale locale;

	/**
	 * ResourceBundle.
	 */
	private static ResourceBundle bundle;

	/**
	 * Application start point.
	 * 
	 * @param args
	 *            ignored by program
	 * @throws FileNotFoundException
	 *             if file doesn't exists
	 */
	public static void main(final String[] args) {
		Scanner in = new Scanner(System.in, ENCODING);
		String input = in.nextLine();
		String[] split = input.split(" ");
		System.out.println(getData(split[0], split[1]));

		while (true) {
			input = in.nextLine();
			if (!input.equals("stop")) {
				String[] wSplit1 = input.split(" ");
				System.out.println(getData(wSplit1[0], wSplit1[1]));
			} else {
				break;
			}
		}
		in.close();
	}

	/**
	 * Find value by key from certain localization.
	 * 
	 * @param key
	 *            key to find
	 * @param language
	 *            language of key value
	 * @return founded value
	 */
	public static String getData(final String key, final String language) {
		locale = new Locale(language);
		try {
			bundle = ResourceBundle.getBundle(BASE_NAME, locale);
			return bundle.getString(key);
		} catch (MissingResourceException e) {
			System.out.println("input data is incorrect: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	/**
	 * Private constructor.
	 */
	private Part5() {
		/* private constructor */
	}

}
