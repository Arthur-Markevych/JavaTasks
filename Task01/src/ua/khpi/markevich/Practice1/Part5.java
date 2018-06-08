package ua.khpi.markevich.Practice1;

/**
 * Practice1 Part5.
 * 
 * @author Art
 *
 */
public final class Part5 {

	public static void main(final String... args) {
		if (args.length > 0) {
			run(args);
		} else {
			System.out.println("no args found");
		}
	}

	private static void run(final String... args) {
		int a = Integer.parseInt(args[0]);
		if (a > 0) {
			int sum = 0;
			String s = String.valueOf(a);
			for (int i = 0; i < s.length(); i++) {
				sum += Integer.parseInt(String.valueOf(s.charAt(i)));
			}
			System.out.println(sum);
		} else {
			System.out.println(0);
		}

	}

	/**
	 * private constructor.
	 * 
	 * @throws IllegalAccessError
	 *             in case constructor will be called
	 */
	private Part5() {
		throw new IllegalAccessError();
	}

}
