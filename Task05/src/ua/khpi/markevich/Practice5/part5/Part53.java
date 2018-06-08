package ua.khpi.markevich.Practice5.part5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Part53 {

	static final Lock LOCK = new ReentrantLock(true);

	private static final int ITERATION_NUMBER = 3;

	private static final int READERS_NUMBER = 3;

	private static final int BUFFER_LENGTH = 5;

	private static final int PAUSE = 5;

	private static final StringBuilder BUFFER = new StringBuilder();

	private static boolean isWrite = false;

	private static boolean stop;

	public static void main(String[] args) throws InterruptedException {
		// create writer
		Writer writer = new Writer();

		// create readers
		List<Thread> readers = new ArrayList<>();
		for (int j = 0; j < READERS_NUMBER; j++) {
			readers.add(new Reader());
		}

		// start readers
		Thread.sleep(PAUSE);
		for (Thread reader : readers) {
			reader.start();
		}

		// start writer
		Thread.sleep(PAUSE);
		writer.start();

		// main thread is waiting for the child threads
		writer.join();
		for (Thread reader : readers) {
			reader.join();
		}
	}

	// reader
	private static class Reader extends Thread {
		public void run() {
			while (!stop) {
				try {
					LOCK.lock();
					if (isWrite) {
						read(getName());
					}
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				} finally {
					LOCK.unlock();
				}

			}
		}
	}

	// writer
	private static class Writer extends Thread {
		public void run() {
			int tact = 0;
			while (!stop) {
				try {
					LOCK.lock();
					write();
					isWrite = true;
					sleep(PAUSE);
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				} finally {
					if (++tact == ITERATION_NUMBER) {
						stop = true;
					}
					LOCK.unlock();
				}
			}
		}
	}

	private static void read(String threadName) throws InterruptedException {
		System.out.printf("Reader %s:", threadName);
		for (int j = 0; j < BUFFER_LENGTH; j++) {
			Thread.sleep(PAUSE);
			System.out.print(BUFFER.charAt(j));
		}
		System.out.println();
		Thread.sleep(5);
	}

	private static void write() throws InterruptedException {
		// clear buffer
		BUFFER.setLength(0);

		// write to buffer
		System.err.print("Writer writes:");

		Random random = new Random();
		for (int j = 0; j < BUFFER_LENGTH; j++) {
			Thread.sleep(PAUSE);
			char ch = (char) ('A' + random.nextInt(26));
			System.err.print(ch);
			BUFFER.append(ch);
		}
		System.err.println();
		Thread.sleep(5);
	}

}
