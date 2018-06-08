package ua.khpi.markevich.Practice5.part2;

import java.io.IOException;

/**
 * Prints to console certain message after defined time.
 * 
 * @author Arthur Markevich
 *
 */
public final class Spam implements Runnable {

	/**
	 * Array of milliseconds to suspend printing messages.
	 */
	private static final long[] SUSPENDS = { 1500, 1000, 2000, 500, 1000, 500, 500 };

	/**
	 * Array of messages.
	 */
	private static final String[] MESSAGES = { "Spam1", "Spam2", "Spam3", "Spam4", "Spam5", "Spam6", "Spam7" };

	/**
	 * Bytes size.
	 */
	private static final int BYTE_SIZE = 10;

	/**
	 * Emergent stop time.
	 */
	private static final int SLEEP = 10_000;

	/**
	 * Application start point.
	 * 
	 * @param args
	 *            is ignored
	 * @throws InterruptedException
	 *             in case of thread exception
	 */
	public static void main(final String[] args) throws InterruptedException {
		Thread thread = new Thread(new Spam());
		thread.setDaemon(true);
		thread.start();

		Thread waitForEnter = new Thread() {
			public void run() {
				byte[] buffer = new byte[BYTE_SIZE];
				int count;
				try {
					do {
						while ((count = System.in.read(buffer)) == -1)
							;
					} while (!System.lineSeparator().equals(new String(buffer, 0, count)));
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				System.out.println("ENTER has been obtained");
			}
		};
		waitForEnter.start();
		// Thread.sleep(SLEEP);
		// waitForEnter.stop();
	}

	/**
	 * Prints messages after according suspends.
	 */
	@Override
	public void run() {
		while (true) {
			for (int i = 0; i < MESSAGES.length; i++) {
				System.out.println(MESSAGES[i] + " sleep " + SUSPENDS[i]);
				try {
					Thread.sleep(SUSPENDS[i]);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

}
