package ua.khpi.markevich.Practice5.part6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

/**
 * Application write data to file using 10 threads at the time.
 * 
 * @author Arthur Markevich
 *
 */
public class Part6 extends Thread {

	/**
	 * Object monitor.
	 */
	private static final Part6 MONITOR = new Part6();

	/**
	 * Count of ran thread.
	 */
	private static final int THREADS_NUMBER = 10;

	/**
	 * Count of symbols for each thread.
	 */
	private static final int COLUMNS = 20;

	/**
	 * Thread sleep time.
	 */
	private static final int SLEEP = 1000;

	/**
	 * Path to file.
	 */
	private static String fileName = "test.txt";

	/**
	 * Line separator.
	 */
	private static final byte[] LINE_SEPARATOR = System.lineSeparator().getBytes();

	/**
	 * Shared <tt>RandomAccessFile</tt> to manipulate with file.
	 */
	private static RandomAccessFile out;

	/**
	 * Current number which thread will repeat and write.
	 */
	private int number;

	/**
	 * current line where thread will write data.
	 */
	private int line;

	/**
	 * Start thread to write the file.
	 */
	@Override
	public void run() {
		try {
			write();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Preparing data for new thread.
	 */
	private void inc() {
		number++;
		line += COLUMNS;
	}

	/**
	 * Start point of application.
	 * 
	 * @param args
	 *            is ignored
	 * @throws InterruptedException
	 *             in case of thread exception
	 * @throws IOException
	 *             in case of writing file problem
	 */
	public static void main(final String[] args) throws IOException, InterruptedException {
		File file = new File(fileName);

		file.delete();
		for (int i = 0; i < THREADS_NUMBER; i++) {
			Thread t = new Thread(MONITOR);
			t.start();
		}
		Thread.sleep(SLEEP);
		System.out.println(getStringFromFile(file.toString()));
	}

	/**
	 * Write digits to the file.
	 * 
	 * @throws IOException
	 *             in case of writing file problem
	 */
	public synchronized void write() throws IOException {
		if (out == null) {
			out = new RandomAccessFile(fileName, "rw");
		}
		out.seek(out.length());
		byte[] bytes = String.valueOf(this.number).getBytes();
		if (this.line > 0) {
			out.write(LINE_SEPARATOR);
		}
		for (int i = 0; i < COLUMNS; i++) {
			out.write(bytes);
		}
		inc();
	}

	/**
	 * Return string presentation of file.
	 * 
	 * @param fileName
	 *            path to read file
	 * @return file content
	 */
	public static String getStringFromFile(final String fileName) {
		File file = new File(fileName);
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "cp1251"))) {
			String s;
			while ((s = br.readLine()) != null) {
				sb.append(s);
				sb.append(System.lineSeparator());
			}
		} catch (UnsupportedEncodingException e) {
			e.getMessage();
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.getMessage();
			e.printStackTrace();
		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		}
		return sb.toString();
	}

}
