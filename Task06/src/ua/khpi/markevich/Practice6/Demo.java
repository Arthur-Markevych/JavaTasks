package ua.khpi.markevich.Practice6;

import ua.khpi.markevich.Practice6.part1.Part1;
import ua.khpi.markevich.Practice6.part2.Part2;
import ua.khpi.markevich.Practice6.part3.Part3;
import ua.khpi.markevich.Practice6.part4.Part4;
import ua.khpi.markevich.Practice6.part5.Part5;
import ua.khpi.markevich.Practice6.part6.Part6;
import ua.khpi.markevich.Practice6.part7.Part7;

/**
 * Demonstrate work of Practice 6.
 *
 */
public final class Demo {

	/**
	 * Application start point.
	 * 
	 * @param args
	 *            ignored by application
	 * @throws Exception
	 */
	public static void main(final String[] args) {
		System.out.println("~~~~~~~~~~~~Part1");
		Part1.main(args);

		System.out.println("~~~~~~~~~~~~Part2");
		Part2.main(args);

		System.out.println("~~~~~~~~~~~~Part3");
		Part3.main(args);

		System.out.println("~~~~~~~~~~~~Part4");
		Part4.main(args);

		System.out.println("~~~~~~~~~~~~Part5");
		Part5.main(args);

		System.out.println("~~~~~~~~~~~~Part6");
		Part6.main(new String[] { "-i", "input.txt", "-t", "frequency" });
		Part6.main(new String[] { "-i", "input.txt", "-t", "length" });
		Part6.main(new String[] { "-i", "input.txt", "-t", "duplicates" });

		System.out.println("~~~~~~~~~~~~Part7");
		Part7.main(args);
	}

	/**
	 * Private constructor.
	 */
	private Demo() {
		// TODO Auto-generated constructor stub
	}

}
