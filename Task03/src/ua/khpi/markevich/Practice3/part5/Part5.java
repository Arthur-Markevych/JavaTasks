package ua.khpi.markevich.Practice3.part5;

public final class Part5 {

	private static final int MAX_INPUT = 100;

	private static final int[] NUMBER_VALUES = { 100, 90, 50, 40, 10, 9, 5, 4, 1 };

	private static final String[] NUMBER_LETTERS = { "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

	/**
	 * Print to the console <tt>i</tt> as <tt>i</tt> ====> <tt>i</tt>(roman) ====>
	 * <tt>i</tt>(dec).
	 * 
	 * @param i
	 */
	public static void output(int i) {
		String roman = decimal2Roman(i);
		System.out.println(i + " ====> " + roman + " ====> " + roman2Decimal(roman));
	}

	public static int roman2Decimal(String roman) {
		roman = roman.toUpperCase();
		for (int i = 0; i < NUMBER_VALUES.length; i++) {
			if (roman.startsWith(NUMBER_LETTERS[i])) {
				return NUMBER_VALUES[i] + roman2Decimal(roman.replaceFirst(NUMBER_LETTERS[i], ""));
			}
		}
		return 0;
	}

	public static String decimal2Roman(int num) {
		if (num > MAX_INPUT || num < 1) {
			throw new IllegalArgumentException("The number must be less or greater than 1000 " + "number: " + num);
		}
		StringBuilder roman = new StringBuilder("");
		for (int i = 0; i < NUMBER_VALUES.length; i++) {
			while (num >= NUMBER_VALUES[i]) {
				roman.append(NUMBER_LETTERS[i]);
				num -= NUMBER_VALUES[i];
			}
		}
		return roman.toString();
	}

	/**
	 * private constructor.
	 * 
	 * @throws IllegalAccessError
	 *             if constructor will be invoked
	 */
	private Part5() {
		throw new IllegalAccessError("Tha instance not allowed here");
	}

}
