package ua.khpi.markevich.Practice4.part2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;

/**
 * The class manipulating with numbers and files.
 * 
 * @author Arthur Markevich
 *
 */
public final class Part2 {
	/**
	 * File to write random numbers.
	 */
	private static final String RAW_DATA = "part2.txt";

	/**
	 * File to write converted data from <tt>RAW_DATA</tt>.
	 */
	private static final String SORTED_DATA = "part2_sorted.txt";

	/**
	 * Default encoding.
	 */
	private static final String ENCODING = "UTF-8";

	/**
	 * Count of numbers to write to file.
	 */
	private static final int N = 10;

	/**
	 * Max number which will be randomly generated.
	 */
	private static final int MAX = 50;

	/**
	 * Start point of application.
	 * 
	 * @param args
	 *            ignored by program
	 * @throws IOException
	 *             in case of wrong reading file
	 */
	public static void main(final String[] args) throws IOException {
		String randomNumbers = getRundomNumbers(MAX);
		writeDataToFile(RAW_DATA, randomNumbers, ENCODING);

		int[] data = readDataFromFile(RAW_DATA);
		System.out.print("input  ==> ");
		print(data);
		System.out.println();
		int[] sortedData = sort(data);

		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < sortedData.length; i++) {
			sb.append(sortedData[i] + " ");
		}
		/**
		 * Preparing data to console output.
		 */
		writeDataToFile(SORTED_DATA, sb.toString(), ENCODING);
		System.out.print("output ==> ");
		print(sortedData);

		System.out.println();
	}

	/**
	 * The method is writing data to the file.
	 * 
	 * @param path
	 *            destination file
	 * @param data
	 *            to be written to destination file
	 * @param encoding
	 *            write file encoding
	 * @throws IOException
	 *             in case of problem with writing file
	 */
	public static void writeDataToFile(final String path, final String data, final String encoding) throws IOException {
		try (OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(path), encoding)) {
			out.write(data);
		} catch (IOException e) {
			throw new IOException(e);
		}
	}

	/**
	 * The method is reading numbers from file.
	 * 
	 * @param path
	 *            destination file
	 * @return array with all numbers from file
	 * @throws FileNotFoundException
	 *             in case if file doesn't exists
	 * @throws IOException
	 *             in case of problem to read the file
	 */
	public static int[] readDataFromFile(final String path) throws FileNotFoundException, IOException {
		StringBuilder sb = new StringBuilder("");
		try (FileReader reader = new FileReader(path)) {
			int c;
			while ((c = reader.read()) != -1) {
				sb.append((char) c);
			}
		}
		String[] numbers = sb.toString().split("\\s");
		int[] data = new int[numbers.length];
		for (int i = 0; i < data.length; i++) {
			data[i] = Integer.valueOf(numbers[i]);
		}
		return data;
	}

	/**
	 * Generates string with random <tt>N</tt> numbers.
	 * 
	 * @param max
	 *            max value of generated number
	 * @return string which contains all of generated numbers
	 */
	public static String getRundomNumbers(final int max) {
		StringBuilder out = new StringBuilder();
		Random random = new Random();
		for (int i = 1; i < N; i++) {
			out.append(random.nextInt(max) + " ");
		}
		return out.toString();
	}

	/**
	 * Sorts numbers by natural order.
	 * 
	 * @param data
	 *            array with numbers to be sorted
	 * @return sorted data
	 */
	public static int[] sort(final int[] data) {
		for (int i = 0; i < data.length; i++) {
			for (int j = 1; j < data.length - i; j++) {
				if (data[j - 1] > data[j]) {
					int temp = data[j];
					data[j] = data[j - 1];
					data[j - 1] = temp;
				}
			}
		}
		return data;
	}

	/**
	 * Print array of numbers to console in one line.
	 * 
	 * @param data
	 *            to be printed
	 */
	public static void print(final int[] data) {
		for (int i : data) {
			System.out.print(i + " ");
		}
	}

	/**
	 * Private constructor.
	 * 
	 * @throws IllegalAccessError
	 *             if constructor will be invoked
	 */
	private Part2() {
		throw new IllegalAccessError();
	}

}
