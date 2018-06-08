package ua.khpi.markevich.Practice5.part5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Note!!! Without synchronization this application not work properly!! Most
 * likely a runtime exception will be thrown..
 * 
 */
public final class Part51 {

	/**
	 * Count of alphabet letters to generate line id.
	 */
	private static final int ALPHABET_COUNT = 26;

	/**
	 * Count of iterations.
	 */
	private static final int ITERATION_NUMBER = 3;

	/**
	 * Count of readers.
	 */
	private static final int READERS_NUMBER = 3;

	/**
	 * Reader monitor.
	 */
	private static final Object READER_MONITOR = new Object();

	/**
	 * Writer monitor.
	 */
	private static final Object WRITER_MONITOR = new Object();

	/**
	 * Shared resource (not thread-safe!!!).
	 */
	private static final StringBuilder BUFFER = new StringBuilder();

	/**
	 * Buffer length.
	 */
	private static final int BUFFER_LENGTH = 5;

	/**
	 * Speed parameter.
	 */
	private static final int PAUSE = 5;

	/**
	 * Thread sleep time.
	 */
	private static final int PAUSE_2 = 10;

	/**
	 * Flag to check read status.
	 */
	private static boolean isRead = false;

	/**
	 * Flag to check write status.
	 */
	private static boolean isWrite = false;

	/**
	 * Flag to stop.
	 */
	private static boolean stop;

	/**
	 * Reader class.
	 *
	 */
	private static class Reader extends Thread {
		/**
		 * Read data from <tt>BUFFER</tt> 3 times after writer wrote one line.
		 * 
		 */
		public void run() {
			while (!stop) {
				synchronized (READER_MONITOR) {
					try {
						if (isWrite) {
							read(getName());
						}
						READER_MONITOR.wait();
						synchronized (WRITER_MONITOR) {
							WRITER_MONITOR.notify();
							isRead = true;
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * Writer class.
	 *
	 */
	private static class Writer extends Thread {
		/**
		 * Write data to <tt>BUFFER</tt> and print it to console and then wait while
		 * reader will read it 3 times.
		 */
		public void run() {
			int tact = 0;
			while (!stop) {
				try {
					synchronized (READER_MONITOR) {
						write();
						isWrite = true;
						READER_MONITOR.notifyAll();
					}
					synchronized (WRITER_MONITOR) {
						while (!isRead) {
							WRITER_MONITOR.wait();
						}
					}
					Thread.sleep(PAUSE);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					synchronized (READER_MONITOR) {
						if (++tact == ITERATION_NUMBER) {
							stop = true;
							READER_MONITOR.notifyAll();
						}
					}
				}
			}
		}
	}

	/**
	 * Read data from <tt>BUFFER</tt> and write it to console with
	 * <tt>CurrentThread</tt> name.
	 * 
	 * @param threadName
	 *            current thread name
	 * @throws InterruptedException
	 *             in case of thread exception
	 */
	private static void read(final String threadName) throws InterruptedException {
		System.out.printf("Reader %s:", threadName);
		for (int j = 0; j < BUFFER_LENGTH; j++) {
			Thread.sleep(PAUSE);
			System.out.print(BUFFER.charAt(j));
		}
		System.out.println();
		Thread.sleep(PAUSE);
	}

	/**
	 * Write data to <tt>BUFFER</tt>.
	 * 
	 * @throws InterruptedException
	 *             in case of thread exception
	 */
	private static void write() throws InterruptedException {
		// clear buffer
		BUFFER.setLength(0);

		// write to buffer
		System.err.print("Writer writes:");

		Random random = new Random();
		for (int j = 0; j < BUFFER_LENGTH; j++) {
			Thread.sleep(PAUSE);
			char ch = (char) ('A' + random.nextInt(ALPHABET_COUNT));
			System.err.print(ch);
			BUFFER.append(ch);
		}
		System.err.println();
		Thread.sleep(PAUSE);
	}

	/**
	 * Application start point.
	 * 
	 * @param args
	 *            is ignored
	 * @throws InterruptedException
	 *             in case of thread exception
	 */
	public static void main(final String[] args) throws InterruptedException {
		// create writer
		Writer writer = new Writer();

		// create readers
		List<Thread> readers = new ArrayList<>();
		for (int j = 0; j < READERS_NUMBER; j++) {
			readers.add(new Reader());
		}

		// start readers
		Thread.sleep(PAUSE_2);
		for (Thread reader : readers) {
			reader.start();
		}

		// start writer
		Thread.sleep(PAUSE_2);
		writer.start();

		// main thread is waiting for the child threads
		writer.join();
		for (Thread reader : readers) {
			reader.join();
		}
	}

	/**
	 * Private constructor.
	 */
	private Part51() {
		// TODO Auto-generated constructor stub
	}

}
