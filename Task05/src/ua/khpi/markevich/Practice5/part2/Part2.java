package ua.khpi.markevich.Practice5.part2;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Imitation of pressing enter to console after 5 sec.
 *
 */
public final class Part2 {
	/**
	 * Time (milliseconds) sleep before sending enter.
	 */
	private static final int SLEEP = 5000;

	/**
	 * Application start point.
	 * 
	 * @param args
	 *            is ignored
	 * @throws InterruptedException
	 *             thread exception
	 */
	public static void main(final String[] args) throws InterruptedException {
		// save standard input
		InputStream stdIn = System.in;

		ByteArrayInputStream bais = new ByteArrayInputStream(System.lineSeparator().getBytes());

		// move internal pointer of input stream to the end of input
		bais.skip(System.lineSeparator().length());

		// assign new value of standard input
		System.setIn(bais);

		// main functionality
		Spam.main(args);

		// wait for 5 sec
		Thread.sleep(SLEEP);

		// move internal pointer to begin of input
		bais.reset();

		// restore standard input
		System.setIn(stdIn);
	}

	/**
	 * Private constructor.
	 */
	private Part2() {
		/* private constructor */
	}

}
