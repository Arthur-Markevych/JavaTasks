package ua.khpi.markevich.Practice4.part1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Print to console consistent of text file with changed every word which has
 * more than three characters capitalized.
 * 
 * @author Arthur Markevich
 */
public final class Part1 {
	/**
	 * Path to file for parsing.
	 */
	private static final String FILE_NAME = "part1.txt";

	/**
	 * Encoding to read the file.
	 */
	private static final String ENCODING = "Cp1251";

	/**
	 * String for manipulation with text file.
	 */
	private final String input;

	/**
	 * Start application.
	 * 
	 * @param args
	 *            commandline arguments, the program doesn't parse them
	 * 
	 * @throws IOException
	 *             if reading file will get some problem
	 */
	public static void main(final String[] args) throws IOException {
		String input = getTextFromFile(FILE_NAME, ENCODING);
		Part1 part1 = new Part1(input);
		System.out.println(part1.toUpperCase());
	}

	/**
	 * Public constructor.
	 * 
	 * @param text
	 *            set <tt>text</tt> to <tt>input</tt>
	 */
	public Part1(final String text) {
		this.input = text;
	}

	/**
	 * Takes text from <tt>input</tt> and transform it. Makes capitalized every word
	 * longer than 3 characters.
	 * 
	 * @return result string
	 */
	public String toUpperCase() {
		String res = new String(input);
		Pattern pat = Pattern.compile("[а-яА-Яa-zA-Z]{4,}\\b");
		Matcher mat = pat.matcher(input);
		while (mat.find()) {
			res = res.replace(mat.group(), mat.group().toUpperCase());
		}
		return res;
	}

	/**
	 * Read text file and return text as <tt>String</tt>.
	 * 
	 * @param path
	 *            path to text file
	 * @param encoding
	 *            encoding of file
	 * @return text from file as <tt>String</tt>
	 * @throws IOException
	 *             if reading file will get some problem
	 */
	public static String getTextFromFile(final String path, final String encoding) throws IOException {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path), encoding))) {
			String str = "";
			while ((str = in.readLine()) != null) {
				sb.append(str);
			}
		} catch (IOException e) {
			throw new IOException(e);
		}
		return sb.toString();
	}

}
