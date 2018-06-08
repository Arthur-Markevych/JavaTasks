package ua.khpi.markevich.Practice3.part1;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class contains methods for converting strings.
 * 
 * @author Arthur Markevich
 *
 */
public final class Part1 {

	/**
	 * The string which will parse.
	 */
	private final String input;

	/**
	 * A constant value 3 of {@code int}.
	 */
	private static final int THREE = 3;

	/**
	 * A constant value 4 of {@code int}.
	 */
	private static final int FOUR = 4;

	/**
	 * A constant value 9 of {@code int}.
	 */
	private static final int NINE = 9;

	private static Pattern pattern;

	private static Matcher mattcher;

	/**
	 * Constructor takes {@code String} for parsing.
	 * 
	 * @param data
	 */
	public Part1(String data) {
		this.input = data;
	}

	/**
	 * The method parse String in form ivanov ==> ivanov@mail.ru.
	 * 
	 * @return String in form ivanov ==> ivanov@mail.ru for each line.
	 */
	public String convert1() {
		StringBuilder sb = new StringBuilder();
		String[] lines = getLinesInput();
		String[][] strings = new String[lines.length][THREE];
		for (int i = 0; i < lines.length; i++) {
			strings[i] = lines[i].split(";");
		}
		for (int i = 0; i < strings.length; i++) {
			sb.append(strings[i][0] + " ==> " + strings[i][2] + "\n");
		}
		return sb.toString();
	}

	/**
	 * The method parse String in form Ivanov Ivan (email: ivanov@mail.ru).
	 * 
	 * @return String in form Ivanov Ivan (email: ivanov@mail.ru) for each line.
	 */
	public String convert2() {
		StringBuilder sb = new StringBuilder();
		String[] lines = getLinesInput();
		String[][] strings = new String[lines.length][FOUR];
		for (int i = 0; i < lines.length; i++) {
			strings[i] = lines[i].split("[;\\s]");
		}
		for (int i = 0; i < strings.length; i++) {
			sb.append(strings[i][2] + " " + strings[i][1] + "(email: " + strings[i][THREE] + ")" + "\n");
		}
		return sb.toString();
	}

	/**
	 * Parse String and finding users of different email providers.
	 * 
	 * @return String of which contain all email providers and relevant users.
	 */
	public String convert3() {
		String[] lines = getLinesInput();
		Set<String> set = new HashSet<>();
		pattern = Pattern.compile("(?<=@).*");
		mattcher = pattern.matcher(input);
		while (mattcher.find()) {
			set.add(mattcher.group());
		}
		StringBuilder sb = new StringBuilder();
		for (String s : set) {
			sb.append(s).append(" ==> ");
			pattern = Pattern.compile(s);

			int k = 0;
			int j = 0;
			mattcher = pattern.matcher(input);
			while (mattcher.find()) {
				k++;
			}
			for (int i = 0; i < lines.length; i++) {
				mattcher = pattern.matcher(lines[i]);
				while (mattcher.find()) {
					j++;
					if (j < k) {
						sb.append(lines[i].split(";")[0] + ", ");
					} else {
						sb.append(lines[i].split(";")[0] + "\n");
						j = 0;
					}
				}
			}
		}
		return sb.toString();
	}

	/**
	 * Pars String with adding random password out of 4 digits for each user (line).
	 * 
	 * @return String with formated data added random password out of 4 digits for
	 *         each user (line).
	 */
	public String convert4() {
		String[] lines = input.split("\n");
		StringBuilder sb = new StringBuilder();
		sb.append(lines[0] + "\n");
		for (int i = 1; i < lines.length; i++) {
			sb.append(lines[i] + ";" + randomGenerator() + "\n");
		}
		return sb.toString();
	}

	/**
	 * Generate String of the four random digit number.
	 * 
	 * @return String of generated the four random digit number.
	 */
	private String randomGenerator() {
		String sResult = "";
		for (int i = 0; i <= THREE; i++) {
			Integer intRandom = new Random().nextInt(NINE);
			sResult = sResult.concat(intRandom.toString());
		}
		return sResult;
	}

	/**
	 * Split INPUT by each user.
	 * 
	 * @return String[] which contains splitted data by users.
	 */
	private String[] getLinesInput() {
		String[] lines = input.split("\n");
		String[] output = new String[lines.length - 1];
		System.arraycopy(lines, 1, output, 0, lines.length - 1);
		return output;
	}

}
