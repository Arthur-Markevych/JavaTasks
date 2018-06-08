package ua.khpi.markevich.Practice4;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import ua.khpi.markevich.Practice4.Part4.Part4;
import ua.khpi.markevich.Practice4.part1.Part1;
import ua.khpi.markevich.Practice4.part2.Part2;
import ua.khpi.markevich.Practice4.part3.Part3;
import ua.khpi.markevich.Practice4.part5.Part5;

/**
 * Practice 4.
 * 
 * @author Arthur Markevich
 *
 */
public final class Demo {

	/**
	 * Takes <tt>System.in</tt>.
	 */
	private static final InputStream STD_IN = System.in;

	/**
	 * Default encoding.
	 */
	private static final String ENCODING = "Cp1251";

	/**
	 * Application start point.
	 * 
	 * @param args
	 *            is ignored
	 * @throws IOException
	 *             in case of problem of reading file
	 */
	public static void main(final String[] args) throws IOException {
		System.out.println("=========================== PART1");
		Part1.main(args);
		System.out.println("=========================== PART2");
		Part2.main(args);
		System.out.println("=========================== PART3");
		// set the mock input
		System.setIn(new ByteArrayInputStream("char\nString\nint\ndouble".getBytes(ENCODING)));
		Part3.main(args);
		// restore the standard input
		System.setIn(STD_IN);
		System.out.println("=========================== PART4");
		Part4.main(args);
		System.out.println("=========================== PART5");
		// set the mock input
		System.setIn(new ByteArrayInputStream("table ru\ntable en\napple ru\nstop".getBytes(ENCODING)));
		Part5.main(args);
		// restore the standard input
	}

	/**
	 * Private constructor.
	 */
	private Demo() {
		/* private constructor */
	}
}
