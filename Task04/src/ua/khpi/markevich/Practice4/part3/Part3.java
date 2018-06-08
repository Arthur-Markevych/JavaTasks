package ua.khpi.markevich.Practice4.part3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ua.khpi.markevich.Practice4.part1.Part1;

/**
 * Takes from console input data type to find it in file and then print it to
 * console.
 * 
 * @author Arthur MArkevich
 *
 */
public final class Part3 {
	/**
	 * Default encoding.
	 */
	private static final String ENCODING = "cp1251";

	/**
	 * Path to destination file.
	 */
	private static final String FILE_NAME = "part3.txt";

	/**
	 * Reading console input count.
	 */
	private static final int COUNT = 4;

	/**
	 * <tt>Pattern</tt> which class uses.
	 */
	private static Pattern pat;

	/**
	 * <tt>Matcher</tt> which class uses.
	 */
	private static Matcher mat;

	/**
	 * Application start point.
	 * 
	 * @param args
	 *            data type to find it in the file
	 * @throws IOException
	 *             in case of problem reading file
	 */
	public static void main(final String[] args) throws IOException {
		final String file = Part1.getTextFromFile(FILE_NAME, ENCODING);
		Scanner scan = new Scanner(System.in, ENCODING);

		String type = "";
		for (int i = 0; i < COUNT; i++) {
			type = scan.nextLine();
			System.out.println(printDataFromFile(type, file));
		}
		scan.close();
	}

	/**
	 * The method find particular data in the text.
	 * 
	 * @param type
	 *            data type to find (case insensitive)
	 * @param text
	 *            string for parsing
	 * @return string contains all founded data
	 */
	public static String printDataFromFile(final String type, final String text) {
		String nType = type.trim().toLowerCase();
		if (nType.equals("string")) {
			return findParticularData(text, "[^0-9.\\s]{2,}");
		} else if (nType.equals("char")) {
			return findParticularData(text, "\\b[^0-9.\\s]{1,1}\\b");
		} else if (nType.equals("int")) {
			return findParticularData(text, "(^\\d*)|\\s\\d*\\d+\\s|(\\s\\d*$)");
		} else if (nType.equals("double")) {
			return findParticularData(text, "(^\\.\\d+)|([+-]?\\d+|\\s)\\.\\d+");
		} else {
			if (nType.trim().equals("")) {
				nType = "null";
			}
			return "type: " + "[" + nType + "]" + " is not allowed";
		}
	}

	/**
	 * Parse string.
	 * 
	 * @param text
	 *            string to be parsed
	 * @param regex
	 *            regular expression which will be used for parsing <tt>text</tt>
	 * @return resulting string
	 */
	private static String findParticularData(final String text, final String regex) {
		pat = Pattern.compile(regex);
		mat = pat.matcher(text);
		StringBuilder sb = new StringBuilder("");
		while (mat.find()) {
			sb.append(mat.group().trim()).append(" ");
		}
		return sb.toString().trim();
	}

	/**
	 * Reads data from file.
	 * 
	 * @param path
	 *            path to destination file
	 * @return text from file
	 * @throws FileNotFoundException
	 *             if file doesn't exists
	 * @throws IOException
	 *             in case of problem reading file
	 */
	public static String readDataFromFile(final String path) throws FileNotFoundException, IOException {
		StringBuilder sb = new StringBuilder("");
		try (FileReader reader = new FileReader(path)) {
			int c;
			while ((c = reader.read()) != -1) {
				sb.append((char) c);
			}
		} catch (IOException e) {
			throw new IOException(e);
		}
		return sb.toString();
	}

	/**
	 * Private constructor.
	 */
	private Part3() {
		/* private constructor */
	}

}
