package ua.khpi.markevich.Practice5.part5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Part52 {

	private static final int READERS_NUMBER = 3;

	/**
	 * Speed parameter.
	 */
	public static final int PAUSE = 5;

	/**
	 * Start point of application;
	 * 
	 * @see {@link Part51#main(String[])}
	 */
	public static void main(String[] args) throws InterruptedException {
		Buffer buffer = new Buffer();

		// create readers
		List<Thread> readers = new ArrayList<>();
		for (int j = 0; j < READERS_NUMBER; j++) {
			readers.add(new Reader(buffer));
		}

		// start readers
		Thread.sleep(PAUSE);
		for (Thread readerT : readers) {
			readerT.start();
		}

		// start writer
		Writer writer = new Writer(buffer);
		Thread.sleep(PAUSE);
		writer.start();

		// main thread is waiting for the child threads
		writer.join();
		for (Thread readerT : readers) {
			readerT.join();
		}

	}

}

class Buffer {

	public static final int ITERATION_NUMBER = 3;

	public static final int BUFFER_LENGTH = 5;

	static boolean isWrite;
	static boolean isRead;
	static boolean stop;

	static final Lock WRITER_LOCK = new ReentrantLock();
	static final Condition WRITER_CONDITION = WRITER_LOCK.newCondition();

	static final Lock READER_LOCK = new ReentrantLock();
	static final Condition READER_CONDITION = READER_LOCK.newCondition();

	final StringBuilder buffer;

	Buffer() {
		buffer = new StringBuilder();
	}

	void write() throws InterruptedException {
		int tact = 0;
		while (!stop) {
			try {
				READER_LOCK.lock();
				write_();
				isWrite = true;
				READER_CONDITION.signalAll();
				READER_LOCK.unlock();

				WRITER_LOCK.lock();
				while (!isRead) {
					WRITER_CONDITION.await();
				}
				WRITER_LOCK.unlock();
				Thread.sleep(Part52.PAUSE);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			} finally {
				READER_LOCK.lock();
				if (++tact == ITERATION_NUMBER) {
					stop = true;
					READER_CONDITION.signalAll();
				}
				READER_LOCK.unlock();
			}
		}
	}

	void read() throws InterruptedException {
		while (!stop) {
			try {
				READER_LOCK.lock();

				if (isWrite) {
					read(Thread.currentThread().getName());
				}
				READER_CONDITION.await();

				WRITER_LOCK.lock();
				WRITER_CONDITION.signal();
				isRead = true;
				WRITER_LOCK.unlock();
				READER_LOCK.unlock();
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	void read(String threadName) throws InterruptedException {
		System.out.printf("Reader %s:", threadName);
		for (int j = 0; j < BUFFER_LENGTH; j++) {
			Thread.sleep(Part52.PAUSE);
			System.out.print(buffer.charAt(j));
		}
		System.out.println();
		Thread.sleep(5);
	}

	void write_() throws InterruptedException {
		// clear buffer
		buffer.setLength(0);

		// write to buffer
		System.err.print("Writer writes:");

		Random random = new Random();
		for (int j = 0; j < BUFFER_LENGTH; j++) {
			Thread.sleep(Part52.PAUSE);
			char ch = (char) ('A' + random.nextInt(26));
			System.err.print(ch);
			buffer.append(ch);
		}
		System.err.println();
		Thread.sleep(5);
	}

}

class Writer extends Thread {

	Buffer buffer;

	Writer(Buffer buf) {
		this.buffer = buf;
	}

	@Override
	public void run() {
		for (int i = 0; i < Buffer.ITERATION_NUMBER; i++) {
			try {
				buffer.write();
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
	}

}

class Reader extends Thread {

	Buffer buffer;

	Reader(Buffer buf) {
		this.buffer = buf;
	}

	@Override
	public void run() {
		try {
			buffer.read();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}

}
