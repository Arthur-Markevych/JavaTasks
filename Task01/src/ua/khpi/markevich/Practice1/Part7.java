package ua.khpi.markevich.Practice1;

public final class Part7 {

	private final static int ALPHABET_SIZE = 26;
	private final static int CHAR_OFFSET = 64;

	public static void main(String[] args) {
		System.out.println(digits2chars(Integer.parseInt(args[0])));
		System.out.println(chars2digits(args[1]));
		System.out.println(rightColumn(args[2]));
		// System.out.println(chars2digits("abc"));
		// System.out.println(digits2chars(731));

	}

	public static int chars2digits(String number) {
		if (number == null || number.isEmpty())
			throw new IllegalArgumentException();
		String chars = number.toUpperCase();
		int[] res = new int[chars.length()];
		res[0] = chars.charAt(0) - CHAR_OFFSET;

		for (int i = 1; i < chars.length(); i++) {
			res[i - 1] = res[i - 1] * ALPHABET_SIZE;
			res[i] = res[i - 1] + chars.charAt(i) - CHAR_OFFSET;
		}

		return res[res.length - 1];
	}

	public static String digits2chars(int number) {
		if (number < 0)
			throw new IllegalArgumentException();
		StringBuffer chars = new StringBuffer();
		StringBuffer charsResult = new StringBuffer();
		int module;
		int dividend = number;
		while (dividend != 0) {
			module = dividend % ALPHABET_SIZE;
			if (module == 0) {
				chars.append("Z");
				dividend = (dividend - 1) / ALPHABET_SIZE;
			} else {
				chars.append(Character.valueOf((char) (module + CHAR_OFFSET)));
				dividend = (dividend - module) / ALPHABET_SIZE;
			}
		}
		for (int i = 0; i < chars.length(); i++) {
			charsResult.append(chars.charAt(chars.length() - i - 1));
		}
		return charsResult.toString();
	}

	public static String rightColumn(String number) {
		if (number == null || number.isEmpty())
			throw new IllegalArgumentException();
		String rightColumn = "";
		int num;
		num = chars2digits(number);
		rightColumn = digits2chars(num + 1);
		return rightColumn;
	}

}
